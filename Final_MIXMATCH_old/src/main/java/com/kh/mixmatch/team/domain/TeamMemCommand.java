package com.kh.mixmatch.team.domain;

import java.io.IOException;
import java.sql.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class TeamMemCommand {
	private int t_mem_seq;
	@NotEmpty
	private String id;
	@NotEmpty
	private String t_name;
	private Date t_mem_regdate;
	private int t_mem_auth;
	
	private String name;	// 조인 MEMBER
	private String profile_name; //프로필명 : MemberCommand 조인
	private byte[] profile;
	private MultipartFile profile_upload;
	private String master;
	
	
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
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
	public int getT_mem_auth() {
		return t_mem_auth;
	}
	public void setT_mem_auth(int t_mem_auth) {
		this.t_mem_auth = t_mem_auth;
	}
	public int getT_mem_seq() {
		return t_mem_seq;
	}
	public void setT_mem_seq(int t_mem_seq) {
		this.t_mem_seq = t_mem_seq;
	}
	public Date getT_mem_regdate() {
		return t_mem_regdate;
	}
	public void setT_mem_regdate(Date t_mem_regdate) {
		this.t_mem_regdate = t_mem_regdate;
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
	@Override
	public String toString() {
		return "TeamMemCommand [t_mem_seq=" + t_mem_seq + ", id=" + id + ", t_name=" + t_name + ", t_mem_regdate="
				+ t_mem_regdate + ", t_mem_auth=" + t_mem_auth + "]";
	}
	
	
}
