package com.kh.mixmatch.stadium.domain;

import java.io.IOException;
import java.sql.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class StadiumCommand {
	private int s_seq;
	@NotEmpty
	private String s_name;
	@NotEmpty
	private String s_address1;	// 지역
	private String s_address2;	// 상세주소
	private String s_logo_name;
	private byte[] s_logo;
	private MultipartFile s_logo_upload;
	private Date s_regdate;
	
	
	public int getS_seq() {
		return s_seq;
	}
	public void setS_seq(int s_seq) {
		this.s_seq = s_seq;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getS_address1() {
		return s_address1;
	}
	public void setS_address1(String s_address1) {
		this.s_address1 = s_address1;
	}
	public String getS_address2() {
		return s_address2;
	}
	public void setS_address2(String s_address2) {
		this.s_address2 = s_address2;
	}
	public String getS_logo_name() {
		return s_logo_name;
	}
	public void setS_logo_name(String s_logo_name) {
		this.s_logo_name = s_logo_name;
	}
	public byte[] getS_logo() {
		return s_logo;
	}
	public void setS_logo(byte[] s_logo) {
		this.s_logo = s_logo;
	}
	public MultipartFile getS_logo_upload() {
		return s_logo_upload;
	}
	public void setS_logo_upload(MultipartFile s_logo_upload) throws IOException {
		this.s_logo_upload = s_logo_upload;
		setS_logo_name(s_logo_upload.getOriginalFilename());
		setS_logo(s_logo_upload.getBytes());
	}
	public Date getS_regdate() {
		return s_regdate;
	}
	public void setS_regdate(Date s_regdate) {
		this.s_regdate = s_regdate;
	}
	
	
	
}
