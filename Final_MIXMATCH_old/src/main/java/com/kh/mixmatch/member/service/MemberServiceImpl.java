package com.kh.mixmatch.member.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kh.mixmatch.member.dao.MemberMapper;
import com.kh.mixmatch.member.domain.MemberCommand;
import com.kh.mixmatch.team.dao.TeamMapper;
import com.kh.mixmatch.team.domain.TeamCommand;
@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Resource
	private MemberMapper memberMapper;
	
	@Override
	public void insertMember(MemberCommand member) {
		memberMapper.insertMember(member);
	}

	@Override
	public MemberCommand selectMember(String id) {
		return memberMapper.selectMember(id);
	}

	@Override
	public void updateMember(String id) {
		memberMapper.updateMember(id);
	}

	@Override
	public void deleteMember(String id) {
		memberMapper.deleteMember(id);
	}
	
	
}
