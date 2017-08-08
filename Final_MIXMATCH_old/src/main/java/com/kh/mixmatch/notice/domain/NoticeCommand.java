package com.kh.mixmatch.notice.domain;

import java.io.IOException;
import java.sql.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;


public class NoticeCommand {
	private int n_seq;
	@NotEmpty
	private String n_title;
	@NotEmpty
	private String n_content;
	private int n_hit;
	private byte[] n_file;
	private String n_file_name;
	private MultipartFile n_file_upload;
	private Date n_regdate;
	@NotEmpty
	private String id;
	
	public int getN_seq() {
		return n_seq;
	}
	public void setN_seq(int n_seq) {
		this.n_seq = n_seq;
	}
	public String getN_title() {
		return n_title;
	}
	public void setN_title(String n_title) {
		this.n_title = n_title;
	}
	public String getN_content() {
		return n_content;
	}
	public void setN_content(String n_content) {
		this.n_content = n_content;
	}
	public int getN_hit() {
		return n_hit;
	}
	public void setN_hit(int n_hit) {
		this.n_hit = n_hit;
	}
	public byte[] getN_file() {
		return n_file;
	}
	public void setN_file(byte[] n_file) {
		this.n_file = n_file;
	}
	public String getN_file_name() {
		return n_file_name;
	}
	public void setN_file_name(String n_file_name) {
		this.n_file_name = n_file_name;
	}
	public MultipartFile getN_file_upload() {
		return n_file_upload;
	}
	public void setN_file_upload(MultipartFile n_file_upload) throws IOException {
		this.n_file_upload = n_file_upload;
		setN_file(n_file_upload.getBytes());
		setN_file_name(n_file_upload.getOriginalFilename());
	}
	public Date getN_regdate() {
		return n_regdate;
	}
	public void setN_regdate(Date n_regdate) {
		this.n_regdate = n_regdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "NoticeCommand [n_seq=" + n_seq + ", n_title=" + n_title + ", n_content=" + n_content + ", n_hit="
				+ n_hit + ", n_file_name=" + n_file_name +  ", n_regdate="
				+ n_regdate + ", id=" + id + "]";
	}
	
	
}
