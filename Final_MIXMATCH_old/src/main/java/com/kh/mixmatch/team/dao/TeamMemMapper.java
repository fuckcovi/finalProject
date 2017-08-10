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
	/*@Select("SELECT * FROM g_team_member WHERE id=#{id}")	// ���Խ�û Ȥ�� ��ϵ� ������� ����Ʈ�γ���
	public TeamMemCommand selectTeamMem(String id);*/
	
	@Insert("INSERT INTO g_team_member (t_mem_seq,id,t_name,t_mem_regdate,t_mem_auth) VALUES (g_team_member_seq.nextval,#{id},#{t_name},sysdate,#{t_mem_auth})")
	public void insertTeamMem(TeamMemCommand teamMem);
	@Update("UPDATE g_team_member SET t_mem_auth=1 WHERE id=#{id} AND t_name=#{t_name}")
	public void updateTeamMem(Map<String, Object> map);	// �������Ͱ� ���Խ�û�� ȸ�� ���� ��.
	@Delete("DELETE FROM g_team_member WHERE id=#{id} AND t_name=#{t_name}")
	public void deleteTeamMem(Map<String, Object> map);	// �������Ͱ� ��ȸ�� ������ Ż���Ű�ų� ȸ���� ����öȸ ��.
	@Delete("DELETE FROM g_team_member WHERE t_name=#{t_name}")
	public void deleteTeam(String tname);	// �������Ͱ� ������ �� �ش��� ��� ����.
	
	@Select("SELECT COUNT(*) FROM g_team_member WHERE id=#{id}")
	public int getRowMemCount(String id);	// ������ ����/��û�� ���� ��
	@Select("SELECT COUNT(*) FROM g_team_member WHERE id=#{id} AND t_mem_auth=1")
	public int getRowTeamCount(String id);	// ������ ���ΰ����� ���� ��
	
	
	@Select("SELECT COUNT(*) FROM g_team_member WHERE t_name=#{t_name} AND t_mem_auth=1")
	public int getRowTeamMemCount(String t_name);	// �Ҽ����� �������� ��
	
	@Select("SELECT * FROM g_team_member WHERE id=#{id}")
	public List<TeamMemCommand> list(Map<String, Object> map);	// ������ ����/��û�� �� ����Ʈ
	@Select("SELECT * FROM g_team_member WHERE id=#{id} AND t_mem_auth=1")
	public List<TeamMemCommand> listConfirmTeam(Map<String, Object> map); // ������ ���ΰ����� �� ����Ʈ
	
	
	@Select("SELECT * FROM g_member g,(SELECT t.*, tm.id master FROM g_team tm, (SELECT * FROM g_team_member WHERE t_name=#{t_name})t  WHERE t.t_name=tm.t_name)tt WHERE g.id=tt.id")
	public List<TeamMemCommand> listTeamMem(Map<String, Object> map);	// �� �� �Ҽ� ȸ��,������
	
	//  ���Ҽ� ��ŷ
	public List<FootCommand> listTMemFoot(Map<String, Object> map);	// ���������� �౸���
	public List<BaseCommand> listTMemBase(Map<String, Object> map);	// ���������� �߱����
	public List<BasketCommand> listTMemBasket(Map<String, Object> map);	// ���������� �󱸱��
	
	// ��ġmapper
	@Select("SELECT match.m_type,match.m_challenger,f.* FROM (SELECT * FROM g_match)match, (SELECT foot.*, m.name name FROM g_member m ,g_football foot WHERE m.id=foot.id) f WHERE match.m_seq=f.m_seq AND match.m_seq=#{m_seq}")
	public List<FootCommand> listMatchFoot(Integer mseq);	// �� ��ġ�� �������
	@Select("SELECT match.m_type,match.m_challenger,f.* FROM (SELECT * FROM g_match)match, (SELECT base.*, m.name name  FROM g_member m ,g_baseball base WHERE m.id=base.id) f WHERE match.m_seq=f.m_seq AND match.m_seq=#{m_seq}")
	public List<BaseCommand> listMatchBase(Integer mseq);	// �� ��ġ�� �������
	@Select("SELECT match.m_type,match.m_challenger,f.* FROM (SELECT * FROM g_match)match, (SELECT basket.*, m.name name FROM g_member m ,g_basketball basket WHERE m.id=basket.id) f WHERE match.m_seq=f.m_seq AND match.m_seq=#{m_seq}")
	public List<BasketCommand> listMatchBasket(Integer mseq);	// �� ��ġ�� �������
	
	
	// ��� ����� �����ϴ������� �ִ��� Ȯ���Ͽ� ���η�ŷ
	public int getMemRecordCount(Map<String,Object> map);
	// �ش��� �Ҽ� ����� ����� �����ϴ��� Ȯ��
	public int getRowTeamMemRecordCount(Map<String, Object> map);
	// ���mapper
	@Select("SELECT COUNT(*) FROM g_member")	// �� ȸ����
	public int getMemCount();
	
	public List<MemberCommand> getMemList(Map<String, Object> map);	// �� ȸ������Ʈ
	
	// 해당아이디가 소속된 팀 리스트
	@Select("SELECT t_name FROM g_team_member WHERE id='admin' and T_Mem_Auth=1")
	public List<String> getTeamMemList(String user_id);
}