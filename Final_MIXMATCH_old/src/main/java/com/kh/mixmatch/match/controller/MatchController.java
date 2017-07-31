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
	public ModelAndView matchBoardForm(@RequestParam(value="pageNum", defaultValue="1") int currentPage,
									   @RequestParam(value="type", defaultValue="축구") String type,
									   HttpSession session) {
		String board = "match";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("board", board);
		
		int count = matchService.getRowCount(map);
		if (log.isDebugEnabled()) {
			log.debug("<<pageNum>> : " + currentPage);
			log.debug("<<type>> : " + type);		
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page = new PagingUtil(type, board, currentPage, count, 10, 10, "match/matchBoard.do");
		
		List<MatchCommand> list = null;
		if (count > 0) {
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			map.put("type", type);
			
			list = matchService.matchList(map);
		}
		
		String id = (String) session.getAttribute("user_id");
		String t_name = matchService.getTeamName(id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("matchBoard");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("type", type);
		mav.addObject("t_name", t_name);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	// 스코어보드
	@RequestMapping("/match/scoreBoard.do")
	public ModelAndView scoreBoardForm(@RequestParam(value="pageNum", defaultValue="1") int currentPage,
									   @RequestParam(value="type", defaultValue="축구") String type) {		
		String board = "score";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("board", board);
		
		int count = matchService.getRowCount(map);
		if (log.isDebugEnabled()) {
			log.debug("<<pageNum>> : " + currentPage);
			log.debug("<<type>> : " + type);
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page = new PagingUtil(type, board, currentPage, count, 10, 10, "match/scoreBoard.do");
		
		List<MatchCommand> list = null;
		if (count > 0) {
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			map.put("type", type);
			
			list = matchService.matchList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("scoreBoard");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("type", type);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	// 매치등록폼
	@RequestMapping(value="/match/matchInsert.do", method=RequestMethod.GET)
	public ModelAndView matchInsertForm(HttpSession session, Model model) {
		String id = (String) session.getAttribute("user_id");
		String t_name = matchService.getTeamName(id);
		
		MatchCommand command = new MatchCommand();
		command.setT_name(t_name);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("matchInsert");
		mav.addObject("command", command);
		
		return mav;
	}
	
	// 매치등록
	@RequestMapping(value="/match/matchInsert.do", method=RequestMethod.POST)
	public String matchInsertSubmit(@ModelAttribute("command") @Valid MatchCommand matchCommand,
									BindingResult result, HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("<<matchCommand>> : " + matchCommand);
		}
		
		if (result.hasErrors()) {
			return "matchInsert";
		}
		
		matchService.insertMatch(matchCommand);
		
		return "redirect:/match/matchBoard.do";
	}
	
	// 매치 상세보기
	@RequestMapping("/match/matchDetail.do")
	public ModelAndView matchDetail(@RequestParam("m_seq") int m_seq,
									HttpSession session) {
		String id = (String) session.getAttribute("user_id");
		String t_name = matchService.getTeamName(id);
		
		if (log.isDebugEnabled()) {
			log.debug("<<m_seq>> : " + m_seq);
		}
		
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
		matchService.deleteMatch(m_seq);
		
		return "redirect:/match/matchBoard.do";
	}
	
	// 매치신청
	@RequestMapping("/match/updateChallenger.do")
	public String updateChallengerSubmit(@RequestParam("m_seq") int m_seq, HttpSession session) {
		String id = (String) session.getAttribute("user_id");
		String t_name = matchService.getTeamName(id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m_seq", m_seq);
		map.put("t_name", t_name);
		
		matchService.updateChallenger(map);
		
		return "redirect:/match/matchBoard.do";
	}
	
}
