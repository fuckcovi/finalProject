package com.kh.mixmatch.team.domain;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import oracle.sql.BLOB;

public class FootCommand {
	private int f_seq;
	private String id;
	private String t_name;
	private int m_seq;
	private int f_shoot;
	private int f_assist;
	private int f_goal;
	private int f_attack;
	private String name; //회원명 : MemberCommand 조인
	private String profile_name; //프로필명 : MemberCommand 조인
	private byte[] profile;
	private MultipartFile profile_upload;

	
	private String m_challenger;	// 팀별 매치번호에 따른 기록
	private String m_type;	// 매치타입
	
	
	public String getM_challenger() {
		return m_challenger;
	}
	public void setM_challenger(String m_challenger) {
		this.m_challenger = m_challenger;
	}
	public String getM_type() {
		return m_type;
	}
	public void setM_type(String m_type) {
		this.m_type = m_type;
	}
	public void setProfile_upload(MultipartFile profile_upload) throws IOException {
		this.profile_upload = profile_upload;
		setProfile(profile_upload.getBytes());
		setProfile_name(profile_upload.getOriginalFilename());
	}
	public byte[] getProfile() {
		return profile;
	}
	public void setProfile(byte[] profile) {
		this.profile = profile;
	}
	public MultipartFile getProfile_upload() {
		return profile_upload;
	}
	public String getProfile_name() {
		return profile_name;
	}
	public void setProfile_name(String profile_name) {
		this.profile_name = profile_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getF_seq() {
		return f_seq;
	}
	public void setF_seq(int f_seq) {
		this.f_seq = f_seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public int getM_seq() {
		return m_seq;
	}
	public void setM_seq(int m_seq) {
		this.m_seq = m_seq;
	}
	public int getF_shoot() {
		return f_shoot;
	}
	public void setF_shoot(int f_shoot) {
		this.f_shoot = f_shoot;
	}
	public int getF_assist() {
		return f_assist;
	}
	public void setF_assist(int f_assist) {
		this.f_assist = f_assist;
	}
	public int getF_goal() {
		return f_goal;
	}
	public void setF_goal(int f_goal) {
		this.f_goal = f_goal;
	}
	public int getF_attack() {
		return f_attack;
	}
	public void setF_attack(int f_attack) {
		this.f_attack = f_attack;
	}
	@Override
	public String toString() {
		return "FootCommand [f_seq=" + f_seq + ", id=" + id + ", t_name=" + t_name + ", m_seq=" + m_seq + ", f_shoot="
				+ f_shoot + ", f_assist=" + f_assist + ", f_goal=" + f_goal + ", f_attack=" + f_attack + "]";
	}
	
	
}
