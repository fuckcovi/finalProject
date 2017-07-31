package com.kh.mixmatch.match.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.mixmatch.match.domain.MatchCommand;

public interface MatchMapper {

	public List<MatchCommand> matchList(Map<String, Object> map);
	public int getRowCount(Map<String,Object> map);
	@Insert("INSERT INTO g_match (m_seq,t_name,m_area,m_date,m_time,m_place,m_cost,m_content,m_type) VALUES (g_match_seq.nextval,#{t_name},#{m_area},#{m_date},#{m_time},#{m_place},#{m_cost},#{m_content,jdbcType=VARCHAR},#{m_type})")
	public void insertMatch(MatchCommand match);
	@Select("SELECT * FROM g_match WHERE m_seq=#{m_seq}")
	public MatchCommand selectMatch(Integer m_seq);
	public void updateMatch(MatchCommand match);
	@Delete("DELETE FROM g_match WHERE m_seq=#{m_seq}")
	public void deleteMatch(Integer m_seq);
	
	@Select("SELECT t_name FROM g_team WHERE id=#{id,jdbcType=VARCHAR}")
	public String getTeamName(String id);
	
}
