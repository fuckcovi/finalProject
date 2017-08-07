package com.kh.mixmatch.mypage.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kh.mixmatch.mypage.dao.MypageMapper;
import com.kh.mixmatch.mypage.domain.MypageCommand;
import com.kh.mixmatch.mypage.domain.MypageCommand2;
import com.kh.mixmatch.mypage.domain.MypageReplyCommand;
import com.kh.mixmatch.team.domain.BaseCommand;
import com.kh.mixmatch.team.domain.BasketCommand;
import com.kh.mixmatch.team.domain.FootCommand;

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

	@Override
	public void delete(Integer h_seq) {
		//댓글이 존재하면 댓글을 우선 삭제하고 부모글을 삭제
		mypageMapper.deleteReplyBySeq(h_seq);
		//부모글 삭제
		mypageMapper.delete(h_seq);
	}

	@Override
	public void deleteReply(Integer h_re_seq) {
		mypageMapper.deleteReply(h_re_seq);
	}

	@Override
	public void updateReply(MypageReplyCommand mypageReply) {
		mypageMapper.updateReply(mypageReply);
	}

	@Override
	public FootCommand selectFootball(String id) {
		return mypageMapper.selectFootball(id);
	}

	@Override
	public BasketCommand selectBasketball(String id) {
		return mypageMapper.selectBasketball(id);
	}

	@Override
	public BaseCommand selectBaseball(String id) {
		return mypageMapper.selectBaseball(id);
	}

	

	

	

	

	
	

	


	


	

	
	
	
	
}
