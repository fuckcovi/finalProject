package com.kh.mixmatch.team.dao;
 
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.kh.mixmatch.member.domain.MemberCommand;
import com.kh.mixmatch.team.domain.BaseCommand;
import com.kh.mixmatch.team.domain.BasketCommand;
import com.kh.mixmatch.team.domain.FootCommand;
import com.kh.mixmatch.team.domain.TeamCommand;
import com.kh.mixmatch.team.domain.TeamMemCommand;

public interface TeamMemMapper {
	/*@Select("SELECT * FROM g_team_member WHERE id=#{id}")	// 가입신청 혹은 등록된 팀목록이 리스트로나옴
	public TeamMemCommand selectTeamMem(String id);*/
	
	@Insert("INSERT INTO g_team_member (t_mem_seq,id,t_name,t_mem_regdate,t_mem_auth) VALUES (g_team_member_seq.nextval,#{id},#{t_name},sysdate,#{t_mem_auth})")
	public void insertTeamMem(TeamMemCommand teamMem);
	
	@Select("SELECT COUNT(*) FROM g_team_member WHERE id=#{id}")
	public int getRowMemCount(String id);	// 유저가 가입/신청한 팀의 수
	@Select("SELECT COUNT(*) FROM g_team_member WHERE id=#{id} AND t_mem_auth=1")
	public int getRowTeamCount(String id);	// 유저가 승인가입한 팀의 수
	
	
	@Select("SELECT COUNT(*) FROM g_team_member WHERE t_name=#{t_name} AND t_mem_auth=1")
	public int getRowTeamMemCount(String t_name);	// 소속팀의 정식팀원 수
	
	@Select("SELECT * FROM g_team_member WHERE id=#{id}")
	public List<TeamMemCommand> list(Map<String, Object> map);	// 유저가 가입/신청한 팀 리스트
	@Select("SELECT * FROM g_team_member WHERE id=#{id} AND t_mem_auth=1")
	public List<TeamMemCommand> listConfirmTeam(Map<String, Object> map); // 유저가 승인가입한 팀 리스트
	
	
	@Select("SELECT * FROM g_member g,(SELECT * FROM g_team_member WHERE t_name=#{t_name})t WHERE g.id=t.id")
	public List<TeamMemCommand> listTeamMem(Map<String, Object> map);
	
	/*@Select("SELECT * FROM g_football g ,(SELECT * FROM g_team_member WHERE t_name=#{t_name} AND t_mem_auth=1)t WHERE g.id=t.id")*/
	@Select("SELECT * FROM g_member m, (SELECT id,sum(f_goal) AS f_goal,sum(f_assist) f_assist,sum(f_shoot) f_shoot,sum(f_attack) f_attack FROM (SELECT * FROM g_football WHERE t_name=#{t_name}) GROUP BY ROLLUP(id))r WHERE m.id=r.id")
	public List<FootCommand> listTMemFoot(Map<String, Object> map);	// 정식팀원의 축구기록
	/*@Select("SELECT * FROM g_baseball g ,(SELECT * FROM g_team_member WHERE t_name=#{t_name} AND t_mem_auth=1)t WHERE g.id=t.id")*/
	@Select("SELECT * FROM g_member m, (SELECT id,sum(b_bat) b_bat,sum(b_hit) b_hit,sum(b_rbi) b_rbi,sum(b_score) b_score, sum(b_win) b_win, sum(b_lose) b_lose, sum(b_strike) b_strike, sum(b_ip) b_ip, sum(b_er) b_er  FROM (SELECT * FROM g_baseball WHERE t_name=#{t_name}) GROUP BY ROLLUP(id))r WHERE m.id=r.id")
	public List<BaseCommand> listTMemBase(Map<String, Object> map);	// 정식팀원의 야구기록
	/*@Select("SELECT * FROM g_football g ,(SELECT * FROM g_team_member WHERE t_name=#{t_name} AND t_mem_auth=1)t WHERE g.id=t.id")*/
	@Select("SELECT * FROM g_member m , (SELECT id,sum(b_3point) AS b_3point,sum(b_assist) b_assist,sum(b_steel) b_steel,sum(b_score) b_score,sum(b_block) b_block,sum(b_rebound) b_rebound FROM (SELECT * FROM g_basketball WHERE t_name=#{t_name}) GROUP BY ROLLUP(id))r WHERE m.id=r.id")
	public List<BasketCommand> listTMemBasket(Map<String, Object> map);	// 정식팀원의 농구기록
	
	
	// 멤버mapper
	@Select("SELECT COUNT(*) FROM g_member")	// 총 회원수
	public int getMemCount();
	
	@Select("SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM g_member ORDER BY point DESC)a)")
	public List<MemberCommand> getMemList();	// 총 회원리스트
	
}