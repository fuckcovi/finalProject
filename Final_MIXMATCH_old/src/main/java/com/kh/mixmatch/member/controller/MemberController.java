package com.kh.mixmatch.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
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
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String writeForm(){
		return "memberInsert";
	}
	
	@RequestMapping(value="/member/insert.do",method=RequestMethod.POST)
	public String writeAction(@ModelAttribute("memberCommand")@Valid MemberCommand memberCommand, BindingResult result){
		if(log.isDebugEnabled()){
			log.debug("<<memberCommand>> : " + memberCommand);
		}
		
		if(result.hasFieldErrors("id") || result.hasFieldErrors("name") || 
				result.hasFieldErrors("pw") || result.hasFieldErrors("birth") || 
				result.hasFieldErrors("phone") || result.hasFieldErrors("email") ||
				result.hasFieldErrors("address")){
			return writeForm();
		}
		
		memberService.insertMember(memberCommand);
		
		return "redirect:/main.do";
	}
	
	@RequestMapping(value="/member/confirmId.do")
	@ResponseBody
	public Map<String, String> process(@RequestParam("id")String id){
		if(log.isDebugEnabled()){
			log.debug("<<id>>> : " + id);
		}
		
		Map<String, String> map = new HashMap<String, String>();
		
		MemberCommand member = memberService.selectMember(id);
		
		if(member != null){
			//아이디 중복
			map.put("result", "idDuplicated");
		}else{
			//아이디 미중복
			map.put("result", "idNotFound");
		}
		
		return map;
	}
	
	@RequestMapping(value="/member/pwUpdate.do",method=RequestMethod.GET)
	public String pwUpdateForm(){
		return "pwUpdate";
	}
	
	@RequestMapping(value="/member/pwUpdate.do",method=RequestMethod.POST)
	public String pwUpdateAction(@ModelAttribute("memberCommand")@Valid MemberCommand memberCommand, BindingResult result, HttpSession session){
		String id = (String)session.getAttribute("user_id");
		
		MemberCommand member = memberService.selectMember(id);
		
		if(result.hasFieldErrors("pw") || result.hasFieldErrors("changePw") || result.hasFieldErrors("changePwCheck")){
			return "pwUpdate";
		}
		
		if(!member.getPw().equals(memberCommand.getPw())){
			result.rejectValue("pw", "invalidPassword");
		}else if(memberCommand.getPw().equals(memberCommand.getChangePw())){
			result.rejectValue("changePw", "sameBeforeAfterPw");
		}else if(!memberCommand.getChangePw().equals(memberCommand.getChangePwCheck())){
			result.rejectValue("changePw", "notSameChangePw");
		}else{
			memberCommand.setId(id);
			memberService.updatePw(memberCommand);
			return "redirect:/main.do";
			//return "redirect:/member/detail.do";
		}
		return "pwUpdate";
	}
	
	@RequestMapping(value="/member/pwCheck.do",method=RequestMethod.GET)
	public String pwCheckForm(){
		return "pwCheck";
	}
	
	@RequestMapping(value="/member/pwCheck.do",method=RequestMethod.POST)
	public String pwCheckAction(@ModelAttribute("memberCommand")@Valid MemberCommand memberCommand, BindingResult result, HttpSession session){
		String id = (String)session.getAttribute("user_id");
		
		MemberCommand member = memberService.selectMember(id);
		
		if(result.hasFieldErrors("pw")){
			return "pwCheck";
		}
		
		if(!member.getPw().equals(memberCommand.getPw())){
			result.rejectValue("pw", "invalidPassword");
			return "pwCheck";
		}
		
		return "redirect:/member/update.do";
	}
	
	@RequestMapping(value="/member/update.do",method=RequestMethod.GET)
	public String updateForm(HttpSession session, Model model){
		String id = (String)session.getAttribute("user_id");
		
		MemberCommand member = memberService.selectMember(id);
		
		model.addAttribute("memberCommand", member);
		
		return "memberUpdate";
	}
	
	@RequestMapping(value="/member/update.do",method=RequestMethod.POST)
	public String updateAction(@ModelAttribute("memberCommand")@Valid MemberCommand memberCommand, BindingResult result){
		if(log.isDebugEnabled()){
			log.debug("<<memberCommand>> : " + memberCommand);
		}
		
		if(result.hasFieldErrors("name") || result.hasFieldErrors("phone") || result.hasFieldErrors("email") || result.hasFieldErrors("address")){
			return "memberModify";
		}
		
		memberService.updateMember(memberCommand);
		
		return "redirect:/main.do";
		//return "redirect:/member/detail.do";
	}
	
	@RequestMapping(value="/member/delete.do",method=RequestMethod.GET)
	public String deleteForm(HttpSession session, Model model){
		
		MemberCommand member = new MemberCommand();
		
		member.setId((String)session.getAttribute("user_id"));
		
		model.addAttribute("memberCommand",member);
		
		return "memberDelete";
	}
	
	@RequestMapping(value="/member/delete.do",method=RequestMethod.POST)
	public String deleteAction(@ModelAttribute("memberCommand")@Valid MemberCommand memberCommand, BindingResult result, HttpSession session){
		if(log.isDebugEnabled()){
			log.debug("<<memberCommand>> : " + memberCommand);
		}
		
		if(result.hasFieldErrors("pw")){
			return "memberDelete";
		}
		
		try {
			MemberCommand member = memberService.selectMember(memberCommand.getId());
			
			boolean check = false;
			
			if(member != null){
				check = member.isCheckedPasswd(memberCommand.getPw());
			}
			
			if(check){
				memberService.deleteMember(memberCommand.getId());
				session.invalidate();
				
				return "redirect:/main.do";
			}else{
				throw new Exception();
			}
		} catch (Exception e) {
			result.rejectValue("pw", "invalidPassword");
			
			return "memberDelete";
		}
	}
}
