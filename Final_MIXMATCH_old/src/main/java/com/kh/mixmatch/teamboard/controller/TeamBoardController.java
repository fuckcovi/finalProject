package com.kh.mixmatch.teamboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.mixmatch.team.domain.TeamMemCommand;
import com.kh.mixmatch.team.service.TeamMemService;
import com.kh.mixmatch.teamboard.domain.TeamBoardCommand;
import com.kh.mixmatch.teamboard.service.TeamBoardService;
import com.kh.mixmatch.util.PagingUtil;



@Controller
public class TeamBoardController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private TeamBoardService teamBoardService;
	@Resource
	private TeamMemService teamMemService;
	@ModelAttribute
	public TeamBoardCommand initCommand(){
		return new TeamBoardCommand();
	}
	
	private int rowCount = 2;
	private int pageCount = 3;
	
	@RequestMapping("/teamboard.do")
	public ModelAndView teamboardList(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="") String keyword,
			@RequestParam(value="t_name",defaultValue="") String t_name, HttpSession session){
		if(log.isDebugEnabled()){
			log.debug("<<pageNum>> : " +currentPage);
			log.debug("<<keyfield>> : " +keyfield);
			log.debug("<<keyword>> : " +keyword);
		}

		/// 소속 팀 목록 구하기
		List<TeamMemCommand> teamlist = null;
		Map<String, Object> tmap = new HashMap<String, Object>();
		String user_id = (String)session.getAttribute("user_id");
		tmap.put("id", user_id);
		int teamcount = teamMemService.getRowTeamCount(user_id);
		ModelAndView mav = new ModelAndView();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("keyfield",keyfield);
		map.put("keyword", keyword);
		if(teamcount>0){
			teamlist = teamMemService.listConfirmTeam(tmap);
				if(t_name.equals("")){
					map.put("t_name", teamlist.get(0).getT_name());	// 임의로 첫번째 팀을 넣어서 검색
				}else{
					map.put("t_name", t_name);
				}
				
				
			
		
		
		
		// 총 글의 갯수 또는 검색된 글의 갯수
		int count = teamBoardService.getTbRowCount(map);
		if(log.isDebugEnabled()){
			log.debug("<<<count>> : "+count );
		}
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, "teamboard.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		List<TeamBoardCommand> list = null;
		
		if(count>0){
			list = teamBoardService.teamboardList(map);
		}
		
		
		
		mav.setViewName("teamboard");
		mav.addObject("pagingHtml",page.getPagingHtml());
		mav.addObject("count",count);
		mav.addObject("list",list);
		
		}
		mav.addObject("teamcount",teamcount);
		mav.addObject("teamlist",teamlist);
		return mav; 
	}
	@RequestMapping("/teamboardDetail.do")
	public ModelAndView teamboardDetail(@RequestParam int gt_seq){
		if(log.isDebugEnabled()){
			log.debug("<<<< gt_seq>>>>> : " +gt_seq);
		}
		// 해당 글의 조회수 증가
		teamBoardService.teamboardUpdateHit(gt_seq);
		TeamBoardCommand teamboard = teamBoardService.teamboardSelect(gt_seq);
		
		return new ModelAndView("teamboardDetail","teamboard",teamboard);
	}

	// 파일 다운로드
	@RequestMapping("/teamboardfile.do")
	public ModelAndView download(@RequestParam int gt_seq){
		TeamBoardCommand teamboard = teamBoardService.teamboardSelect(gt_seq);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("downloadView");
		mav.addObject("downloadFile",teamboard.getGt_uploadfile());
		mav.addObject("filename", teamboard.getGt_filename());
		return mav;
	}
	
	// 이미지 출력
	@RequestMapping("/teamboardimageView.do")
	public ModelAndView viewImage(@RequestParam int gt_seq){
		TeamBoardCommand teamboard = teamBoardService.teamboardSelect(gt_seq);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile",teamboard.getGt_uploadfile());
		mav.addObject("filename",  teamboard.getGt_filename());
		return mav;
	}
	
	@RequestMapping(value="/teamboardInsert.do",method=RequestMethod.GET)
	public ModelAndView teamboardInsertForm(HttpSession session){
		String id = (String)session.getAttribute("user_id");
		
		int teamcount = teamMemService.getRowTeamCount(id);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id",id);
		List<TeamMemCommand> teamlist = null;
		if(teamcount>0){
			teamlist = teamMemService.listConfirmTeam(map);
		}
		TeamBoardCommand teamboardCommand = new TeamBoardCommand();
		teamboardCommand.setId(id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("teamboardInsert");
		mav.addObject("teamboardCommand", teamboardCommand);
		mav.addObject("teamlist", teamlist);
		return mav;
	}
	@RequestMapping(value="/teamboardInsert.do",method=RequestMethod.POST)
	public String teamboardInsertSubmit(@ModelAttribute("command") @Valid TeamBoardCommand teamboardCommand, BindingResult result, HttpServletRequest request){
		System.out.println(teamboardCommand.getT_name());
		if(log.isDebugEnabled()){
			System.out.println("asdffffasdfsdfsdf");
			log.debug("<<<<<<< teamboardCommand : " +teamboardCommand);
		}
		if(result.hasErrors()){
			return "redirect:/teamboardInsert.do";
		}
		// 글쓰기
		teamboardCommand.setIp(request.getRemoteAddr());
		System.out.println(teamboardCommand);
		teamBoardService.teamboardInsert(teamboardCommand);
		return "redirect:/teamboard.do";
	}
	// 글 수정폼
	@RequestMapping(value="teamboardUpdate.do", method=RequestMethod.GET)
	public ModelAndView teamboardUpdateForm(@RequestParam("gt_seq") int gt_seq, HttpSession session) {
		String id = (String)session.getAttribute("user_id");
		
		int teamcount = teamMemService.getRowTeamCount(id);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id",id);
		List<TeamMemCommand> teamlist = null;
		if(teamcount>0){
			teamlist = teamMemService.listConfirmTeam(map);
			System.out.println("<<팀게시판 수정>> : " + teamlist);
		}
		
		TeamBoardCommand teamBoardCommand = teamBoardService.teamboardSelect(gt_seq);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("teamboardUpdate");
		mav.addObject("teamboard", teamBoardCommand);
		mav.addObject("teamlist", teamlist);
		
		return mav;
	}
	
	// 글 수정
	@RequestMapping(value="teamboardUpdate.do", method=RequestMethod.POST)
	public String teamboardUpdateSubmit(@ModelAttribute("teamboard") TeamBoardCommand teamBoardCommand,
										BindingResult result, HttpSession session, HttpServletRequest request) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("<<teamBoardCommand>> : " + teamBoardCommand);
		}
		
		String user_id = (String) session.getAttribute("user_id");
		if (!user_id.equals(teamBoardCommand.getId())) {
			throw new Exception("본인 글이 아니면 삭제하실 수 없습니다.");
		}
		
		TeamBoardCommand teamBoard = teamBoardService.teamboardSelect(teamBoardCommand.getGt_seq());
		
		if (result.hasErrors()) {
			teamBoardCommand.setGt_filename(teamBoard.getGt_filename());
			return "teamboardUpdate";
		}
		
		if (teamBoardCommand.getGt_uploadfile_upload().isEmpty()) {
			teamBoardCommand.setGt_uploadfile(teamBoard.getGt_uploadfile());
			teamBoardCommand.setGt_filename(teamBoard.getGt_filename());
			
		}
		
		teamBoardCommand.setIp(request.getRemoteAddr());
		
		teamBoardService.teamboardUpdate(teamBoardCommand);
		
		return "redirect:/teamboard.do";
	}
	
	// 글 삭제
	@RequestMapping("teamboardDelete.do")
	public String teamboardDeleteProcess(@RequestParam("gt_seq") int gt_seq, HttpSession session) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("<<gt_seq>> : " + gt_seq);
		}
		
		TeamBoardCommand teamBoardCommand = teamBoardService.teamboardSelect(gt_seq);
		String user_id = (String) session.getAttribute("user_id");
		if (!user_id.equals(teamBoardCommand.getId())) {
			throw new Exception("본인 글이 아니면 삭제하실 수 없습니다.");
		}
		
		teamBoardService.teamboardDelete(teamBoardCommand.getGt_seq());
		
		return "redirect:/teamboard.do";
	}
	
}
