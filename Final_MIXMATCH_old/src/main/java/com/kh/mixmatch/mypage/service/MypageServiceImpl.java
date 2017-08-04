package com.kh.mixmatch.mypage.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kh.mixmatch.mypage.dao.MypageMapper;
import com.kh.mixmatch.mypage.domain.FootballCommand;
import com.kh.mixmatch.mypage.domain.MypageCommand;
import com.kh.mixmatch.mypage.domain.MypageCommand2;
import com.kh.mixmatch.mypage.domain.MypageReplyCommand;

@Service("mypageService")
public class MypageServiceImpl implements MypageService{

	@Resource
	private MypageMapper mypageMapper;

	@Override
	public void insert(MypageCommand mypage) {
		mypageMapper.insert(mypage);
		
	}
	
	@Override
	public List<MypageCommand> list(Map<String, Object> map) {
		return mypageMapper.list(map);
	}


	@Override
	public MypageCommand selectMypage(Integer seq) {
		return mypageMapper.selectMypage(seq);
	}

	@Override
	public int getRowCount(Map<String, Object> map) {
		return mypageMapper.getRowCount(map);
	}


	@Override
	public FootballCommand selectFootball(String id) {
		return mypageMapper.selectFootball(id);
	}

	@Override
	public List<MypageReplyCommand> listReply(Map<String, Object> map) {
		return mypageMapper.listReply(map);
	}

	@Override
	public int getRowCountReply(Map<String, Object> map) {
		return mypageMapper.getRowCountReply(map);
	}

	@Override
	public void insertReply(MypageReplyCommand mypageReplyCommand) {
		mypageMapper.insertReply(mypageReplyCommand);
	}

	@Override
	public void update(MypageCommand2 mypage) {
		mypageMapper.update(mypage);
	}

	/*@Override
	public void update(MypageCommand mypage) {
		mypageMapper.update(mypage);
	}*/

	

	

	

	
	

	


	


	

	
	
	
	
}
