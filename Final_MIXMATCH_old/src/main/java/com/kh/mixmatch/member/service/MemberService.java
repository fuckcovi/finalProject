package com.kh.mixmatch.member.service;

import org.springframework.transaction.annotation.Transactional;

import com.kh.mixmatch.member.domain.MemberCommand;

@Transactional
public interface MemberService {
	public void insertMember(MemberCommand member);
	@Transactional(readOnly=true)
	public MemberCommand selectMember(String id);
	public void updateMember(MemberCommand member);
	public void updatePw(MemberCommand member);
	public void deleteMember(String id);
}
