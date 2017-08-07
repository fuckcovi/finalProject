package com.kh.mixmatch.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.kh.mixmatch.mypage.domain.MypageCommand;
import com.kh.mixmatch.mypage.domain.MypageCommand2;
import com.kh.mixmatch.mypage.domain.MypageReplyCommand;
import com.kh.mixmatch.team.domain.BaseCommand;
import com.kh.mixmatch.team.domain.BasketCommand;
import com.kh.mixmatch.team.domain.FootCommand;

@Transactional
public interface MypageService {
	
	//부모글
	public List<MypageCommand> list(Map<String,Object> map);		//미니홈피 글 리스트 호출
	public int getRowCount(Map<String,Object> map);		//미니홈피 글의 갯수
	public void insert(MypageCommand mypage);			//미니홈피 글등록
	public MypageCommand selectMypage(Integer seq);		//각각의 게시글
	public void update(MypageCommand2 mypage);			//글수정
	public void delete(Integer h_seq);					//미니홈피 글삭제

	//댓글
	public List<MypageReplyCommand> listReply(Map<String,Object> map);	//댓글목록
	public int getRowCountReply(Map<String,Object> map);
	public void insertReply(MypageReplyCommand mypageReplyCommand);		//댓글작성
	public void updateReply(MypageReplyCommand mypageReply);			//댓글수정
	public void deleteReply(Integer h_re_seq);
	
	//축구기록
	public FootCommand selectFootball(String id);
	
	//농구기록
	public BasketCommand selectBasketball(String id);
	
	//야구기록
	public BaseCommand selectBaseball(String id);
}
