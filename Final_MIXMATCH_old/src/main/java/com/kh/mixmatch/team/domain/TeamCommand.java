package com.kh.mixmatch.team.domain;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class TeamCommand {
	@NotEmpty
	private String t_name;
	@NotEmpty
	private String t_type;
	private Date t_regdate;
	@NotEmpty
	private String t_address;
	@NotEmpty
	private String id;
	
	private int t_win;
	private int t_draw;
	private int t_lose;
	
	private MultipartFile t_logo_upload;
	private String t_logo_name;
	private byte[] t_logo;
	

	@Override
	public String toString() {
		return "TeamCommand [t_name=" + t_name + ", t_type=" + t_type + ", t_regdate=" + t_regdate + ", t_address="
				+ t_address + ", id=" + id + ", t_win=" + t_win + ", t_draw=" + t_draw + ", t_lose="
				+ t_lose + ", t_logo_name=" + t_logo_name +"]";
	}

	public void setT_logo_upload(MultipartFile t_logo_upload) throws IOException {
		this.t_logo_upload = t_logo_upload;
		setT_logo(t_logo_upload.getBytes());
		setT_logo_name(t_logo_upload.getOriginalFilename());
	}
	
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public MultipartFile getT_logo_upload() {
		return t_logo_upload;
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

	
}
