package com.kh.mixmatch.teamboard.domain;

import java.io.IOException;
import java.sql.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;


public class TeamBoardCommand {
	private int tb_seq;
	@NotEmpty
	private String t_name;
	@NotEmpty
	private String tb_title;
	@NotEmpty
	private String tb_content;
	private int tb_hit;
	private byte[] tb_file;
	private String tb_file_name;
	private MultipartFile tb_file_upload;
	private Date tb_regdate;
	@NotEmpty
	private String id;
	
	public int getTb_seq() {
		return tb_seq;
	}
	public void setTb_seq(int tb_seq) {
		this.tb_seq = tb_seq;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public String getTb_title() {
		return tb_title;
	}
	public void setTb_title(String tb_title) {
		this.tb_title = tb_title;
	}
	public String getTb_content() {
		return tb_content;
	}
	public void setTb_content(String tb_content) {
		this.tb_content = tb_content;
	}
	public int getTb_hit() {
		return tb_hit;
	}
	public void setTb_hit(int tb_hit) {
		this.tb_hit = tb_hit;
	}
	public byte[] getTb_file() {
		return tb_file;
	}
	public void setTb_file(byte[] tb_file) {
		this.tb_file = tb_file;
	}
	public String getTb_file_name() {
		return tb_file_name;
	}
	public void setTb_file_name(String tb_file_name) {
		this.tb_file_name = tb_file_name;
	}
	public MultipartFile getTb_file_upload() {
		return tb_file_upload;
	}
	public void setTb_file_upload(MultipartFile tb_file_upload) throws IOException {
		this.tb_file_upload = tb_file_upload;
		setTb_file_name(tb_file_upload.getOriginalFilename());
		setTb_file(tb_file_upload.getBytes());
	}
	public Date getTb_regdate() {
		return tb_regdate;
	}
	public void setTb_regdate(Date tb_regdate) {
		this.tb_regdate = tb_regdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "TeamBoardCommand [tb_seq=" + tb_seq + ", t_name=" + t_name + ", tb_title=" + tb_title + ", tb_content="
				+ tb_content + ", tb_hit=" + tb_hit + ", tb_file_name=" + tb_file_name + ", tb_regdate=" + tb_regdate
				+ ", id=" + id + "]";
	}
	
	
}
