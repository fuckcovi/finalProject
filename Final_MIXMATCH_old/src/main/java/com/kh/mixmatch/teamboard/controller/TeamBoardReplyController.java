package com.kh.mixmatch.teamboard.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.mixmatch.teamboard.domain.TeamBoardReplyCommand;
import com.kh.mixmatch.teamboard.service.TeamBoardService;
import com.kh.mixmatch.util.PagingUtil;

@Controller
public class TeamBoardReplyController {

	private Logger log = Logger.getLogger(this.getClass());
	
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Resource
	private TeamBoardService teamBoardService;
	
	@RequestMapping("teamBoardListReply.do")
	@ResponseBody
	public Map<String, Object> teamBoardListReplyProcess(@RequestParam(value="pageNum", defaultValue="1") int currentPage, @RequestParam("gt_seq") int gt_seq) {
		if (log.isDebugEnabled()) {
			log.debug("<<currentPage>> : " + currentPage);
			log.debug("<<gt_seq>> : " + gt_seq);
		}
	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gt_seq", gt_seq);
		
		int count = teamBoardService.getRowCountReply(map);
		
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, null);
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<TeamBoardReplyCommand> list = null;
		if (count > 0) {
			list = teamBoardService.listReply(map);
		} else {
			list = Collections.emptyList();
		}
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("count", count);
		mapJson.put("rowCount", rowCount);
		mapJson.put("list", list);
		
		return mapJson;
	}
	
	@RequestMapping("teamBoardWriteReply.do")
	@ResponseBody
	public Map<String, String> teamBoardWriteReplyProcess(TeamBoardReplyCommand teamBoardReplyCommand, HttpSession session, HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("<<teamBoardReplyCommand>> : " + teamBoardReplyCommand);
		}
		
		Map<String, String> map = new HashMap<String, String>();
		
		String user_id = (String)session.getAttribute("user_id");
		if (user_id == null) {
			map.put("result",  "logout");
		} else {
			//ip등록
			teamBoardReplyCommand.setIp(request.getRemoteAddr());
			
			//로그인 됨, 댓글 등록
			teamBoardService.insertReply(teamBoardReplyCommand);
			map.put("result", "success");
		}
		return map;
	}
	
	@RequestMapping("teamBoardUpdateReply.do")
	@ResponseBody
	public Map<String, String> teamBoardUpdateReplyProcess(TeamBoardReplyCommand teamBoardReplyCommand, HttpSession session, HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("<<teamBoardReplyCommand>> : " + teamBoardReplyCommand);
		}
		
Map<String,String> map = new HashMap<String,String>();
		
		String user_id = (String)session.getAttribute("user_id");
		
		if(user_id==null){
			//로그인이 안 되어있는 경우
			map.put("result", "logout");
		}else if(user_id!=null && user_id.equals(teamBoardReplyCommand.getId())){
			//로그인 아이디와 작성자 아이디 일치
			
			//ip 등록
			teamBoardReplyCommand.setIp(request.getRemoteAddr());
			
			//댓글 수정
			teamBoardService.updateReply(teamBoardReplyCommand);
			map.put("result", "success");
		}else{
			//로그인 아이디와 작성자 아이디 불일치
			map.put("result", "wrongAccess");
		}
		
		return map;
	}

	@RequestMapping("teamBoardDeleteReply.do")
	@ResponseBody
	public Map<String,String> process(
			@RequestParam("gtre_no") int gtre_no,
			@RequestParam("id") String id,
			HttpSession session){
		
		if(log.isDebugEnabled()){
			log.debug("<<gtre_no>> : " + gtre_no);
			log.debug("<<id>> : " + id);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			//로그인이 되어있지 않음
			map.put("result", "logout");
		}else if(user_id!=null && user_id.equals(id)){
			//로그인 되어 있고 로그인한 아이디와 작성자 아이디 일치
			teamBoardService.deleteReply(gtre_no);
			map.put("result", "success");
		}else{
			map.put("result", "wrongAccess");
		}
		
		return map;
	}
	
}
