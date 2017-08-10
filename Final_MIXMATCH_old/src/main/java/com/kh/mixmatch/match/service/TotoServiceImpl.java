package com.kh.mixmatch.match.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kh.mixmatch.match.dao.TotoMapper;
import com.kh.mixmatch.match.domain.MatchCommand;
import com.kh.mixmatch.match.domain.TotoCommand;

@Service("totoService")
public class TotoServiceImpl implements TotoService {

	@Resource
	private TotoMapper totoMapper;
	 
	@Override
	public List<TotoCommand> totoList(int m_seq) {
		return totoMapper.totoList(m_seq);
	}
	
	@Override
	public int getPoint(String id) {
		return totoMapper.getPoint(id);
	}
	
	@Override
	public void updateTeamWin(String t_name) {
		totoMapper.updateTeamWin(t_name);
	}

	@Override
	public void updateTeamDraw(String t_name) {
		totoMapper.updateTeamDraw(t_name);
	}

	@Override
	public void updateTeamLose(String t_name) {
		totoMapper.updateTeamLose(t_name);
	}

	@Override
	public void updateMemberPoint(String id) {
		totoMapper.updateMemberPoint(id);
	}

	@Override
	public void updatePointWin(String t_name) {
		totoMapper.updatePointWin(t_name);
	}

	@Override
	public void updatePointDraw(String t_name) {
		totoMapper.updatePointDraw(t_name);
	}

	@Override
	public void updatePointLose(String t_name) {
		totoMapper.updatePointLose(t_name);
	}

	@Override
	public void insertToto(TotoCommand toto) {
		totoMapper.insertToto(toto);
	}
	
	@Override
	public ArrayList<String> totoTeamList(Map<String, Object> map) {
		return totoMapper.totoTeamList(map);
	}

	@Override
	public ArrayList<String> totoAllList(Map<String, Object> map) {
		return totoMapper.totoAllList(map);
	}
	
	@Override
	public void upPointTeam(Map<String, Object> teamMap) {
		totoMapper.upPointTeam(teamMap);
	}
	
	@Override
	public void upPointAll(Map<String, Object> allMap) {
		totoMapper.upPointAll(allMap);
	}
	
	@Override
	public void downPoint(Map<String, Object> map) {
		totoMapper.downPoint(map);
	}
	
	@Override
	public void totoDraw(MatchCommand matchCommand) {
		totoMapper.totoDraw(matchCommand);		
	}
	
	@Override
	public ArrayList<Integer> totoTeamPoint(Map<String, Object> map) {
		return totoMapper.totoTeamPoint(map);
	}
	
	@Override
	public ArrayList<Integer> totoAllPoint(Map<String, Object> map) {
		return totoMapper.totoAllPoint(map);
	}

	@Override
	public double totoRate(Map<String, Object> map) {
		return totoMapper.totoRate(map);
	}
	
}
