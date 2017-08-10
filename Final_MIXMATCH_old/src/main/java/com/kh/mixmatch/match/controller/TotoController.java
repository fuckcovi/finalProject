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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.mixmatch.match.domain.MatchCommand;
import com.kh.mixmatch.match.domain.TotoCommand;
import com.kh.mixmatch.match.service.MatchService;
import com.kh.mixmatch.match.service.TotoService;
import com.kh.mixmatch.team.domain.TeamCommand;

@Controller
public class TotoController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MatchService matchService;
	@Resource
	private TotoService totoService;
	
	@ModelAttribute("matchCommand")
	public MatchCommand initMatchCommand() {
		return new MatchCommand();
	}
	
	@ModelAttribute("totoCommand")
	public TotoCommand initTotoCommand() {
		return new TotoCommand();
	}
	
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
		
		int point = totoService.getPoint(id);
		
		// 베팅 리스트
		List<TotoCommand> list = totoService.totoList(m_seq);
		
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
		mav.addObject("point", point);
		mav.addObject("list", list);
			
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
		totoService.insertToto(totoCommand);
		
		// 베팅한 포인트 감소
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", totoCommand.getId());
		map.put("point", totoCommand.getT_point());
		totoService.downPoint(map);
		
		return "redirect:/match/totoInsert.do";
	}
	
}
