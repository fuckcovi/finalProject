package com.kh.mixmatch.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.mixmatch.match.domain.MatchCommand;
import com.kh.mixmatch.match.service.MatchService;
import com.kh.mixmatch.notice.domain.NoticeCommand;
import com.kh.mixmatch.notice.service.NoticeService;
import com.kh.mixmatch.team.domain.TeamCommand;
import com.kh.mixmatch.team.service.TeamService;
import com.kh.mixmatch.util.PagingUtil;

@Controller
public class MainController {
	
	@Resource
	private NoticeService noticeService;
	@Resource
	private TeamService teamService;
	@Resource
	private MatchService matchService;
	
	@RequestMapping("/home.do")
	public ModelAndView mainPage(){
	// 공지사항 //
		Map<String, Object> map = new HashMap<String, Object>();
		// map에 아무것도 전달해주지 않으면서 단순 공지사항 글 수 개져옴
		int noticeCount = noticeService.getRowCount(map);
		List<NoticeCommand> noticeList = null;
		if(noticeCount>0){
			// 최근 글 5개만 리스트생성
			map.put("start", 1);
			map.put("end", 5);
			noticeList = noticeService.noticeList(map);
		}
		
	// 야구팀랭킹	- 승리 1~5위 리스트
		Map<String, Object> bmap = new HashMap<String, Object>();
		bmap.put("keyword","야구");
		bmap.put("keyfield","teamtype");
		int baseTeamCount = teamService.getTeamCount(bmap);
		bmap.put("start", 1);
		bmap.put("end",5);
		
		List<TeamCommand> baseTeamList = null;
		if(baseTeamCount > 0){
			bmap.put("order", "t_win");
			baseTeamList = teamService.listRank(bmap);
		}
	// 축구팀
		Map<String, Object> fmap = new HashMap<String, Object>();
		fmap.put("keyword","축구");
		fmap.put("keyfield","teamtype");
		int footTeamCount = teamService.getTeamCount(fmap);
		fmap.put("start", 1);
		fmap.put("end",5);
		
		List<TeamCommand> footTeamList = null;
		if(footTeamCount > 0){
			fmap.put("order", "t_win");
			footTeamList = teamService.listRank(fmap);
		}
	// 농구팀
		Map<String, Object> bkmap = new HashMap<String, Object>();
		bkmap.put("keyword","농구");
		bkmap.put("keyfield","teamtype");
		int basketTeamCount = teamService.getTeamCount(bkmap);
		bkmap.put("start", 1);
		bkmap.put("end",5);
		
		List<TeamCommand> basketTeamList = null;
		if(basketTeamCount > 0){
			bkmap.put("order", "t_win");
			basketTeamList = teamService.listRank(bkmap);
		}
		
	// 최근경기결과 - 야구
		Map<String, Object> matchmap = new HashMap<String, Object>();
		matchmap.put("type", "야구");
		matchmap.put("board", "score");
		
		int matchResultCount = matchService.getRowCount(matchmap);
		
		List<MatchCommand> matchResultlist = null;
		if (matchResultCount > 0) {
			matchResultlist = matchService.matchList(matchmap);
		}
		
		//////////////
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		mav.addObject("noticeCount",noticeCount);
		mav.addObject("noticeList",noticeList);
		mav.addObject("baseTeamCount",baseTeamCount);
		mav.addObject("baseTeamList",baseTeamList);
		mav.addObject("basketTeamCount",basketTeamCount);
		mav.addObject("basketTeamList",basketTeamList);
		mav.addObject("footTeamCount",footTeamCount);
		mav.addObject("footTeamList",footTeamList);
		
		mav.addObject("matchResultCount", matchResultCount);
		mav.addObject("matchResultlist", matchResultlist);
		return mav;
	}
}
