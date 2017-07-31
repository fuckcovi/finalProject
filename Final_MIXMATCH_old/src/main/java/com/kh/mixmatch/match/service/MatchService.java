package com.kh.mixmatch.match.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.kh.mixmatch.match.domain.MatchCommand;

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
	
	public String getTeamName(String id);
	public void updateChallenger(Map<String,Object> map);
	
}
