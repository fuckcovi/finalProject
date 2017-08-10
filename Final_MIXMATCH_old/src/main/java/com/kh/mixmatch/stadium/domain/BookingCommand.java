package com.kh.mixmatch.stadium.domain;

import java.sql.Date;

public class BookingCommand {
	private int b_seq;
	private int s_seq;
	private Date b_regdate;
	private String b_time;
	private int b_check;
	public int getB_seq() {
		return b_seq;
	}
	public void setB_seq(int b_seq) {
		this.b_seq = b_seq;
	}
	public int getS_seq() {
		return s_seq;
	}
	public void setS_seq(int s_seq) {
		this.s_seq = s_seq;
	}
	public Date getB_regdate() {
		return b_regdate;
	}
	public void setB_regdate(Date b_regdate) {
		this.b_regdate = b_regdate;
	}
	public String getB_time() {
		return b_time;
	}
	public void setB_time(String b_time) {
		this.b_time = b_time;
	}
	public int getB_check() {
		return b_check;
	}
	public void setB_check(int b_check) {
		this.b_check = b_check;
	}
	
	
}
