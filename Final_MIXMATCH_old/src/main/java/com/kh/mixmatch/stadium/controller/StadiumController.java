package com.kh.mixmatch.stadium.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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

import com.kh.mixmatch.stadium.domain.BookingCommand;
import com.kh.mixmatch.stadium.domain.StadiumCommand;
import com.kh.mixmatch.stadium.service.StadiumService;
import com.kh.mixmatch.team.domain.TeamCommand;
import com.kh.mixmatch.team.domain.TeamMemCommand;
import com.kh.mixmatch.team.service.TeamService;
import com.kh.mixmatch.util.PagingUtil;


@Controller
public class StadiumController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private StadiumService stadiumService;
	@Resource
	private TeamService teamService;
	
	@ModelAttribute("stadiumCommand")
	public StadiumCommand initsCommand(){
		return new StadiumCommand();
	}
	@ModelAttribute("bookingCommand")
	public BookingCommand initbCommand(){
		return new BookingCommand();
	}
	
	@RequestMapping("/stadium.do")
	public ModelAndView stadium(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="") String keyword){
		if(log.isDebugEnabled()){
			log.debug("<<pageNum>> : " +currentPage);
			log.debug("<<keyfield>> : " +keyfield);
			log.debug("<<keyword>> : " +keyword);
		}
		
		int rowCount = 10;
		int pageCount = 5;
		
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield",keyfield);
		map.put("keyword", keyword);
		int stadiumCount = stadiumService.getTotalCountStadium(map);
		
		PagingUtil page = new PagingUtil(currentPage, stadiumCount, rowCount, pageCount, "stadium.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<StadiumCommand> stadiumList = null;
		if(stadiumCount>0){
			stadiumList = stadiumService.listStadium(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("stadium");
		mav.addObject("stadiumList", stadiumList);
		mav.addObject("stadiumCount", stadiumCount);
		mav.addObject("pagingHtml", page.getPagingHtml());
		return mav;
	}
	@RequestMapping(value="/stadiumRegi.do",method=RequestMethod.GET)
	public String stadiumRegiForm(HttpSession session){
		String id = (String)session.getAttribute("user_id");
		if(!id.equals("admin")){
			return "redirect:/stadium.do";
		}
		return "stadiumRegi";
	}
	@RequestMapping(value="/stadiumRegi.do",method=RequestMethod.POST)
	public String stadiumRegi(@ModelAttribute("command") @Valid StadiumCommand stadium,BindingResult result,HttpSession session) throws Exception{
		if(log.isDebugEnabled()){
			log.debug("<<<< StadiumCommand >>>>  : " + stadium);
		}
		if(result.hasErrors()){
			return stadiumRegiForm(session);
		}
		
		String user_id = (String)session.getAttribute("user_id");
		if(user_id != null){
			if(user_id.equals("admin")){
			// 愿�由ъ옄留� �벑濡앷��뒫
				stadiumService.insertStadium(stadium);
			}
		}else{
			return "redirect:/login.do";
		}
		return "redirect:/stadium.do";
	
	}
	@RequestMapping("/imageViewStadium.do")
	public ModelAndView imageViewStadium(@RequestParam int s_seq){
		StadiumCommand stadium = stadiumService.selectStadium(s_seq);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile",stadium.getS_logo());
		mav.addObject("filename", stadium.getS_logo_name());
		return mav;
	}
	
	
	@RequestMapping("/stadiumDetail.do")
	public ModelAndView stadiumDetail(@RequestParam int s_seq,HttpSession session){
		StadiumCommand stadium = stadiumService.selectStadium(s_seq);
		String id = (String)session.getAttribute("user_id");
		Map<String, Object> map = new HashMap<String, Object>();

		List<TeamCommand> t_name = teamService.listMaster(id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("stadiumDetail");
		mav.addObject("stadium",stadium);
		mav.addObject("t_name",t_name);
		return mav;
	}
	
	
	@RequestMapping("/bookingList.do")
	@ResponseBody
	public Map<String, Object> bookingList(@RequestParam String b_regdate, @RequestParam int s_seq){
		if(log.isDebugEnabled()){
			log.debug("<<< b_regdate >>> : " + b_regdate);
			log.debug("<<< s_seq >>> : " + s_seq);
		}
		// 해당 경기장 예약 갯수
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("s_seq", s_seq);
		map.put("b_regdate", b_regdate);
		
		int rowCount = 10;
		int pageCount = 1;
		//해당경기장 해당날짜에 예약된 갯수 
		int bookCount = stadiumService.getTotalCountBooking(map);
		PagingUtil page = new PagingUtil(1, bookCount, rowCount, pageCount, null);
		map.put("start", page.getStartCount());
		map.put("end",page.getEndCount());
		
		
		
		List<BookingCommand> bookList = null;
		if(bookCount>0){
			bookList = stadiumService.listBooking(map);
			String time = "";
			for(int i =0;i<bookCount;i++){
				time += "["+bookList.get(i).getB_time()+"]";
			}
			for(int i =1;i<5;i++){
				if(!time.contains("["+i+"타임]")){
					BookingCommand book = new BookingCommand();
					book.setS_seq(s_seq);
					book.setB_regdate(b_regdate);
					book.setB_time(i+"타임");
					book.setB_check(0);
					bookList.add(book);
				}
			}
		
		}else{
			bookList = new ArrayList<BookingCommand>();
			for(int i =1;i<5;i++){
				BookingCommand book = new BookingCommand();
				book.setS_seq(s_seq);
				book.setB_regdate(b_regdate);
				book.setB_time(i+"타임");
				book.setB_check(0);
				bookList.add(book);
			}
		}
		
		
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("bookList", bookList);
		mapJson.put("bookCount", bookCount);
		return mapJson;
	}
	
	@RequestMapping("/stadiumBooking.do")
	public ModelAndView stadiumBooking(@RequestParam String b_regdate, @RequestParam int s_seq,@RequestParam String b_time,@RequestParam String t_name){
		if(log.isDebugEnabled()){
			log.debug("<<< b_regdate >>> : " + b_regdate);
			log.debug("<<< s_seq >>> : " + s_seq);
			log.debug("<<< b_time >>> : " + b_time);
			log.debug("<<< t_name >>> : " + t_name);
		}
		BookingCommand booking = new BookingCommand();
		booking.setS_seq(s_seq);
		booking.setB_time(b_time);
		booking.setB_regdate(b_regdate);
		booking.setT_name(t_name);
		
		stadiumService.insertBooking(booking);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("stadiumBooking");
		mav.addObject("booking", booking);
		return mav;
	}
	
	@RequestMapping("/stadiumMap.do")
	public String map(){
		return "stadiumMap";
	}
	@RequestMapping(value="/stadiumUpdate.do",method=RequestMethod.GET)
	public String stadiumUpdateForm(@RequestParam int s_seq,HttpSession session,Model model){
		String id =(String)session.getAttribute("user_id");
		if(!id.equals("admin")){
			return "redirect:/stadium.do";
		}
		StadiumCommand stadiumCommand = stadiumService.selectStadium(s_seq);
		model.addAttribute("stadiumCommand",stadiumCommand);
		return "stadiumUpdate";
	}
	@RequestMapping(value="/stadiumUpdate.do",method=RequestMethod.POST)
	public String stadiumUpdate(@ModelAttribute("command") @Valid StadiumCommand stadiumCommand,BindingResult result,HttpSession session) throws Exception{
		if(log.isDebugEnabled()){
			log.debug("<<<< StadiumCommand >>>>  : " + stadiumCommand);
		}

		StadiumCommand stadium = stadiumService.selectStadium(stadiumCommand.getS_seq()); 
		if(result.hasErrors()){
			stadiumCommand.setS_logo_name(stadiumCommand.getS_logo_name());	
			return "stadiumUpdate";
		}
		
		String id = (String)session.getAttribute("user_id");
		if(!id.equals("admin")){
			throw new Exception("관리자가 아니면 수정하실 수 없습니다.");
		}

		if(stadiumCommand.getS_logo_upload().isEmpty()){
			stadiumCommand.setS_logo(stadium.getS_logo());
			stadiumCommand.setS_logo_name(stadium.getS_logo_name());
		}
		
		stadiumService.updateStadium(stadiumCommand);
		return "redirect:/stadium.do";
	}
	@RequestMapping("/stadiumDel.do")
	public String stadiumDel(@RequestParam int s_seq,HttpSession session){
		String id =(String)session.getAttribute("user_id");
		if(!id.equals("admin")){
			return "redirect:/stadium.do";
		}
		stadiumService.deleteStadium(s_seq);
		return "redirect:/stadium.do";
	}
	@RequestMapping("/stadiumConfirm.do")
	public ModelAndView stadiumConfirm(HttpSession session){
		// 예약 리스트
		String id =(String)session.getAttribute("user_id");
		List<BookingCommand> booklist = null;
		List<TeamCommand> teamlist = null;
		int teamCountMaster = teamService.countMasterTeam(id);
		if(teamCountMaster>0){
			booklist = stadiumService.listBookingTeam(id);
			teamlist = teamService.listMaster(id);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("stadiumConfirm");
		mav.addObject("booklist", booklist);
		
		mav.addObject("teamCountMaster", teamCountMaster);
		mav.addObject("teamlist", teamlist);
		return mav;
	}
	@RequestMapping("/stadiumBookF.do")
	public String stadiumBookF(@RequestParam int b_seq){
		
		stadiumService.updateCheckBooking(b_seq);
		
		return "redirect:/stadiumConfirm.do";
	}
	
	@RequestMapping("/stadiumBookC.do")
	public String stadiumBookc(@RequestParam int b_seq){
		
		stadiumService.deleteBooking(b_seq);
		
		return "redirect:/stadiumConfirm.do";
	}
}
