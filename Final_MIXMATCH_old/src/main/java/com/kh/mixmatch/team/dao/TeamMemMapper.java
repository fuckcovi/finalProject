package com.kh.mixmatch.team.dao;
 
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
	@Update("UPDATE g_team_member SET t_mem_auth=1 WHERE id=#{id} AND t_name=#{t_name}")
	public void updateTeamMem(Map<String, Object> map);	// 팀마스터가 가입신청한 회원 승인 시.
	@Delete("DELETE FROM g_team_member WHERE id=#{id} AND t_name=#{t_name}")
	public void deleteTeamMem(Map<String, Object> map);	// 팀마스터가 팀회원 팀에서 탈퇴시키거나 회원이 가입철회 시.
	@Delete("DELETE FROM g_team_member WHERE t_name=#{t_name}")
	public void deleteTeam(String tname);	// 팀마스터가 팀삭제 시 해당팀 목록 삭제.
	
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
	
	
	@Select("SELECT * FROM g_member g,(SELECT t.*, tm.id master FROM g_team tm, (SELECT * FROM g_team_member WHERE t_name=#{t_name})t  WHERE t.t_name=tm.t_name)tt WHERE g.id=tt.id")
	public List<TeamMemCommand> listTeamMem(Map<String, Object> map);	// 이 팀 소속 회원,팀정보
	
	//  팀소속 랭킹
	public List<FootCommand> listTMemFoot(Map<String, Object> map);	// 정식팀원의 축구기록
	public List<BaseCommand> listTMemBase(Map<String, Object> map);	// 정식팀원의 야구기록
	public List<BasketCommand> listTMemBasket(Map<String, Object> map);	// 정식팀원의 농구기록
	
	// 매치mapper
	@Select("SELECT match.m_type,match.m_challenger,f.* FROM (SELECT * FROM g_match)match, (SELECT foot.*, m.name name FROM g_member m ,g_football foot WHERE m.id=foot.id) f WHERE match.m_seq=f.m_seq AND match.m_seq=#{m_seq}")
	public List<FootCommand> listMatchFoot(Integer mseq);	// 각 매치별 선수기록
	@Select("SELECT match.m_type,match.m_challenger,f.* FROM (SELECT * FROM g_match)match, (SELECT base.*, m.name name  FROM g_member m ,g_baseball base WHERE m.id=base.id) f WHERE match.m_seq=f.m_seq AND match.m_seq=#{m_seq}")
	public List<BaseCommand> listMatchBase(Integer mseq);	// 각 매치별 선수기록
	@Select("SELECT match.m_type,match.m_challenger,f.* FROM (SELECT * FROM g_match)match, (SELECT basket.*, m.name name FROM g_member m ,g_basketball basket WHERE m.id=basket.id) f WHERE match.m_seq=f.m_seq AND match.m_seq=#{m_seq}")
	public List<BasketCommand> listMatchBasket(Integer mseq);	// 각 매치별 선수기록
	
	
	// 경기 기록이 존재하는유저가 있는지 확인하여 개인랭킹
	public int getMemRecordCount(Map<String,Object> map);
	// 해당팀 소속 멤버의 기록이 존재하는지 확인
	public int getRowTeamMemRecordCount(Map<String, Object> map);
	// 멤버mapper
	@Select("SELECT COUNT(*) FROM g_member")	// 총 회원수
	public int getMemCount();
	
	public List<MemberCommand> getMemList(Map<String, Object> map);	// 총 회원리스트
	
}