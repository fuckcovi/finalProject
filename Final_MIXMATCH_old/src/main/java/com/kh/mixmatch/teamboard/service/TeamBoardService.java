package com.kh.mixmatch.teamboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.kh.mixmatch.teamboard.domain.TeamBoardCommand;
import com.kh.mixmatch.teamboard.domain.TeamBoardReplyCommand;

@Transactional
public interface TeamBoardService {
	
	
	public void teamboardInsert(TeamBoardCommand teamboard);
	public void teamboardDelete(Integer gt_seq);
	public void teamboardUpdate(TeamBoardCommand teamboard);
	public void teamboardUpdateHit(Integer gt_seq);
	@Transactional(readOnly=true)
	public TeamBoardCommand teamboardSelect(Integer gt_seq);
	@Transactional(readOnly=true)
	public int getTbRowCount(Map<String, Object> map);
	@Transactional(readOnly=true) 
	public List<TeamBoardCommand> teamboardList(Map<String, Object> map);

	//댓글
	@Transactional(readOnly=true)
	public List<TeamBoardReplyCommand> listReply(Map<String,Object> map);
	@Transactional(readOnly=true)
	public int getRowCountReply(Map<String,Object> map);
	public void insertReply(TeamBoardReplyCommand teamBoardReply);
	public void updateReply(TeamBoardReplyCommand teamBoardReply);
	public void deleteReply(Integer gtre_no);
}
