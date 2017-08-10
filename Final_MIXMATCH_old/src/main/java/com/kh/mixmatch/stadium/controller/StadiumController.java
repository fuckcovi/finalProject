package com.kh.mixmatch.stadium.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StadiumController {
	
	
	@RequestMapping("/stadium.do")
	public String stadium(){
		return "stadium";
	}
}
