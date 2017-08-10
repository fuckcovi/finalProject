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
			  
			if(check && member.getStatus().equals("Y")){
				session.setAttribute("user_id", member.getId());
				session.setAttribute("auth", member.getAuth());
				session.setAttribute("status", member.getStatus());
				session.setAttribute("user_point", member.getPoint());
				session.setAttribute("member", member);
				return "redirect:/home.do";
			}else if(check && member.getStatus().equals("N")){
				result.reject("unableMember");
				return loginForm();
			}else{
				result.reject("invalidIdOrPassword");
				return loginForm();
			}
		} catch (Exception e) {
			result.reject("loginError");
			return loginForm();
		} 
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/home.do";
	}
}