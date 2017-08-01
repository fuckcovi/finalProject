package com.kh.mixmatch.match.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.mixmatch.match.domain.MatchCommand;
import com.kh.mixmatch.team.domain.TeamCommand;
import com.kh.mixmatch.team.domain.TeamMemCommand;

public interface MatchMapper {

	public List<MatchCommand> matchList(Map<String, Object> map);
	public int getRowCount(Map<String,Object> map);
	@Insert("INSERT INTO g_match (m_seq,t_name,m_area,m_date,m_time,m_place,m_cost,m_content,m_type) VALUES (g_match_seq.nextval,#{t_name},#{m_area},#{m_date},#{m_time},#{m_place},#{m_cost},#{m_content,jdbcType=VARCHAR},#{m_type})")
	public void insertMatch(MatchCommand match);
	@Select("SELECT m.*,t.t_logo,t.t_logo_name FROM g_match m, (SELECT * FROM g_team)t WHERE m.t_name=t.t_name AND m_seq=#{m_seq}")
	public MatchCommand selectMatch(Integer m_seq);
	@Update("UPDATE g_match SET m_area=#{m_area},m_date=#{m_date},m_time=#{m_time},m_place=#{m_place},m_cost=#{m_cost},m_content=#{m_content},m_type=#{m_type} WHERE m_seq=#{m_seq}")
	public void updateMatch(MatchCommand match);
	@Delete("DELETE FROM g_match WHERE m_seq=#{m_seq}")
	public void deleteMatch(Integer m_seq);
	
	@Update("UPDATE g_match SET m_home=#{m_home},m_away=#{m_away},m_mvp=#{m_mvp} WHERE m_seq=#{m_seq}")
	public void updateScore(MatchCommand match);
	@Select("SELECT id FROM g_team_member WHERE t_name=(SELECT t_name FROM g_match WHERE m_seq=#{m_seq}) OR t_name=(SELECT m_challenger FROM g_match WHERE m_seq=#{m_seq})")
	public List<String> getMvpMember(Integer m_seq);
	
	@Select("SELECT t_name FROM g_team WHERE id=#{id,jdbcType=VARCHAR}")
	public List<String> getTeamList(String id);
	@Select("SELECT * FROM g_team WHERE t_name=#{t_name}")
	public TeamCommand getTeam(String t_name);
	@Update("UPDATE g_match SET m_challenger=#{t_name} WHERE m_seq=#{m_seq}")
	public void updateChallenger(MatchCommand match);
	
	// ÆÀ win Áõ°¡
	@Update("UPDATE g_team SET t_win=t_win+1 WHERE t_name=#{t_name}")
	public void updateTeamWin(String t_name);
	// ÆÀ draw Áõ°¡
	@Update("UPDATE g_team SET t_draw=t_draw+1 WHERE t_name=#{t_name}")
	public void updateTeamDraw(String t_name);
	// ÆÀ lose Áõ°¡
	@Update("UPDATE g_team SET t_lose=t_lose+1 WHERE t_name=#{t_name}")
	public void updateTeamLose(String t_name);
	
	// MVP¸â¹ö Æ÷ÀÎÆ® Áõ°¡
	@Update("UPDATE g_member SET point=point+500 WHERE id=#{id}")
	public void updateMemberPoint(String id);
	// ÆÀ win ¸â¹ö Æ÷ÀÎÆ® Áõ°¡
	@Update("UPDATE g_member SET point=point+100 WHERE id IN(SELECT id FROM g_team_member WHERE t_name=#{t_name})")
	public void updatePointWin(String t_name);
	// ÆÀ draw ¸â¹ö Æ÷ÀÎÆ® Áõ°¡
	@Update("UPDATE g_member SET point=point+50 WHERE id IN(SELECT id FROM g_team_member WHERE t_name=#{t_name})")
	public void updatePointDraw(String t_name);
	// ÆÀ lose ¸â¹ö Æ÷ÀÎÆ® Áõ°¡
	@Update("UPDATE g_member SET point=point+10 WHERE id IN(SELECT id FROM g_team_member WHERE t_name=#{t_name})")
	public void updatePointLose(String t_name);
	
}
