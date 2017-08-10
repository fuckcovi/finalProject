package com.kh.mixmatch.league.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kh.mixmatch.league.dao.LeagueMapper;
import com.kh.mixmatch.league.domain.LeagueCommand;

@Service("leagueService")
public class LeagueServiceImpl implements LeagueService {
	
	@Resource
	private LeagueMapper leagueMapper;

	@Override
	public List<LeagueCommand> leagueList(Map<String, Object> map) {
		return leagueMapper.leagueList(map);
	}

	@Override
	public int getRowCount(Map<String, Object> map) {
		return leagueMapper.getRowCount(map);
	}

}
