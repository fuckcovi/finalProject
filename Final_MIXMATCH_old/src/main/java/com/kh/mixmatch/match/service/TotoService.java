package com.kh.mixmatch.match.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kh.mixmatch.match.domain.MatchCommand;
import com.kh.mixmatch.match.domain.TotoCommand;

public interface TotoService {

	public List<TotoCommand> totoList(int m_seq);
	public int getPoint(String id);
	public void updateTeamWin(String t_name);
	public void updateTeamDraw(String t_name);
	public void updateTeamLose(String t_name);
	
	public void updateMemberPoint(String id);
	public void updatePointWin(String t_name);
	public void updatePointDraw(String t_name);
	public void updatePointLose(String t_name);
	
	public void insertToto(TotoCommand toto);
	
	public ArrayList<String> totoTeamList(Map<String, Object> map);
	public ArrayList<String> totoAllList(Map<String, Object> map);
	public void upPointTeam(Map<String, Object> teamMap);
	public void upPointAll(Map<String, Object> allMap);
	public void downPoint(Map<String, Object> map);
	public void totoDraw(MatchCommand matchCommand);
	
	public ArrayList<Integer> totoTeamPoint(Map<String, Object> map);
	public ArrayList<Integer> totoAllPoint(Map<String, Object> map);
	public double totoRate(Map<String, Object> map);
	
}
