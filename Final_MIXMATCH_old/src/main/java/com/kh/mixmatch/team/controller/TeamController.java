package com.kh.mixmatch.team.controller;

import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.servlet.ModelAndView;

import com.kh.mixmatch.match.domain.MatchCommand;
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
	public ModelAndView teamInfo(@RequestParam String t_name,HttpSession session){
		TeamCommand team = teamService.selectTeam(t_name);
		Map<String, Object> map = new HashMap<String, Object>();
		List<MatchCommand> match = null;
		int matchCount = teamService.countHomeMatch(t_name) + teamService.countAwayMatch(t_name);
		
		if(matchCount > 0){
			match = teamService.listMatch(map);
			for(int i=0;i<match.size();i++){
				if(match.get(i).getM_home()==-1 || match.get(i).getM_away() ==-1){
					match.remove(i);	// ��Ī��� �Է¾ȵȰ��� ����Ʈ���� ��.
				}
			}
		}
		String id = (String)session.getAttribute("user_id");
		boolean tCheck = false;
		// �� ���Խ�û ��ҹ�ư Ȱ��ȭ
		map.put("id", id);
		List<TeamMemCommand> list = teamMemService.list(map);
		for(int i=0;i<list.size();i++){
			if(list.get(i).getT_name().equals(t_name) && list.get(i).getT_mem_auth()==0){
				tCheck = true;
				break;
			}
		}
		// ������
		int count = teamMemService.getRowTeamMemCount(t_name);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("teamInfo");
		mav.addObject("team",team);
		mav.addObject("matchCount",matchCount);
		mav.addObject("match",match);
		mav.addObject("count",count);
		mav.addObject("tCheck",tCheck);
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
	@RequestMapping("/cancelRegi.do")
	public String cancelRegi(HttpSession session,@RequestParam String t_name){

		Map<String, Object> map = new HashMap<String, Object>();
		String user_id = (String)session.getAttribute("user_id");
		map.put("id", user_id);
		map.put("t_name",t_name);
		teamMemService.deleteTeamMem(map);
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
	
	
	@RequestMapping(value="/teamUpdate.do",method=RequestMethod.GET)
	public String teamUpdateForm(@RequestParam String t_name, Model model){
		TeamCommand teamCommand = teamService.selectTeam(t_name);
		model.addAttribute("teamCommand",teamCommand);
		return "teamUpdate";
	}
	@RequestMapping(value="/teamUpdate.do",method=RequestMethod.POST)
	public String teamUpdateSubmit(@ModelAttribute("command") @Valid TeamCommand teamCommand,BindingResult result,HttpSession session) throws Exception{
		
		if(log.isDebugEnabled()){
			log.debug("<<<< teamCommand >>>>  : " + teamCommand);
		}
		// ������ ������
		TeamCommand team = teamService.selectTeam(teamCommand.getT_name()); 
		if(result.hasErrors()){
			teamCommand.setT_logo_name(team.getT_logo_name());	
			return "teamUpdate";
		}
		
		String id = (String)session.getAttribute("user_id");
		if(!id.equals(teamCommand.getId())){
			throw new Exception("�������Ͱ� �ƴϸ� ���� �Ұ�");
		}
		// ���۵� ������ ���°�� �������� ���ε�
		if(teamCommand.getT_logo_upload().isEmpty()){
			teamCommand.setT_logo(team.getT_logo());
			teamCommand.setT_logo_name(team.getT_logo_name());
		}
		
		teamService.updateTeam(teamCommand);
		return "redirect:/team.do";
	}
	
	@RequestMapping("/deleteTeam.do")
	public String deleteTeam(@RequestParam String t_name,HttpSession session) throws Exception{
		String id = (String)session.getAttribute("user_id");
		// �α����� ������ �������� �̸� �� ����
		TeamCommand team = teamService.selectTeam(t_name);
		if(!id.equals(team.getId())){
			throw new Exception("�������͸� ������ �� �ֽ��ϴ�.");
		}
		teamMemService.deleteTeam(t_name);
		teamService.deleteTeam(t_name);
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
	
	@RequestMapping("/approveMem.do")
	public ModelAndView teamMemApprove(@RequestParam String id,@RequestParam String t_name){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("t_name",t_name);
		teamMemService.updateTeamMem(map);
		List<TeamMemCommand> tMemList = teamMemService.listTeamMem(map);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("teamMemView");
		mav.addObject("tMemList",tMemList);
		return mav;
	}
	
	@RequestMapping("/deleteMem.do")
	public ModelAndView teamMemDelete(@RequestParam String id,@RequestParam String t_name){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("t_name",t_name);
		teamMemService.deleteTeamMem(map);
		List<TeamMemCommand> tMemList = teamMemService.listTeamMem(map);
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
		// ��� ��ġ����-��� ����Ʈ
		List<MatchCommand> matchList = teamService.listMatch(null);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("teamRecord");
		mav.addObject("list",list);
		mav.addObject("count",count);
		mav.addObject("matchList",matchList);
		return mav;
	}
	@RequestMapping("/matchDetail.do")
	public ModelAndView teamRecordMatch(@RequestParam int m_seq,HttpSession session){
		
		MatchCommand match = teamService.selectMatchDetail(m_seq);
		List<FootCommand> footlist = null;
		List<BaseCommand> baselist = null;
		List<BasketCommand> basketlist = null;
		int footcount = 0;
		int basecount = 0;
		int basketcount = 0;
		if(match.getM_type().equals("�౸")){
			footlist = teamMemService.listMatchFoot(m_seq);
			footcount = footlist.size();
		}else if(match.getM_type().equals("�߱�")){
			baselist = teamMemService.listMatchBase(m_seq);
			basecount = baselist.size();
		}else if(match.getM_type().equals("��")){
			basketlist = teamMemService.listMatchBasket(m_seq);
			basketcount = basketlist.size();
		}
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("matchDetailRecord");
		mav.addObject("match",match);
		mav.addObject("footlist",footlist);
		mav.addObject("baselist",baselist);
		mav.addObject("basketlist",basketlist);
		mav.addObject("footcount",footcount);
		mav.addObject("basecount",basecount);
		mav.addObject("basketcount",basketcount);
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
