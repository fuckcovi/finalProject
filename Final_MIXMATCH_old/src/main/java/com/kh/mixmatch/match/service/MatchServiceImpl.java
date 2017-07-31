package com.kh.mixmatch.match.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kh.mixmatch.match.dao.MatchMapper;
import com.kh.mixmatch.match.domain.MatchCommand;

@Service("matchService")
public class MatchServiceImpl implements MatchService {

	@Resource
	private MatchMapper matchMapper;
	
	@Override
	public List<MatchCommand> matchList(Map<String, Object> map) {
		return matchMapper.matchList(map);
	}

	@Override
	public int getRowCount(Map<String,Object> map) {
		return matchMapper.getRowCount(map);
	}

	@Override
	public void insertMatch(MatchCommand match) {
		matchMapper.insertMatch(match);
	}

	@Override
	public MatchCommand selectMatch(Integer m_seq) {
		return matchMapper.selectMatch(m_seq);
	}

	@Override
	public void updateMatch(MatchCommand match) {
		matchMapper.updateMatch(match);
	}

	@Override
	public void deleteMatch(Integer m_seq) {
		matchMapper.deleteMatch(m_seq);
	}

	@Override
	public String getTeamName(String id) {
		return matchMapper.getTeamName(id);
	}

	@Override
	public void updateChallenger(Map<String,Object> map) {
		matchMapper.updateChallenger(map); 
	}

}
