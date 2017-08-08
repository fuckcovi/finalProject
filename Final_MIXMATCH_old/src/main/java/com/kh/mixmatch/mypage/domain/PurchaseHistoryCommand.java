package com.kh.mixmatch.mypage.domain;

import java.sql.Date;

public class PurchaseHistoryCommand {
	private String id;
	private int p_seq;
	private Date s_regdate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getP_seq() {
		return p_seq;
	}
	public void setP_seq(int p_seq) {
		this.p_seq = p_seq;
	}
	public Date getS_regdate() {
		return s_regdate;
	}
	public void setS_regdate(Date s_regdate) {
		this.s_regdate = s_regdate;
	}
	
	@Override
	public String toString() {
		return "PurchaseHistoryCommand [id=" + id + ", p_seq=" + p_seq + ", s_regdate=" + s_regdate + "]";
	}
	
	
}
