package com.kh.mixmatch.mypage.domain;

import java.sql.Date;

public class MypageCommand2 {
	
	/*글을 수정할때 이미지는 수정할 수 없는 자바빈을 하나 더 만들어줘
	 원래 자바빈을 사용하면 글을 수정할때 이미지를 수정못하기때문에 원래 있던 이미지는 사라져*/
	
	private int h_seq;
	private String id;
	private Date h_regdate;
	private String h_content;
	private String h_show;
	
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
	@Override
	public String toString() {
		return "MypageCommand2 [h_seq=" + h_seq + ", id=" + id + ", h_regdate=" + h_regdate + ", h_content=" + h_content
				+ ", h_show=" + h_show + "]";
	}
	
	
	
}
