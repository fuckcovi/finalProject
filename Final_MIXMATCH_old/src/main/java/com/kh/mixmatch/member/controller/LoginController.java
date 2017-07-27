package com.kh.mixmatch.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kh.mixmatch.member.domain.MemberCommand;
import com.kh.mixmatch.member.service.MemberService;

@Controller
public class LoginController {

private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MemberService memberService;
	
	@ModelAttribute("memberCommand")
	public MemberCommand initCommand(){
		return new MemberCommand();
	}
	
	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public String loginForm(){
		return "login";
	}
	
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public String loginAction(@ModelAttribute("memberCommand")@Valid MemberCommand memberCommand, BindingResult result, HttpSession session){
		if(log.isDebugEnabled()){
			log.debug("<<memberCommand>> : " + memberCommand);
		}
		
		if(result.hasFieldErrors("id") || result.hasFieldErrors("pw")){
			return loginForm();
		}
		
		try {
			MemberCommand member = memberService.selectMember(memberCommand.getId());
			
			boolean check = false;
			
			if(member != null){
				check = member.isCheckedPasswd(memberCommand.getPw());
			}
			  
			if(check){
				session.setAttribute("user_id", memberCommand.getId());
				session.setAttribute("auth", memberCommand.getAuth());
				
				return "redirect:/main.do";
			}else{
				throw new Exception();
			}
		} catch (Exception e) {
			result.reject("invalidIdOrPassword");
			return loginForm();
		}
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/main.do";
	}
}