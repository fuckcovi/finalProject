package com.kh.mixmatch.teamboard.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kh.mixmatch.teamboard.dao.TeamBoardMapper;
import com.kh.mixmatch.teamboard.domain.TeamBoardCommand;
import com.kh.mixmatch.teamboard.domain.TeamBoardReplyCommand;
@Service("teamBoardService")
public class TeamBoardServiceImpl implements TeamBoardService{
	
	@Resource
	private TeamBoardMapper teamBoardMapper;

	@Override
	public void teamboardInsert(TeamBoardCommand teamboard) {
		teamBoardMapper.teamboardInsert(teamboard);
	}

	@Override
	public void teamboardDelete(Integer gt_seq) {
		teamBoardMapper.deleteReply(gt_seq);
		teamBoardMapper.teamboardDelete(gt_seq);
	}

	@Override
	public void teamboardUpdate(TeamBoardCommand teamboard) {
		teamBoardMapper.teamboardUpdate(teamboard);
	}

	@Override
	public void teamboardUpdateHit(Integer gt_seq) {
		teamBoardMapper.teamboardUpdateHit(gt_seq);
	} 

	@Override
	public TeamBoardCommand teamboardSelect(Integer gt_seq) {
		return teamBoardMapper.teamboardSelect(gt_seq);
	}

	@Override
	public int getTbRowCount(Map<String, Object> map) {
		return teamBoardMapper.getTbRowCount(map);
	}

	@Override
	public List<TeamBoardCommand> teamboardList(Map<String, Object> map) {
		return teamBoardMapper.teamboardList(map);
	}

	@Override
	public List<TeamBoardReplyCommand> listReply(Map<String, Object> map) {
		return teamBoardMapper.listReply(map);
	}

	@Override
	public int getRowCountReply(Map<String, Object> map) {
		return teamBoardMapper.getRowCountReply(map);
	}

	@Override
	public void insertReply(TeamBoardReplyCommand teamBoardReply) {
		teamBoardMapper.insertReply(teamBoardReply);
	}

	@Override
	public void updateReply(TeamBoardReplyCommand teamBoardReply) {
		teamBoardMapper.updateReply(teamBoardReply);
	}

	@Override
	public void deleteReply(Integer gtre_no) {
		teamBoardMapper.deleteReply(gtre_no);
	}
	
	
}
