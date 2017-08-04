package com.kh.mixmatch.mypage.domain;

import java.sql.Date;


public class MypageReplyCommand {
	private int h_re_seq;					//댓글번호
	private int h_seq;						//글번호
	private String h_re_content;			//댓글 내용
	//private MultipartFile re_uploadfile;	//업로드파일
	//private byte[] h_re_file;				//파일크기
	//private String h_re_file_name;			//파일명
	private Date h_re_regdate;				//등록일
	private String id;						//작성자
	
	
	/*public void setRe_uploadfile(MultipartFile re_uploadfile) throws IOException {
		this.re_uploadfile = re_uploadfile;
		
		setH_re_file(re_uploadfile.getBytes());
		setH_re_file_name(re_uploadfile.getOriginalFilename());
	}*/
	
	public int getH_re_seq() {
		return h_re_seq;
	}
	public void setH_re_seq(int h_re_seq) {
		this.h_re_seq = h_re_seq;
	}
	public int getH_seq() {
		return h_seq;
	}
	public void setH_seq(int h_seq) {
		this.h_seq = h_seq;
	}
	public String getH_re_content() {
		return h_re_content;
	}
	public void setH_re_content(String h_re_content) {
		this.h_re_content = h_re_content;
	}
	/*public MultipartFile getRe_uploadfile() {
		return re_uploadfile;
	}
	public byte[] getH_re_file() {
		return h_re_file;
	}
	public void setH_re_file(byte[] h_re_file) {
		this.h_re_file = h_re_file;
	}
	public String getH_re_file_name() {
		return h_re_file_name;
	}
	public void setH_re_file_name(String h_re_file_name) {
		this.h_re_file_name = h_re_file_name;
	}*/
	public Date getH_re_regdate() {
		return h_re_regdate;
	}
	public void setH_re_regdate(Date h_re_regdate) {
		this.h_re_regdate = h_re_regdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "MypageReplyCommand [h_re_seq=" + h_re_seq + ", h_seq=" + h_seq + ", h_re_content=" + h_re_content
				+ ", h_re_regdate=" + h_re_regdate + ", id=" + id + "]";
	}

	/*@Override
	public String toString() {
		return "MypageReplyCommand [h_re_seq=" + h_re_seq + ", h_seq=" + h_seq + ", h_re_content=" + h_re_content
				+ ", re_uploadfile=" + re_uploadfile + ", h_re_file_name=" + h_re_file_name + ", h_re_regdate="
				+ h_re_regdate + ", id=" + id + "]";
	}*/
	
	
}
