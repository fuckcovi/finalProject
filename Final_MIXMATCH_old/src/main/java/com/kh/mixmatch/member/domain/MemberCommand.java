package com.kh.mixmatch.member.domain;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;


public class MemberCommand {
	@NotEmpty
	private String id;
	@NotEmpty
	private String pw;
	@NotEmpty
	private String name;
	@NotEmpty
	private String birtrh;
	@NotEmpty
	private String phone;
	@NotEmpty
	private String email;
	@NotEmpty
	private String favor;
	private int point;
	private Date regdate;
	@NotEmpty
	private String address;

	private String auth;

	private String profile_name;
	private byte[] profile;
	private MultipartFile profile_upload;
	
	// 비밀번호 일치 여부 체크
	public boolean isCheckedPasswd(String userPasswd){
		if(pw.equals(userPasswd)){
			return true;
		}
		return false;
	}
	


	@Override
	public String toString() {
		return "MemberCommand [id=" + id + ", pw=" + pw + ", name=" + name + ", birtrh=" + birtrh + ", phone=" + phone
				+ ", email=" + email + ", favor=" + favor + ", point=" + point + ", regdate=" + regdate + ", address="
				+ address + ", auth=" + auth + ", profile_name=" + profile_name +  "]";
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirtrh() {
		return birtrh;
	}
	public void setBirtrh(String birtrh) {
		this.birtrh = birtrh;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFavor() {
		return favor;
	}
	public void setFavor(String favor) {
		this.favor = favor;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getProfile_name() {
		return profile_name;
	}
	public void setProfile_name(String profile_name) {
		this.profile_name = profile_name;
	}
	
	

	
}
