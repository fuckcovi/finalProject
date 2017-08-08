package com.kh.mixmatch.teamboard.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import com.kh.mixmatch.teamboard.domain.TeamBoardCommand;
@Transactional
public interface TeamBoardService {
	
	
	public void teamboardInsert(TeamBoardCommand teamboard);
	public void teamboardDelete(Integer gt_seq);
	public void teamboardUpdate(TeamBoardCommand teamboard);
	public void teamboardUpdateHit(Integer gt_seq);
	@Transactional(readOnly=true)
	public TeamBoardCommand teamboardSelect(Integer gt_seq);
	@Transactional(readOnly=true)
	public int getRowCount(Map<String, Object> map);
	@Transactional(readOnly=true)
	public List<TeamBoardCommand> teamboardList(Map<String, Object> map);
}
