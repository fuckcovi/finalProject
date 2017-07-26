package com.kh.mixmatch.team.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kh.mixmatch.team.dao.TeamMemMapper;
import com.kh.mixmatch.team.domain.TeamMemCommand;

@Service("teamMemService")
public class TeamMemServiceImpl implements TeamMemService{

	@Resource
	private TeamMemMapper teamMemMapper;
	
	@Override
	public TeamMemCommand selectTeamMem(String id) {
		// TODO Auto-generated method stub
		return teamMemMapper.selectTeamMem(id);
	}
	
}
