package com.kh.mixmatch.team.service;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import com.kh.mixmatch.member.domain.MemberCommand;
import com.kh.mixmatch.team.domain.BaseCommand;
import com.kh.mixmatch.team.domain.BasketCommand;
import com.kh.mixmatch.team.domain.FootCommand;
import com.kh.mixmatch.team.domain.TeamMemCommand;

@Transactional
public interface TeamMemService {
	/*@Transactional(readOnly=true)
	public TeamMemCommand selectTeamMem(String id);
	*/
	public void insertTeamMem(TeamMemCommand teamMem);
	public void updateTeamMem(Map<String, Object> map);
	public void deleteTeamMem(Map<String, Object> map);
	public void deleteTeam(String tname);
	
	@Transactional(readOnly=true)
	public int getRowMemCount(String id);
	@Transactional(readOnly=true)
	public int getRowTeamCount(String id);
	
	
	
	@Transactional(readOnly=true)
	public List<TeamMemCommand> list(Map<String, Object> map);
	
	@Transactional(readOnly=true)
	public int getRowTeamMemCount(String t_name);
	
	@Transactional(readOnly=true)
	public List<TeamMemCommand> listTeamMem(Map<String, Object> map);
	@Transactional(readOnly=true)
	public List<TeamMemCommand> listConfirmTeam(Map<String, Object> map);
	
	
	@Transactional(readOnly=true)
	public List<FootCommand> listTMemFoot(Map<String, Object> map);	// ���������� �౸���
	@Transactional(readOnly=true)
	public List<BaseCommand> listTMemBase(Map<String, Object> map);	// ���������� �߱����
	@Transactional(readOnly=true)
	public List<BasketCommand> listTMemBasket(Map<String, Object> map);	// ���������� �󱸱��
	
	@Transactional(readOnly=true)
	public List<FootCommand> listMatchFoot(Integer mseq);	// �� ��ġ�� �������	
	@Transactional(readOnly=true)
	public List<BaseCommand> listMatchBase(Integer mseq);	// �� ��ġ�� �������
	@Transactional(readOnly=true)
	public List<BasketCommand> listMatchBasket(Integer mseq);	
	@Transactional(readOnly=true)
	public int getMemRecordCount(Map<String,Object> map);
	@Transactional(readOnly=true)
	public int getRowTeamMemRecordCount(Map<String, Object> map);
	
	// ���service
	@Transactional(readOnly=true)
	public int getMemCount();	// �� ȸ�� ��
	@Transactional(readOnly=true)
	public List<MemberCommand> getMemList(Map<String, Object> map);	// �� ȸ������Ʈ
}
