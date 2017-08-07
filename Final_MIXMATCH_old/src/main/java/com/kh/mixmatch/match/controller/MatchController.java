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
	
	// 매치보드
	@RequestMapping("/match/matchBoard.do")
	public ModelAndView matchBoardForm(@RequestParam(value="type", defaultValue="축구") String type,
									   HttpSession session) {				
		// 종목 받아오기
		String board = "match";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("board", board);
		
		// 종목별 게시글 수 카운트
		int count = matchService.getRowCount(map);
		
		if (log.isDebugEnabled()) {
			log.debug("<<매치보드 type>> : " + type);		
			log.debug("<<매치보드 count>> : " + count);
		}
		
		// 리스트에 저장
		List<MatchCommand> list = null;
		if (count > 0) {
			map.put("type", type);		
			list = matchService.matchList(map);
		}
		
		// 유저 팀이름 받아오기
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
	
	// 스코어보드
	@RequestMapping("/match/scoreBoard.do")
	public ModelAndView scoreBoardForm(@RequestParam(value="type", defaultValue="축구") String type,
									   HttpSession session) {			
		// 종목 받아오기
		String board = "score";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("board", board);
		
		// 종목별 게시글 수 카운트
		int count = matchService.getRowCount(map);
		
		if (log.isDebugEnabled()) {
			log.debug("<<스코어보드 type>> : " + type);
			log.debug("<<스코어보드 count>> : " + count);
		}
		
		// 리스트에 저장
		List<MatchCommand> list = null;
		if (count > 0) {
			map.put("type", type);		
			list = matchService.matchList(map);
		}
		
		// 유저 팀이름 받아오기
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
	
	// 팀 이미지 출력
	@RequestMapping("/match/matchImageView.do")
	public ModelAndView matchImageView(@RequestParam("t_name") String t_name) {
		TeamCommand team = matchService.getTeam(t_name);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile",team.getT_logo());
		mav.addObject("filename", team.getT_logo_name());
		
		return mav;
	}
	
	// 매치등록폼
	@RequestMapping(value="/match/matchInsert.do", method=RequestMethod.GET)
	public ModelAndView matchInsertForm(HttpSession session, Model model) {
		// 유저 팀이름 받아오기
		String id = (String) session.getAttribute("user_id");
		List<String> t_name = matchService.getTeamList(id);
		
		if (log.isDebugEnabled()) {
			log.debug("<<매치등록폼 t_name>> : " + t_name);
		}
		
		MatchCommand matchCommand = new MatchCommand();
		
		List<TeamCommand> teamCommand = matchService.getTeamType(id);
		
		ArrayList<String> area = new ArrayList<String>();
		area.add("서울");
		area.add("인천");
		area.add("경기");
		area.add("강원");
		area.add("대전");
		area.add("충북");
		area.add("충남");
		area.add("광주");
		area.add("전북");
		area.add("전남");
		area.add("대구");
		area.add("울산");
		area.add("경북");
		area.add("경남");
		area.add("부산");
		area.add("제주");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("matchInsert");
		mav.addObject("match", matchCommand);
		mav.addObject("team", teamCommand);
		mav.addObject("teamList", t_name);
		mav.addObject("area", area);
		
		return mav;
	}
	
	// 매치등록
	@RequestMapping(value="/match/matchInsert.do", method=RequestMethod.POST)
	public String matchInsertSubmit(@ModelAttribute("match") @Valid MatchCommand matchCommand,
									BindingResult result, HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("<<매치등록 matchCommand>> : " + matchCommand);
		}
		
		if (result.hasErrors()) {
			return "matchInsert";
		}
		
		System.out.println(matchCommand.getT_name());
		
		String[] array = matchCommand.getT_name().split(":");
		matchCommand.setT_name(array[0]);
		matchCommand.setM_type(array[1]);
		
		// 매치등록
		matchService.insertMatch(matchCommand);
		
		return "redirect:/match/matchBoard.do";
	}
	
	// 매치 상세보기
	@RequestMapping("/match/matchDetail.do")
	public ModelAndView matchDetailForm(@RequestParam("m_seq") int m_seq,
										Model model, HttpSession session) {		
		if (log.isDebugEnabled()) {
			log.debug("<<매치 상세보기 m_seq>> : " + m_seq);
		}
		
		// 유저 팀이름 받아오기
		String id = (String) session.getAttribute("user_id");
		List<String> t_name = matchService.getTeamList(id);
		
		// 글번호(m_seq)와 일치하는 레코드 선택
		MatchCommand match = matchService.selectMatch(m_seq);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("matchDetail");
		mav.addObject("match", match);
		mav.addObject("t_name", t_name);
		
		return mav;
	}
	
	// 매치삭제
	@RequestMapping("/match/matchDelete.do")
	public String matchDeleteSubmit(@RequestParam("m_seq") int m_seq) {
		if (log.isDebugEnabled()) {
			log.debug("<<매치삭제 m_seq>> : " + m_seq);
		}
		
		// 매치삭제
		matchService.deleteMatch(m_seq);
		
		return "redirect:/match/matchBoard.do";
	}
	
	// 매치신청
	@RequestMapping("/match/challengerUpdate.do")
	public String challengerUpdateSubmit(@ModelAttribute("match") @Valid MatchCommand matchCommand,
										 BindingResult result, HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("<<매치신청 matchCommand>> : " + matchCommand);
		}
		
		if (result.hasErrors()) {
			return "matchInsert";
		}
		
		// 매치등록
		matchService.updateChallenger(matchCommand);
		
		return "redirect:/match/matchBoard.do";
	}
	
	// 매치수정폼
	@RequestMapping(value="/match/matchUpdate.do", method=RequestMethod.GET)
	public String matchUpdateForm(@RequestParam("m_seq") int m_seq,
								  HttpSession session, Model model) {		
		// 유저 팀이름 받아오기
		String id = (String) session.getAttribute("user_id");
		List<String> t_name = matchService.getTeamList(id);
		
		if (log.isDebugEnabled()) {
			log.debug("<<매치수정폼 m_seq>> : " + m_seq);
			log.debug("<<매치수정폼 t_name>> : " + t_name);
		}
		
		ArrayList<String> type = new ArrayList<String>();
		type.add("축구");
		type.add("야구");
		type.add("농구");
		
		ArrayList<String> area = new ArrayList<String>();
		area.add("서울");
		area.add("인천");
		area.add("경기");
		area.add("강원");
		area.add("대전");
		area.add("충북");
		area.add("충남");
		area.add("광주");
		area.add("전북");
		area.add("전남");
		area.add("대구");
		area.add("울산");
		area.add("경북");
		area.add("경남");
		area.add("부산");
		area.add("제주");
		
		// 글번호(m_seq)와 일치하는 레코드 선택
		MatchCommand matchCommand = matchService.selectMatch(m_seq);
		model.addAttribute("match", matchCommand);
		model.addAttribute("teamList", t_name);
		model.addAttribute("type", type);
		model.addAttribute("area", area);
		
		return "matchUpdate";
	}
	
	// 매치수정
	@RequestMapping(value="/match/matchUpdate.do", method=RequestMethod.POST)
	public String matchUpdateSubmit(@ModelAttribute("match") @Valid MatchCommand matchCommand,
									BindingResult result, HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("<<매치수정 matchCommand>> : " + matchCommand);
		}
		
		if (result.hasErrors()) {
			return "matchUpdate";
		}
		
		// 매치수정
		matchService.updateMatch(matchCommand);
		
		return "redirect:/match/matchBoard.do";
	}
	
	// 점수보기
	@RequestMapping("/match/scoreDetail.do")
	public ModelAndView scoreDetailForm(@RequestParam("m_seq") int m_seq) {
		if (log.isDebugEnabled()) {
			log.debug("<<점수보기 m_seq>> : " + m_seq);
		}
		
		// 글번호(m_seq)와 일치하는 레코드 선택
		MatchCommand match = matchService.selectMatch(m_seq);
		
		// 팀 이름 가져오기
		TeamCommand t_name = matchService.getTeam(match.getT_name());
		TeamCommand m_challenger = matchService.getTeam(match.getM_challenger());
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("scoreDetail");
		mav.addObject("match", match);
		mav.addObject("t_name", t_name.getT_logo_name());
		mav.addObject("m_challenger", m_challenger.getT_logo_name());
		
		return mav;
	}
	
	// 결과등록폼
	@RequestMapping(value="/match/scoreUpdate.do", method=RequestMethod.GET)
	public String updateScoreForm(@RequestParam("m_seq") int m_seq, Model model) {
		if (log.isDebugEnabled()) {
			log.debug("<<결과등록폼 m_seq>> : " + m_seq);
		}
		
		// 글번호(m_seq)와 일치하는 레코드 선택
		MatchCommand matchCommand = matchService.selectMatch(m_seq);
		
		// MVP멤버 선택을 위한 팀 멤버 가져오기
		List<String> mvpMember = matchService.getMvpMember(m_seq);
		
		model.addAttribute("match", matchCommand);
		model.addAttribute("mvpMember", mvpMember);
		
		return "scoreUpdate";
	}
	
	// 결과등록
	@RequestMapping(value="/match/scoreUpdate.do", method=RequestMethod.POST)
	public String updateScoreSubmit(@ModelAttribute("match") @Valid MatchCommand matchCommand,
									BindingResult result, HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("<<결과등록 matchCommand>> : " + matchCommand);
		}
		
		// 결과등록
		matchService.updateScore(matchCommand);
		
		// MVP 멤버 포인트 증가
		String id = matchCommand.getM_mvp();
		matchService.updateMemberPoint(id);
		
		// 팀 전적 증가, 포인트 증가
		int home = matchCommand.getM_home();
		int away = matchCommand.getM_away();
		
		String t_home = matchCommand.getT_name();
		String t_away = matchCommand.getM_challenger();
		
		if (home > away) {
			matchService.updateTeamWin(t_home);
			matchService.updateTeamLose(t_away);
			matchService.updatePointWin(t_home);
			matchService.updatePointLose(t_away);
			
			// 베팅한 회원 포인트 증가
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("team", matchCommand.getT_name());
			map.put("score", matchCommand.getM_home());
			ArrayList<String> teamList = matchService.totoTeamList(map);
			ArrayList<String> allList = matchService.totoAllList(map);
			
			if (teamList != null) {
				for (int i = 0; i < teamList.size(); i++) {
					matchService.upPointTeam(teamList.get(i));
				}	
			}
			
			if (allList != null) {
				for (int i = 0; i < allList.size(); i++) {
					matchService.upPointAll(allList.get(i));
				}	
			}			
		} else if (home == away) {
			matchService.updateTeamDraw(matchCommand.getT_name());
			matchService.updateTeamDraw(matchCommand.getM_challenger());
			matchService.updatePointDraw(t_home);
			matchService.updatePointDraw(t_away);
			
			// 베팅한 회원 포인트 증가
			matchService.totoDraw(matchCommand);
			
		} else if (home < away) {
			matchService.updateTeamLose(matchCommand.getT_name());
			matchService.updateTeamWin(matchCommand.getM_challenger());
			matchService.updatePointWin(t_away);
			matchService.updatePointLose(t_home);
			
			// 베팅한 회원 포인트 증가
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("team", matchCommand.getM_challenger());
			map.put("score", matchCommand.getM_away());
			ArrayList<String> teamList = matchService.totoTeamList(map);
			ArrayList<String> allList = matchService.totoAllList(map);
			
			if (teamList != null) {
				for (int i = 0; i < teamList.size(); i++) {
					matchService.upPointTeam(teamList.get(i));
				}	
			}
			
			if (allList != null) {
				for (int i = 0; i < allList.size(); i++) {
					matchService.upPointAll(allList.get(i));
				}	
			}			
		}
		
		return "redirect:/match/scoreBoard.do";
	}
	
	///////////////////////////////////승부예측///////////////////////////////////
	// 승부예측
	@RequestMapping("/match/sportsToto.do")
	public ModelAndView sportsTotoForm(@RequestParam(value="type", defaultValue="축구") String type) {			
		// 종목 받아오기
		String board = "toto";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("board", board);
			
		// 종목별 게시글 수 카운트
		int count = matchService.getRowCount(map);
			
		if (log.isDebugEnabled()) {
			log.debug("<<승부예측 type>> : " + type);
			log.debug("<<승부예측 count>> : " + count);
		}
		
		// 리스트에 저장
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
	
	// 베팅하기 폼
	@RequestMapping(value="/match/totoDetail.do")
	public ModelAndView totoDetailForm(@RequestParam("m_seq") int m_seq,
										Model model, HttpSession session) {		
		if (log.isDebugEnabled()) {
			log.debug("<<베팅하기 폼 m_seq>> : " + m_seq);
		}
			
		// 유저 팀이름 받아오기
		String id = (String) session.getAttribute("user_id");
		List<String> myteam = matchService.getTeamList(id);
			
		// 글번호(m_seq)와 일치하는 레코드 선택
		MatchCommand match = matchService.selectMatch(m_seq);
		
		// 팀 이름 가져오기
		TeamCommand t_name = matchService.getTeam(match.getT_name());
		TeamCommand m_challenger = matchService.getTeam(match.getM_challenger());
		
		// 경기수 구하기
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
	
	// 베팅하기
	@RequestMapping("/match/totoInsert.do")
	public String insertTotoSubmit(@ModelAttribute("toto") @Valid TotoCommand totoCommand,
								   BindingResult result, HttpServletRequest request) {
		if (log.isDebugEnabled()) {
		log.debug("<<베팅하기 totoCommand>> : " + totoCommand);
		}
		
		if (result.hasErrors()) {
		return "totoDetail";
		}
		
		// DB에 저장
		
		matchService.insertToto(totoCommand);
		
		return "redirect:/match/sportsToto.do";
	}
	
}
