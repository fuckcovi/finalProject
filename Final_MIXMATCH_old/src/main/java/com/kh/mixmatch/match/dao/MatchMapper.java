package com.kh.mixmatch.match.dao;

import java.util.List;
import java.util.Map;

import com.kh.mixmatch.match.domain.MatchCommand;

public interface MatchMapper {

	public List<MatchCommand> matchList(Map<String, Object> map);
	public int getRowCount(Map<String, Object> map);
	public void insertMatch(MatchCommand match);
	public MatchCommand selectMatch(Integer m_seq);
	public void updateMatch(MatchCommand match);
	public void deleteMatch(Integer m_seq);
	
}
