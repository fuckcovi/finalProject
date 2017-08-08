package com.kh.mixmatch.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
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
		// ÃÑ ±ÛÀÇ °¹¼ö ¶Ç´Â °Ë»öµÈ ±ÛÀÇ °¹¼ö
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
	
	
}
