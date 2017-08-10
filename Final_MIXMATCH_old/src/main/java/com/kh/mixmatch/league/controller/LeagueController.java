package com.kh.mixmatch.league.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.mixmatch.league.domain.LeagueCommand;
import com.kh.mixmatch.league.service.LeagueService;
import com.kh.mixmatch.util.PagingUtil;

@Controller
public class LeagueController {

	private Logger log = Logger.getLogger(this.getClass());
	
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Resource LeagueService leagueService;
	
	@ModelAttribute("leagueCommand")
	public LeagueCommand initLeagueCommand() {
		return new LeagueCommand();
	}
	
	@RequestMapping("/league/leagueList.do")
	public ModelAndView leagueListForm(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
									   @RequestParam(value="keyword",defaultValue="") String keyword,
									   @RequestParam(value="type",defaultValue="축구") String type) {				
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("keyword", keyword);
		
		
		int count = leagueService.getRowCount(map);
		
		if (log.isDebugEnabled()) {
			log.debug("<<type>> : " + type);
			log.debug("<<pageNum>> : " + currentPage);
			log.debug("<<keyword>> : " + keyword);
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page = new PagingUtil(null, keyword, currentPage, count, rowCount, pageCount, "leagueList.do");
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<LeagueCommand> list = null;
		if (count > 0) {
			list = leagueService.leagueList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("leagueList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("type", type);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	// 리그등록폼
	@RequestMapping(value="/league/leagueInsert.do", method=RequestMethod.GET)
	public ModelAndView leagueInsertForm() {
		ModelAndView mav = new ModelAndView();
		
		return mav; 
	}
	
	// 리그등록
	@RequestMapping(value="/league/leagueInsert.do", method=RequestMethod.POST)
	public String leagueInsertSubmit() {
		return "redirect:/league/leagueList.do";
	}
	
}
