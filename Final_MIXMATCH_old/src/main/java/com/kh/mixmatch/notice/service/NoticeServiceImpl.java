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
	public void noticeDelete(Integer gn_seq) {
		noticeMapper.noticeDelete(gn_seq);		
	}

	@Override
	public void noticeUpdate(NoticeCommand notice) {
		noticeMapper.noticeUpdate(notice);
	}

	@Override
	public void noticeUpdateHit(Integer gn_seq) {
		noticeMapper.noticeUpdateHit(gn_seq);
	}

	@Override
	public NoticeCommand noticeSelect(Integer gn_seq) {
		return noticeMapper.noticeSelect(gn_seq);
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
