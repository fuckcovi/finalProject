package com.kh.mixmatch.team.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;

import com.kh.mixmatch.team.domain.TeamCommand;

public interface TeamMapper {
	
	public List<TeamCommand> list(Map<String, Object> map);
	
	@Insert("INSERT INTO g_team (t_name,t_type,t_regdate,t_address,id,t_logo,t_logo_name) VALUES(#{t_name},#{t_type},sysdate,#{t_address},#{id},#{t_logo,jdbcType=BLOB},#{t_logo_name,jdbcType=VARCHAR})")
	public void insertTeam(TeamCommand team);
	public int getTeamCount(Map<String, Object> map);
	public TeamCommand selectTeam(String tname);
	public void updateTeam(String tname);
	public void deleteTeam(String tname);
	
}
