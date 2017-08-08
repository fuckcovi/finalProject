package com.kh.mixmatch.notice.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kh.mixmatch.notice.dao.NoticeMapper;
import com.kh.mixmatch.notice.domain.NoticeCommand;
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{
	
	@Resource
	private NoticeMapper noticeMapper;
	
	@Override
	public void noticeInsert(NoticeCommand notice) {
		noticeMapper.noticeInsert(notice);
	}

	@Override
	public void noticeDelete(Integer n_seq) {
		noticeMapper.noticeDelete(n_seq);		
	}

	@Override
	public NoticeCommand noticeUpdate(NoticeCommand notice) {
		return noticeMapper.noticeUpdate(notice);
	}

	@Override
	public NoticeCommand noticeUpdateHit(Integer n_seq) {
		return noticeMapper.noticeUpdateHit(n_seq);
	}

	@Override
	public NoticeCommand noticeSelect(Integer n_seq) {
		return noticeMapper.noticeSelect(n_seq);
	}

	@Override
	public List<NoticeCommand> noticeList(Map<String, Object> map) {
		return noticeMapper.noticeList(map);
	}

	@Override
	public int getRowCount(Map<String, Object> map) {
		return noticeMapper.getRowCount(map);
	}
	
}
