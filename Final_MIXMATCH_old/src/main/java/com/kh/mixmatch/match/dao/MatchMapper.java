package com.kh.mixmatch.match.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.mixmatch.match.domain.MatchCommand;
import com.kh.mixmatch.team.domain.TeamMemCommand;

public interface MatchMapper {

	public List<MatchCommand> matchList(Map<String, Object> map);
	public int getRowCount(Map<String,Object> map);
	@Insert("INSERT INTO g_match (m_seq,t_name,m_area,m_date,m_time,m_place,m_cost,m_content,m_type) VALUES (g_match_seq.nextval,#{t_name},#{m_area},#{m_date},#{m_time},#{m_place},#{m_cost},#{m_content,jdbcType=VARCHAR},#{m_type})")
	public void insertMatch(MatchCommand match);
	@Select("SELECT * FROM g_match WHERE m_seq=#{m_seq}")
	public MatchCommand selectMatch(Integer m_seq);
	@Update("UPDATE g_match SET m_area=#{m_area},m_date=#{m_date},m_time=#{m_time},m_place=#{m_place},m_cost=#{m_cost},m_content=#{m_content},m_type=#{m_type} WHERE m_seq=#{m_seq}")
	public void updateMatch(MatchCommand match);
	@Delete("DELETE FROM g_match WHERE m_seq=#{m_seq}")
	public void deleteMatch(Integer m_seq);
	
	@Update("UPDATE g_match SET m_home=#{m_home},m_away=#{m_away},m_mvp=#{m_mvp} WHERE m_seq=#{m_seq}")
	public void updateScore(MatchCommand match);
	
	@Select("SELECT t_name FROM g_team WHERE id=#{id,jdbcType=VARCHAR}")
	public List<String> getTeamList(String id);
	@Select("SELECT t_name FROM g_team WHERE id=#{id,jdbcType=VARCHAR}")
	public String getTeamName(String id);
	@Update("UPDATE g_match SET m_challenger=#{t_name} WHERE m_seq=#{m_seq}")
	public void updateChallenger(MatchCommand match);
	
}
