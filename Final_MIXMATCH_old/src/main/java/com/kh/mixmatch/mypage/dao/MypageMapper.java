package com.kh.mixmatch.mypage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.mixmatch.mypage.domain.MypageCommand;
import com.kh.mixmatch.mypage.domain.MypageCommand2;
import com.kh.mixmatch.mypage.domain.MypageReplyCommand;
import com.kh.mixmatch.team.domain.BaseCommand;
import com.kh.mixmatch.team.domain.BasketCommand;
import com.kh.mixmatch.team.domain.FootCommand;

public interface MypageMapper {
	
	//부모글
	public List<MypageCommand> list(Map<String,Object> map);		//미니홈피 글 리스트 호출
	@Select("SELECT count(*) FROM g_home WHERE id=#{id}")
	public int getRowCount(Map<String,Object> map);					//미니홈피 총 글의 갯수
	@Select("SELECT count(*) FROM g_home WHERE id=#{id} and h_show='y'")
	public int getShowRowCount(Map<String,Object> map);				//미니홈피 전체공개인 글의 갯수
	@Insert("INSERT INTO g_home(h_seq,id,h_regdate,h_content,h_show,h_file,h_file_name) VALUES(g_home_seq.nextval,#{id},sysdate,#{h_content},#{h_show},#{h_file,jdbcType=BLOB},#{h_file_name,jdbcType=VARCHAR})")
	public void insert(MypageCommand mypage);						//미니홈피 글등록
	@Select("SELECT * FROM g_home WHERE h_seq=#{seq}")
	public MypageCommand selectMypage(Integer seq);					//각각의 게시글
	public void update(MypageCommand2 mypage);						//미니홈피 글수정
	@Delete("DELETE FROM g_home WHERE h_seq=#{h_seq}")
	public void delete(Integer h_seq);								//미니홈피 글삭제

	//댓글
	public List<MypageReplyCommand> listReply(Map<String,Object> map);	//댓글목록
	@Select("SELECT count(*) FROM g_home_re WHERE h_seq=#{h_seq}")		//댓글 총 갯수
	public int getRowCountReply(Map<String,Object> map);
	@Insert("INSERT INTO g_home_re (h_re_seq,h_seq,h_re_content,h_re_regdate,id)VALUES(g_home_re_seq.nextval,#{h_seq},#{h_re_content},sysdate,#{id})")
	public void insertReply(MypageReplyCommand mypageReplyCommand);		//댓글작성
	@Update("UPDATE g_home_re SET h_re_content=#{h_re_content} WHERE h_re_seq=#{h_re_seq}")
	public void updateReply(MypageReplyCommand mypageReply);			//댓글수정
	@Delete("DELETE FROM g_home_re WHERE h_re_seq=#{h_re_seq}")		 	//댓글삭제
	public void deleteReply(Integer h_re_seq);

	//축구기록
	@Select("SELECT * FROM (SELECT * FROM g_football ORDER BY f_seq DESC) WHERE ROWNUM <=1 AND id=#{id}")
	public FootCommand selectFootball(String id);
	
	//농구기록
	@Select("SELECT * FROM (SELECT * FROM g_basketball ORDER BY b_seq DESC) WHERE ROWNUM <=1 AND id=#{id}")
	public BasketCommand selectBasketball(String id);
	
	//야구기록
	@Select("SELECT * FROM (SELECT * FROM g_baseball ORDER BY b_seq DESC) WHERE ROWNUM <=1 AND id=#{id}")
	public BaseCommand selectBaseball(String id);
	
	//부모글 삭제시 댓글이 존재하면 부모글 삭제전 댓글 삭제
	@Delete("DELETE FROM g_home_re WHERE h_seq=#{h_seq}")
	public void deleteReplyBySeq(Integer h_seq);

}
