package com.kh.mixmatch.match.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;

import com.kh.mixmatch.match.domain.MatchCommand;
import com.kh.mixmatch.match.domain.TotoCommand;
import com.kh.mixmatch.match.service.MatchService;
import com.kh.mixmatch.team.domain.TeamCommand;

@Controller
public class MatchController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MatchService matchService;
	
	// ��ġ����
	@RequestMapping("/match/matchBoard.do")
	public ModelAndView matchBoardForm(@RequestParam(value="type", defaultValue="�౸") String type,
									   HttpSession session) {				
		// ���� �޾ƿ���
		String board = "match";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("board", board);
		
		// ���� �Խñ� �� ī��Ʈ
		int count = matchService.getRowCount(map);
		
		if (log.isDebugEnabled()) {
			log.debug("<<��ġ���� type>> : " + type);		
			log.debug("<<��ġ���� count>> : " + count);
		}
		
		// ����Ʈ�� ����
		List<MatchCommand> list = null;
		if (count > 0) {
			map.put("type", type);		
			list = matchService.matchList(map);
		}
		
		// ���� ���̸� �޾ƿ���
		String id = (String) session.getAttribute("user_id");
		List<String> t_name = matchService.getTeamList(id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("matchBoard");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("type", type);
		mav.addObject("t_name", t_name);
		
		return mav;
	}
	
	// ���ھ��
	@RequestMapping("/match/scoreBoard.do")
	public ModelAndView scoreBoardForm(@RequestParam(value="type", defaultValue="�౸") String type,
									   HttpSession session) {			
		// ���� �޾ƿ���
		String board = "score";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("board", board);
		
		// ���� �Խñ� �� ī��Ʈ
		int count = matchService.getRowCount(map);
		
		if (log.isDebugEnabled()) {
			log.debug("<<���ھ�� type>> : " + type);
			log.debug("<<���ھ�� count>> : " + count);
		}
		
		// ����Ʈ�� ����
		List<MatchCommand> list = null;
		if (count > 0) {
			map.put("type", type);		
			list = matchService.matchList(map);
		}
		
		// ���� ���̸� �޾ƿ���
		String id = (String) session.getAttribute("user_id");
		List<String> t_name = matchService.getTeamList(id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("scoreBoard");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("type", type);
		mav.addObject("t_name", t_name);
		
		return mav;
	}
	
	// �� �̹��� ���
	@RequestMapping("/match/matchImageView.do")
	public ModelAndView matchImageView(@RequestParam("t_name") String t_name) {
		TeamCommand team = matchService.getTeam(t_name);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile",team.getT_logo());
		mav.addObject("filename", team.getT_logo_name());
		
		return mav;
	}
	
	// ��ġ�����
	@RequestMapping(value="/match/matchInsert.do", method=RequestMethod.GET)
	public ModelAndView matchInsertForm(HttpSession session, Model model) {
		// ���� ���̸� �޾ƿ���
		String id = (String) session.getAttribute("user_id");
		List<String> t_name = matchService.getTeamList(id);
		
		if (log.isDebugEnabled()) {
			log.debug("<<��ġ����� t_name>> : " + t_name);
		}
		
		MatchCommand matchCommand = new MatchCommand();
		
		ArrayList<String> type = new ArrayList<String>();
		type.add("�౸");
		type.add("�߱�");
		type.add("��");
		
		ArrayList<String> area = new ArrayList<String>();
		area.add("����");
		area.add("��õ");
		area.add("���");
		area.add("����");
		area.add("����");
		area.add("���");
		area.add("�泲");
		area.add("����");
		area.add("����");
		area.add("����");
		area.add("�뱸");
		area.add("���");
		area.add("���");
		area.add("�泲");
		area.add("�λ�");
		area.add("����");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("matchInsert");
		mav.addObject("match", matchCommand);
		mav.addObject("teamList", t_name);
		mav.addObject("type", type);
		mav.addObject("area", area);
		
		return mav;
	}
	
	// ��ġ���
	@RequestMapping(value="/match/matchInsert.do", method=RequestMethod.POST)
	public String matchInsertSubmit(@ModelAttribute("match") @Valid MatchCommand matchCommand,
									BindingResult result, HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("<<��ġ��� matchCommand>> : " + matchCommand);
		}
		
		if (result.hasErrors()) {
			return "matchInsert";
		}
		
		// ��ġ���
		matchService.insertMatch(matchCommand);
		
		return "redirect:/match/matchBoard.do";
	}
	
	// ��ġ �󼼺���
	@RequestMapping("/match/matchDetail.do")
	public ModelAndView matchDetailForm(@RequestParam("m_seq") int m_seq,
										Model model, HttpSession session) {		
		if (log.isDebugEnabled()) {
			log.debug("<<��ġ �󼼺��� m_seq>> : " + m_seq);
		}
		
		// ���� ���̸� �޾ƿ���
		String id = (String) session.getAttribute("user_id");
		List<String> t_name = matchService.getTeamList(id);
		
		// �۹�ȣ(m_seq)�� ��ġ�ϴ� ���ڵ� ����
		MatchCommand match = matchService.selectMatch(m_seq);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("matchDetail");
		mav.addObject("match", match);
		mav.addObject("t_name", t_name);
		
		return mav;
	}
	
	// ��ġ����
	@RequestMapping("/match/matchDelete.do")
	public String matchDeleteSubmit(@RequestParam("m_seq") int m_seq) {
		if (log.isDebugEnabled()) {
			log.debug("<<��ġ���� m_seq>> : " + m_seq);
		}
		
		// ��ġ����
		matchService.deleteMatch(m_seq);
		
		return "redirect:/match/matchBoard.do";
	}
	
	// ��ġ��û
	@RequestMapping("/match/challengerUpdate.do")
	public String challengerUpdateSubmit(@ModelAttribute("match") @Valid MatchCommand matchCommand,
										 BindingResult result, HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("<<��ġ��û matchCommand>> : " + matchCommand);
		}
		
		if (result.hasErrors()) {
			return "matchInsert";
		}
		
		// ��ġ���
		matchService.updateChallenger(matchCommand);
		
		return "redirect:/match/matchBoard.do";
	}
	
	// ��ġ������
	@RequestMapping(value="/match/matchUpdate.do", method=RequestMethod.GET)
	public String matchUpdateForm(@RequestParam("m_seq") int m_seq,
								  HttpSession session, Model model) {		
		// ���� ���̸� �޾ƿ���
		String id = (String) session.getAttribute("user_id");
		List<String> t_name = matchService.getTeamList(id);
		
		if (log.isDebugEnabled()) {
			log.debug("<<��ġ������ m_seq>> : " + m_seq);
			log.debug("<<��ġ������ t_name>> : " + t_name);
		}
		
		ArrayList<String> type = new ArrayList<String>();
		type.add("�౸");
		type.add("�߱�");
		type.add("��");
		
		ArrayList<String> area = new ArrayList<String>();
		area.add("����");
		area.add("��õ");
		area.add("���");
		area.add("����");
		area.add("����");
		area.add("���");
		area.add("�泲");
		area.add("����");
		area.add("����");
		area.add("����");
		area.add("�뱸");
		area.add("���");
		area.add("���");
		area.add("�泲");
		area.add("�λ�");
		area.add("����");
		
		// �۹�ȣ(m_seq)�� ��ġ�ϴ� ���ڵ� ����
		MatchCommand matchCommand = matchService.selectMatch(m_seq);
		model.addAttribute("match", matchCommand);
		model.addAttribute("teamList", t_name);
		model.addAttribute("type", type);
		model.addAttribute("area", area);
		
		return "matchUpdate";
	}
	
	// ��ġ����
	@RequestMapping(value="/match/matchUpdate.do", method=RequestMethod.POST)
	public String matchUpdateSubmit(@ModelAttribute("match") @Valid MatchCommand matchCommand,
									BindingResult result, HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("<<��ġ���� matchCommand>> : " + matchCommand);
		}
		
		if (result.hasErrors()) {
			return "matchUpdate";
		}
		
		// ��ġ����
		matchService.updateMatch(matchCommand);
		
		return "redirect:/match/matchBoard.do";
	}
	
	// ��������
	@RequestMapping("/match/scoreDetail.do")
	public ModelAndView scoreDetailForm(@RequestParam("m_seq") int m_seq) {
		if (log.isDebugEnabled()) {
			log.debug("<<�������� m_seq>> : " + m_seq);
		}
		
		// �۹�ȣ(m_seq)�� ��ġ�ϴ� ���ڵ� ����
		MatchCommand match = matchService.selectMatch(m_seq);
		
		// �� �̸� ��������
		TeamCommand t_name = matchService.getTeam(match.getT_name());
		TeamCommand m_challenger = matchService.getTeam(match.getM_challenger());
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("scoreDetail");
		mav.addObject("match", match);
		mav.addObject("t_name", t_name.getT_logo_name());
		mav.addObject("m_challenger", m_challenger.getT_logo_name());
		
		return mav;
	}
	
	// ��������
	@RequestMapping(value="/match/scoreUpdate.do", method=RequestMethod.GET)
	public String updateScoreForm(@RequestParam("m_seq") int m_seq, Model model) {
		if (log.isDebugEnabled()) {
			log.debug("<<�������� m_seq>> : " + m_seq);
		}
		
		// �۹�ȣ(m_seq)�� ��ġ�ϴ� ���ڵ� ����
		MatchCommand matchCommand = matchService.selectMatch(m_seq);
		
		// MVP��� ������ ���� �� ��� ��������
		List<String> mvpMember = matchService.getMvpMember(m_seq);
		
		model.addAttribute("match", matchCommand);
		model.addAttribute("mvpMember", mvpMember);
		
		return "scoreUpdate";
	}
	
	// ������
	@RequestMapping(value="/match/scoreUpdate.do", method=RequestMethod.POST)
	public String updateScoreSubmit(@ModelAttribute("match") @Valid MatchCommand matchCommand,
									BindingResult result, HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("<<������ matchCommand>> : " + matchCommand);
		}
		
		// ������
		matchService.updateScore(matchCommand);
		
		// MVP ��� ����Ʈ ����
		String id = matchCommand.getM_mvp();
		matchService.updateMemberPoint(id);
		
		// �� ���� ����, ����Ʈ ����
		int home = matchCommand.getM_home();
		int away = matchCommand.getM_away();
		
		String t_home = matchCommand.getT_name();
		String t_away = matchCommand.getM_challenger();
		
		if (home > away) {
			matchService.updateTeamWin(t_home);
			matchService.updateTeamLose(t_away);
			matchService.updatePointWin(t_home);
			matchService.updatePointLose(t_away);
		} else if (home == away) {
			matchService.updateTeamDraw(matchCommand.getT_name());
			matchService.updateTeamDraw(matchCommand.getM_challenger());
			matchService.updatePointDraw(t_home);
			matchService.updatePointDraw(t_away);
		} else if (home < away) {
			matchService.updateTeamLose(matchCommand.getT_name());
			matchService.updateTeamWin(matchCommand.getM_challenger());
			matchService.updatePointWin(t_away);
			matchService.updatePointLose(t_home);
		}
		
		return "redirect:/match/scoreBoard.do";
	}
	
	///////////////////////////////////�ºο���///////////////////////////////////
	// �ºο���
	@RequestMapping("/match/sportsToto.do")
	public ModelAndView sportsTotoForm(@RequestParam(value="type", defaultValue="�౸") String type) {			
		// ���� �޾ƿ���
		String board = "toto";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("board", board);
			
		// ���� �Խñ� �� ī��Ʈ
		int count = matchService.getRowCount(map);
			
		if (log.isDebugEnabled()) {
			log.debug("<<�ºο��� type>> : " + type);
			log.debug("<<�ºο��� count>> : " + count);
		}
		
		// ����Ʈ�� ����
		List<MatchCommand> list = null;
		if (count > 0) {
			map.put("type", type);		
			list = matchService.matchList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sportsToto");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("type", type);
		
		return mav;
	}
	
	// ���� ��
	@RequestMapping(value="/match/totoDetail.do", method=RequestMethod.GET)
	public ModelAndView totoDetailForm(@RequestParam("m_seq") int m_seq,
										Model model, HttpSession session) {		
		if (log.isDebugEnabled()) {
			log.debug("<<���� �� m_seq>> : " + m_seq);
		}
			
		// ���� ���̸� �޾ƿ���
		String id = (String) session.getAttribute("user_id");
		List<String> myteam = matchService.getTeamList(id);
			
		// �۹�ȣ(m_seq)�� ��ġ�ϴ� ���ڵ� ����
		MatchCommand match = matchService.selectMatch(m_seq);
		
		// �� �̸� ��������
		TeamCommand t_name = matchService.getTeam(match.getT_name());
		TeamCommand m_challenger = matchService.getTeam(match.getM_challenger());
		
		// ���� ���ϱ�
		int home = t_name.getT_win() + t_name.getT_lose() + t_name.getT_draw();
		int away = m_challenger.getT_win() + m_challenger.getT_lose() + m_challenger.getT_draw();
		
		TotoCommand toto = new TotoCommand();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("totoDetail");
		mav.addObject("match", match);
		mav.addObject("t_name", t_name);
		mav.addObject("m_challenger", m_challenger);
		mav.addObject("myteam", myteam);
		mav.addObject("home", home);
		mav.addObject("away", away);
		mav.addObject("user_id", id);
		mav.addObject("toto", toto);
			
		return mav;
	}
	
	/*// �����ϱ�
	@RequestMapping(value="/match/totoDetail.do", method=RequestMethod.POST)
	public String insertTotoSubmit(@ModelAttribute("toto") @Valid TotoCommand totoCommand,
								   BindingResult result, HttpServletRequest request) {
		if (log.isDebugEnabled()) {
		log.debug("<<�����ϱ� totoCommand>> : " + totoCommand);
		}
		
		if (result.hasErrors()) {
		return "totoDetail";
		}
		
		// DB�� ����
		matchService.insertToto(totoCommand);
		
		return "redirect:/match/sportsToto.do";
	}*/
	
}
