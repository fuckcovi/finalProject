package com.kh.mixmatch.member.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kh.mixmatch.member.domain.MemberCommand;
import com.kh.mixmatch.member.service.MemberService;

@Controller
public class MemberController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MemberService memberService;
	
	@ModelAttribute("memberCommand")
	public MemberCommand initCommand(){
		return new MemberCommand();
	}
	
	@RequestMapping(value="/member/insert.do",method=RequestMethod.GET)
	public String insertForm(){
		return "memberInsert";
	}
	
	@RequestMapping(value="/member/insert.do",method=RequestMethod.POST)
	public String insertAction(){
		return "redirect:/main.do";
	}
}
