package com.kh.mixmatch.board.controller;

import java.util.Collections;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.mixmatch.board.domain.GBoardCommand;
import com.kh.mixmatch.board.domain.GBoardReplyCommand;
import com.kh.mixmatch.board.domain.GNoticeCommand;
import com.kh.mixmatch.board.domain.GNoticeReplyCommand;
import com.kh.mixmatch.board.domain.GTBoardCommand;
import com.kh.mixmatch.board.domain.GTBoardReplyCommand;
import com.kh.mixmatch.board.service.BoardService;
import com.kh.mixmatch.util.PagingUtil;

@Controller
public class BoardController {
private Logger log = Logger.getLogger(this.getClass());
	
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Resource
	private BoardService boardService;
	//자유게시판목록
	@RequestMapping("/board/Gboard.do")
	public ModelAndView Glistprocess(
			@RequestParam(value="pageNum",defaultValue="1")
			int currentPage,
			@RequestParam(value="keyfield",defaultValue="")
			String keyfield,
			@RequestParam(value="keyword",defaultValue="")
			String keyword){
		
		if(log.isDebugEnabled()){
			log.debug("<<pageNum>> : " + currentPage);
			log.debug("<<keyfield>> : " + keyfield);
			log.debug("<<keyword>> : " + keyword);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//총 글의 갯수 또는 검색된 글의 갯수
		//int count = boardService.GgetRowCount(map);
		
		if(log.isDebugEnabled()){
			log.debug("<<Gcount>> : " + "aaa");
		}
		
		/*if(log.isDebugEnabled()){
			log.debug("<<Gcount>> : " + count);
		}*/
		
		PagingUtil Gpage =
				new PagingUtil(keyfield,keyword,currentPage,
						count,rowCount,pageCount,"Gboard.do");
		
		map.put("start", Gpage.getStartCount());
		map.put("end", Gpage.getEndCount());
		
		List<GBoardCommand> Glist = null;
		if(count > 0){
			Glist = boardService.Glist(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("GboardList");
		mav.addObject("count",count);
		mav.addObject("list", Glist);
		mav.addObject("pagingHtml", Gpage.getPagingHtml());
		
		return mav;
	}
	//팀게시판목록
	@RequestMapping("/board/GTboard.do")
	public ModelAndView GTlistprocess(
			@RequestParam(value="pageNum",defaultValue="1")
			int currentPage,
			@RequestParam(value="keyfield",defaultValue="")
			String keyfield,
			@RequestParam(value="keyword",defaultValue="")
			String keyword){
		
		if(log.isDebugEnabled()){
			log.debug("<<pageNum>> : " + currentPage);
			log.debug("<<keyfield>> : " + keyfield);
			log.debug("<<keyword>> : " + keyword);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//총 글의 갯수 또는 검색된 글의 갯수
		int count = boardService.GTgetRowCount(map);
		
		if(log.isDebugEnabled()){
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page =
				new PagingUtil(keyfield,keyword,currentPage,
						count,rowCount,pageCount,"GTboard.do");
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<GTBoardCommand> GTlist = null;
		if(count > 0){
			GTlist = boardService.GTlist(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("GTboardList");
		mav.addObject("count",count);
		mav.addObject("list", GTlist);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	//공지사항목록
	@RequestMapping("/board/GNotice.do")
	public ModelAndView GNlistprocess(
			@RequestParam(value="pageNum",defaultValue="1")
			int currentPage,
			@RequestParam(value="keyfield",defaultValue="")
			String keyfield,
			@RequestParam(value="keyword",defaultValue="")
			String keyword){
		
		if(log.isDebugEnabled()){
			log.debug("<<pageNum>> : " + currentPage);
			log.debug("<<keyfield>> : " + keyfield);
			log.debug("<<keyword>> : " + keyword);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//총 글의 갯수 또는 검색된 글의 갯수
		int count = boardService.GNgetRowCount(map);
		
		if(log.isDebugEnabled()){
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page =
				new PagingUtil(keyfield,keyword,currentPage,
						count,rowCount,pageCount,"list.do");
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<GNoticeCommand> GNlist = null;
		if(count > 0){
			GNlist = boardService.GNlist(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardList");
		mav.addObject("count",count);
		mav.addObject("list", GNlist);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	//자유게시판댓글 
	@RequestMapping("/board/GlistReply.do")
	@ResponseBody
	public Map<String,Object> GlistReprocess(
			@RequestParam(value="pageNum",defaultValue="1")
			int currentPage,
			@RequestParam("seq") int seq){
		
		if(log.isDebugEnabled()){
			log.debug("<<currentPage>> : " + currentPage);
			log.debug("<<seq>> : " + seq);
		}
		
		Map<String,Object> map = 
				new HashMap<String,Object>();
		map.put("seq", seq);
		
		//총 글의 갯수
		int count = boardService.GgetRowCountReply(map);
		
		PagingUtil page = new PagingUtil(currentPage, 
				count, rowCount, pageCount,null);
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<GBoardReplyCommand> Glist = null;
		if(count > 0){
			Glist = boardService.GlistReply(map);
		}else{
			Glist = Collections.emptyList();
		}
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("count", count);
		mapJson.put("rowCount", rowCount);
		mapJson.put("list", Glist);
		
		return mapJson;
	}
	//팀게시판댓글
	@RequestMapping("/board/GTlistReply.do")
	@ResponseBody
	public Map<String,Object> GTlistReprocess(
			@RequestParam(value="pageNum",defaultValue="1")
			int currentPage,
			@RequestParam("seq") int seq){
		
		if(log.isDebugEnabled()){
			log.debug("<<currentPage>> : " + currentPage);
			log.debug("<<seq>> : " + seq);
		}
		
		Map<String,Object> map = 
				new HashMap<String,Object>();
		map.put("seq", seq);
		
		//총 글의 갯수
		int count = boardService.GTgetRowCountReply(map);
		
		PagingUtil page = new PagingUtil(currentPage, 
				count, rowCount, pageCount,null);
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<GTBoardReplyCommand> GTlist = null;
		if(count > 0){
			GTlist = boardService.GTlistReply(map);
		}else{
			GTlist = Collections.emptyList();
		}
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("count", count);
		mapJson.put("rowCount", rowCount);
		mapJson.put("list", GTlist);
		
		return mapJson;
	}
	
	//공지사항댓글
	@RequestMapping("/board/GNlistReply.do")
	@ResponseBody
	public Map<String,Object> GNlistReprocess(
			@RequestParam(value="pageNum",defaultValue="1")
			int currentPage,
			@RequestParam("seq") int seq){
		
		if(log.isDebugEnabled()){
			log.debug("<<currentPage>> : " + currentPage);
			log.debug("<<seq>> : " + seq);
		}
		
		Map<String,Object> map = 
				new HashMap<String,Object>();
		map.put("seq", seq);
		
		//총 글의 갯수
		int count = boardService.GNgetRowCountReply(map);
		
		PagingUtil page = new PagingUtil(currentPage, 
				count, rowCount, pageCount,null);
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<GNoticeReplyCommand> GNlist = null;
		if(count > 0){
			GNlist = boardService.GNlistReply(map);
		}else{
			GNlist = Collections.emptyList();
		}
		
		Map<String,Object> mapJson   = new HashMap<String,Object>();
		mapJson.put("count", count);
		mapJson.put("rowCount", rowCount);
		mapJson.put("list", GNlist);
		
		return mapJson;
	}
	//자유게시판글쓰기
	@RequestMapping(value="/board/GboardWrite.do",method=RequestMethod.GET)
	public String Ginsertform(HttpSession session, Model model){
		String id = (String)session.getAttribute("user_id");
		
		GBoardCommand command = new GBoardCommand();
		command.setId(id);
		model.addAttribute("command", command);
		
		return "GboardWrite";
	}
	
	@RequestMapping(value="/board/GboardWrite.do",method=RequestMethod.POST)
	public String Ginsertsubmit(@ModelAttribute("command")
	                     @Valid GBoardCommand boardCommand,
	                     BindingResult result,
	                     HttpServletRequest request){
		
		if(log.isDebugEnabled()){
			log.debug("<<boardCommand>> : " + boardCommand);
		}
		
		//유효성 체크
		if(result.hasErrors()){
			return "GboardWrite";
		}
		
		//ip 셋팅
		boardCommand.setIp(request.getRemoteAddr());
		
		//글쓰기
		boardService.Ginsert(boardCommand);
		
		return "redirect:/board/Gboard.do";
	}
	//팀게시판글쓰기
	@RequestMapping(value="/board/GTboardWrite.do",method=RequestMethod.GET)
	public String GTinsertform(HttpSession session, Model model){
		String id = (String)session.getAttribute("user_id");
		
		GTBoardCommand command = new GTBoardCommand();
		command.setId(id);
		model.addAttribute("command", command);
		
		return "TeamboardWrite";
	}
	
	@RequestMapping(value="/board/GTboardWrite.do",method=RequestMethod.POST)
	public String GTinsertsubmit(@ModelAttribute("command")
	                     @Valid GTBoardCommand boardCommand,
	                     BindingResult result,
	                     HttpServletRequest request){
		
		if(log.isDebugEnabled()){
			log.debug("<<boardCommand>> : " + boardCommand);
		}
		
		//유효성 체크
		if(result.hasErrors()){
			return "TeamboardWrite";
		}
		
		//ip 셋팅
		boardCommand.setIp(request.getRemoteAddr());
		
		//글쓰기
		boardService.GTinsert(boardCommand);
		
		return "redirect:/board/GTboard.do";
	}
	//공지사항글쓰기
	@RequestMapping(value="/board/GNoticeWrite.do",method=RequestMethod.GET)
	public String form(HttpSession session, Model model){
		String id = (String)session.getAttribute("user_id");
		
		GNoticeCommand command = new GNoticeCommand();
		command.setId(id);
		model.addAttribute("command", command);
		
		return "NoticeWrite";
	}
	
	@RequestMapping(value="/board/GNoticeWrite.do",method=RequestMethod.POST)
	public String GNinsertsubmit(@ModelAttribute("command")
	                     @Valid GNoticeCommand boardCommand,
	                     BindingResult result,
	                     HttpServletRequest request){
		
		if(log.isDebugEnabled()){
			log.debug("<<boardCommand>> : " + boardCommand);
		}
		
		//유효성 체크
		if(result.hasErrors()){
			return "boardWrite";
		}
		
		//ip 셋팅
		boardCommand.setIp(request.getRemoteAddr());
		
		//글쓰기
		boardService.GNinsert(boardCommand);
		
		return "redirect:/board/GNotice.do";
	}
	//자유게시판댓글쓰기
	@RequestMapping("/board/GboardWriteReply.do")
	@ResponseBody
	public Map<String,String> GinsertReprocess(GBoardReplyCommand 
			                          boardReplyCommand,
			                          HttpSession session,
			                          HttpServletRequest request){
		if(log.isDebugEnabled()){
			log.debug("<<boardReplyCommand>> : " 
		                             + boardReplyCommand);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			//로그인 안 됨
			map.put("result", "logout");
		}else{
			
			//ip등록
			boardReplyCommand.setRe_ip(request.getRemoteAddr());
			
			//로그인 됨, 댓글 등록
			boardService.GinsertReply(boardReplyCommand);
			map.put("result", "success");
		}
		return map;
	}
	//팀게시판댓글쓰기
	@RequestMapping("/board/GTboardWriteReply.do")
	@ResponseBody
	public Map<String,String> GTinsertReprocess(GTBoardReplyCommand 
			                          boardReplyCommand,
			                          HttpSession session,
			                          HttpServletRequest request){
		if(log.isDebugEnabled()){
			log.debug("<<boardReplyCommand>> : " 
		                             + boardReplyCommand);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			//로그인 안 됨
			map.put("result", "logout");
		}else{
			
			//ip등록
			boardReplyCommand.setRe_ip(request.getRemoteAddr());
			
			//로그인 됨, 댓글 등록
			boardService.GTinsertReply(boardReplyCommand);
			map.put("result", "success");
		}
		return map;
	}
	//공지사항댓글쓰기
	@RequestMapping("/board/GNoticeWriteReply.do")
	@ResponseBody
	public Map<String,String> GNinsertReprocess(GNoticeReplyCommand 
			                          boardReplyCommand,
			                          HttpSession session,
			                          HttpServletRequest request){
		if(log.isDebugEnabled()){
			log.debug("<<boardReplyCommand>> : " 
		                             + boardReplyCommand);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			//로그인 안 됨
			map.put("result", "logout");
		}else{
			
			//ip등록
			boardReplyCommand.setRe_ip(request.getRemoteAddr());
			
			//로그인 됨, 댓글 등록
			boardService.GNinsertReply(boardReplyCommand);
			map.put("result", "success");
		}
		return map;
	}
	//자유게시판수정
	@RequestMapping(value="/board/GboardUpdate.do",method=RequestMethod.GET)
	public String Gupdateform(@RequestParam("seq") int seq,
			           Model model){
		GBoardCommand boardCommand = boardService.GselectBoard(seq);
		model.addAttribute("command", boardCommand);
		
		return "boardModify";
	}
	
	@RequestMapping(value="/board/update.do",method=RequestMethod.POST)
	public String Gupdatesubmit(@ModelAttribute("command")
	                     @Valid GBoardCommand boardCommand,
	                     BindingResult result,
	                     HttpSession session,
	                     HttpServletRequest request) throws Exception{
		
		if(log.isDebugEnabled()){
			log.debug("<<boardCommand>> : " + boardCommand);
		}
		
		String user_id = (String)session.getAttribute("user_id");
		if(!user_id.equals(boardCommand.getId())){
			throw new Exception(
			"로그인한 아이디로 작성된 글이 아니기 때문에 수정할 수 없습니다.");
		}
		
		GBoardCommand board = boardService.GselectBoard(
				                     boardCommand.getGb_seq());
		
		if(result.hasErrors()){
			//원래 파일명 셋팅
			boardCommand.setGb_filename(board.getGb_filename());
			return "GboardModify";
		}
		
		//전송된 파일이 없을 경우
		if(boardCommand.getUpload().isEmpty()){
			//기존 정보 셋팅
			boardCommand.setGb_uploadfile(board.getGb_uploadfile());
			boardCommand.setGb_filename(board.getGb_filename());
		}
		
		//ip셋팅
		boardCommand.setIp(request.getRemoteAddr());
		
		//글수정
		boardService.Gupdate(boardCommand);
		
		return "redirect:/board/list.do";
	}
	//팀게시판수정
	@RequestMapping(value="/board/GTboardUpdate.do",method=RequestMethod.GET)
	public String GTupdateform(@RequestParam("seq") int seq,
			           Model model){
		GTBoardCommand boardCommand = boardService.GTselectBoard(seq);
		model.addAttribute("command", boardCommand);
		
		return "TeamboardModify";
	}
	
	@RequestMapping(value="/board/GTboardUpdate.do",method=RequestMethod.POST)
	public String GTupdatesubmit(@ModelAttribute("command")
	                     @Valid GTBoardCommand boardCommand,
	                     BindingResult result,
	                     HttpSession session,
	                     HttpServletRequest request) throws Exception{
		
		if(log.isDebugEnabled()){
			log.debug("<<boardCommand>> : " + boardCommand);
		}
		
		String user_id = (String)session.getAttribute("user_id");
		if(!user_id.equals(boardCommand.getId())){
			throw new Exception(
			"로그인한 아이디로 작성된 글이 아니기 때문에 수정할 수 없습니다.");
		}
		
		GTBoardCommand board = boardService.GTselectBoard(
				                     boardCommand.getGt_seq());
		
		if(result.hasErrors()){
			//원래 파일명 셋팅
			boardCommand.setGt_filename(board.getGt_filename());
			return "TeamboardModify";
		}
		
		//전송된 파일이 없을 경우
		if(boardCommand.getUpload().isEmpty()){
			//기존 정보 셋팅
			boardCommand.setGt_uploadfile(board.getGt_uploadfile());
			boardCommand.setGt_filename(board.getGt_filename());
		}
		
		//ip셋팅
		boardCommand.setIp(request.getRemoteAddr());
		
		//글수정
		boardService.GTupdate(boardCommand);
		
		return "redirect:/board/GTboardUpdate.do";
	}
	//공지사항수정
	@RequestMapping(value="/board/GNoticeUpdate.do",method=RequestMethod.GET)
	public String form(@RequestParam("seq") int seq,
			           Model model){
		GNoticeCommand boardCommand = boardService.GNselectBoard(seq);
		model.addAttribute("command", boardCommand);
		
		return "boardModify";
	}
	
	@RequestMapping(value="/board/GNoticeUpdate.do",method=RequestMethod.POST)
	public String GNupdatesubmit(@ModelAttribute("command")
	                     @Valid GNoticeCommand boardCommand,
	                     BindingResult result,
	                     HttpSession session,
	                     HttpServletRequest request) throws Exception{
		
		if(log.isDebugEnabled()){
			log.debug("<<boardCommand>> : " + boardCommand);
		}
		
		String user_id = (String)session.getAttribute("user_id");
		if(!user_id.equals(boardCommand.getId())){
			throw new Exception(
			"로그인한 아이디로 작성된 글이 아니기 때문에 수정할 수 없습니다.");
		}
		
		GNoticeCommand board = boardService.GNselectBoard(
				                     boardCommand.getGn_seq());
		
		if(result.hasErrors()){
			//원래 파일명 셋팅
			boardCommand.setGn_filename(board.getGn_filename());
			return "GNoticeModify";
		}
		
		//전송된 파일이 없을 경우
		if(boardCommand.getUpload().isEmpty()){
			//기존 정보 셋팅
			boardCommand.setGn_uploadfile(board.getGn_uploadfile());
			boardCommand.setGn_filename(board.getGn_filename());
		}
		
		//ip셋팅
		boardCommand.setIp(request.getRemoteAddr());
		
		//글수정
		boardService.GNupdate(boardCommand);
		
		return "redirect:/board/GNoticeUpdate.do";
	}
	//자유게시판댓글수정
	@RequestMapping("/board/GboardUpdateReply.do")
	@ResponseBody
	public Map<String,String> GupdateReprocess(
			GBoardReplyCommand boardReplyCommand,
			HttpSession session,
			HttpServletRequest request){
		
		if(log.isDebugEnabled()){
			log.debug("<<boardReplyCommand>> : " + boardReplyCommand);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		String user_id = (String)session.getAttribute("user_id");
		
		if(user_id==null){
			//로그인이 안 되어있는 경우
			map.put("result", "logout");
		}else if(user_id!=null && 
				user_id.equals(boardReplyCommand.getId())){
			//로그인 아이디와 작성자 아이디 일치
			
			//ip 등록
			boardReplyCommand.setRe_ip(request.getRemoteAddr());
			
			//댓글 수정
			boardService.GupdateReply(boardReplyCommand);
			map.put("result", "success");
		}else{
			//로그인 아이디와 작성자 아이디 불일치
			map.put("result", "wrongAccess");
		}
		
		return map;
	}
	//팀게시판댓글수정
	@RequestMapping("/board/GTboardUpdateReply.do")
	@ResponseBody
	public Map<String,String> GTupdateReprocess(
			GTBoardReplyCommand boardReplyCommand,
			HttpSession session,
			HttpServletRequest request){
		
		if(log.isDebugEnabled()){
			log.debug("<<boardReplyCommand>> : " + boardReplyCommand);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		String user_id = (String)session.getAttribute("user_id");
		
		if(user_id==null){
			//로그인이 안 되어있는 경우
			map.put("result", "logout");
		}else if(user_id!=null && 
				user_id.equals(boardReplyCommand.getId())){
			//로그인 아이디와 작성자 아이디 일치
			
			//ip 등록
			boardReplyCommand.setRe_ip(request.getRemoteAddr());
			
			//댓글 수정
			boardService.GTupdateReply(boardReplyCommand);
			map.put("result", "success");
		}else{
			//로그인 아이디와 작성자 아이디 불일치
			map.put("result", "wrongAccess");
		}
		
		return map;
	}
	//공지사항댓글수정
	@RequestMapping("/board/GNoticeUpdateReply.do")
	@ResponseBody
	public Map<String,String> GNupdateReprocess(
			GNoticeReplyCommand boardReplyCommand,
			HttpSession session,
			HttpServletRequest request){
		
		if(log.isDebugEnabled()){
			log.debug("<<boardReplyCommand>> : " + boardReplyCommand);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		String user_id = (String)session.getAttribute("user_id");
		
		if(user_id==null){
			//로그인이 안 되어있는 경우
			map.put("result", "logout");
		}else if(user_id!=null && 
				user_id.equals(boardReplyCommand.getId())){
			//로그인 아이디와 작성자 아이디 일치
			
			//ip 등록
			boardReplyCommand.setRe_ip(request.getRemoteAddr());
			
			//댓글 수정
			boardService.GNupdateReply(boardReplyCommand);
			map.put("result", "success");
		}else{
			//로그인 아이디와 작성자 아이디 불일치
			map.put("result", "wrongAccess");
		}
		
		return map;
	}
	//자유게시판삭제
	@RequestMapping("/board/GboardDelete.do")
	public String Gdeletesubmit(@RequestParam("seq") int seq,
			             HttpSession session)
			            		    throws Exception{
		if(log.isDebugEnabled()){
			log.debug("<<seq>> : " + seq);
		}
		
		GBoardCommand boardCommand = 
				boardService.GselectBoard(seq);
		String user_id = (String)session.getAttribute("user_id");
		if(!user_id.equals(boardCommand.getId())){
			throw new Exception("로그인한 아이디로 작성된 글이 아니기 때문에 삭제할 수 없습니다.");
		}
		
		//글 삭제
		boardService.Gdelete(boardCommand.getGb_seq());
		
		return "redirect:/board/GboardDelete.do";
	}
	//팀게시판삭제
	@RequestMapping("/board/GTboardDelete.do")
	public String GTdeletesubmit(@RequestParam("seq") int seq,
			             HttpSession session)
			            		    throws Exception{
		if(log.isDebugEnabled()){
			log.debug("<<seq>> : " + seq);
		}
		
		GTBoardCommand boardCommand = 
				boardService.GTselectBoard(seq);
		String user_id = (String)session.getAttribute("user_id");
		if(!user_id.equals(boardCommand.getId())){
			throw new Exception("로그인한 아이디로 작성된 글이 아니기 때문에 삭제할 수 없습니다.");
		}
		
		//글 삭제
		boardService.GTdelete(boardCommand.getGt_seq());
		
		return "redirect:/board/GTboardDelete.do";
	}
	//공지사항삭제
	@RequestMapping("/board/GNoticeDelete.do")
	public String GNdeletesubmit(@RequestParam("seq") int seq,
			             HttpSession session)
			            		    throws Exception{
		if(log.isDebugEnabled()){
			log.debug("<<GNseq>> : " + seq);
		}
		
		GNoticeCommand boardCommand = 
				boardService.GNselectBoard(seq);
		String user_id = (String)session.getAttribute("user_id");
		if(!user_id.equals(boardCommand.getId())){
			throw new Exception("로그인한 아이디로 작성된 글이 아니기 때문에 삭제할 수 없습니다.");
		}
		
		//글 삭제
		boardService.GNdelete(boardCommand.getGn_seq());
		
		return "redirect:/board/GNotice.do";
	}
	//자유게시판댓글삭제
	@RequestMapping("/board/GboardDeleteReply.do")
	@ResponseBody
	public Map<String,String> GdeleteReprocess(
			@RequestParam("re_no") int re_no,
			@RequestParam("id") String id,
			HttpSession session){
		
		if(log.isDebugEnabled()){
			log.debug("<<re_no>> : " + re_no);
			log.debug("<<id>> : " + id);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			//로그인이 되어있지 않음
			map.put("result", "logout");
		}else if(user_id!=null && user_id.equals(id)){
			//로그인 되어 있고 로그인한 아이디와 작성자 아이디 일치
			boardService.GdeleteReply(re_no);
			map.put("result", "success");
		}else{
			map.put("result", "wrongAccess");
		}
		
		return map;
	}
	//팀게시판댓글삭제
	@RequestMapping("/board/GTboardDeleteReply.do")
	@ResponseBody
	public Map<String,String> GTdeleteReprocess(
			@RequestParam("re_no") int re_no,
			@RequestParam("id") String id,
			HttpSession session){
		
		if(log.isDebugEnabled()){
			log.debug("<<re_no>> : " + re_no);
			log.debug("<<id>> : " + id);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			//로그인이 되어있지 않음
			map.put("result", "logout");
		}else if(user_id!=null && user_id.equals(id)){
			//로그인 되어 있고 로그인한 아이디와 작성자 아이디 일치
			boardService.GTdeleteReply(re_no);
			map.put("result", "success");
		}else{
			map.put("result", "wrongAccess");
		}
		
		return map;
	}
	//공지사항댓글삭제
	@RequestMapping("/board/GNoticeDeleteReply.do")
	@ResponseBody
	public Map<String,String> GNdeleteReprocess(
			@RequestParam("re_no") int re_no,
			@RequestParam("id") String id,
			HttpSession session){
		
		if(log.isDebugEnabled()){
			log.debug("<<re_no>> : " + re_no);
			log.debug("<<id>> : " + id);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		String user_id = (String)session.getAttribute("user_id");
		if(user_id==null){
			//로그인이 되어있지 않음
			map.put("result", "logout");
		}else if(user_id!=null && user_id.equals(id)){
			//로그인 되어 있고 로그인한 아이디와 작성자 아이디 일치
			boardService.GNdeleteReply(re_no);
			map.put("result", "success");
		}else{
			map.put("result", "wrongAccess");
		}
		
		return map;
	}
	//자유게시판글상세
	@RequestMapping("/board/GboardDetail.do")
	public ModelAndView Gdetailprocess(@RequestParam("seq") int seq){
		
		if(log.isDebugEnabled()){
			log.debug("<<seq>> : " + seq);
		}
		
		//해당 글의 조회수 증가
		boardService.GupdateHit(seq);
		GBoardCommand board = boardService.GselectBoard(seq);
		
		return new ModelAndView("boardView","board",board);
	}
	//파일 다운로드
	@RequestMapping("/board/Gfile.do")
	public ModelAndView Gdownload(@RequestParam("seq") int seq){
		
		GBoardCommand board = boardService.GselectBoard(seq);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("downloadView");
		mav.addObject("downloadFile", board.getGb_uploadfile());
		mav.addObject("filename", board.getGb_filename());
		
		return mav;
	}
	//이미지 출력
	@RequestMapping("/board/GimageView.do")
	public ModelAndView GviewImage(@RequestParam("seq") int seq){
		
		GBoardCommand board = boardService.GselectBoard(seq);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", board.getGb_uploadfile());
		mav.addObject("filename", board.getGb_filename());
		
		return mav;
	}
	//팀게시판글상세
	@RequestMapping("/board/GTboardDetail.do")
	public ModelAndView GTdetailprocess(@RequestParam("seq") int seq){
		
		if(log.isDebugEnabled()){
			log.debug("<<seq>> : " + seq);
		}
		
		//해당 글의 조회수 증가
		boardService.GTupdateHit(seq);
		GTBoardCommand board = boardService.GTselectBoard(seq);
		
		return new ModelAndView("boardView","board",board);
	}
	//파일 다운로드
	@RequestMapping("/board/GTfile.do")
	public ModelAndView GTdownload(@RequestParam("seq") int seq){
		
		GTBoardCommand board = boardService.GTselectBoard(seq);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("downloadView");
		mav.addObject("downloadFile", board.getGt_uploadfile());
		mav.addObject("filename", board.getGt_filename());
		
		return mav;
	}
	//이미지 출력
	@RequestMapping("/board/GTimageView.do")
	public ModelAndView GTviewImage(@RequestParam("seq") int seq){
		
		GTBoardCommand board = boardService.GTselectBoard(seq);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", board.getGt_uploadfile());
		mav.addObject("filename", board.getGt_filename());
		
		return mav;
	}
	//공지사항글상세
	@RequestMapping("/board/GNoticeDetail.do")
	public ModelAndView GNdetailprocess(@RequestParam("seq") int seq){
		
		if(log.isDebugEnabled()){
			log.debug("<<seq>> : " + seq);
		}
		
		//해당 글의 조회수 증가
		boardService.GNupdateHit(seq);
		GNoticeCommand board = boardService.GNselectBoard(seq);
		
		return new ModelAndView("boardView","board",board);
	}
	//파일 다운로드
	@RequestMapping("/board/GNfile.do")
	public ModelAndView GNdownload(@RequestParam("seq") int seq){
		
		GNoticeCommand board = boardService.GNselectBoard(seq);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("downloadView");
		mav.addObject("downloadFile", board.getGn_uploadfile());
		mav.addObject("filename", board.getGn_filename());
		
		return mav;
	}
	//이미지 출력
	@RequestMapping("/board/GNimageView.do")
	public ModelAndView viewImage(@RequestParam("seq") int seq){
		
		GNoticeCommand board = boardService.GNselectBoard(seq);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", board.getGn_uploadfile());
		mav.addObject("filename", board.getGn_filename());
		
		return mav;
	}
}
