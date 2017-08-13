package com.kh.mixmatch.teamboard.domain;

import java.io.IOException;
import java.sql.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;


public class TeamBoardCommand {
	private int gt_seq;
	@NotEmpty
	private String t_name;
	@NotEmpty
	private String gt_title;
	@NotEmpty
	private String gt_content;
	private int gt_hit;
	private byte[] gt_uploadfile;
	private String gt_filename;
	private MultipartFile gt_uploadfile_upload;
	private Date gt_regdate;
	@NotEmpty
	private String id;
	private String ip;
	
	public int getGt_seq() {
		return gt_seq;
	}
	public void setGt_seq(int gt_seq) {
		this.gt_seq = gt_seq;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
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
	public MultipartFile getGt_uploadfile_upload() {
		return gt_uploadfile_upload;
	}
	public void setGt_uploadfile_upload(MultipartFile gt_uploadfile_upload) throws IOException {
		this.gt_uploadfile_upload = gt_uploadfile_upload;
		setGt_uploadfile(gt_uploadfile_upload.getBytes());
		setGt_filename(gt_uploadfile_upload.getOriginalFilename());
	}
	public Date getGt_regdate() {
		return gt_regdate;
	}
	public void setGt_regdate(Date gt_regdate) {
		this.gt_regdate = gt_regdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Override
	public String toString() {
		return "TeamBoardCommand [gt_seq=" + gt_seq + ", t_name=" + t_name + ", gt_title=" + gt_title + ", gt_content="
				+ gt_content + ", gt_hit=" + gt_hit + ", gt_filename=" + gt_filename + ", gt_regdate=" + gt_regdate
				+ ", id=" + id + ", ip=" + ip + "]";
	}
	
	
}
