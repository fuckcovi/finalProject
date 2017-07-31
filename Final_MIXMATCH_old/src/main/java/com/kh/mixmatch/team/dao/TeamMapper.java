package com.kh.mixmatch.team.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.kh.mixmatch.match.domain.MatchCommand;
import com.kh.mixmatch.team.domain.TeamCommand;

public interface TeamMapper {
	
	@Select("SELECT * FROM g_team ORDER BY t_regdate DESC")
	public List<TeamCommand> list(Map<String, Object> map);
	
	@Insert("INSERT INTO g_team (t_name,t_type,t_regdate,t_address,id,t_logo,t_logo_name) VALUES(#{t_name},#{t_type},sysdate,#{t_address},#{id},#{t_logo,jdbcType=BLOB},#{t_logo_name,jdbcType=VARCHAR})")
	public void insertTeam(TeamCommand team);
	
	
	public int getTeamCount(Map<String, Object> map);
	
	@Select("SELECT * FROM g_team WHERE t_name=#{t_name}")
	public TeamCommand selectTeam(String tname);
	
	public void updateTeam(String tname);
	public void deleteTeam(String tname);
	
	
	public List<TeamCommand> listRank(Map<String, Object> map);
	
	
	// 매치 mapper
	@Select("SELECT t.t_logo_name,t.t_logo,m.* FROM g_team t , (SELECT * FROM g_match)m WHERE t.t_name=m.t_name")	// teamInfo에서 호출
	public List<MatchCommand> listMatch(Map<String, Object> map);
	
}
