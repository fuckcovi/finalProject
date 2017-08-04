package com.kh.mixmatch.board.domain;

import java.io.IOException;
import java.sql.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class GNoticeCommand {
	private int gn_seq;
	@NotEmpty
	private	String gn_title;
	@NotEmpty
	private String gn_content;
	private int gn_hit;
	private Date gn_regdate;
	private MultipartFile upload;
	private byte[] gn_uploadfile;
	private String gn_filename;
	private String ip;
	@NotEmpty
	private String id;
	
	private int reply_cnt;
	
	public void setUpload(MultipartFile upload) throws IOException{
		
		this.upload = upload;
		
		setGn_uploadfile(upload.getBytes());
		setGn_filename(upload.getOriginalFilename());
	}
	
	public int getGn_seq() {
		return gn_seq;
	}
	public void setGn_seq(int gn_seq) {
		this.gn_seq = gn_seq;
	}
	public String getGn_title() {
		return gn_title;
	}
	public void setGn_title(String gn_title) {
		this.gn_title = gn_title;
	}
	public String getGn_content() {
		return gn_content;
	}
	public void setGn_content(String gn_content) {
		this.gn_content = gn_content;
	}
	public int getGn_hit() {
		return gn_hit;
	}
	public void setGn_hit(int gn_hit) {
		this.gn_hit = gn_hit;
	}
	public Date getGn_regdate() {
		return gn_regdate;
	}
	public void setGn_regdate(Date gn_regdate) {
		this.gn_regdate = gn_regdate;
	}
	public MultipartFile getUpload() {
		return upload;
	}
	public byte[] getGn_uploadfile() {
		return gn_uploadfile;
	}
	public void setGn_uploadfile(byte[] gn_uploadfile) {
		this.gn_uploadfile = gn_uploadfile;
	}
	public String getGn_filename() {
		return gn_filename;
	}
	public void setGn_filename(String gn_filename) {
		this.gn_filename = gn_filename;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public int getReply_cnt() {
		return reply_cnt;
	}

	public void setReply_cnt(int reply_cnt) {
		this.reply_cnt = reply_cnt;
	}

	@Override
	public String toString() {
		return "GNoticeCommand [gn_seq=" + gn_seq + ", gn_title=" + gn_title + ", gn_content=" + gn_content
				+ ", gn_hit=" + gn_hit + ", gn_regdate=" + gn_regdate + ", upload=" + upload + ", gn_filename="
				+ gn_filename + ", ip=" + ip + ", id=" + id + ", reply_cnt=" + reply_cnt + "]";
	}
	
}
