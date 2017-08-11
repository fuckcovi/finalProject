package com.kh.mixmatch.team.controller;

import java.security.PublicKey;
import java.util.ArrayList;
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

//===================== ���솃 ============
	
	@RequestMapping("/team.do")
	public ModelAndView process(@RequestParam(defaultValue="") String t_type,@RequestParam(value="pageNum",defaultValue="1") int currentPage,HttpSession session){
		if(log.isDebugEnabled()){
			log.debug("<<< currentPage >>> : " + currentPage);
		}
		
		// �궗�씠�듃�뿉 �벑濡앸릺�뼱 �엳�뒗 �� 紐⑸줉
		Map<String, Object> map = new HashMap<String, Object>();
		List<TeamCommand> list = null;
		map.put("keyfield", "teamtype");
		map.put("keyword",t_type);
		int count = teamService.getTeamCount(map);

		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, "team.do?t_type="+t_type);
		map.put("start", page.getStartCount());
		map.put("end",page.getEndCount());
		
		if(count>0){
			list = teamService.list(map);
		}
		// 媛��엯�떊泥��븳 �� 紐⑸줉
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
		mav.addObject("count", count);	// �벑濡앸맂 ���쓽 珥� �닔
		mav.addObject("list",list);	// �벑濡앸맂 ���쓽 由ъ뒪�듃
		mav.addObject("pagingHtml",page.getPagingHtml());
		mav.addObject("joinCount",joinCount);
		mav.addObject("joinList",joinList);			// 媛��엯�떊泥��븳 �� 紐⑸줉
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
					match.remove(i);	// 留ㅼ묶寃곌낵 �엯�젰�븞�맂寃껋� 由ъ뒪�듃�뿉�꽌 類�.
				}
			}
		}
		String id = (String)session.getAttribute("user_id");
		boolean tCheck = false;
		// �� 媛��엯�떊泥� 痍⑥냼踰꾪듉 �솢�꽦�솕
		map.put("id", id);
		List<TeamMemCommand> list = teamMemService.list(map);
		for(int i=0;i<list.size();i++){
			if(list.get(i).getT_name().equals(t_name) && list.get(i).getT_mem_auth()==0){
				tCheck = true;
				break;
			}
		}
		// ���썝�닔
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
//=========== ���뿉 媛��엯�떊泥� ======================
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
			if(list.get(i).getT_name().equals(teamMem.getT_name())){ // �씠誘� 媛��엯�떊泥��븳 ���씠誘�濡� �떊泥� �븞�릺怨� 由ы꽩
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
	
//=============== �� �벑濡� ============================
	
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
		teamMem.setT_mem_auth(1);//���쓣 �깮�꽦�븳 留덉뒪�꽣�뒗 auth媛믪쓣 1濡� 以�.
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
		// �썝�옒�쓽 ���젙蹂�
		TeamCommand team = teamService.selectTeam(teamCommand.getT_name()); 
		if(result.hasErrors()){
			teamCommand.setT_logo_name(team.getT_logo_name());	
			return "teamUpdate";
		}
		
		String id = (String)session.getAttribute("user_id");
		if(!id.equals(teamCommand.getId())){
			throw new Exception("占쏙옙占쏙옙占쏙옙占싶곤옙 占싣니몌옙 占쏙옙占쏙옙 占쌀곤옙");
		}
		// �쟾�넚�맂 �뙆�씪�씠 �뾾�뒗寃쎌슦 湲곗〈�뙆�씪 �뾽濡쒕뱶
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
		// 濡쒓렇�씤�븳 �쑀��媛� ��留덉뒪�꽣 �씠硫� �� �궘�젣
		TeamCommand team = teamService.selectTeam(t_name);
		if(!id.equals(team.getId())){
			throw new Exception("��留덉뒪�꽣留� �궘�젣�븷 �닔 �엳�뒿�땲�떎.");
		}
		teamMemService.deleteTeam(t_name);
		teamService.deleteTeam(t_name);
		return "redirect:/team.do";
	}
	
//============= ��紐� 以묐났泥댄겕 =========================
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
	
	
//============== ���썝愿�由� =======================
	@RequestMapping("/teamMem.do")
	public ModelAndView teamMemView(@RequestParam String t_name){
		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("t_name", t_name);
		List<TeamMemCommand> tMemList = teamMemService.listTeamMem(map);		// 濡쒓렇�씤�븳 �븘�씠�뵒媛� �냼�냽�맂 ��由ъ뒪�듃
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
	
//============= �� �옲�궧 ===========================
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
		map.put("type", team.getT_type());
		
		int teamMemCount = teamMemService.getRowTeamMemRecordCount(map);
		if(teamMemCount>0 && team.getT_type().equals("異뺢뎄")){
			map.put("forder",forder);
			listTMemFoot = teamMemService.listTMemFoot(map);
		}else if(teamMemCount>0 && team.getT_type().equals("�빞援�")){
			map.put("border",border);
			listTMemBase = teamMemService.listTMemBase(map);
		}else if(teamMemCount>0 && team.getT_type().equals("�냽援�")){
			map.put("bkorder",bkorder);
			listTMemBasket = teamMemService.listTMemBasket(map);
		}
		mav.addObject("teamMemCount", teamMemCount);
		mav.addObject("listTMemFoot",listTMemFoot);
		mav.addObject("listTMemBase",listTMemBase);
		mav.addObject("listTMemBasket",listTMemBasket);
		return mav;
	}

	
//================================ �� �씪�젙寃곌낵/湲곕줉=====================
	@RequestMapping("/teamSchedule.do")
	public ModelAndView teamSchedule(HttpSession session){
		String user_id = (String)session.getAttribute("user_id");
		// 濡쒓렇�씤�븳 �쑀���쓽 �듅�씤 �냼�냽�� 由ъ뒪�듃
		List<TeamMemCommand> list = null;
		List<String> teamList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", user_id);
		int count = teamMemService.getRowTeamCount(user_id);
		if(count>0){
			list = teamMemService.listConfirmTeam(map);
			teamList= teamMemService.getTeamMemList(user_id);
		}
		
		// 紐⑤뱺 留ㅼ튂�씪�젙-寃곌낵 由ъ뒪�듃
		List<MatchCommand> matchList = teamService.listMatch(null);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("teamSchedule");
		mav.addObject("list",list);
		mav.addObject("teamList",teamList);
		mav.addObject("count",count);
		mav.addObject("matchList",matchList);
		return mav;
	}
	
	
	
//==============================   留ㅼ묶寃곌낵 : 媛쒖씤湲곕줉 �벑濡�  =====================================
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
		
		// �빐�떦留ㅼ튂�쓽 湲곕줉�씠 �삱�씪媛붾뒗吏� 由ъ뒪�듃 蹂댁뿬以�
		List<FootCommand> footlist = null;
		List<BaseCommand> baselist = null;
		List<BasketCommand> basketlist = null;
		int footcount = 0;
		int basecount = 0;
		int basketcount = 0;
		if(match.getM_type().equals("異뺢뎄")){
			footlist = teamMemService.listMatchFoot(m_seq);
			footcount = footlist.size();
		}else if(match.getM_type().equals("�빞援�")){
			baselist = teamMemService.listMatchBase(m_seq);
			basecount = baselist.size();
		}else if(match.getM_type().equals("�냽援�")){
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
////異뺢뎄
	@RequestMapping("/homeMemRecordFoot.do")
	public ModelAndView homeMemRecordFoot(@ModelAttribute("footCommand") FootCommand footCommand,BindingResult result,HttpSession session){
		System.out.println(footCommand );
		int m_seq = footCommand.getM_seq();
			
		// �씠誘� �빐�떦 留ㅼ튂踰덊샇�쓽 �냼�냽���쓽 �쑀�� 湲곕줉�씠 �벑濡앸릺�엳�쑝硫� insert�븞�릺怨� 由ы꽩.
		List<FootCommand> footlist = teamMemService.listMatchFoot(m_seq);
		for(int i =0;i<footlist.size();i++){
			if(footlist.get(i).getId().equals(footCommand.getId())&& footlist.get(i).getT_name().equals(footCommand.getT_name())){
				// �씠誘� �씠�궗�엺�� �씠踰덈ℓ移섏쓽 媛쒖씤湲곕줉�쓣 �벑濡앺븿
				return matchMemRecordInsertForm(m_seq);
			}
		}
		totalTypeService.insertFoot(footCommand);
		return matchMemRecordInsertForm(m_seq);
	}
	@RequestMapping("/awayMemRecordFoot.do")
	public ModelAndView awayMemRecordFoot(@ModelAttribute("footCommand") FootCommand footCommand,BindingResult result,HttpSession session){
		int m_seq = footCommand.getM_seq();
		
		// �씠誘� �빐�떦 留ㅼ튂踰덊샇�쓽 �냼�냽���쓽 �쑀�� 湲곕줉�씠 �벑濡앸릺�엳�쑝硫� insert�븞�릺怨� 由ы꽩.
		List<FootCommand> footlist = teamMemService.listMatchFoot(m_seq);
		for(int i =0;i<footlist.size();i++){
			if(footlist.get(i).getId().equals(footCommand.getId())&& footlist.get(i).getT_name().equals(footCommand.getT_name())){
				// �씠誘� �씠�궗�엺�� �씠踰덈ℓ移섏쓽 媛쒖씤湲곕줉�쓣 �벑濡앺븿			
				return matchMemRecordInsertForm(m_seq);
			}
		}
		totalTypeService.insertFoot(footCommand);
			
		return matchMemRecordInsertForm(m_seq);
	}
	//////////// �냽援�
	@RequestMapping("/homeMemRecordBasket.do")
	public ModelAndView homeMemRecordBasket(@ModelAttribute("basketCommand") BasketCommand basketCommand ,BindingResult result,HttpSession session){
		
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
///////////s�빞援�
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
	
// =================== 媛쒖씤湲곕줉 �닔�젙 - 異뺢뎄
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
// ================= 媛쒖씤湲곕줉 �닔�젙 - �냽援�
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
// ==================媛쒖씤湲곕줉 �닔�젙 - �빞援�
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
	
	
// ============================================ ��湲곕줉
	
	@RequestMapping("/teamRecord.do")
	public ModelAndView teamRecord(HttpSession session){
		String user_id = (String)session.getAttribute("user_id");
		// 濡쒓렇�씤�븳 �쑀���쓽 �듅�씤 �냼�냽�� 由ъ뒪�듃
		List<TeamMemCommand> list = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", user_id);
		int count = teamMemService.getRowTeamCount(user_id);
		if(count>0){
			list = teamMemService.listConfirmTeam(map);
		}
		// 紐⑤뱺 留ㅼ튂�씪�젙-寃곌낵 由ъ뒪�듃
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
		if(match.getM_type().equals("異뺢뎄")){
			footlist = teamMemService.listMatchFoot(m_seq);
			footcount = footlist.size();
		}else if(match.getM_type().equals("�빞援�")){
			baselist = teamMemService.listMatchBase(m_seq);
			basecount = baselist.size();
		}else if(match.getM_type().equals("�냽援�")){
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
	
	
	
	
	
//================================ �넻�빀�룷�씤�듃�옲�궧==========================
	private int totalProwCount =10	;
	private int totalPpageCount = 5;
	@RequestMapping("/totalRank.do")
	public ModelAndView totalPointRank(@RequestParam(value="pageNum",defaultValue="1") int currentPage){
		// 媛��엯�븳 珥� �쑀�� 紐⑸줉
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
//=============================== �넻�빀�빞援щ옲�궧 =================
	@RequestMapping("/totalBaseRank.do")
	public ModelAndView totalBaseRank(@RequestParam(defaultValue="t_win") String order,@RequestParam(value="pageNum",defaultValue="1") int currentPage){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword","야구");
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
		// 占쌩깍옙占쏙옙占쏙옙 占쏙옙占� 확占쏙옙 占쏙옙.
		map.put("keyword","야구");
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
//============================= �넻�빀�냽援щ옲�궧 ========================
	@RequestMapping("/totalBasketRank.do")
	public ModelAndView totalBasketRank(@RequestParam(defaultValue="t_win") String order,@RequestParam(value="pageNum",defaultValue="1") int currentPage){
		/// ���엯�씠 �냽援ъ씤 �� 紐⑸줉
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword","농구");
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
		// 占쏙옙占쏙옙占쏙옙 占쏙옙占� 확占쏙옙 占쏙옙.
		map.put("keyword","농구");
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
//================================= �넻�빀異뺢뎄�옲�궧 =============
	@RequestMapping("/totalFootRank.do")
	public ModelAndView totalFootRank(@RequestParam(defaultValue="t_win") String order,@RequestParam(value="pageNum",defaultValue="1") int currentPage){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword","축구");
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
		// 占썅구占쏙옙占쏙옙 占쏙옙占� 확占쏙옙 占쏙옙.
		map.put("keyword","축구");
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
