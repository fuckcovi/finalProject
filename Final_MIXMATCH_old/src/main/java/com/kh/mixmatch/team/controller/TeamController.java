package com.kh.mixmatch.team.controller;

import java.util.Collections;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.mixmatch.match.domain.MatchCommand;
import com.kh.mixmatch.match.service.MatchService;
import com.kh.mixmatch.member.domain.MemberCommand;
import com.kh.mixmatch.member.service.MemberService;
import com.kh.mixmatch.team.domain.BaseCommand;
import com.kh.mixmatch.team.domain.BasketCommand;
import com.kh.mixmatch.team.domain.FootCommand;
import com.kh.mixmatch.team.domain.TeamCommand;
import com.kh.mixmatch.team.domain.TeamMemCommand;
import com.kh.mixmatch.team.service.TeamMemService;
import com.kh.mixmatch.team.service.TeamService;
import com.kh.mixmatch.team.service.TotalTypeService;

@Controller
public class TeamController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private TeamService teamService;
	@Resource
	private TeamMemService teamMemService;
	@Resource
	private TotalTypeService totalTypeService;
	@Resource
	private MemberService memberService; 
	
	
	@ModelAttribute("teamCommand")
	public TeamCommand initCommand(){
		return new TeamCommand();
	}
	
//=================== ��Ȩ ==============
	
	@RequestMapping("/team.do")
	public ModelAndView process(HttpSession session){
		// ����Ʈ�� ��ϵǾ� �ִ� �� ���
		Map<String, Object> map = new HashMap<String, Object>();
		List<TeamCommand> list = null;
		int count = teamService.getTeamCount(map);
		if(count>0){
			list = teamService.list(map);
		}
		
		// ���Խ�û�� �� ���
		Map<String, Object> map2 = new HashMap<String, Object>();
		String user_id = (String)session.getAttribute("user_id");
		map2.put("id", user_id);
		int joinCount = teamMemService.getRowMemCount(user_id);
		List<TeamMemCommand> joinList = null;
		if(joinCount>0){
			joinList = teamMemService.list(map2);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("teamHome");
		mav.addObject("count", count);	// ��ϵ� ���� �� ��
		mav.addObject("list",list);	// ��ϵ� ���� ����Ʈ

		mav.addObject("joinCount",joinCount);
		mav.addObject("joinList",joinList);			// ���Խ�û�� �� ���
		return mav;
	}
	
	@RequestMapping("/teamInfo.do")
	public ModelAndView teamInfo(@RequestParam String t_name){
		TeamCommand team = teamService.selectTeam(t_name);
		Map<String, Object> map = new HashMap<String, Object>();
		List<MatchCommand> match = teamService.listMatch(map);
		for(int i=0;i<match.size();i++){
			if(match.get(i).getM_home()==-1 || match.get(i).getM_away() ==-1){
				match.remove(i);	// ��Ī��� �Է¾ȵȰ��� ����Ʈ���� ��.
			}
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("teamInfo");
		mav.addObject("team",team);
		mav.addObject("match",match);
		return mav;
	}
	
	@RequestMapping("/imageViewMem.do")
	public ModelAndView viewImageMemProfile(@RequestParam String id){
		MemberCommand member = memberService.selectMember(id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile",member.getProfile());
		mav.addObject("filename", member.getProfile_name());
		return mav;
	}
	
	@RequestMapping("/imageView.do")
	public ModelAndView viewImageTeamLogo(@RequestParam String t_name){
		TeamCommand team = teamService.selectTeam(t_name);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile",team.getT_logo());
		mav.addObject("filename", team.getT_logo_name());
		return mav;
	}
//============= ���� ���Խ�û ======================
	@RequestMapping("/teamMemJoin.do")
	public String teamMemJoin(@ModelAttribute("command") @Valid TeamMemCommand teamMem,BindingResult result,HttpSession session){

		if(log.isDebugEnabled()){
			log.debug("<<<< teamMemCommand >>>>  : " + teamMem);
		}
		if(result.hasErrors()){
			return form();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		String user_id = (String)session.getAttribute("user_id");
		map.put("id", user_id);
		List<TeamMemCommand> list =teamMemService.list(map);
		for(int i =0 ;i<list.size();i++){
			if(list.get(i).getT_name().equals(teamMem.getT_name())){ // �̹� ���Խ�û�� ���̹Ƿ� ��û �ȵǰ� ����
				return "redirect:/team.do";
			}
		}
		teamMemService.insertTeamMem(teamMem);
		return "redirect:/team.do";
	}
	
//============== �� ��� =============================
	
	@RequestMapping(value="/teamRegister.do",method=RequestMethod.GET)
	public String form(){
		return "teamRegister";
	}
	@RequestMapping(value="/teamRegister.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid TeamCommand teamCommand,BindingResult result,HttpSession session){
		
		if(log.isDebugEnabled()){
			log.debug("<<<< teamCommand >>>>  : " + teamCommand);
		}
		if(result.hasErrors()){
			return form();
		}
		session.setAttribute("user_team", "1");
		teamService.insertTeam(teamCommand);
		
		TeamMemCommand teamMem =new TeamMemCommand();
		teamMem.setId(teamCommand.getId());
		teamMem.setT_name(teamCommand.getT_name());
		teamMem.setT_mem_auth(1);//���� ������ �����ʹ� auth���� 1�� ��.
		teamMemService.insertTeamMem(teamMem);
		return "redirect:/team.do";
	}
//============== ���� �ߺ�üũ =========================
	@RequestMapping("/confirmTname.do")
	@ResponseBody
	public Map<String, String> confirmTname(@RequestParam String tname){
		if(log.isDebugEnabled()){
			log.debug("<<<<<< tname >>>>>>> : " + tname);
		}
		Map<String, String> map = new HashMap<String, String>();
		TeamCommand team = teamService.selectTeam(tname);
		if(team!= null){
			map.put("result", "tnamdDuplicated");
		}else{
			map.put("result", "tnameNotFound");
		}
		return map;
	}
	
	
//============== �������� =========================
	@RequestMapping("/teamMem.do")
	public ModelAndView teamMemView(@RequestParam String t_name){
		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("t_name", t_name);
		List<TeamMemCommand> tMemList = teamMemService.listTeamMem(map);	// �α����� ���̵� �Ҽӵ� ������Ʈ
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("teamMemView");
		mav.addObject("tMemList",tMemList);
		return mav;
	}
	
//============== �� ��ŷ =============================
	@RequestMapping("/teamRank.do")
	public ModelAndView teamRank(@RequestParam String t_name){

		
		TeamCommand team = teamService.selectTeam(t_name);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("teamRank");
		mav.addObject("team",team);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("t_name", t_name);
		List<FootCommand> listTMemFoot = null;
		List<BaseCommand> listTMemBase = null;
		List<BasketCommand> listTMemBasket = null;
		int teamMemCount = teamMemService.getRowTeamMemCount(t_name);
		if(teamMemCount>0 && team.getT_type().equals("�౸")){
			listTMemFoot = teamMemService.listTMemFoot(map);
		}else if(teamMemCount>0 && team.getT_type().equals("�߱�")){
			listTMemBase = teamMemService.listTMemBase(map);
		}else if(teamMemCount>0 && team.getT_type().equals("��")){
			System.out.println("sd");
			listTMemBasket = teamMemService.listTMemBasket(map);

			System.out.println(listTMemBasket);
		}
		System.out.println(team.getT_type());
		System.out.println(teamMemCount);
		System.out.println(listTMemBasket);
		mav.addObject("teamMemCount", teamMemCount);
		mav.addObject("listTMemFoot",listTMemFoot);
		mav.addObject("listTMemBase",listTMemBase);
		mav.addObject("listTMemBasket",listTMemBasket);
		return mav;
	}

	
//=============================== �� �������/���=========================
	@RequestMapping("/teamSchedule.do")
	public ModelAndView teamSchedule(HttpSession session){
		String user_id = (String)session.getAttribute("user_id");
		// �α����� ������ ���� �Ҽ��� ����Ʈ
		List<TeamMemCommand> list = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", user_id);
		int count = teamMemService.getRowTeamCount(user_id);
		if(count>0){
			list = teamMemService.listConfirmTeam(map);
		}
		
		// ��� ��ġ����-��� ����Ʈ
		List<MatchCommand> matchList = teamService.listMatch(null);

		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("teamSchedule");
		mav.addObject("list",list);
		mav.addObject("count",count);
		mav.addObject("matchList",matchList);
		
		return mav;
	}
	
	@RequestMapping("/teamRecord.do")
	public ModelAndView teamRecord(HttpSession session){
		String user_id = (String)session.getAttribute("user_id");
		// �α����� ������ ���� �Ҽ��� ����Ʈ
		List<TeamMemCommand> list = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", user_id);
		int count = teamMemService.getRowTeamCount(user_id);
		if(count>0){
			list = teamMemService.listConfirmTeam(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("teamSchedule");
		mav.addObject("list",list);
		mav.addObject("count",count);
		return mav;
	}
	
	
	
	
	
	
	
	
	
	
//=============================== ��������Ʈ��ŷ=========================
	@RequestMapping("/totalRank.do")
	public ModelAndView totalPointRank(){
		// ������ �� ���� ���
		int count = teamMemService.getMemCount();
		List<MemberCommand> list = null;
		if(count>0){
			list = teamMemService.getMemList();
		}else{
			list = Collections.emptyList();
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("totalPointRank");
		mav.addObject("count",count);
		mav.addObject("list",list);
		return mav;
	}
//=============================== ���վ߱���ŷ ====================
	@RequestMapping("/totalBaseRank.do")
	public ModelAndView totalBaseRank(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword","�߱�");
		map.put("keyfield","teamtype");
		int count = teamService.getTeamCount(map);
		List<TeamCommand> list = null;
		if(count > 0){
			list = teamService.listRank(map);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("totalBaseRank");
		mav.addObject("count",count);
		mav.addObject("list",list);
		
		// ���η�ŷ
		Map<String, Object> mapMem = new HashMap<String, Object>();
		List<BaseCommand> listMem = null;
		if(true){
			listMem = totalTypeService.listBase();
		}
		mav.addObject("listMem",listMem);
		
		return mav;
	}
//=============================== ���ճ󱸷�ŷ ====================
	@RequestMapping("/totalBasketRank.do")
	public ModelAndView totalBasketRank(){
		// Ÿ���� ���� �� ���
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword","��");
		map.put("keyfield","teamtype");
		int count = teamService.getTeamCount(map);
		List<TeamCommand> list = null;
		if(count > 0){
			list = teamService.listRank(map);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("totalBasketRank");
		mav.addObject("count",count);
		mav.addObject("list",list);
		
		// ���η�ŷ
		Map<String, Object> mapMem = new HashMap<String, Object>();
		List<BasketCommand> listMem = null;
		if(true){
			listMem = totalTypeService.listBasket();
		}
		mav.addObject("listMem",listMem);
		
		return mav;
	}
//=============================== �����౸��ŷ ====================
	@RequestMapping("/totalFootRank.do")
	public ModelAndView totalFootRank(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword","�౸");
		map.put("keyfield","teamtype");
		int count = teamService.getTeamCount(map);
		List<TeamCommand> list = null;
		if(count > 0){
			list = teamService.listRank(map);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("totalFootRank");
		mav.addObject("count",count);
		mav.addObject("list",list);
		
		// ���η�ŷ
		Map<String, Object> mapMem = new HashMap<String, Object>();
		List<FootCommand> listMem = null;
		if(true){
			listMem = totalTypeService.listFoot();
		}
		mav.addObject("listMem",listMem);
		
		return mav;
	}
	
}
