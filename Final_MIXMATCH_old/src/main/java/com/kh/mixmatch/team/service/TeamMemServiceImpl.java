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
		return teamMemMapper.getRowTeamMemCount(t_name);
	}

	@Override
	public List<TeamMemCommand> listTeamMem(Map<String, Object> map) {
		return teamMemMapper.listTeamMem(map);
	}

	@Override
	public List<FootCommand> listTMemFoot(Map<String, Object> map) {
		return teamMemMapper.listTMemFoot(map);
	}

	@Override
	public List<BaseCommand> listTMemBase(Map<String, Object> map) {
		return teamMemMapper.listTMemBase(map);
	}

	@Override
	public List<BasketCommand> listTMemBasket(Map<String, Object> map) {
		return teamMemMapper.listTMemBasket(map);
	}

	@Override
	public int getRowTeamCount(String id) {
		return teamMemMapper.getRowTeamCount(id);
	}

	@Override
	public List<TeamMemCommand> listConfirmTeam(Map<String, Object> map) {
		return teamMemMapper.listConfirmTeam(map);
	}

	@Override
	public int getMemCount() {
		return teamMemMapper.getMemCount();
	}

	@Override
	public List<MemberCommand> getMemList(Map<String, Object> map) {
		return teamMemMapper.getMemList(map);
	}
	
	@Override
	public void updateTeamMem(Map<String, Object> map) {
		teamMemMapper.updateTeamMem(map);
		
	}

	@Override
	public void deleteTeamMem(Map<String, Object> map) {
		teamMemMapper.deleteTeamMem(map);
		
	}

	@Override
	public List<FootCommand> listMatchFoot(Integer mseq) {
		return teamMemMapper.listMatchFoot(mseq);
	}

	@Override
	public void deleteTeam(String tname) {
		teamMemMapper.deleteTeam(tname);
	}

	@Override
	public List<BaseCommand> listMatchBase(Integer mseq) {
		return teamMemMapper.listMatchBase(mseq);
	}

	@Override
	public List<BasketCommand> listMatchBasket(Integer mseq) {
		return teamMemMapper.listMatchBasket(mseq);
	}

	@Override
	public int getMemRecordCount(Map<String, Object> map) {
		return teamMemMapper.getMemRecordCount(map);
	}

	@Override
	public int getRowTeamMemRecordCount(Map<String, Object> map) {
		return teamMemMapper.getRowTeamMemRecordCount(map);
	}

	@Override
	public List<String> getTeamMemList(String user_id) {
		return teamMemMapper.getTeamMemList(user_id);
	}
	
}
