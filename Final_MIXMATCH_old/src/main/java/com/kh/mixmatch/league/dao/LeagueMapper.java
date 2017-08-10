package com.kh.mixmatch.league.dao;

import java.util.List;
import java.util.Map;

import com.kh.mixmatch.league.domain.LeagueCommand;

public interface LeagueMapper {

	public List<LeagueCommand> leagueList(Map<String, Object> map);
	public int getRowCount(Map<String, Object> map);
	
}
