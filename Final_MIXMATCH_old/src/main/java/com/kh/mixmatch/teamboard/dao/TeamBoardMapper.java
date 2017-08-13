package com.kh.mixmatch.teamboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.mixmatch.teamboard.domain.TeamBoardCommand;
import com.kh.mixmatch.teamboard.domain.TeamBoardReplyCommand;

public interface TeamBoardMapper {
	@Insert("INSERT INTO g_teamboard (gt_seq,t_name,gt_title,gt_content,gt_hit,gt_uploadfile,gt_filename,gt_regdate,ip,id) VALUES(g_teamboard_seq.nextval,#{t_name},#{gt_title},#{gt_content},#{gt_hit},#{gt_uploadfile,jdbcType=BLOB},#{gt_filename,jdbcType=VARCHAR},sysdate,#{ip},#{id})")
	public void teamboardInsert(TeamBoardCommand teamboard);
	@Delete("DELETE FROM g_teamboard WHERE gt_seq=#{gt_seq}")
	public void teamboardDelete(Integer gt_seq);
	@Update("UPDATE g_teamboard SET gt_title=#{gt_title},gt_content=#{gt_content},gt_uploadfile=#{gt_uploadfile,jdbcType=BLOB},gt_filename=#{gt_filename,jdbcType=VARCHAR} WHERE gt_seq=#{gt_seq}")
	public void teamboardUpdate(TeamBoardCommand teamboard);
	@Update("UPDATE g_teamboard SET gt_hit=gt_hit+1 WHERE gt_seq=#{gt_seq}")
	public void teamboardUpdateHit(Integer gt_seq);
	@Select("SELECT * FROM g_teamboard WHERE gt_seq=#{gt_seq}")
	public TeamBoardCommand teamboardSelect(Integer gt_seq);
	public int getTbRowCount(Map<String, Object> map);
	public List<TeamBoardCommand> teamboardList(Map<String, Object> map);

	// 댓글
	public List<TeamBoardReplyCommand> listReply(Map<String, Object> map);
	@Select("SELECT count(*) FROM g_teamboard_re WHERE gt_seq=#{gt_seq}")
	public int getRowCountReply(Map<String,Object> map);
	@Insert("INSERT INTO g_teamboard_re (gtre_no,gtre_content,gtre_date,ip,gt_seq,id) VALUES (gtbre_seq.nextval,#{gtre_content},sysdate,#{ip},#{gt_seq},#{id})")
	public void insertReply(TeamBoardReplyCommand teamBoardReply);
	@Update("UPDATE g_teamboard_re SET gtre_content=#{gtre_content},ip=#{ip} WHERE gtre_no=#{gtre_no}")
	public void updateReply(TeamBoardReplyCommand teamBoardReply);
	@Delete("DELETE FROM g_teamboard_re WHERE gtre_no=#{gtre_no}")
	public void deleteReply(Integer gtre_no);
	
	//부모글 삭제시 댓글이 존재하면 부모글 삭제전 댓글 삭제
	@Delete("DELETE FROM g_teamboard_re WHERE gt_seq=#{gt_seq}")
	public void deleteReplyBySeq(Integer gt_seq);
}
