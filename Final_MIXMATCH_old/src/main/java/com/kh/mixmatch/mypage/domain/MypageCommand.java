package com.kh.mixmatch.mypage.domain;

import java.io.IOException;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;


public class MypageCommand {
	private int h_seq;
	private String id;
	private Date h_regdate;
	private String h_content;		//CLOB
	private String h_show;
	private MultipartFile uploadfile;	//업로드한 파일의 정보를 uploadfile이 가지고있어.
	private byte[] h_file;		//blob를 바이트 배열로 넣어주기 위해 필요해(Mybatis가 MultipartFile을 바로 BLOB타입으로 못 바꿔줘). 다시 데이터베이스에서 빼낼때도 BLOB타입을 배열로 바꾸어서 꺼내와
	private String h_file_name;
	
	private int reply_cnt;		//댓글 수
	
	//MultipartFile 객체로부터 전송된 파일을 byte[]로 변환하고 파일명을 구함
	public void setUploadfile(MultipartFile uploadfile) throws IOException {
		this.uploadfile = uploadfile;
		
		//전송된 파일을 byte[]로 반환
		setH_file(uploadfile.getBytes());
		//파일명
		setH_file_name(uploadfile.getOriginalFilename());
	}

	
	
	public int getH_seq() {
		return h_seq;
	}
	public void setH_seq(int h_seq) {
		this.h_seq = h_seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getH_regdate() {
		return h_regdate;
	}
	public void setH_regdate(Date h_regdate) {
		this.h_regdate = h_regdate;
	}
	public String getH_content() {
		return h_content;
	}
	public void setH_content(String h_content) {
		this.h_content = h_content;
	}

	public String getH_show() {
		return h_show;
	}
	public void setH_show(String h_show) {
		this.h_show = h_show;
	}
	public byte[] getH_file() {
		return h_file;
	}
	public void setH_file(byte[] h_file) {
		this.h_file = h_file;
	}
	public String getH_file_name() {
		return h_file_name;
	}
	public void setH_file_name(String h_file_name) {
		this.h_file_name = h_file_name;
	}
	public MultipartFile getUploadfile() {
		return uploadfile;
	}
	
	
	
	public int getReply_cnt() {
		return reply_cnt;
	}
	public void setReply_cnt(int reply_cnt) {
		this.reply_cnt = reply_cnt;
	}



	@Override
	public String toString() {
		return "MypageCommand [h_seq=" + h_seq + ", id=" + id + ", h_regdate=" + h_regdate + ", h_content=" + h_content
				+ ", h_show=" + h_show + ", uploadfile=" + uploadfile + ", h_file_name=" + h_file_name + ", reply_cnt="
				+ reply_cnt + "]";
	}



	

	

	

	
	
	
	
}
