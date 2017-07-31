package com.kh.mixmatch.match.controller;

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
import com.kh.mixmatch.match.service.MatchService;
import com.kh.mixmatch.util.PagingUtil;

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
			log.debug("<<type>> : " + type);		
			log.debug("<<count>> : " + count);
		}
		
		// 리스트에 저장
		List<MatchCommand> list = null;
		if (count > 0) {
			map.put("type", type);		
			list = matchService.matchList(map);
		}
		
		// 유저 팀이름 받아오기
		String id = (String) session.getAttribute("user_id");
		String t_name = matchService.getTeamName(id);
		
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
			log.debug("<<type>> : " + type);
			log.debug("<<count>> : " + count);
		}
		
		// 리스트에 저장
		List<MatchCommand> list = null;
		if (count > 0) {
			map.put("type", type);		
			list = matchService.matchList(map);
		}
		
		// 유저 팀이름 받아오기
		String id = (String) session.getAttribute("user_id");
		String t_name = matchService.getTeamName(id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("scoreBoard");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("type", type);
		mav.addObject("t_name", t_name);
		
		return mav;
	}
	
	// 매치등록폼
	@RequestMapping(value="/match/matchInsert.do", method=RequestMethod.GET)
	public ModelAndView matchInsertForm(HttpSession session, Model model) {
		// 유저 팀이름 받아오기
		String id = (String) session.getAttribute("user_id");
		String t_name = matchService.getTeamName(id);
		
		// 받아온 팀이름을 matchCommand에 저장
		MatchCommand matchCommand = new MatchCommand();
		matchCommand.setT_name(t_name);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("matchInsert");
		mav.addObject("match", matchCommand);
		
		return mav;
	}
	
	// 매치등록
	@RequestMapping(value="/match/matchInsert.do", method=RequestMethod.POST)
	public String matchInsertSubmit(@ModelAttribute("match") @Valid MatchCommand matchCommand,
									BindingResult result, HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("<<matchCommand>> : " + matchCommand);
		}
		
		if (result.hasErrors()) {
			return "matchInsert";
		}
		
		// 매치등록
		matchService.insertMatch(matchCommand);
		
		return "redirect:/match/matchBoard.do";
	}
	
	// 매치 상세보기
	@RequestMapping("/match/matchDetail.do")
	public ModelAndView matchDetailForm(@RequestParam("m_seq") int m_seq, HttpSession session) {		
		if (log.isDebugEnabled()) {
			log.debug("<<m_seq>> : " + m_seq);
		}
		
		// 유저 팀이름 받아오기
		String id = (String) session.getAttribute("user_id");
		String t_name = matchService.getTeamName(id);
		
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
			log.debug("<<m_seq>> : " + m_seq);
		}
		
		// 매치삭제
		matchService.deleteMatch(m_seq);
		
		return "redirect:/match/matchBoard.do";
	}
	
	// 매치신청
	@RequestMapping("/match/challengerUpdate.do")
	public String challengerUpdateSubmit(@RequestParam("m_seq") int m_seq, HttpSession session) {
		// 유저 팀이름 받아오기
		String id = (String) session.getAttribute("user_id");
		String t_name = matchService.getTeamName(id);
		
		// 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m_seq", m_seq);
		map.put("t_name", t_name);
		
		// m_challenger에 받아온 팀이름 저장
		matchService.challengerUpdate(map);
		
		return "redirect:/match/matchBoard.do";
	}
	
	// 매치수정폼
	@RequestMapping(value="/match/matchUpdate.do", method=RequestMethod.GET)
	public String matchUpdateForm(@RequestParam("m_seq") int m_seq, Model model) {
		// 글번호(m_seq)와 일치하는 레코드 선택
		MatchCommand matchCommand = matchService.selectMatch(m_seq);
		model.addAttribute("match", matchCommand);
		
		return "matchUpdate";
	}
	
	// 매치수정
	@RequestMapping(value="/match/matchUpdate.do", method=RequestMethod.POST)
	public String updateMatchSubmit(@ModelAttribute("command") @Valid MatchCommand matchCommand,
									BindingResult result, HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("<<matchCommand>> : " + matchCommand);
		}
		
		if (result.hasErrors()) {
			return "matchUpdate";
		}
		
		matchService.updateMatch(matchCommand);
		
		return "redirect:/match/matchBoard.do";
	}
	
	// 점수보기
	@RequestMapping("/match/selectScore.do")
	public ModelAndView selectScoreForm(@RequestParam("m_seq") int m_seq) {
		if (log.isDebugEnabled()) {
			log.debug("<<m_seq>> : " + m_seq);
		}
		
		MatchCommand match = matchService.selectMatch(m_seq);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("selectScore");
		mav.addObject("match", match);
		
		return mav;
	}
	
	// 결과등록폼
	@RequestMapping(value="/match/updateScore.do", method=RequestMethod.GET)
	public String updateScoreForm(@RequestParam("m_seq") int m_seq, Model model) {
		MatchCommand matchCommand = matchService.selectMatch(m_seq);
		model.addAttribute("command", matchCommand);
		
		return "updateScore";
	}
	
	// 결과등록
	@RequestMapping(value="/match/updateScore.do", method=RequestMethod.POST)
	public String updateScoreSubmit(@ModelAttribute("command") @Valid MatchCommand matchCommand,
									BindingResult result, HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("<<matchCommand>> : " + matchCommand);
		}
		
		matchService.updateScore(matchCommand);
		
		return "redirect:/match/scoreBoard.do";
	}
	
}
