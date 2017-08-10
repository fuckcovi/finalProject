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
import com.kh.mixmatch.match.service.TotoService;
import com.kh.mixmatch.team.domain.TeamCommand;

@Controller
public class MatchController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MatchService matchService;
	@Resource
	private TotoService totoService;
	
	// 留ㅼ튂蹂대뱶
	@RequestMapping("/match/matchBoard.do")
	public ModelAndView matchBoardForm(@RequestParam(value="type", defaultValue="異뺢뎄") String type,
									   HttpSession session) {				
		// 醫낅ぉ 諛쏆븘�삤湲�
		String board = "match";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("board", board);
		
		// 醫낅ぉ蹂� 寃뚯떆湲� �닔 移댁슫�듃
		int count = matchService.getRowCount(map);
		
		if (log.isDebugEnabled()) {
			log.debug("<<留ㅼ튂蹂대뱶 type>> : " + type);		
			log.debug("<<留ㅼ튂蹂대뱶 count>> : " + count);
		}
		
		// 由ъ뒪�듃�뿉 ���옣
		List<MatchCommand> list = null;
		if (count > 0) {
			map.put("type", type);		
			list = matchService.matchList(map);
		}
		
		// �쑀�� ���씠由� 諛쏆븘�삤湲�
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
	
	// �뒪肄붿뼱蹂대뱶
	@RequestMapping("/match/scoreBoard.do")
	public ModelAndView scoreBoardForm(@RequestParam(value="type", defaultValue="異뺢뎄") String type,
									   HttpSession session) {			
		// 醫낅ぉ 諛쏆븘�삤湲�
		String board = "score";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("board", board);
		
		// 醫낅ぉ蹂� 寃뚯떆湲� �닔 移댁슫�듃
		int count = matchService.getRowCount(map);
		
		if (log.isDebugEnabled()) {
			log.debug("<<�뒪肄붿뼱蹂대뱶 type>> : " + type);
			log.debug("<<�뒪肄붿뼱蹂대뱶 count>> : " + count);
		}
		
		// 由ъ뒪�듃�뿉 ���옣
		List<MatchCommand> list = null;
		if (count > 0) {
			map.put("type", type);		
			list = matchService.matchList(map);
		}
		
		// �쑀�� ���씠由� 諛쏆븘�삤湲�
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
	
	// �� �씠誘몄� 異쒕젰
	@RequestMapping("/match/matchImageView.do")
	public ModelAndView matchImageView(@RequestParam("t_name") String t_name) {
		TeamCommand team = matchService.getTeam(t_name);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile",team.getT_logo());
		mav.addObject("filename", team.getT_logo_name());
		
		return mav;
	}
	
	// 留ㅼ튂�벑濡앺뤌
	@RequestMapping(value="/match/matchInsert.do", method=RequestMethod.GET)
	public ModelAndView matchInsertForm(HttpSession session, Model model) {
		// �쑀�� ���씠由� 諛쏆븘�삤湲�
		String id = (String) session.getAttribute("user_id");
		List<String> t_name = matchService.getTeamList(id);
		
		if (log.isDebugEnabled()) {
			log.debug("<<留ㅼ튂�벑濡앺뤌 t_name>> : " + t_name);
		}
		
		MatchCommand matchCommand = new MatchCommand();
		
		List<TeamCommand> teamCommand = matchService.getTeamType2(id);
		
		ArrayList<String> area = new ArrayList<String>();
		area.add("�꽌�슱");
		area.add("�씤泥�");
		area.add("寃쎄린");
		area.add("媛뺤썝");
		area.add("���쟾");
		area.add("異⑸턿");
		area.add("異⑸궓");
		area.add("愿묒＜");
		area.add("�쟾遺�");
		area.add("�쟾�궓");
		area.add("��援�");
		area.add("�슱�궛");
		area.add("寃쎈턿");
		area.add("寃쎈궓");
		area.add("遺��궛");
		area.add("�젣二�");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("matchInsert");
		mav.addObject("match", matchCommand);
		mav.addObject("team", teamCommand);
		mav.addObject("teamList", t_name);
		mav.addObject("area", area);
		
		return mav;
	}
	
	// 留ㅼ튂�벑濡�
	@RequestMapping(value="/match/matchInsert.do", method=RequestMethod.POST)
	public String matchInsertSubmit(@ModelAttribute("match") @Valid MatchCommand matchCommand,
									BindingResult result, HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("<<留ㅼ튂�벑濡� matchCommand>> : " + matchCommand);
		}
		
		if (result.hasErrors()) {
			return "matchInsert";
		}
		
		String[] array = matchCommand.getT_name().split(":");
		matchCommand.setT_name(array[0]);
		matchCommand.setM_type(array[1]);
		
		// 留ㅼ튂�벑濡�
		matchService.insertMatch(matchCommand);
		
		return "redirect:/match/matchBoard.do";
	}
	
	// 留ㅼ튂 �긽�꽭蹂닿린
	@RequestMapping("/match/matchDetail.do")
	public ModelAndView matchDetailForm(@RequestParam("m_seq") int m_seq,
										Model model, HttpSession session) {		
		if (log.isDebugEnabled()) {
			log.debug("<<留ㅼ튂 �긽�꽭蹂닿린 m_seq>> : " + m_seq);
		}
		
		// �쑀�� ���씠由� 諛쏆븘�삤湲�
		String id = (String) session.getAttribute("user_id");
		List<String> t_name = matchService.getTeamList(id);
		
		// 湲�踰덊샇(m_seq)�� �씪移섑븯�뒗 �젅肄붾뱶 �꽑�깮
		MatchCommand match = matchService.selectMatch(m_seq); 
		 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("m_seq", m_seq);
		
		List<String> teamCommand = matchService.getTeamType(map);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("matchDetail");
		mav.addObject("match", match);
		mav.addObject("team", teamCommand);
		mav.addObject("t_name", t_name);
		
		return mav;
	}
	
	// 留ㅼ튂�궘�젣
	@RequestMapping("/match/matchDelete.do")
	public String matchDeleteSubmit(@RequestParam("m_seq") int m_seq) {
		if (log.isDebugEnabled()) {
			log.debug("<<留ㅼ튂�궘�젣 m_seq>> : " + m_seq);
		}
		
		// 留ㅼ튂�궘�젣
		matchService.deleteMatch(m_seq);
		
		return "redirect:/match/matchBoard.do";
	}
	
	// 留ㅼ튂�떊泥�
	@RequestMapping("/match/challengerUpdate.do")
	public String challengerUpdateSubmit(@ModelAttribute("match") @Valid MatchCommand matchCommand,
										 BindingResult result, HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("<<留ㅼ튂�떊泥� matchCommand>> : " + matchCommand);
		}
		
		if (result.hasErrors()) {
			return "matchInsert";
		}
		
		// 留ㅼ튂�벑濡�
		matchService.updateChallenger(matchCommand);
		
		return "redirect:/match/matchBoard.do";
	}
	
	// 留ㅼ튂�닔�젙�뤌
	@RequestMapping(value="/match/matchUpdate.do", method=RequestMethod.GET)
	public String matchUpdateForm(@RequestParam("m_seq") int m_seq,
								  HttpSession session, Model model) {		
		// �쑀�� ���씠由� 諛쏆븘�삤湲�
		String id = (String) session.getAttribute("user_id");
		List<String> t_name = matchService.getTeamList(id);
		
		if (log.isDebugEnabled()) {
			log.debug("<<留ㅼ튂�닔�젙�뤌 m_seq>> : " + m_seq);
			log.debug("<<留ㅼ튂�닔�젙�뤌 t_name>> : " + t_name);
		}
		
		ArrayList<String> type = new ArrayList<String>();
		type.add("異뺢뎄");
		type.add("�빞援�");
		type.add("�냽援�");
		
		ArrayList<String> area = new ArrayList<String>();
		area.add("�꽌�슱");
		area.add("�씤泥�");
		area.add("寃쎄린");
		area.add("媛뺤썝");
		area.add("���쟾");
		area.add("異⑸턿");
		area.add("異⑸궓");
		area.add("愿묒＜");
		area.add("�쟾遺�");
		area.add("�쟾�궓");
		area.add("��援�");
		area.add("�슱�궛");
		area.add("寃쎈턿");
		area.add("寃쎈궓");
		area.add("遺��궛");
		area.add("�젣二�");
		
		// 湲�踰덊샇(m_seq)�� �씪移섑븯�뒗 �젅肄붾뱶 �꽑�깮
		MatchCommand matchCommand = matchService.selectMatch(m_seq);
		model.addAttribute("match", matchCommand);
		model.addAttribute("teamList", t_name);
		model.addAttribute("type", type);
		model.addAttribute("area", area);
		
		return "matchUpdate";
	}
	
	// 留ㅼ튂�닔�젙
	@RequestMapping(value="/match/matchUpdate.do", method=RequestMethod.POST)
	public String matchUpdateSubmit(@ModelAttribute("match") @Valid MatchCommand matchCommand,
									BindingResult result, HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("<<留ㅼ튂�닔�젙 matchCommand>> : " + matchCommand);
		}
		
		if (result.hasErrors()) {
			return "matchUpdate";
		}
		
		// 留ㅼ튂�닔�젙
		matchService.updateMatch(matchCommand);
		
		return "redirect:/match/matchBoard.do";
	}
	
	// �젏�닔蹂닿린
	@RequestMapping("/match/scoreDetail.do")
	public ModelAndView scoreDetailForm(@RequestParam("m_seq") int m_seq) {
		if (log.isDebugEnabled()) {
			log.debug("<<�젏�닔蹂닿린 m_seq>> : " + m_seq);
		}
		
		// 湲�踰덊샇(m_seq)�� �씪移섑븯�뒗 �젅肄붾뱶 �꽑�깮
		MatchCommand match = matchService.selectMatch(m_seq);
		
		// �� �씠由� 媛��졇�삤湲�
		TeamCommand t_name = matchService.getTeam(match.getT_name());
		TeamCommand m_challenger = matchService.getTeam(match.getM_challenger());
		
		// 踰좏똿 由ъ뒪�듃
		List<TotoCommand> list = totoService.totoList(m_seq);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("scoreDetail");
		mav.addObject("match", match);
		mav.addObject("t_name", t_name.getT_logo_name());
		mav.addObject("m_challenger", m_challenger.getT_logo_name());
		mav.addObject("list", list);
		
		return mav;
	}
	
	// 寃곌낵�벑濡앺뤌
	@RequestMapping(value="/match/scoreUpdate.do", method=RequestMethod.GET)
	public String updateScoreForm(@RequestParam("m_seq") int m_seq, Model model) {
		if (log.isDebugEnabled()) {
			log.debug("<<寃곌낵�벑濡앺뤌 m_seq>> : " + m_seq);
		}
		
		// 湲�踰덊샇(m_seq)�� �씪移섑븯�뒗 �젅肄붾뱶 �꽑�깮
		MatchCommand matchCommand = matchService.selectMatch(m_seq);
		
		// MVP硫ㅻ쾭 �꽑�깮�쓣 �쐞�븳 �� 硫ㅻ쾭 媛��졇�삤湲�
		List<String> mvpMember = matchService.getMvpMember(m_seq);
		
		model.addAttribute("match", matchCommand);
		model.addAttribute("mvpMember", mvpMember);
		
		return "scoreUpdate";
	}
	
	// 寃곌낵�벑濡�
	@RequestMapping(value="/match/scoreUpdate.do", method=RequestMethod.POST)
	public String updateScoreSubmit(@ModelAttribute("match") @Valid MatchCommand matchCommand,
									BindingResult result, HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("<<寃곌낵�벑濡� matchCommand>> : " + matchCommand);
		}
		
		// 寃곌낵�벑濡�
		matchService.updateScore(matchCommand);
		
		// MVP 硫ㅻ쾭 �룷�씤�듃 利앷�
		String id = matchCommand.getM_mvp();
		totoService.updateMemberPoint(id);
		
		// �� �쟾�쟻 利앷�, �룷�씤�듃 利앷�
		int home = matchCommand.getM_home();
		int away = matchCommand.getM_away();
		
		String t_home = matchCommand.getT_name();
		String t_away = matchCommand.getM_challenger();
		
		if (home > away) {
			totoService.updateTeamWin(t_home);
			totoService.updateTeamLose(t_away);
			totoService.updatePointWin(t_home);
			totoService.updatePointLose(t_away);
			System.out.println("���썝 �룷�씤�듃 利앷� �셿猷�");
			
			// 踰좏똿�븳 �쉶�썝 �룷�씤�듃 利앷�
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("team", matchCommand.getT_name());
			map.put("score", matchCommand.getM_home());
			map.put("m_seq", matchCommand.getM_seq());
			ArrayList<String> teamList = totoService.totoTeamList(map);
			ArrayList<String> allList = totoService.totoAllList(map);
			ArrayList<String> failList = totoService.totoFailList(map);
			System.out.println("teamList" + teamList);
			System.out.println("allList" + allList);
			System.out.println("failList" + failList);
						
			// �씠湲댄�留� 留욎텣 硫ㅻ쾭媛� 踰좏똿�븳 �룷�씤�듃 媛��졇�삤湲�
			ArrayList<Integer> teamPoint = totoService.totoTeamPoint(map);
			System.out.println("teamPoint" + teamPoint);
			// �씠湲댄�怨� �젏�닔瑜� 留욎텣 硫ㅻ쾭媛� 踰좏똿�븳 �룷�씤�듃 媛��졇�삤湲�
			ArrayList<Integer> allPoint = totoService.totoAllPoint(map);
			System.out.println("allPoint" + allPoint);
			// 踰좏똿 �떎�뙣�븳 硫ㅻ쾭 �룷�씤�듃 媛��졇�삤湲�
			ArrayList<Integer> failPoint = totoService.totoFailPoint(map);
			System.out.println("failPoint" + failPoint);
			// 諛곕떦瑜� 媛��졇�삤湲�
			double t_rate = totoService.totoRate(map);
			System.out.println("t_rate=" + t_rate);
					
			if (teamList != null) {
				for (int i = 0; i < teamList.size(); i++) {
					int point = (int) (teamPoint.get(i) * t_rate);
					Map<String, Object> teamMap = new HashMap<String, Object>();
					teamMap.put("point", point);
					System.out.println("teamPoint.get("+i+")="+point);
					teamMap.put("teamList", teamList.get(i));
					System.out.println("teamList.get("+i+")="+teamList.get(i));
					totoService.upPointTeam(teamMap);
					System.out.println("teamList 踰좏똿 �룷�씤�듃 利앷� �셿猷�");
				}
			}
						
			if (allList != null) {
				for (int i = 0; i < allList.size(); i++) {
					int point = (int) (allPoint.get(i) * t_rate * 2);
					Map<String, Object> allMap = new HashMap<String, Object>();
					allMap.put("point", point);
					System.out.println("allPoint.get("+i+")="+point);
					allMap.put("allList", allList.get(i));
					System.out.println("allList.get("+i+")="+allList.get(i));
					totoService.upPointAll(allMap);
					System.out.println("allList 踰좏똿 �룷�씤�듃 利앷� �셿猷�");
				}	
			}	
			
			if (failList != null) {
				for (int i = 0; i < failList.size(); i++) {
					int point = failPoint.get(i);
					Map<String, Object> failMap = new HashMap<String, Object>();
					failMap.put("point", point);
					System.out.println("failPoint.get("+i+")="+point);
					failMap.put("failList", failList.get(i));
					System.out.println("failList.get("+i+")="+failList.get(i));
					totoService.downPoint(failMap);
					System.out.println("failList 踰좏똿 �룷�씤�듃 媛먯냼 �셿猷�");
				}
			}
		} else if (home == away) {
			totoService.updateTeamDraw(matchCommand.getT_name());
			totoService.updateTeamDraw(matchCommand.getM_challenger());
			totoService.updatePointDraw(t_home);
			totoService.updatePointDraw(t_away);
			
			// 踰좏똿�븳 �쉶�썝 �룷�씤�듃 利앷�
			totoService.totoDraw(matchCommand);
			
		} else if (home < away) {
			totoService.updateTeamLose(matchCommand.getT_name());
			totoService.updateTeamWin(matchCommand.getM_challenger());
			totoService.updatePointWin(t_away);
			totoService.updatePointLose(t_home);
			System.out.println("���썝 �룷�씤�듃 利앷� �셿猷�");
			
			// 踰좏똿�븳 �쉶�썝 �룷�씤�듃 利앷�
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("team", matchCommand.getM_challenger());
			map.put("score", matchCommand.getM_away());
			map.put("m_seq", matchCommand.getM_seq());
			ArrayList<String> teamList = totoService.totoTeamList(map);
			ArrayList<String> allList = totoService.totoAllList(map);
			ArrayList<String> failList = totoService.totoFailList(map);
			System.out.println("teamList" + teamList);
			System.out.println("allList" + allList);
			System.out.println("failList" + failList);
			
			// �씠湲댄�留� 留욎텣 硫ㅻ쾭媛� 踰좏똿�븳 �룷�씤�듃 媛��졇�삤湲�
			ArrayList<Integer> teamPoint = totoService.totoTeamPoint(map);
			System.out.println("teamPoint" + teamPoint);
			// �씠湲댄�怨� �젏�닔瑜� 留욎텣 硫ㅻ쾭媛� 踰좏똿�븳 �룷�씤�듃 媛��졇�삤湲�
			ArrayList<Integer> allPoint = totoService.totoAllPoint(map);
			System.out.println("allPoint" + allPoint);
			// 踰좏똿 �떎�뙣�븳 硫ㅻ쾭 �룷�씤�듃 媛��졇�삤湲�
			ArrayList<Integer> failPoint = totoService.totoFailPoint(map);
			System.out.println("failPoint" + failPoint);
			// 諛곕떦瑜� 媛��졇�삤湲�
			double t_rate = totoService.totoRate(map);
			System.out.println("t_rate=" + t_rate);
			
			if (teamList != null) {
				for (int i = 0; i < teamList.size(); i++) {
					int point = (int) (teamPoint.get(i) * t_rate);
					Map<String, Object> teamMap = new HashMap<String, Object>();
					teamMap.put("point", point);
					System.out.println("teamPoint.get("+i+")="+point);
					teamMap.put("teamList", teamList.get(i));
					System.out.println("teamList.get("+i+")="+teamList.get(i));
					totoService.upPointTeam(teamMap);
					System.out.println("teamList 踰좏똿 �룷�씤�듃 利앷� �셿猷�");
				}
			}
			
			if (allList != null) {
				for (int i = 0; i < allList.size(); i++) {
					int point = (int) (allPoint.get(i) * t_rate * 2);
					Map<String, Object> allMap = new HashMap<String, Object>();
					allMap.put("point", point);
					System.out.println("allPoint.get("+i+")="+point);
					allMap.put("allList", allList.get(i));
					System.out.println("allList.get("+i+")="+allList.get(i));
					totoService.upPointAll(allMap);
					System.out.println("allList 踰좏똿 �룷�씤�듃 利앷� �셿猷�");
				}	
			}	
			
			if (failList != null) {
				for (int i = 0; i < failList.size(); i++) {
					int point = failPoint.get(i);
					Map<String, Object> failMap = new HashMap<String, Object>();
					failMap.put("point", point);
					System.out.println("failPoint.get("+i+")="+point);
					failMap.put("failList", failList.get(i));
					System.out.println("failList.get("+i+")="+failList.get(i));
					totoService.downPoint(failMap);
					System.out.println("failList 踰좏똿 �룷�씤�듃 媛먯냼 �셿猷�");
				}
			}
		}
		
		return "redirect:/match/scoreBoard.do";
	}
	
}
