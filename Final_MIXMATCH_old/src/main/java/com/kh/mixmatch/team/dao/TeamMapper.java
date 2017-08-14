package com.kh.mixmatch.team.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.mixmatch.match.domain.MatchCommand;
import com.kh.mixmatch.team.domain.TeamCommand;

public interface TeamMapper {
	
	public List<TeamCommand> list(Map<String, Object> map);
	
	@Insert("INSERT INTO g_team (t_name,t_type,t_regdate,t_address,id,t_logo,t_logo_name) VALUES(#{t_name},#{t_type},sysdate,#{t_address},#{id},#{t_logo,jdbcType=BLOB},#{t_logo_name,jdbcType=VARCHAR})")
	public void insertTeam(TeamCommand team);
	
	
	public int getTeamCount(Map<String, Object> map);
	
	@Select("SELECT * FROM g_team WHERE t_name=#{t_name}")
	public TeamCommand selectTeam(String tname);
	@Update("UPDATE g_team SET t_type=#{t_type},t_address=#{t_address},t_logo=#{t_logo,jdbcType=BLOB},t_logo_name=#{t_logo_name,jdbcType=VARCHAR} WHERE t_name=#{t_name}")
	public void updateTeam(TeamCommand team);
	@Delete("DELETE FROM g_team WHERE t_name=#{t_name}")
	public void deleteTeam(String tname);
	
	public List<TeamCommand> listRank(Map<String, Object> map);
	
	// 팀장인 팀 리스트
	@Select("SELECT * FROM g_team WHERE id=#{id}")
	public List<TeamCommand> listMaster(String id);
	@Select("SELECT COUNT(*) FROM g_team WHERE id=#{id}")
	public int countMasterTeam(String id);
	
	// 매치 mapper
	@Select("SELECT t.t_logo_name,t.t_logo,m.* FROM g_team t , (SELECT * FROM g_match)m WHERE t.t_name=m.t_name")	// teamInfo에서 호출
	public List<MatchCommand> listMatch(Map<String, Object> map); // 매치 리스트 다 가져오기
	@Select("SELECT COUNT(*) FROM g_match WHERE t_name=#{t_name}")
	public int countHomeMatch(String tname);	// 매치수
	@Select("SELECT COUNT(*) FROM g_match WHERE m_challenger=#{t_name}")
	public int countAwayMatch(String tname);	// 매치수
	@Select("SELECT * FROM g_match WHERE m_seq=#{m_seq}")
	public MatchCommand selectMatchDetail(Integer mseq);// 매치번호로 매치내용 찾기
	
}