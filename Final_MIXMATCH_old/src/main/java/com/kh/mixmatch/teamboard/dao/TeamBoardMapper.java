package com.kh.mixmatch.teamboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import com.kh.mixmatch.notice.domain.NoticeCommand;
import com.kh.mixmatch.teamboard.domain.TeamBoardCommand;


public interface TeamBoardMapper {
	@Insert("INSERT INTO g_teamboard (tb_seq,t_name,tb_title,tb_content,tb_hit,tb_file,tb_file_name,tb_regdate,id) VALUES(g_notice_seq.nextval,#{t_name},#{tb_title},#{tb_content},#{tb_hit},#{tb_file,jdbcType=BLOB},#{tb_file_name,jdbcType=VARCHAR},sysdate,#{id})")
	public void teamboardInsert(TeamBoardCommand teamboard);
	@Delete("DELETE FROM g_teamboard WHERE tb_seq=#{tb_seq}")
	public void teamboardDelete(Integer tb_seq);
	@Update("UPDATE g_teamboard SET tb_title=#{tb_title},tb_content=#{tb_content},tb_file=#{tb_file,jdbcType=BLOB},tb_file_name=#{tb_file_name,jdbcType=VARCHAR} WHERE tb_seq=#{tb_seq}")
	public void teamboardUpdate(TeamBoardCommand teamboard);
	@Update("UPDATE g_teamboard SET tb_hit=tb_hit+1 WHERE tb_seq=#{tb_seq}")
	public void teamboardeUpdateHit(Integer tb_seq);
	@Select("SELECT * FROM g_teamboard WHERE tb_seq=#{tb_seq}")
	public TeamBoardCommand teamboardSelect(Integer tb_seq);
	@Select("SELECT COUNT(*) FROM g_teamboard")
	public int getRowCount(Map<String, Object> map);
	public List<TeamBoardCommand> teamboardList(Map<String, Object> map);
}
