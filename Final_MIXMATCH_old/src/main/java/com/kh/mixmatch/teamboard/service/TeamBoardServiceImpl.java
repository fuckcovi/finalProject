package com.kh.mixmatch.teamboard.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kh.mixmatch.teamboard.dao.TeamBoardMapper;
import com.kh.mixmatch.teamboard.domain.TeamBoardCommand;
@Service("teamBoardService")
public class TeamBoardServiceImpl implements TeamBoardService{
	
	@Resource
	private TeamBoardMapper teamBoardMapper;

	@Override
	public void teamboardInsert(TeamBoardCommand teamboard) {
		teamBoardMapper.teamboardInsert(teamboard);
	}

	@Override
	public void teamboardDelete(Integer tb_seq) {
		teamBoardMapper.teamboardDelete(tb_seq);
	}

	@Override
	public void teamboardUpdate(TeamBoardCommand teamboard) {
		teamBoardMapper.teamboardUpdate(teamboard);
	}

	@Override
	public void teamboardeUpdateHit(Integer tb_seq) {
		teamBoardMapper.teamboardeUpdateHit(tb_seq);
	}

	@Override
	public TeamBoardCommand teamboardSelect(Integer tb_seq) {
		return teamBoardMapper.teamboardSelect(tb_seq);
	}

	@Override
	public int getRowCount(Map<String, Object> map) {
		return teamBoardMapper.getRowCount(map);
	}

	@Override
	public List<TeamBoardCommand> teamboardList(Map<String, Object> map) {
		return teamBoardMapper.teamboardList(map);
	}
	
	
}
