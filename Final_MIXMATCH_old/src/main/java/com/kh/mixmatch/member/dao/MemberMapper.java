package com.kh.mixmatch.member.dao;


import org.apache.ibatis.annotations.Select;

import com.kh.mixmatch.member.domain.MemberCommand;

public interface MemberMapper {
	public void insertMember(MemberCommand member);
	
	@Select("SELECT * FROM g_member WHERE id=#{id}")
	public MemberCommand selectMember(String id);
	public void updateMember(String id);
	public void deleteMember(String id);
}
