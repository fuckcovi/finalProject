package com.kh.mixmatch.mypage.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.mixmatch.mypage.domain.FootballCommand;
import com.kh.mixmatch.mypage.domain.MypageCommand;
import com.kh.mixmatch.mypage.domain.MypageReplyCommand;

@Transactional
public interface MypageService {
	
	//부모글
	public List<MypageCommand> list(Map<String,Object> map);		//미니홈피 글 리스트 호출
	public int getRowCount(Map<String,Object> map);		//미니홈피 글의 갯수
	public void insert(MypageCommand mypage);			//미니홈피 글등록
	public MypageCommand selectMypage(Integer seq);		//각각의 게시글


	//댓글
	public List<MypageReplyCommand> listReply(Map<String,Object> map);	//댓글목록
	public int getRowCountReply(Map<String,Object> map);
	public void insertReply(MypageReplyCommand mypageReplyCommand);		//댓글작성

	//축구기록
	public FootballCommand selectFootball(String id);
}
