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
	public List<FootCommand> listTMemFoot(Map<String, Object> map);	// 정식팀원의 축구기록
	@Transactional(readOnly=true)
	public List<BaseCommand> listTMemBase(Map<String, Object> map);	// 정식팀원의 야구기록
	@Transactional(readOnly=true)
	public List<BasketCommand> listTMemBasket(Map<String, Object> map);	// 정식팀원의 농구기록
	
	@Transactional(readOnly=true)
	public List<FootCommand> listMatchFoot(Integer mseq);	// 각 매치별 선수기록	
	@Transactional(readOnly=true)
	public List<BaseCommand> listMatchBase(Integer mseq);	// 각 매치별 선수기록
	@Transactional(readOnly=true)
	public List<BasketCommand> listMatchBasket(Integer mseq);	
	@Transactional(readOnly=true)
	public int getMemRecordCount(Map<String,Object> map);
	@Transactional(readOnly=true)
	public int getRowTeamMemRecordCount(Map<String, Object> map);
	
	// 멤버service
	@Transactional(readOnly=true)
	public int getMemCount();	// 총 회원 수
	@Transactional(readOnly=true)
	public List<MemberCommand> getMemList(Map<String, Object> map);	// 총 회원리스트
}
