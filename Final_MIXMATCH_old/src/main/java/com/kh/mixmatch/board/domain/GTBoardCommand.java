package com.kh.mixmatch.board.domain;

import java.io.IOException;
import java.sql.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class GTBoardCommand {
	private int gt_seq;
	@NotEmpty
	private	String gt_title;
	@NotEmpty
	private String gt_content;
	private int gt_hit;
	private Date gt_regdate;
	private MultipartFile upload;
	private byte[] gt_uploadfile;
	private String gt_filename;
	private String ip;
	@NotEmpty
	private String id;
	
	private int reply_cnt;
	
	public void setUpload(MultipartFile upload) throws IOException{
		this.upload = upload;
		
		setGt_uploadfile(upload.getBytes());
		setGt_filename(upload.getOriginalFilename());
	}

	public int getGt_seq() {
		return gt_seq;
	}

	public void setGt_seq(int gt_seq) {
		this.gt_seq = gt_seq;
	}

	public String getGt_title() {
		return gt_title;
	}

	public void setGt_title(String gt_title) {
		this.gt_title = gt_title;
	}

	public String getGt_content() {
		return gt_content;
	}

	public void setGt_content(String gt_content) {
		this.gt_content = gt_content;
	}

	public int getGt_hit() {
		return gt_hit;
	}

	public void setGt_hit(int gt_hit) {
		this.gt_hit = gt_hit;
	}

	public Date getGt_regdate() {
		return gt_regdate;
	}

	public void setGt_regdate(Date gt_regdate) {
		this.gt_regdate = gt_regdate;
	}

	public MultipartFile getUpload() {
		return upload;
	}

	public byte[] getGt_uploadfile() {
		return gt_uploadfile;
	}

	public void setGt_uploadfile(byte[] gt_uploadfile) {
		this.gt_uploadfile = gt_uploadfile;
	}

	public String getGt_filename() {
		return gt_filename;
	}

	public void setGt_filename(String gt_filename) {
		this.gt_filename = gt_filename;
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
		return "GTBoardCommand [gt_seq=" + gt_seq + ", gt_title=" + gt_title + ", gt_content=" + gt_content
				+ ", gt_hit=" + gt_hit + ", gt_regdate=" + gt_regdate + ", upload=" + upload + ", gt_filename="
				+ gt_filename + ", ip=" + ip + ", id=" + id + ", reply_cnt=" + reply_cnt + "]";
	}
	
	
	
	
}
