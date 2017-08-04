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
	//�����Խ��Ǹ��
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
		
		//�� ���� ���� �Ǵ� �˻��� ���� ����
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
	//���Խ��Ǹ��
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
		
		//�� ���� ���� �Ǵ� �˻��� ���� ����
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
	//�������׸��
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
		
		//�� ���� ���� �Ǵ� �˻��� ���� ����
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
	//�����Խ��Ǵ�� 
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
		
		//�� ���� ����
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
	//���Խ��Ǵ��
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
		
		//�� ���� ����
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
	
	//�������״��
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
		
		//�� ���� ����
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
	//�����Խ��Ǳ۾���
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
		
		//��ȿ�� üũ
		if(result.hasErrors()){
			return "GboardWrite";
		}
		
		//ip ����
		boardCommand.setIp(request.getRemoteAddr());
		
		//�۾���
		boardService.Ginsert(boardCommand);
		
		return "redirect:/board/Gboard.do";
	}
	//���Խ��Ǳ۾���
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
		
		//��ȿ�� üũ
		if(result.hasErrors()){
			return "TeamboardWrite";
		}
		
		//ip ����
		boardCommand.setIp(request.getRemoteAddr());
		
		//�۾���
		boardService.GTinsert(boardCommand);
		
		return "redirect:/board/GTboard.do";
	}
	//�������ױ۾���
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
		
		//��ȿ�� üũ
		if(result.hasErrors()){
			return "boardWrite";
		}
		
		//ip ����
		boardCommand.setIp(request.getRemoteAddr());
		
		//�۾���
		boardService.GNinsert(boardCommand);
		
		return "redirect:/board/GNotice.do";
	}
	//�����Խ��Ǵ�۾���
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
			//�α��� �� ��
			map.put("result", "logout");
		}else{
			
			//ip���
			boardReplyCommand.setRe_ip(request.getRemoteAddr());
			
			//�α��� ��, ��� ���
			boardService.GinsertReply(boardReplyCommand);
			map.put("result", "success");
		}
		return map;
	}
	//���Խ��Ǵ�۾���
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
			//�α��� �� ��
			map.put("result", "logout");
		}else{
			
			//ip���
			boardReplyCommand.setRe_ip(request.getRemoteAddr());
			
			//�α��� ��, ��� ���
			boardService.GTinsertReply(boardReplyCommand);
			map.put("result", "success");
		}
		return map;
	}
	//�������״�۾���
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
			//�α��� �� ��
			map.put("result", "logout");
		}else{
			
			//ip���
			boardReplyCommand.setRe_ip(request.getRemoteAddr());
			
			//�α��� ��, ��� ���
			boardService.GNinsertReply(boardReplyCommand);
			map.put("result", "success");
		}
		return map;
	}
	//�����Խ��Ǽ���
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
			"�α����� ���̵�� �ۼ��� ���� �ƴϱ� ������ ������ �� �����ϴ�.");
		}
		
		GBoardCommand board = boardService.GselectBoard(
				                     boardCommand.getGb_seq());
		
		if(result.hasErrors()){
			//���� ���ϸ� ����
			boardCommand.setGb_filename(board.getGb_filename());
			return "GboardModify";
		}
		
		//���۵� ������ ���� ���
		if(boardCommand.getUpload().isEmpty()){
			//���� ���� ����
			boardCommand.setGb_uploadfile(board.getGb_uploadfile());
			boardCommand.setGb_filename(board.getGb_filename());
		}
		
		//ip����
		boardCommand.setIp(request.getRemoteAddr());
		
		//�ۼ���
		boardService.Gupdate(boardCommand);
		
		return "redirect:/board/list.do";
	}
	//���Խ��Ǽ���
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
			"�α����� ���̵�� �ۼ��� ���� �ƴϱ� ������ ������ �� �����ϴ�.");
		}
		
		GTBoardCommand board = boardService.GTselectBoard(
				                     boardCommand.getGt_seq());
		
		if(result.hasErrors()){
			//���� ���ϸ� ����
			boardCommand.setGt_filename(board.getGt_filename());
			return "TeamboardModify";
		}
		
		//���۵� ������ ���� ���
		if(boardCommand.getUpload().isEmpty()){
			//���� ���� ����
			boardCommand.setGt_uploadfile(board.getGt_uploadfile());
			boardCommand.setGt_filename(board.getGt_filename());
		}
		
		//ip����
		boardCommand.setIp(request.getRemoteAddr());
		
		//�ۼ���
		boardService.GTupdate(boardCommand);
		
		return "redirect:/board/GTboardUpdate.do";
	}
	//�������׼���
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
			"�α����� ���̵�� �ۼ��� ���� �ƴϱ� ������ ������ �� �����ϴ�.");
		}
		
		GNoticeCommand board = boardService.GNselectBoard(
				                     boardCommand.getGn_seq());
		
		if(result.hasErrors()){
			//���� ���ϸ� ����
			boardCommand.setGn_filename(board.getGn_filename());
			return "GNoticeModify";
		}
		
		//���۵� ������ ���� ���
		if(boardCommand.getUpload().isEmpty()){
			//���� ���� ����
			boardCommand.setGn_uploadfile(board.getGn_uploadfile());
			boardCommand.setGn_filename(board.getGn_filename());
		}
		
		//ip����
		boardCommand.setIp(request.getRemoteAddr());
		
		//�ۼ���
		boardService.GNupdate(boardCommand);
		
		return "redirect:/board/GNoticeUpdate.do";
	}
	//�����Խ��Ǵ�ۼ���
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
			//�α����� �� �Ǿ��ִ� ���
			map.put("result", "logout");
		}else if(user_id!=null && 
				user_id.equals(boardReplyCommand.getId())){
			//�α��� ���̵�� �ۼ��� ���̵� ��ġ
			
			//ip ���
			boardReplyCommand.setRe_ip(request.getRemoteAddr());
			
			//��� ����
			boardService.GupdateReply(boardReplyCommand);
			map.put("result", "success");
		}else{
			//�α��� ���̵�� �ۼ��� ���̵� ����ġ
			map.put("result", "wrongAccess");
		}
		
		return map;
	}
	//���Խ��Ǵ�ۼ���
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
			//�α����� �� �Ǿ��ִ� ���
			map.put("result", "logout");
		}else if(user_id!=null && 
				user_id.equals(boardReplyCommand.getId())){
			//�α��� ���̵�� �ۼ��� ���̵� ��ġ
			
			//ip ���
			boardReplyCommand.setRe_ip(request.getRemoteAddr());
			
			//��� ����
			boardService.GTupdateReply(boardReplyCommand);
			map.put("result", "success");
		}else{
			//�α��� ���̵�� �ۼ��� ���̵� ����ġ
			map.put("result", "wrongAccess");
		}
		
		return map;
	}
	//�������״�ۼ���
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
			//�α����� �� �Ǿ��ִ� ���
			map.put("result", "logout");
		}else if(user_id!=null && 
				user_id.equals(boardReplyCommand.getId())){
			//�α��� ���̵�� �ۼ��� ���̵� ��ġ
			
			//ip ���
			boardReplyCommand.setRe_ip(request.getRemoteAddr());
			
			//��� ����
			boardService.GNupdateReply(boardReplyCommand);
			map.put("result", "success");
		}else{
			//�α��� ���̵�� �ۼ��� ���̵� ����ġ
			map.put("result", "wrongAccess");
		}
		
		return map;
	}
	//�����Խ��ǻ���
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
			throw new Exception("�α����� ���̵�� �ۼ��� ���� �ƴϱ� ������ ������ �� �����ϴ�.");
		}
		
		//�� ����
		boardService.Gdelete(boardCommand.getGb_seq());
		
		return "redirect:/board/GboardDelete.do";
	}
	//���Խ��ǻ���
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
			throw new Exception("�α����� ���̵�� �ۼ��� ���� �ƴϱ� ������ ������ �� �����ϴ�.");
		}
		
		//�� ����
		boardService.GTdelete(boardCommand.getGt_seq());
		
		return "redirect:/board/GTboardDelete.do";
	}
	//�������׻���
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
			throw new Exception("�α����� ���̵�� �ۼ��� ���� �ƴϱ� ������ ������ �� �����ϴ�.");
		}
		
		//�� ����
		boardService.GNdelete(boardCommand.getGn_seq());
		
		return "redirect:/board/GNotice.do";
	}
	//�����Խ��Ǵ�ۻ���
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
			//�α����� �Ǿ����� ����
			map.put("result", "logout");
		}else if(user_id!=null && user_id.equals(id)){
			//�α��� �Ǿ� �ְ� �α����� ���̵�� �ۼ��� ���̵� ��ġ
			boardService.GdeleteReply(re_no);
			map.put("result", "success");
		}else{
			map.put("result", "wrongAccess");
		}
		
		return map;
	}
	//���Խ��Ǵ�ۻ���
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
			//�α����� �Ǿ����� ����
			map.put("result", "logout");
		}else if(user_id!=null && user_id.equals(id)){
			//�α��� �Ǿ� �ְ� �α����� ���̵�� �ۼ��� ���̵� ��ġ
			boardService.GTdeleteReply(re_no);
			map.put("result", "success");
		}else{
			map.put("result", "wrongAccess");
		}
		
		return map;
	}
	//�������״�ۻ���
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
			//�α����� �Ǿ����� ����
			map.put("result", "logout");
		}else if(user_id!=null && user_id.equals(id)){
			//�α��� �Ǿ� �ְ� �α����� ���̵�� �ۼ��� ���̵� ��ġ
			boardService.GNdeleteReply(re_no);
			map.put("result", "success");
		}else{
			map.put("result", "wrongAccess");
		}
		
		return map;
	}
	//�����Խ��Ǳۻ�
	@RequestMapping("/board/GboardDetail.do")
	public ModelAndView Gdetailprocess(@RequestParam("seq") int seq){
		
		if(log.isDebugEnabled()){
			log.debug("<<seq>> : " + seq);
		}
		
		//�ش� ���� ��ȸ�� ����
		boardService.GupdateHit(seq);
		GBoardCommand board = boardService.GselectBoard(seq);
		
		return new ModelAndView("boardView","board",board);
	}
	//���� �ٿ�ε�
	@RequestMapping("/board/Gfile.do")
	public ModelAndView Gdownload(@RequestParam("seq") int seq){
		
		GBoardCommand board = boardService.GselectBoard(seq);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("downloadView");
		mav.addObject("downloadFile", board.getGb_uploadfile());
		mav.addObject("filename", board.getGb_filename());
		
		return mav;
	}
	//�̹��� ���
	@RequestMapping("/board/GimageView.do")
	public ModelAndView GviewImage(@RequestParam("seq") int seq){
		
		GBoardCommand board = boardService.GselectBoard(seq);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", board.getGb_uploadfile());
		mav.addObject("filename", board.getGb_filename());
		
		return mav;
	}
	//���Խ��Ǳۻ�
	@RequestMapping("/board/GTboardDetail.do")
	public ModelAndView GTdetailprocess(@RequestParam("seq") int seq){
		
		if(log.isDebugEnabled()){
			log.debug("<<seq>> : " + seq);
		}
		
		//�ش� ���� ��ȸ�� ����
		boardService.GTupdateHit(seq);
		GTBoardCommand board = boardService.GTselectBoard(seq);
		
		return new ModelAndView("boardView","board",board);
	}
	//���� �ٿ�ε�
	@RequestMapping("/board/GTfile.do")
	public ModelAndView GTdownload(@RequestParam("seq") int seq){
		
		GTBoardCommand board = boardService.GTselectBoard(seq);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("downloadView");
		mav.addObject("downloadFile", board.getGt_uploadfile());
		mav.addObject("filename", board.getGt_filename());
		
		return mav;
	}
	//�̹��� ���
	@RequestMapping("/board/GTimageView.do")
	public ModelAndView GTviewImage(@RequestParam("seq") int seq){
		
		GTBoardCommand board = boardService.GTselectBoard(seq);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", board.getGt_uploadfile());
		mav.addObject("filename", board.getGt_filename());
		
		return mav;
	}
	//�������ױۻ�
	@RequestMapping("/board/GNoticeDetail.do")
	public ModelAndView GNdetailprocess(@RequestParam("seq") int seq){
		
		if(log.isDebugEnabled()){
			log.debug("<<seq>> : " + seq);
		}
		
		//�ش� ���� ��ȸ�� ����
		boardService.GNupdateHit(seq);
		GNoticeCommand board = boardService.GNselectBoard(seq);
		
		return new ModelAndView("boardView","board",board);
	}
	//���� �ٿ�ε�
	@RequestMapping("/board/GNfile.do")
	public ModelAndView GNdownload(@RequestParam("seq") int seq){
		
		GNoticeCommand board = boardService.GNselectBoard(seq);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("downloadView");
		mav.addObject("downloadFile", board.getGn_uploadfile());
		mav.addObject("filename", board.getGn_filename());
		
		return mav;
	}
	//�̹��� ���
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
