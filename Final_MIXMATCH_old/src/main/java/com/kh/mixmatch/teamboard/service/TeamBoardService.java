package com.kh.mixmatch.teamboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.kh.mixmatch.teamboard.domain.TeamBoardCommand;
@Transactional
public interface TeamBoardService {
	public void teamboardInsert(TeamBoardCommand teamboard);
	public void teamboardDelete(Integer tb_seq);
	public void teamboardUpdate(TeamBoardCommand teamboard);
	public void teamboardeUpdateHit(Integer tb_seq);
	@Transactional(readOnly=true)
	public TeamBoardCommand teamboardSelect(Integer tb_seq);
	@Transactional(readOnly=true)
	public int getRowCount(Map<String, Object> map);
	@Transactional(readOnly=true)
	public List<TeamBoardCommand> teamboardList(Map<String, Object> map);
}
