package com.kh.mixmatch.stadium.domain;

public class BookingCommand {
	private int b_seq;
	private int s_seq;
	private String b_regdate;
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
	public String getB_regdate() {
		return b_regdate;
	}
	public void setB_regdate(String b_regdate) {
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
	@Override
	public String toString() {
		return "BookingCommand [b_seq=" + b_seq + ", s_seq=" + s_seq + ", b_regdate=" + b_regdate + ", b_time=" + b_time
				+ ", b_check=" + b_check + "]";
	}
	
	
}
