package com.kh.mixmatch.notice.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.mixmatch.notice.domain.NoticeCommand;
import com.kh.mixmatch.notice.service.NoticeService;

@Controller
public class NoticeController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private NoticeService noticeService;
	@ModelAttribute
	public NoticeCommand initCommand(){
		return new NoticeCommand();
	}
	@RequestMapping("/notice.do")
	public String noticeList(){
		return "notice";
	}
}
