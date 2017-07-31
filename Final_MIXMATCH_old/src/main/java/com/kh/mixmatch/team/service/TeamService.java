package com.kh.mixmatch.team.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import com.kh.mixmatch.match.domain.MatchCommand;
import com.kh.mixmatch.team.domain.TeamCommand;
@Transactional
public interface TeamService {
	@Transactional(readOnly=true)
	public List<TeamCommand> list(Map<String, Object> map);
	
	public void insertTeam(TeamCommand team);
	
	@Transactional(readOnly=true)
	public int getTeamCount(Map<String, Object> map);
	
	@Transactional(readOnly=true)
	public TeamCommand selectTeam(String tname);
	
	public void updateTeam(String tname);
	
	public void deleteTeam(String tname);
	
	@Transactional(readOnly=true)
	public List<TeamCommand> listRank(Map<String, Object> map);
	
	// ¸ÅÄ¡service
	@Transactional(readOnly=true)
	public List<MatchCommand> listMatch(Map<String, Object> map);
	
	
}
