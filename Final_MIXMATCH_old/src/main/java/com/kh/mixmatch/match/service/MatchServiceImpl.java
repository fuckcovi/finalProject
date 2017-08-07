package com.kh.mixmatch.match.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kh.mixmatch.match.dao.MatchMapper;
import com.kh.mixmatch.match.domain.MatchCommand;
import com.kh.mixmatch.team.domain.TeamCommand;

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
	public void updateScore(MatchCommand match) {
		matchMapper.updateScore(match);
	}
	
	@Override
	public List<String> getMvpMember(Integer m_seq) {
		return matchMapper.getMvpMember(m_seq);
	}

	@Override
	public List<String> getTeamList(String id) {
		return matchMapper.getTeamList(id);
	}
	
	@Override
	public TeamCommand getTeam(String t_name) {
		return matchMapper.getTeam(t_name);
	}
	
	@Override
	public void updateChallenger(MatchCommand match) {
		matchMapper.updateChallenger(match); 
	}
	
	@Override
	public List<String> getTeamType(Map<String, Object> map) {
		return matchMapper.getTeamType(map);
	}
	
	@Override
	public List<TeamCommand> getTeamType2(String id) {
		return matchMapper.getTeamType2(id);
	}

}
