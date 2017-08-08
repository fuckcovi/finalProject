package com.kh.mixmatch.notice.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.kh.mixmatch.notice.domain.NoticeCommand;
@Transactional
public interface NoticeService {
	public void noticeInsert(NoticeCommand notice);
	public void noticeDelete(Integer n_seq);
	public NoticeCommand noticeUpdate(NoticeCommand notice);
	public NoticeCommand noticeUpdateHit(Integer n_seq);
	@Transactional(readOnly=true)
	public NoticeCommand noticeSelect(Integer n_seq);
	@Transactional(readOnly=true)
	public int getRowCount(Map<String, Object> map);
	@Transactional(readOnly=true)
	public List<NoticeCommand> noticeList(Map<String, Object> map);
}
