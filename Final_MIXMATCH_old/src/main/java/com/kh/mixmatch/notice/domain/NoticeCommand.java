package com.kh.mixmatch.notice.domain;

import java.io.IOException;
import java.sql.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;


public class NoticeCommand {
	private int gn_seq;
	@NotEmpty
	private String gn_title;
	@NotEmpty
	private String gn_content;
	private int gn_hit;
	private byte[] gn_uploadfile;
	private String gn_filename;
	private MultipartFile gn_uploadfile_upload;
	private Date gn_regdate;
	@NotEmpty
	private String id;
	private String ip;
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
	public MultipartFile getGn_uploadfile_upload() {
		return gn_uploadfile_upload;
	}
	public void setGn_uploadfile_upload(MultipartFile gn_uploadfile_upload) throws IOException {
		this.gn_uploadfile_upload = gn_uploadfile_upload;
		setGn_filename(gn_uploadfile_upload.getOriginalFilename());
		setGn_uploadfile(gn_uploadfile_upload.getBytes());
	}
	public Date getGn_regdate() {
		return gn_regdate;
	}
	public void setGn_regdate(Date gn_regdate) {
		this.gn_regdate = gn_regdate;
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
		return "NoticeCommand [gn_seq=" + gn_seq + ", gn_title=" + gn_title + ", gn_content=" + gn_content + ", gn_hit="
				+ gn_hit + ", gn_filename=" + gn_filename + ", gn_regdate=" + gn_regdate + ", id=" + id + ", ip=" + ip
				+ "]";
	}
	
}
