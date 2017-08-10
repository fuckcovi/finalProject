package com.kh.mixmatch.match.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.mixmatch.match.domain.MatchCommand;
import com.kh.mixmatch.match.domain.TotoCommand;

public interface TotoMapper {

	@Select("SELECT * FROM g_toto WHERE m_seq=#{m_seq}")
	public List<TotoCommand> totoList(int m_seq);
	// 포인트 가져오기
	@Select("SELECT point FROM g_member WHERE id=#{id}")
	public int getPoint(String id);
	// 팀 win 증가
	@Update("UPDATE g_team SET t_win=t_win+1 WHERE t_name=#{t_name}")
	public void updateTeamWin(String t_name);
	// 팀 draw 증가
	@Update("UPDATE g_team SET t_draw=t_draw+1 WHERE t_name=#{t_name}")
	public void updateTeamDraw(String t_name);
	// 팀 lose 증가
	@Update("UPDATE g_team SET t_lose=t_lose+1 WHERE t_name=#{t_name}")
	public void updateTeamLose(String t_name);
	
	// MVP멤버 포인트 증가
	@Update("UPDATE g_member SET point=point+500 WHERE id=#{id}")
	public void updateMemberPoint(String id);
	// 팀 win 멤버 포인트 증가
	@Update("UPDATE g_member SET point=point+100 WHERE id IN(SELECT id FROM g_team_member WHERE t_name=#{t_name})")
	public void updatePointWin(String t_name);
	// 팀 draw 멤버 포인트 증가
	@Update("UPDATE g_member SET point=point+50 WHERE id IN(SELECT id FROM g_team_member WHERE t_name=#{t_name})")
	public void updatePointDraw(String t_name);
	// 팀 lose 멤버 포인트 증가
	@Update("UPDATE g_member SET point=point+10 WHERE id IN(SELECT id FROM g_team_member WHERE t_name=#{t_name})")
	public void updatePointLose(String t_name);

	// 베팅하기
	@Insert("INSERT INTO g_toto (t_seq,m_seq,id,t_point,t_winteam,t_score,t_rate) VALUES (g_toto_seq.nextval,#{m_seq},#{id},#{t_point},#{t_winteam},#{t_score},ROUND(#{t_rate},1))")
	public void insertToto(TotoCommand toto);
	
	// 베팅한 멤버 포인트 증가
	// 이긴팀만 맞춘 멤버ID 
	@Select("SELECT id FROM g_toto WHERE t_winteam=#{team} and t_score!=#{score} and m_seq=#{m_seq}")
	public ArrayList<String> totoTeamList(Map<String, Object> map);
	// 이긴팀과 점수를 맞춘 멤버ID
	@Select("SELECT id FROM g_toto WHERE t_winteam=#{team} and t_score=#{score} and m_seq=#{m_seq}")
	public ArrayList<String> totoAllList(Map<String, Object> map);
	// 이긴팀만 맞춘 멤버 포인트 증가
	@Update("UPDATE g_member SET point=point+#{point} WHERE id=#{teamList}")
	public void upPointTeam(Map<String, Object> teamMap);
	// 이긴팀과 점수를 맞춘 멤버 포인트 증가
	@Update("UPDATE g_member SET point=point+#{point} WHERE id=#{allList}")
	public void upPointAll(Map<String, Object> allMap);
	// 베팅한 멤버 포인트 감소
	@Update("UPDATE g_member SET point=point-#{point} WHERE id=#{id}")
	public void downPoint(Map<String, Object> map);
	// 비긴 경우 베팅한 멤버 포인트 증가
	@Update("UPDATE g_member SET point=point+100 WHERE id IN(SELECT id FROM g_toto WHERE t_winteam=#{t_name} OR t_winteam=#{m_challenger} AND m_seq=#{m_seq})")
	public void totoDraw(MatchCommand matchCommand);
	
	// 이긴팀만 맞춘 멤버가 베팅한 포인트 가져오기
	@Select("SELECT t_point FROM g_toto WHERE t_winteam=#{team} and t_score!=#{score} and m_seq=#{m_seq}")
	public ArrayList<Integer> totoTeamPoint(Map<String, Object> map);
	// 이긴팀과 점수를 맞춘 멤버가 베팅한 포인트 가져오기
	@Select("SELECT t_point FROM g_toto WHERE t_winteam=#{team} and t_score=#{score} and m_seq=#{m_seq}")
	public ArrayList<Integer> totoAllPoint(Map<String, Object> map);
	// 베팅 실패한 멤버 포인트 가져오기
	@Select("SELECT t_point FROM g_toto WHERE t_winteam!=#{team} and m_seq=#{m_seq}")
	public ArrayList<Integer> totoFailPoint(Map<String, Object> map);
	// 배당률 가져오기
	@Select("SELECT DISTINCT t_rate FROM g_toto WHERE t_winteam=#{team} and m_seq=#{m_seq}")
	public double totoRate(Map<String, Object> map);
	
}
