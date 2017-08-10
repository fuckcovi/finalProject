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
	
	private String name;	// ���� MEMBER
	private String profile_name; //�����ʸ� : MemberCommand ����
	private byte[] profile;
	private MultipartFile profile_upload;
	private String master;
	
	private String t_type;
	private Date t_regdate;
	private String t_address;
	private int t_win;
	private int t_draw;
	private int t_lose;
	private String t_logo_name;
	private byte[] t_logo;
	
	
	public String getT_type() {
		return t_type;
	}
	public void setT_type(String t_type) {
		this.t_type = t_type;
	}
	public Date getT_regdate() {
		return t_regdate;
	}
	public void setT_regdate(Date t_regdate) {
		this.t_regdate = t_regdate;
	}
	public String getT_address() {
		return t_address;
	}
	public void setT_address(String t_address) {
		this.t_address = t_address;
	}
	public int getT_win() {
		return t_win;
	}
	public void setT_win(int t_win) {
		this.t_win = t_win;
	}
	public int getT_draw() {
		return t_draw;
	}
	public void setT_draw(int t_draw) {
		this.t_draw = t_draw;
	}
	public int getT_lose() {
		return t_lose;
	}
	public void setT_lose(int t_lose) {
		this.t_lose = t_lose;
	}
	public String getT_logo_name() {
		return t_logo_name;
	}
	public void setT_logo_name(String t_logo_name) {
		this.t_logo_name = t_logo_name;
	}
	public byte[] getT_logo() {
		return t_logo;
	}
	public void setT_logo(byte[] t_logo) {
		this.t_logo = t_logo;
	}
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
