package com.kh.mixmatch.teamboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.mixmatch.teamboard.domain.TeamBoardCommand;


public interface TeamBoardMapper {
	@Insert("INSERT INTO g_teamboard (gt_seq,t_name,gt_title,gt_content,gt_hit,gt_uploadfile,gt_filename,gt_regdate,ip,id) VALUES(g_teamboard_seq.nextval,#{t_name},#{gt_title},#{gt_content},#{gt_hit},#{gt_uploadfile,jdbcType=BLOB},#{gt_filename,jdbcType=VARCHAR},sysdate,#{ip},#{id})")
	public void teamboardInsert(TeamBoardCommand teamboard);
	@Delete("DELETE FROM g_teamboard WHERE gt_seq=#{gt_seq}")
	public void teamboardDelete(Integer gt_seq);
	@Update("UPDATE g_teamboard SET gt_title=#{gt_title},gt_content=#{gt_content},gt_file=#{gt_file,jdbcType=BLOB},gt_file_name=#{gt_file_name,jdbcType=VARCHAR} WHERE gt_seq=#{gt_seq}")
	public void teamboardUpdate(TeamBoardCommand teamboard);
	@Update("UPDATE g_teamboard SET gt_hit=gt_hit+1 WHERE gt_seq=#{gt_seq}")
	public void teamboardUpdateHit(Integer gt_seq);
	@Select("SELECT * FROM g_teamboard WHERE gt_seq=#{gt_seq}")
	public TeamBoardCommand teamboardSelect(Integer gt_seq);
	@Select("SELECT COUNT(*) FROM g_teamboard")
	public int getRowCount(Map<String, Object> map);
	public List<TeamBoardCommand> teamboardList(Map<String, Object> map);
}
