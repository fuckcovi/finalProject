package com.kh.mixmatch.team.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kh.mixmatch.member.domain.MemberCommand;
import com.kh.mixmatch.team.dao.TeamMemMapper;
import com.kh.mixmatch.team.domain.BaseCommand;
import com.kh.mixmatch.team.domain.BasketCommand;
import com.kh.mixmatch.team.domain.FootCommand;
import com.kh.mixmatch.team.domain.TeamMemCommand;

@Service("teamMemService")
public class TeamMemServiceImpl implements TeamMemService{

	@Resource
	private TeamMemMapper teamMemMapper;
	
	/*@Override
	public TeamMemCommand selectTeamMem(String id) {
		return teamMemMapper.selectTeamMem(id);
	}*/

	@Override
	public void insertTeamMem(TeamMemCommand teamMem) {
		teamMemMapper.insertTeamMem(teamMem);
	}

	@Override
	public int getRowMemCount(String id) {
		return teamMemMapper.getRowMemCount(id);
	}

	@Override
	public List<TeamMemCommand> list(Map<String, Object> map) {
		return teamMemMapper.list(map);
	}

	@Override
	public int getRowTeamMemCount(String t_name) {
		// TODO Auto-generated method stub
		return teamMemMapper.getRowTeamMemCount(t_name);
	}

	@Override
	public List<TeamMemCommand> listTeamMem(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return teamMemMapper.listTeamMem(map);
	}

	@Override
	public List<FootCommand> listTMemFoot(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return teamMemMapper.listTMemFoot(map);
	}

	@Override
	public List<BaseCommand> listTMemBase(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return teamMemMapper.listTMemBase(map);
	}

	@Override
	public List<BasketCommand> listTMemBasket(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return teamMemMapper.listTMemBasket(map);
	}

	@Override
	public int getRowTeamCount(String id) {
		// TODO Auto-generated method stub
		return teamMemMapper.getRowTeamCount(id);
	}

	@Override
	public List<TeamMemCommand> listConfirmTeam(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return teamMemMapper.listConfirmTeam(map);
	}

	@Override
	public int getMemCount() {
		// TODO Auto-generated method stub
		return teamMemMapper.getMemCount();
	}

	@Override
	public List<MemberCommand> getMemList() {
		// TODO Auto-generated method stub
		return teamMemMapper.getMemList();
	}
	
}
