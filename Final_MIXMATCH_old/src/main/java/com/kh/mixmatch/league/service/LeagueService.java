package com.kh.mixmatch.league.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.kh.mixmatch.league.domain.LeagueCommand;

@Transactional
public interface LeagueService {

	@Transactional(readOnly=true)
	public List<LeagueCommand> leagueList(Map<String, Object> map);
	@Transactional(readOnly=true)
	public int getRowCount(Map<String, Object> map);
	
}
