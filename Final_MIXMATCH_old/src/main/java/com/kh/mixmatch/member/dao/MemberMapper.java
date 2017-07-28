package com.kh.mixmatch.member.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.mixmatch.member.domain.MemberCommand;

public interface MemberMapper {
	@Insert("INSERT INTO g_member (id,pw,name,birth,phone,email,favor,regdate,address,profile_name,profile) "
			+ "VALUES(#{id},#{pw},#{name},#{birth},#{phone},#{email},#{favor},sysdate,#{address},#{profile_name,jdbcType=VARCHAR},#{profile,jdbcType=BLOB})")
	public void insertMember(MemberCommand member);
	@Select("SELECT * FROM g_member WHERE id=#{id}")
	public MemberCommand selectMember(String id);
	@Update("UPDATE g_member SET name=#{name},phone=#{phone},email=#{email},favor=#{favor},address=#{address},"
			+ "profile_name=#{profile_name,jdbcType=VARCHAR},profile=#{profile,jdbcType=BLOB} WHERE id=#{id}")
	public void updateMember(MemberCommand member);
	@Update("UPDATE g_member SET pw=#{pw} WHERE id=#{id}")
	public void updatePw(MemberCommand member);
	@Update("UPDATE g_member SET status='N' WHERE id=#{id}")
	public void deleteMember(String id);
}
