package com.kh.mixmatch.team.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kh.mixmatch.team.dao.TeamMapper;
import com.kh.mixmatch.team.domain.TeamCommand;
@Service("teamService")
public class TeamServiceImpl implements TeamService {

	@Resource
	private TeamMapper teamMapper;
	
	@Override
	public List<TeamCommand> list(Map<String, Object> map) {
		return teamMapper.list(map);
	}

	@Override
	public void insertTeam(TeamCommand team) {
		teamMapper.insertTeam(team);
	}

	@Override
	public int getTeamCount(Map<String, Object> map) {
		return teamMapper.getTeamCount(map);
	}

	@Override
	public TeamCommand selectTeam(String tname) {
		return teamMapper.selectTeam(tname);
	}

	@Override
	public void updateTeam(String tname) {
		teamMapper.updateTeam(tname);
	}

	@Override
	public void deleteTeam(String tname) {
		// TODO Auto-generated method stub
		
	}
	
}
