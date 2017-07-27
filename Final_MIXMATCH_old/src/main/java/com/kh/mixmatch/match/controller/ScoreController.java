package com.kh.mixmatch.match.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.mixmatch.match.service.MatchService;

@Controller
public class ScoreController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MatchService matchService;
	
	@RequestMapping("/match/scoreBoard.do")
	public ModelAndView process() {		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("match/scoreBoard");
		
		return mav;
	}

}
