package com.kh.mixmatch.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.mixmatch.notice.domain.NoticeCommand;
import com.kh.mixmatch.notice.service.NoticeService;
import com.kh.mixmatch.util.PagingUtil;



@Controller
public class NoticeController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private NoticeService noticeService;
	@ModelAttribute
	public NoticeCommand initCommand(){
		return new NoticeCommand();
	}
	
	
	
	
	private int rowCount = 10;
	private int pageCount = 5;
	
	
	@RequestMapping("/notice.do")
	public ModelAndView noticeList(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="") String keyword){
		if(log.isDebugEnabled()){
			log.debug("<<pageNum>> : " +currentPage);
			log.debug("<<keyfield>> : " +keyfield);
			log.debug("<<keyword>> : " +keyword);
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("keyfield",keyfield);
		map.put("keyword", keyword);
		// 총 글의 갯수 또는 검색된 글의 갯수
		int count = noticeService.getRowCount(map);
		if(log.isDebugEnabled()){
			log.debug("<<<count>> : "+count );
		}
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, "notice.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		List<NoticeCommand> list = null;
		
		if(count>0){
			list = noticeService.noticeList(map);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("notice");
		mav.addObject("pagingHtml",page.getPagingHtml());
		mav.addObject("count",count);
		mav.addObject("list",list);
		return mav; 
	}
	@RequestMapping(value="/noticeInsert.do",method=RequestMethod.GET)
	public String noticeInsertForm(HttpSession session, Model model){
		String id = (String)session.getAttribute("user_id");
		NoticeCommand noticeCommand = new NoticeCommand();
		noticeCommand.setId(id);
		model.addAttribute("noticeCommand", noticeCommand);
		return "noticeInsert";
	}
	@RequestMapping(value="/noticeInsert.do",method=RequestMethod.POST)
	public String noticeInsertSubmit(@ModelAttribute("command") @Valid NoticeCommand noticeCommand, BindingResult result, HttpServletRequest request){
		if(log.isDebugEnabled()){
			log.debug("<<<<<<< noticeCommand : " +noticeCommand);
		}
		if(result.hasErrors()){
			return "noticeInsert";
		}
		noticeCommand.setIp(request.getRemoteAddr());
		// 글쓰기
		noticeService.noticeInsert(noticeCommand);
		return "redirect:/notice.do";
	}
	@RequestMapping("/noticeDetail.do")
	public ModelAndView noticeDetail(@RequestParam int gn_seq){
		if(log.isDebugEnabled()){
			log.debug("<<<< gn_seq>>>>> : " +gn_seq);
		}
		// 해당 글의 조회수 증가
		noticeService.noticeUpdateHit(gn_seq);
		NoticeCommand notice = noticeService.noticeSelect(gn_seq);
		
		return new ModelAndView("noticeDetail","notice",notice);
	}

	// 파일 다운로드
	@RequestMapping("/noticefile.do")
	public ModelAndView download(@RequestParam int gn_seq){
		NoticeCommand notice = noticeService.noticeSelect(gn_seq);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("downloadView");
		mav.addObject("downloadFile",notice.getGn_uploadfile());
		mav.addObject("filename", notice.getGn_filename());
		return mav;
	}
	
	// 이미지 출력
	@RequestMapping("/noticeimageView.do")
	public ModelAndView viewImage(@RequestParam int n_seq){
		NoticeCommand notice = noticeService.noticeSelect(n_seq);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile",notice.getGn_uploadfile());
		mav.addObject("filename",  notice.getGn_filename());
		return mav;
	}
}