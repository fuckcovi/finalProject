package com.kh.mixmatch.board.domain;

import java.io.IOException;
import java.sql.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class GBoardCommand {
	private int gb_seq;
	@NotEmpty
	private	String gb_title;
	@NotEmpty
	private String gb_content;
	private int gb_hit;
	private Date gb_regdate;
	private MultipartFile upload;
	private byte[] gb_uploadfile;
	private String gb_filename;
	private String ip;
	@NotEmpty
	private String id;
	private int reply_cnt; //´ñ±Û¼ö
	
	public void setUpload(MultipartFile upload) throws IOException{
		this.upload = upload;
		
		setGb_uploadfile(upload.getBytes());
		setGb_filename(upload.getOriginalFilename());
	}
	
	public int getGb_seq() {
		return gb_seq;
	}
	public void setGb_seq(int gb_seq) {
		this.gb_seq = gb_seq;
	}
	public String getGb_title() {
		return gb_title;
	}
	public void setGb_title(String gb_title) {
		this.gb_title = gb_title;
	}
	public String getGb_content() {
		return gb_content;
	}
	public void setGb_content(String gb_content) {
		this.gb_content = gb_content;
	}
	public int getGb_hit() {
		return gb_hit;
	}
	public void setGb_hit(int gb_hit) {
		this.gb_hit = gb_hit;
	}
	public Date getGb_regdate() {
		return gb_regdate;
	}
	public void setGb_regdate(Date gb_regdate) {
		this.gb_regdate = gb_regdate;
	}
	public MultipartFile getUpload() {
		return upload;
	}
	public byte[] getGb_uploadfile() {
		return gb_uploadfile;
	}
	public void setGb_uploadfile(byte[] gb_uploadfile) {
		this.gb_uploadfile = gb_uploadfile;
	}
	public String getGb_filename() {
		return gb_filename;
	}
	public void setGb_filename(String gb_filename) {
		this.gb_filename = gb_filename;
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
		return "GBoardCommand [gb_seq=" + gb_seq + ", gb_title=" + gb_title + ", gb_content=" + gb_content + ", gb_hit="
				+ gb_hit + ", gb_regdate=" + gb_regdate + ", upload=" + upload + ", gb_filename=" + gb_filename
				+ ", ip=" + ip + ", id=" + id + ", reply_cnt=" + reply_cnt + "]";
	}
	
	
}
