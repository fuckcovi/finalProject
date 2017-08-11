package com.kh.mixmatch.stadium.controller;

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
	public ModelAndView stadium(@RequestParam(value="pageNum",defaultValue="1") int currentPage){
		int rowCount = 10;
		int pageCount = 5;
		int stadiumCount = stadiumService.getTotalCountStadium();
		
		PagingUtil page = new PagingUtil(currentPage, stadiumCount, rowCount, pageCount, "stadium.do");
		Map<String, Object> map = new HashMap<String, Object>();
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
			// 관리자만 등록가능
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
	
	
}