package com.kh.mixmatch.member;

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
import com.kh.mixmatch.team.domain.TeamCommand;
import com.kh.mixmatch.team.domain.TeamMemCommand;
import com.kh.mixmatch.team.service.TeamMemService;
import com.kh.mixmatch.team.service.TeamService;


@Controller
public class MemberLoginController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private MemberService memberService;
	@Resource
	private TeamMemService teamMemService;
	
	
	@ModelAttribute("memberCommand")
	public MemberCommand initCommand(){
		return new MemberCommand();
	}
	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public String form(){
		return "login";
	}
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid MemberCommand memberCommand, BindingResult result, HttpSession session){
		if(log.isDebugEnabled()){
			log.debug("<<<<memberCommand>>>>> : " + memberCommand);
		}
		// id�� passwd �ʵ常 ��ȿ�� üũ
		if(result.hasFieldErrors("id")||result.hasFieldErrors("pw")){
			return form();
		}
		// �α��� üũ(id �Ǵ� ��й�ȣ ��ġ����üũ)
		try{
			MemberCommand member = memberService.selectMember(memberCommand.getId());
			boolean check = false;
			if(member != null){
				check = member.isCheckedPasswd(memberCommand.getPw());
			}
			if(check){//��������
				session.setAttribute("user_id",memberCommand.getId());
				
				TeamMemCommand teamMemCommand = teamMemService.selectTeamMem(memberCommand.getId());
				session.setAttribute("user_team", teamMemCommand.getT_name());
				return "redirect:/main.do";
			}else{
				throw new Exception();
			}
		}catch(Exception e){
			result.reject("invalidIdOrPassword");
			return form();
		}
	}
	
	@RequestMapping("logout.do")
	public String process(HttpSession session){
		session.invalidate();
		return "redirect:/main.do";
	}
}
