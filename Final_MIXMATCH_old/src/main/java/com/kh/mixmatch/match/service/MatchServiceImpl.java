package com.kh.mixmatch.match.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kh.mixmatch.match.dao.MatchMapper;
import com.kh.mixmatch.match.domain.MatchCommand;
import com.kh.mixmatch.match.domain.TotoCommand;
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
	public void updateTeamWin(String t_name) {
		matchMapper.updateTeamWin(t_name);
	}

	@Override
	public void updateTeamDraw(String t_name) {
		matchMapper.updateTeamDraw(t_name);
	}

	@Override
	public void updateTeamLose(String t_name) {
		matchMapper.updateTeamLose(t_name);
	}

	@Override
	public void updateMemberPoint(String id) {
		matchMapper.updateMemberPoint(id);
	}

	@Override
	public void updatePointWin(String t_name) {
		matchMapper.updatePointWin(t_name);
	}

	@Override
	public void updatePointDraw(String t_name) {
		matchMapper.updatePointDraw(t_name);
	}

	@Override
	public void updatePointLose(String t_name) {
		matchMapper.updatePointLose(t_name);
	}

	@Override
	public TotoCommand insertToto(TotoCommand toto) {
		return matchMapper.insertToto(toto);
	}

}
