package com.kh.mixmatch.notice.controller;

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

import com.kh.mixmatch.notice.domain.NoticeCommand;
import com.kh.mixmatch.notice.service.NoticeService;
import com.kh.mixmatch.util.PagingUtil;



@Controller
public class NoticeController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private NoticeService noticeService;
	@ModelAttribute
	public NoticeCommand initCommand(){
		return new NoticeCommand();
	}
	
	private int rowCount = 2;
	private int pageCount = 3;
	
	@RequestMapping("/notice.do")
	public ModelAndView noticeList(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="") String keyword){
		if(log.isDebugEnabled()){
			log.debug("<<pageNum>> : " +currentPage);
			log.debug("<<keyfield>> : " +keyfield);
			log.debug("<<keyword>> : " +keyword);
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("keyfield",keyfield);
		map.put("keyword", keyword);
		// �� ���� ���� �Ǵ� �˻��� ���� ����
		int count = noticeService.getRowCount(map);
		if(log.isDebugEnabled()){
			log.debug("<<<count>> : "+count );
		}
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, "notice.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		List<NoticeCommand> list = null;
		
		if(count>0){
			list = noticeService.noticeList(map);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("notice");
		mav.addObject("pagingHtml",page.getPagingHtml());
		mav.addObject("count",count);
		mav.addObject("list",list);
		return mav; 
	}
	@RequestMapping(value="/noticeInsert.do",method=RequestMethod.GET)
	public String noticeInsertForm(HttpSession session, Model model){
		String id = (String)session.getAttribute("user_id");
		NoticeCommand noticeCommand = new NoticeCommand();
		noticeCommand.setId(id);
		model.addAttribute("noticeCommand", noticeCommand);
		return "noticeInsert";
	}
	@RequestMapping(value="/noticeInsert.do",method=RequestMethod.POST)
	public String noticeInsertSubmit(@ModelAttribute("command") @Valid NoticeCommand noticeCommand, BindingResult result, HttpServletRequest request){
		if(log.isDebugEnabled()){
			log.debug("<<<<<<< noticeCommand : " +noticeCommand);
		}
		if(result.hasErrors()){
			return "noticeInsert";
		}
		// �۾���
		noticeService.noticeInsert(noticeCommand);
		return "redirect:/notice.do";
	}
	@RequestMapping("/noticeDetail.do")
	public ModelAndView noticeDetail(@RequestParam int n_seq){
		if(log.isDebugEnabled()){
			log.debug("<<<< n_seq>>>>> : " +n_seq);
		}
		// �ش� ���� ��ȸ�� ����
		noticeService.noticeUpdateHit(n_seq);
		NoticeCommand notice = noticeService.noticeSelect(n_seq);
		
		return new ModelAndView("noticeDetail","notice",notice);
	}

	// ���� �ٿ�ε�
	@RequestMapping("/noticefile.do")
	public ModelAndView download(@RequestParam int n_seq){
		NoticeCommand notice = noticeService.noticeSelect(n_seq);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("downloadView");
		mav.addObject("downloadFile",notice.getN_file());
		mav.addObject("filename", notice.getN_file_name());
		return mav;
	}
	
	// �̹��� ���
	@RequestMapping("/noticeimageView.do")
	public ModelAndView viewImage(@RequestParam int n_seq){
		NoticeCommand notice = noticeService.noticeSelect(n_seq);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile",notice.getN_file());
		mav.addObject("filename",  notice.getN_file_name());
		return mav;
	}
}
