package com.kh.mixmatch.pointshop.domain;

import java.io.IOException;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class PointShopCommand {

	private int p_seq;
	@NotEmpty
	private String p_grade;
	@NotEmpty
	private String p_name;
	private String p_context;
	@NotNull
	private int p_price;
	private String p_file_name;
	private byte[] upload_file;
	private MultipartFile p_file;
	private String id;
	
	public String getP_context() {
		return p_context;
	}
	public void setP_context(String p_context) {
		this.p_context = p_context;
	}
	public int getP_seq() {
		return p_seq;
	}
	public void setP_seq(int p_seq) {
		this.p_seq = p_seq;
	}
	public String getP_grade() {
		return p_grade;
	}
	public void setP_grade(String p_grade) {
		this.p_grade = p_grade;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public String getP_file_name() {
		return p_file_name;
	}
	public void setP_file_name(String p_file_name) {
		this.p_file_name = p_file_name;
	}
	public byte[] getUpload_file() {
		return upload_file;
	}
	public void setUpload_file(byte[] upload_file) {
		this.upload_file = upload_file;
	}
	public MultipartFile getP_file() {
		return p_file;
	}
	public void setP_file(MultipartFile p_file) throws IOException {
		this.p_file = p_file;
		
		setUpload_file(p_file.getBytes());
		setP_file_name(p_file.getOriginalFilename());
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "PointShopCommand [p_seq=" + p_seq + ", p_grade=" + p_grade + ", p_name=" + p_name + ", p_price="
				+ p_price + ", p_file_name=" + p_file_name + ", p_file=" + p_file + ", id=" + id + "]";
	}
	
}