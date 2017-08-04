package com.kh.mixmatch.mypage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.mixmatch.mypage.domain.FootballCommand;
import com.kh.mixmatch.mypage.domain.MypageCommand;
import com.kh.mixmatch.mypage.domain.MypageCommand2;
import com.kh.mixmatch.mypage.domain.MypageReplyCommand;

public interface MypageMapper {
	
	//부모글
	public List<MypageCommand> list(Map<String,Object> map);		//미니홈피 글 리스트 호출
	@Select("SELECT count(*) FROM g_home WHERE id=#{user_id}")
	public int getRowCount(Map<String,Object> map);					//미니홈피 글의 갯수
	@Insert("INSERT INTO g_home(h_seq,id,h_regdate,h_content,h_show,h_file,h_file_name) VALUES(g_home_seq.nextval,#{id},sysdate,#{h_content},#{h_show},#{h_file,jdbcType=BLOB},#{h_file_name,jdbcType=VARCHAR})")
	public void insert(MypageCommand mypage);						//미니홈피 글등록
	@Select("SELECT * FROM g_home WHERE h_seq=#{seq}")
	public MypageCommand selectMypage(Integer seq);					//각각의 게시글
	public void update(MypageCommand2 mypage);						//글수정
	

	//댓글
	public List<MypageReplyCommand> listReply(Map<String,Object> map);	//댓글목록
	@Select("SELECT count(*) FROM g_home_re WHERE h_seq=#{h_seq}")		//댓글 총 갯수
	public int getRowCountReply(Map<String,Object> map);
	@Insert("INSERT INTO g_home_re (h_re_seq,h_seq,h_re_content,h_re_regdate,id)VALUES(g_home_re_seq.nextval,#{h_seq},#{h_re_content},sysdate,#{id})")
	public void insertReply(MypageReplyCommand mypageReplyCommand);		//댓글작성


	//축구기록
	@Select("SELECT * FROM g_football WHERE id=#{id}")
	public FootballCommand selectFootball(String id);

}
