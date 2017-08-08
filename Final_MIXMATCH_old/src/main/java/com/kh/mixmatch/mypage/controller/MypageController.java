package com.kh.mixmatch.mypage.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.mixmatch.member.domain.MemberCommand;
import com.kh.mixmatch.member.service.MemberService;
import com.kh.mixmatch.mypage.domain.MypageCommand;
import com.kh.mixmatch.mypage.domain.MypageCommand2;
import com.kh.mixmatch.mypage.domain.MypageReplyCommand;
import com.kh.mixmatch.mypage.service.MypageService;
import com.kh.mixmatch.team.domain.BaseCommand;
import com.kh.mixmatch.team.domain.BasketCommand;
import com.kh.mixmatch.team.domain.FootCommand;
import com.kh.mixmatch.util.MypagePagingUtil;
import com.kh.mixmatch.util.PagingUtil;


@Controller
public class MypageController {
	private Logger log = Logger.getLogger(this.getClass());
	
	private int rowCount = 6;		//한 화면에 6개의 게시글만 노출
	private int pageCount = 10;		//총 10개의 목록
	
	@Resource
	private MypageService mypageService;
	@Resource
	private MemberService memberService;
	
	
	//MyPage 메인(로그인 되어있는 유저ID넘겨줘)
	@RequestMapping(value="/mypage/main.do",method=RequestMethod.GET)
	public ModelAndView process(@RequestParam(value="pageNum", defaultValue="1")int currentPage, HttpSession session, @RequestParam(value="id", required=false)String id){	
		
		String user_id = (String) session.getAttribute("user_id");
		
		MemberCommand member = memberService.selectMember(id);			//유저정보
		FootCommand football = mypageService.selectFootball(id);		//축구기록
		BasketCommand basketball = mypageService.selectBasketball(id);	//농구기록
		BaseCommand baseball = mypageService.selectBaseball(id);		//야구기록

		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		
		//총 글의 갯수
		int count = mypageService.getRowCount(map);
				
		MypagePagingUtil page = new MypagePagingUtil(currentPage, count, rowCount, pageCount, "main.do?id="+id);
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
	
		List<MypageCommand> list = null;
		if (count > 0) {
			list = mypageService.list(map);
		}
			
		
		MypageCommand mypageCommand = new MypageCommand();
		mypageCommand.setId(id);
			
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mypageList");								//View
		mav.addObject("user_id",user_id);							//로그인되어있는 유저
		mav.addObject("member",member);								//유저정보
		mav.addObject("football",football);							//축구기록
		mav.addObject("basketball",basketball);						//농구기록
		mav.addObject("baseball",baseball);							//야구기록
		mav.addObject("count",count);								//총 글의 갯수
		mav.addObject("list",list);									//글 목록
		mav.addObject("pagingHtml",page.getPagingHtml());			//페이징
		mav.addObject("mypageCommand",mypageCommand);				//미니홈피 자바빈
		
		return mav;
	} 
	
	
	
	//미니홈피 글등록
	@RequestMapping(value="/mypage/main.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("mypageCommand")MypageCommand mypageCommand){
		
		//글쓰기
		mypageService.insert(mypageCommand);

		return "redirect:/mypage/main.do?id="+mypageCommand.getId();
	}
	
	
	//미니홈피 글수정
	@RequestMapping(value="/mypage/update.do")
	@ResponseBody
	public Map<String,String> process(MypageCommand2 mypageCommand2, HttpSession session, BindingResult result){
		
		Map<String,String> map = new HashMap<String, String>();
		
		String user_id = (String) session.getAttribute("user_id");
		
		System.out.println("<<h_seq>> : " + mypageCommand2.getH_seq());
		System.out.println("<<id>> : " + mypageCommand2.getId());
		System.out.println("<<h_show>> : " + mypageCommand2.getH_show());
		System.out.println("<<h_content>> : " + mypageCommand2.getH_content());
		
		//h_content만 체크
		if (result.hasFieldErrors("h_content")) {		//별도로 자바빈을 만든게 아니라 mypageCommand를 활용하여 한개의 필드만 체크
			//return form();
		}
		
		if (user_id == null) {
			//로그인이 안 되어있는 경우
			map.put("result", "logout");
		}else if(user_id != null && user_id.equals(mypageCommand2.getId())){
			//로그인 아이디와 작성자 아이디 일치
			//글 수정
			mypageService.update(mypageCommand2);
			map.put("result", "success");
			
			if (log.isDebugEnabled()) {
				log.debug("<<----------------map>> : " + map);
			}
			
		}else{
			//로그인 아이디와 작성자 아이디 불일치
			map.put("result", "wrongAccess");
		}
		
		return map;
	}
	
	//미니홈피 글삭제
	@RequestMapping(value="/mypage/delete.do")
	@ResponseBody
	public Map<String,String> process(@RequestParam("h_seq")int h_seq, @RequestParam("id")String id, HttpSession session){
		
		Map<String,String> map = new HashMap<String, String>();
		
		if (log.isDebugEnabled()) {
			log.debug("<<h_seq>> : " + h_seq);
			log.debug("<<id>> : " + id);
		}
		
		String user_id = (String) session.getAttribute("user_id");
		if (user_id == null) {
			//로그인이 되어 있지 않음
			map.put("result", "logout");
		}else if (user_id != null && user_id.equals(id)) {
			//로그인 되어 있고 로그인한 아이디와 작성자 아이디 일치
			mypageService.delete(h_seq);
			map.put("result", "success");
		}else{
			map.put("result", "wrongAccess");
		}
		return map;
	}
	
	//프로필 이미지 출력
	@RequestMapping("mypage/imageView.do")
	public ModelAndView viewImage(@RequestParam("id")String id){
		
		MemberCommand member = memberService.selectMember(id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", member.getProfile());
		mav.addObject("filename", member.getProfile_name());
		
		return mav;
	}
	
	
	//미니홈피 이미지 출력
	@RequestMapping("mypage/imageView2.do")
	public ModelAndView viewImage(@RequestParam("seq")int seq){
		
		MypageCommand mypage = mypageService.selectMypage(seq);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", mypage.getH_file());
		mav.addObject("filename", mypage.getH_file_name());
		
		return mav;
	}
	
	
	//미니홈피 댓글 리스트
	@RequestMapping("/mypage/postReplyList.do")
	@ResponseBody
	public Map<String,Object> process(@RequestParam(value="pageNum", defaultValue="1")int currentPage,@RequestParam(value="h_seq")String h_seq){	
		int rowCount = 5;
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("h_seq", h_seq);
		
		//총 댓글 갯수
		int count = mypageService.getRowCountReply(map);
		
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, null);
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<MypageReplyCommand> list = null;
		if (count > 0) {
			list = mypageService.listReply(map);
		}else{
			list = Collections.emptyList();
		}
	
		Map<String,Object> mapJson = new HashMap<String, Object>();
		mapJson.put("count", count);
		mapJson.put("rowCount", rowCount);
		mapJson.put("list", list);

		return mapJson;
	}
	
	
	//미니홈피 댓글 작성
	@RequestMapping("/mypage/postWriteReply.do")
	@ResponseBody
	public Map<String,String> process(MypageReplyCommand mypageReplyCommand, HttpSession session){
		
		Map<String,String> map = new HashMap<String, String>();
		
		String user_id = (String) session.getAttribute("user_id");
		if (user_id == null) {
			//로그인 되어있지 않은 상태
			map.put("result", "logout");
		}else{
			//로그인 되어있는 상태. 댓글 등록 가능
			mypageService.insertReply(mypageReplyCommand);
			map.put("result", "success");
		}
		
		return map;
	}
	
	
	//미니홈피 댓글 수정
	@RequestMapping("mypage/updateReply.do")
	@ResponseBody
	public Map<String,String> process1(MypageReplyCommand mypageReplyCommand, HttpSession session){
		
		if (log.isDebugEnabled()) {
			log.debug("<<mypageReplyCommand>> : " + mypageReplyCommand);
		}
		
		System.out.println(mypageReplyCommand);
		
		Map<String,String> map = new HashMap<String, String>();
		
		String user_id = (String)session.getAttribute("user_id");
		
		if (user_id == null) {
			//로그인이 안 되어있는 경우
			map.put("result", "logout");
		}else if(user_id != null && user_id.equals(mypageReplyCommand.getId())){
			//로그인 아이디와 작성자 아이디 일치
			//댓글 수정
			mypageService.updateReply(mypageReplyCommand);
			map.put("result", "success");
		}else{
			//로그인 아이디와 작성자 아이디 불일치
			map.put("result", "wrongAccess");
		}
		
		return map;
		
	}
	
	
	
	//미니홈피 댓글 삭제
	@RequestMapping("mypage/deleteReply.do")
	@ResponseBody
	public Map<String,String> process2(@RequestParam("h_re_seq")int h_re_seq, @RequestParam("id")String id, HttpSession session){
		if (log.isDebugEnabled()) {
			log.debug("<<h_re_seq>> : " + h_re_seq);
			log.debug("<<id>> : " + id);
		}
		
		Map<String,String> map = new HashMap<String, String>();
		
		String user_id = (String) session.getAttribute("user_id");
		if (user_id == null) {
			//로그인이 되어 있지 않음
			map.put("result", "logout");
		}else if (user_id != null && user_id.equals(id)) {
			//로그인이 되어 있고 로그인한 아이디와 작성자 아이디 일치
			mypageService.deleteReply(h_re_seq);
			map.put("result", "success");
		}else{
			map.put("result", "wrongAccess");
		}
		
		return map;

	}
	
	
}
