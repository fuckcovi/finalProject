package com.kh.mixmatch.team.domain;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class BasketCommand {
	private int b_seq;
	private String id;
	private String t_name;
	private int m_seq;
	private int b_score;
	private int b_assist;
	private int b_rebound;
	private int b_steel;
	private int b_block;
	private int b_3point;
	
	private String name; //회원명 : MemberCommand 조인
	private String profile_name; //프로필명 : MemberCommand 조인
	private byte[] profile;
	private MultipartFile profile_upload;
	
	private int recordstatus;

	public int getRecordstatus() {
		return recordstatus;
	}
	public void setRecordstatus(int recordstatus) {
		this.recordstatus = recordstatus;
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
	public int getB_seq() {
		return b_seq;
	}
	public void setB_seq(int b_seq) {
		this.b_seq = b_seq;
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
	public int getB_score() {
		return b_score;
	}
	public void setB_score(int b_score) {
		this.b_score = b_score;
	}
	public int getB_assist() {
		return b_assist;
	}
	public void setB_assist(int b_assist) {
		this.b_assist = b_assist;
	}
	public int getB_rebound() {
		return b_rebound;
	}
	public void setB_rebound(int b_rebound) {
		this.b_rebound = b_rebound;
	}
	public int getB_steel() {
		return b_steel;
	}
	public void setB_steel(int b_steel) {
		this.b_steel = b_steel;
	}
	public int getB_block() {
		return b_block;
	}
	public void setB_block(int b_block) {
		this.b_block = b_block;
	}
	public int getB_3point() {
		return b_3point;
	}
	public void setB_3point(int b_3point) {
		this.b_3point = b_3point;
	}
	@Override
	public String toString() {
		return "BasketCommand [b_seq=" + b_seq + ", id=" + id + ", t_name=" + t_name + ", m_seq=" + m_seq + ", b_score="
				+ b_score + ", b_assist=" + b_assist + ", b_rebound=" + b_rebound + ", b_steel=" + b_steel
				+ ", b_block=" + b_block + ", b_3point=" + b_3point + "]";
	}
	
}
