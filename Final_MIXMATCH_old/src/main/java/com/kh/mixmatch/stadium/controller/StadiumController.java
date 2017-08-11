package com.kh.mixmatch.stadium.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.mixmatch.stadium.domain.BookingCommand;
import com.kh.mixmatch.stadium.domain.StadiumCommand;
import com.kh.mixmatch.stadium.service.StadiumService;
import com.kh.mixmatch.team.domain.TeamCommand;
import com.kh.mixmatch.team.domain.TeamMemCommand;
import com.kh.mixmatch.util.PagingUtil;


@Controller
public class StadiumController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private StadiumService stadiumService;
	
	@ModelAttribute("stadiumCommand")
	public StadiumCommand initsCommand(){
		return new StadiumCommand();
	}
	@ModelAttribute("bookingCommand")
	public BookingCommand initbCommand(){
		return new BookingCommand();
	}
	
	@RequestMapping("/stadium.do")
	public ModelAndView stadium(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="") String keyword){
		if(log.isDebugEnabled()){
			log.debug("<<pageNum>> : " +currentPage);
			log.debug("<<keyfield>> : " +keyfield);
			log.debug("<<keyword>> : " +keyword);
		}
		
		int rowCount = 10;
		int pageCount = 5;
		
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield",keyfield);
		map.put("keyword", keyword);
		int stadiumCount = stadiumService.getTotalCountStadium(map);
		
		PagingUtil page = new PagingUtil(currentPage, stadiumCount, rowCount, pageCount, "stadium.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<StadiumCommand> stadiumList = null;
		if(stadiumCount>0){
			stadiumList = stadiumService.listStadium(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("stadium");
		mav.addObject("stadiumList", stadiumList);
		mav.addObject("stadiumCount", stadiumCount);
		mav.addObject("pagingHtml", page.getPagingHtml());
		return mav;
	}
	@RequestMapping(value="/stadiumRegi.do",method=RequestMethod.GET)
	public String stadiumRegiForm(){
		return "stadiumRegi";
	}
	@RequestMapping(value="/stadiumRegi.do",method=RequestMethod.POST)
	public String stadiumRegi(@ModelAttribute("command") @Valid StadiumCommand stadium,BindingResult result,HttpSession session) throws Exception{
		if(log.isDebugEnabled()){
			log.debug("<<<< StadiumCommand >>>>  : " + stadium);
		}
		if(result.hasErrors()){
			return stadiumRegiForm();
		}
		
		String user_id = (String)session.getAttribute("user_id");
		if(user_id != null){
			if(user_id.equals("admin")){
			// 愿�由ъ옄留� �벑濡앷��뒫
				stadiumService.insertStadium(stadium);
			}
		}else{
			return "redirect:/login.do";
		}
		return "redirect:/stadium.do";
	
	}
	@RequestMapping("/imageViewStadium.do")
	public ModelAndView imageViewStadium(@RequestParam int s_seq){
		StadiumCommand stadium = stadiumService.selectStadium(s_seq);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile",stadium.getS_logo());
		mav.addObject("filename", stadium.getS_logo_name());
		return mav;
	}
	
	
	@RequestMapping("/stadiumDetail.do")
	public ModelAndView stadiumDetail(@RequestParam int s_seq){
		StadiumCommand stadium = stadiumService.selectStadium(s_seq);
		
		Map<String, Object> map = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("stadiumDetail");
		mav.addObject("stadium",stadium);
		return mav;
	}
	
	
	@RequestMapping("/bookingList.do")
	@ResponseBody
	public Map<String, Object> bookingList(@RequestParam String b_regdate, @RequestParam int s_seq){
		if(log.isDebugEnabled()){
			log.debug("<<< b_regdate >>> : " + b_regdate);
			log.debug("<<< s_seq >>> : " + s_seq);
		}
		// 해당 경기장 예약 갯수
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("s_seq", s_seq);
		map.put("b_regdate", b_regdate);
		
		int rowCount = 10;
		int pageCount = 1;
		//해당경기장 해당날짜에 예약된 갯수 
		int bookCount = stadiumService.getTotalCountBooking(map);
		PagingUtil page = new PagingUtil(1, bookCount, rowCount, pageCount, null);
		map.put("start", page.getStartCount());
		map.put("end",page.getEndCount());
		
		
		System.out.println(bookCount + ";;;; "+s_seq +";;;" + b_regdate);
		
		List<BookingCommand> bookList = null;
		if(bookCount>0){
			bookList = stadiumService.listBooking(map);
		}else{
			bookList = Collections.emptyList();	// count가 0이여도 null이아닌 비어있는 리스트 호출
		}
		
		
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("bookList", bookList);
		mapJson.put("bookCount", bookCount);
		System.out.println(bookList);
		return mapJson;
	}
	
	
}
