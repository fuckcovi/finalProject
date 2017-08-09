package com.kh.mixmatch.team.controller;

import java.security.PublicKey;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;
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
import com.kh.mixmatch.util.PagingUtil;

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
	@Resource
	private MatchService matchService;
	
	@ModelAttribute("teamCommand")
	public TeamCommand initCommand(){
		return new TeamCommand();
	}
	
	private int rowCount = 3;
	private int pageCount = 1;

//=================== ��Ȩ ==============
	
	@RequestMapping("/team.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1") int currentPage,HttpSession session){
		if(log.isDebugEnabled()){
			log.debug("<<< currentPage >>> : " + currentPage);
		}
		
		// ����Ʈ�� ��ϵǾ� �ִ� �� ���
		Map<String, Object> map = new HashMap<String, Object>();
		List<TeamCommand> list = null;
		int count = teamService.getTeamCount(map);

		System.out.println(count + " : " + list);
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, "team.do");
		map.put("start", page.getStartCount());
		map.put("end",page.getEndCount());
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
		mav.addObject("pagingHtml",page.getPagingHtml());
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
	public ModelAndView teamRank(@RequestParam String t_name,@RequestParam(defaultValue="f_shoot") String forder,@RequestParam(defaultValue="b_hit") String border,@RequestParam(defaultValue="b_score") String bkorder){

		
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
			map.put("forder",forder);
			listTMemFoot = teamMemService.listTMemFoot(map);
		}else if(teamMemCount>0 && team.getT_type().equals("�߱�")){
			map.put("border",border);
			listTMemBase = teamMemService.listTMemBase(map);
		}else if(teamMemCount>0 && team.getT_type().equals("��")){
			map.put("bkorder",bkorder);
			listTMemBasket = teamMemService.listTMemBasket(map);
		}
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
	
	
	
//===========================   ��Ī��� : ���α�� ���  ==============================
	@RequestMapping(value="/matchMemRecordInsert.do",method=RequestMethod.GET)
	public ModelAndView matchMemRecordInsertForm(@RequestParam int m_seq){
		MatchCommand match = matchService.selectMatch(m_seq);
		String home = match.getT_name();
		String away = match.getM_challenger();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("t_name", home);
		List<TeamMemCommand> homelist = teamMemService.listTeamMem(map);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("t_name", away);
		List<TeamMemCommand> awaylist = teamMemService.listTeamMem(map2);
		FootCommand footCommand = new FootCommand();
		BaseCommand baseCommand = new BaseCommand();
		BasketCommand basketCommand = new BasketCommand();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("matchMemRecordInsert");
		mav.addObject("match",match);
		mav.addObject("homelist",homelist);
		mav.addObject("awaylist",awaylist);
		mav.addObject("footCommand",footCommand);
		mav.addObject("basketCommand",basketCommand);
		mav.addObject("baseCommand",baseCommand);
		
		// �ش��ġ�� ����� �ö󰬴��� ����Ʈ ������
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

		mav.addObject("footlist",footlist);
		mav.addObject("baselist",baselist);
		mav.addObject("basketlist",basketlist);
		mav.addObject("footcount",footcount);
		mav.addObject("basecount",basecount);
		mav.addObject("basketcount",basketcount);
		
		
		return mav;
	}
	//// �౸
	@RequestMapping("/homeMemRecordFoot.do")
	public ModelAndView homeMemRecordFoot(@ModelAttribute("footCommand") FootCommand footCommand,BindingResult result,HttpSession session){
		System.out.println(footCommand );
		int m_seq = footCommand.getM_seq();
			
		// �̹� �ش� ��ġ��ȣ�� �Ҽ����� ���� ����� ��ϵ������� insert�ȵǰ� ����.
		List<FootCommand> footlist = teamMemService.listMatchFoot(m_seq);
		for(int i =0;i<footlist.size();i++){
			if(footlist.get(i).getId().equals(footCommand.getId())&& footlist.get(i).getT_name().equals(footCommand.getT_name())){
				// �̹� �̻���� �̹���ġ�� ���α���� �����
				return matchMemRecordInsertForm(m_seq);
			}
		}
		totalTypeService.insertFoot(footCommand);
		return matchMemRecordInsertForm(m_seq);
	}
	@RequestMapping("/awayMemRecordFoot.do")
	public ModelAndView awayMemRecordFoot(@ModelAttribute("footCommand") FootCommand footCommand,BindingResult result,HttpSession session){
		int m_seq = footCommand.getM_seq();
		
		// �̹� �ش� ��ġ��ȣ�� �Ҽ����� ���� ����� ��ϵ������� insert�ȵǰ� ����.
		List<FootCommand> footlist = teamMemService.listMatchFoot(m_seq);
		for(int i =0;i<footlist.size();i++){
			if(footlist.get(i).getId().equals(footCommand.getId())&& footlist.get(i).getT_name().equals(footCommand.getT_name())){
				// �̹� �̻���� �̹���ġ�� ���α���� �����
			
				return matchMemRecordInsertForm(m_seq);
			}
		}
		totalTypeService.insertFoot(footCommand);
			
		return matchMemRecordInsertForm(m_seq);
	}
	//////////// ��
	@RequestMapping("/homeMemRecordBasket.do")
	public ModelAndView homeMemRecordBasket(@ModelAttribute("basketCommand") BasketCommand basketCommand ,BindingResult result,HttpSession session){
		System.out.println(basketCommand);
		int m_seq = basketCommand.getM_seq();
		List<BasketCommand> basketlist = teamMemService.listMatchBasket(m_seq);
		for(int i =0;i<basketlist.size();i++){
			if(basketlist.get(i).getId().equals(basketCommand.getId())&& basketlist.get(i).getT_name().equals(basketCommand.getT_name())){
				return matchMemRecordInsertForm(m_seq);
			}
		}
		totalTypeService.insertBasket(basketCommand);
	
		
		return matchMemRecordInsertForm(m_seq);
	}
	@RequestMapping("/awayMemRecordBasket.do")
	public ModelAndView awayMemRecordBasket(@ModelAttribute("basketCommand") BasketCommand basketCommand ,BindingResult result,HttpSession session){
		System.out.println(basketCommand);
		int m_seq = basketCommand.getM_seq();
		List<BasketCommand> basketlist = teamMemService.listMatchBasket(m_seq);
		for(int i =0;i<basketlist.size();i++){
			if(basketlist.get(i).getId().equals(basketCommand.getId()) && basketlist.get(i).getT_name().equals(basketCommand.getT_name())){
				return matchMemRecordInsertForm(m_seq);
			}
		}
		totalTypeService.insertBasket(basketCommand);
		
		return matchMemRecordInsertForm(m_seq);
	}
	//////////////////////s�߱�
	@RequestMapping("/homeMemRecordBase.do")
	public ModelAndView homeMemRecordBase(@ModelAttribute("baseCommand") BaseCommand baseCommand,BindingResult result,HttpSession session){
		int m_seq= baseCommand.getM_seq();
		List<BaseCommand> baselist = teamMemService.listMatchBase(m_seq);
		for(int i =0;i<baselist.size();i++){
			if(baselist.get(i).getId().equals(baseCommand.getId())&& baselist.get(i).getT_name().equals(baseCommand.getT_name())){
				return matchMemRecordInsertForm(m_seq);
			}
		}
		totalTypeService.insertBase(baseCommand);
		
		return matchMemRecordInsertForm(m_seq);
	}
	@RequestMapping("/awayMemRecordBase.do")
	public ModelAndView awayMemRecordBase(@ModelAttribute("baseCommand") BaseCommand baseCommand,BindingResult result,HttpSession session){
		int m_seq = baseCommand.getM_seq();
		List<BaseCommand> baselist = teamMemService.listMatchBase(m_seq);
		for(int i =0;i<baselist.size();i++){
			if(baselist.get(i).getId().equals(baseCommand.getId())&& baselist.get(i).getT_name().equals(baseCommand.getT_name())){
				return matchMemRecordInsertForm(m_seq);
			}
		}
		totalTypeService.insertBase(baseCommand);
		
		return matchMemRecordInsertForm(m_seq);
	}
	
// ================== ���α�� ���� - �౸
	@RequestMapping("/footMemModify.do")
	public ModelAndView footMemModify(@RequestParam int m_seq,@RequestParam String t_name, @RequestParam String id,@RequestParam int f_shoot, @RequestParam int f_assist,@RequestParam int f_goal,@RequestParam int f_attack){
		List<FootCommand> footlist = teamMemService.listMatchFoot(m_seq);
		for(int i =0;i<footlist.size();i++){
			if(footlist.get(i).getId().equals(id)&& footlist.get(i).getT_name().equals(t_name)){
				FootCommand foot = new FootCommand();
				foot.setF_seq(footlist.get(i).getF_seq());
				foot.setF_shoot(f_shoot);
				foot.setF_assist(f_assist);
				foot.setF_goal(f_goal);
				foot.setF_attack(f_attack);
				totalTypeService.updateFoot(foot);
			}
		}
		return matchMemRecordInsertForm(m_seq);
	}
// ================== ���α�� ���� - ��
	@RequestMapping("/basketMemModify.do")
	public ModelAndView basketMemModify(@RequestParam int m_seq,@RequestParam String t_name, @RequestParam String id,@RequestParam int b_score, @RequestParam int b_assist,@RequestParam int b_rebound,@RequestParam int b_steel, @RequestParam int b_block,@RequestParam int b_3point){
		List<BasketCommand> basketlist = teamMemService.listMatchBasket(m_seq);
		for(int i =0;i<basketlist.size();i++){
			if(basketlist.get(i).getId().equals(id)&& basketlist.get(i).getT_name().equals(t_name)){
				BasketCommand basket = new BasketCommand();
				basket.setB_seq(basketlist.get(i).getB_seq());
				basket.setB_steel(b_steel);
				basket.setB_score(b_score);
				basket.setB_rebound(b_rebound);
				basket.setB_block(b_block);
				basket.setB_assist(b_assist);
				basket.setB_3point(b_3point);
				totalTypeService.updateBasket(basket);
			}
		}
		return matchMemRecordInsertForm(m_seq);
	}
// ================== ���α�� ���� - �߱�
	@RequestMapping("/baseMemModify.do")
	public ModelAndView baseMemModify(@RequestParam int m_seq,@RequestParam String t_name, @RequestParam String id,
			@RequestParam int b_bat, @RequestParam int b_hit, @RequestParam int b_rbi, @RequestParam int b_score, @RequestParam int b_win,@RequestParam int b_lose, @RequestParam int b_strike,@RequestParam int b_ip, @RequestParam int b_er){
		List<BaseCommand> baselist = teamMemService.listMatchBase(m_seq);
		for(int i =0;i<baselist.size();i++){
			if(baselist.get(i).getId().equals(id)&& baselist.get(i).getT_name().equals(t_name)){
				BaseCommand base = new BaseCommand();
				base.setB_seq(baselist.get(i).getB_seq());
				base.setB_bat(b_bat);
				base.setB_er(b_er);
				base.setB_hit(b_hit);
				base.setB_ip(b_ip);
				base.setB_lose(b_lose);
				base.setB_rbi(b_rbi);
				base.setB_score(b_score);
				base.setB_strike(b_strike);
				base.setB_win(b_win);
				
				totalTypeService.updateBase(base);
			}
		}
		return matchMemRecordInsertForm(m_seq);
	}	
	
	
// =========================================== �����
	
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
	private int totalProwCount =2	;
	private int totalPpageCount = 2;
	@RequestMapping("/totalRank.do")
	public ModelAndView totalPointRank(@RequestParam(value="pageNum",defaultValue="1") int currentPage){
		// ������ �� ���� ���
		int count = teamMemService.getMemCount();
		
		Map<String, Object> map = new HashMap<String, Object>();
		PagingUtil page = new PagingUtil(currentPage, count, totalProwCount, totalPpageCount, "totalRank.do");
		map.put("start", page.getStartCount());
		map.put("end",page.getEndCount());
		
		List<MemberCommand> list = null;
		if(count>0){
			list = teamMemService.getMemList(map);
		}else{
			list = Collections.emptyList();
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("totalPointRank");
		mav.addObject("count",count);
		mav.addObject("list",list);
		mav.addObject("pagingHtml",page.getPagingHtml());
		return mav;
	}
//=============================== ���վ߱���ŷ ====================
	@RequestMapping("/totalBaseRank.do")
	public ModelAndView totalBaseRank(@RequestParam(defaultValue="t_win") String order,@RequestParam(value="pageNum",defaultValue="1") int currentPage){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword","�߱�");
		map.put("keyfield","teamtype");
		int count = teamService.getTeamCount(map);

		PagingUtil page = new PagingUtil(currentPage, count, totalProwCount, totalPpageCount, "totalBaseRank.do?order="+order);
		map.put("start", page.getStartCount());
		map.put("end",page.getEndCount());
		
		List<TeamCommand> list = null;
		if(count > 0){
			map.put("order", order);
			list = teamService.listRank(map);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("totalBaseRank");
		mav.addObject("count",count);
		mav.addObject("list",list);
		mav.addObject("pagingHtml",page.getPagingHtml());
		return mav;
	}
	@RequestMapping("/totalBaseMemRank.do")
	public ModelAndView totalBaseMemRank(@RequestParam(defaultValue="b_hit") String morder,@RequestParam(value="pageNum",defaultValue="1") int currentPage){
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("totalBaseMemRank");
		// �߱����� ��� Ȯ�� ��.
		map.put("keyword","�߱�");
		int count = teamMemService.getMemRecordCount(map);
		
		PagingUtil page = new PagingUtil(currentPage, count, totalProwCount, totalPpageCount, "totalBaseMemRank.do?morder="+morder);
		map.put("start", page.getStartCount());
		map.put("end",page.getEndCount());
		
		List<BaseCommand> listMem  = null;
		if(count > 0){
			map.put("morder", morder);
			listMem = totalTypeService.listBase(map);
		}
		mav.addObject("count",count);
		mav.addObject("listMem",listMem);
		mav.addObject("pagingHtml",page.getPagingHtml());
		return mav;
	}
//=============================== ���ճ󱸷�ŷ ====================
	@RequestMapping("/totalBasketRank.do")
	public ModelAndView totalBasketRank(@RequestParam(defaultValue="t_win") String order,@RequestParam(value="pageNum",defaultValue="1") int currentPage){
		// Ÿ���� ���� �� ���
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword","��");
		map.put("keyfield","teamtype");
		int count = teamService.getTeamCount(map);
		
		PagingUtil page = new PagingUtil(currentPage, count, totalProwCount, totalPpageCount, "totalBasketRank.do");
		map.put("start", page.getStartCount());
		map.put("end",page.getEndCount());
		
		List<TeamCommand> list = null;
		if(count > 0){
			map.put("order", order);
			list = teamService.listRank(map);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("totalBasketRank");
		mav.addObject("count",count);
		mav.addObject("list",list);
		mav.addObject("pagingHtml",page.getPagingHtml());
		return mav;
	}
	@RequestMapping("/totalBasketMemRank.do")
	public ModelAndView totalBasketMemRank(@RequestParam(defaultValue="b_score") String morder,@RequestParam(value="pageNum",defaultValue="1") int currentPage){
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("totalBasketMemRank");
		// ������ ��� Ȯ�� ��.
		map.put("keyword","��");
		int count = teamMemService.getMemRecordCount(map);
		
		PagingUtil page = new PagingUtil(currentPage, count, totalProwCount, totalPpageCount, "totalBasketMemRank.do?morder="+morder);
		map.put("start", page.getStartCount());
		map.put("end",page.getEndCount());
		
		List<BasketCommand> listMem  = null;
		if(count > 0){
			map.put("morder", morder);
			listMem = totalTypeService.listBasket(map);
		}
		mav.addObject("count",count);
		mav.addObject("listMem",listMem);
		mav.addObject("pagingHtml",page.getPagingHtml());
		return mav;
	}
//=============================== �����౸��ŷ ====================
	@RequestMapping("/totalFootRank.do")
	public ModelAndView totalFootRank(@RequestParam(defaultValue="t_win") String order,@RequestParam(value="pageNum",defaultValue="1") int currentPage){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword","�౸");
		map.put("keyfield","teamtype");
		int count = teamService.getTeamCount(map);
		
		PagingUtil page = new PagingUtil(currentPage, count, totalProwCount, totalPpageCount, "totalFootRank.do");
		map.put("start", page.getStartCount());
		map.put("end",page.getEndCount());
		
		List<TeamCommand> list = null;
		if(count > 0){
			map.put("order", order);
			list = teamService.listRank(map);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("totalFootRank");
		mav.addObject("count",count);
		mav.addObject("list",list);
		mav.addObject("pagingHtml",page.getPagingHtml());
		return mav;
	}
	@RequestMapping("/totalFootMemRank.do")
	public ModelAndView totalFootMemRank(@RequestParam(defaultValue="f_goal") String morder,@RequestParam(value="pageNum",defaultValue="1") int currentPage){
		Map<String, Object> map = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("totalFootMemRank");
		// �౸���� ��� Ȯ�� ��.
		map.put("keyword","�౸");
		int count = teamMemService.getMemRecordCount(map);
		
		PagingUtil page = new PagingUtil(currentPage, count, totalProwCount, totalPpageCount, "totalFootMemRank.do?morder="+morder);
		map.put("start", page.getStartCount());
		map.put("end",page.getEndCount());
		
		List<FootCommand> listMem  = null;
		if(count > 0){
			map.put("morder", morder);
			listMem = totalTypeService.listFoot(map);
		}
		mav.addObject("count",count);
		mav.addObject("listMem",listMem);
		mav.addObject("pagingHtml",page.getPagingHtml());
		
		
		return mav;
	}
	
}
