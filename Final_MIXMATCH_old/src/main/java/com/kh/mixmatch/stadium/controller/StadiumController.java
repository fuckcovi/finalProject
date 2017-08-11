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

import com.kh.mixmatch.stadium.domain.BookingCommand;
import com.kh.mixmatch.stadium.domain.StadiumCommand;
import com.kh.mixmatch.stadium.service.StadiumService;
import com.kh.mixmatch.team.domain.TeamMemCommand;

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
	public String stadium(){
		return "stadium";
	}
	@RequestMapping(value="/stadiumRegi.do",method=RequestMethod.GET)
	public String stadiumRegiForm(){
		return "stadiumRegi";
	}
	@RequestMapping(value="/stadiumRegi.do",method=RequestMethod.POST)
	public String stadiumRegi(@ModelAttribute("command") @Valid StadiumCommand stadium,BindingResult result,HttpSession session){
		if(log.isDebugEnabled()){
			log.debug("<<<< StadiumCommand >>>>  : " + stadium);
		}
		if(result.hasErrors()){
			return stadiumRegiForm();
		}
		
		/*
		 Map<String, Object> map = new HashMap<String, Object>();
		String user_id = (String)session.getAttribute("user_id");
		map.put("id", user_id);
		 List<TeamMemCommand> list =teamMemService.list(map);
		for(int i =0 ;i<list.size();i++){
			if(list.get(i).getT_name().equals(teamMem.getT_name())){ // 이미 가입신청한 팀이므로 신청 안되고 리턴
				return "redirect:/team.do";
			}
		}*/
		stadiumService.insertStadium(stadium);
		return "redirect:/stadium.do";
	
	}
}
