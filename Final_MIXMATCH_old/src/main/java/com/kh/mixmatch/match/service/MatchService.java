package com.kh.mixmatch.match.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.kh.mixmatch.match.domain.MatchCommand;
import com.kh.mixmatch.team.domain.TeamCommand;

@Transactional
public interface MatchService {

	@Transactional(readOnly=true)
	public List<MatchCommand> matchList(Map<String, Object> map);
	@Transactional(readOnly=true)
	public int getRowCount(Map<String,Object> map);
	public void insertMatch(MatchCommand match);
	@Transactional(readOnly=true)
	public MatchCommand selectMatch(Integer m_seq);
	public void updateMatch(MatchCommand match);
	public void deleteMatch(Integer m_seq);
	
	public void updateScore(MatchCommand match);
	public List<String> getMvpMember(Integer m_seq);
	
	public List<String> getTeamList(String id);
	public TeamCommand getTeam(String t_name);
	public void updateChallenger(MatchCommand match);
	
	public List<String> getTeamType(Map<String, Object> map);
	public List<TeamCommand> getTeamType2(String id);
	
}
