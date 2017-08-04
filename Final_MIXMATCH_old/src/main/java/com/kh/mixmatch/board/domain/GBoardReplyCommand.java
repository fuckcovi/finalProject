package com.kh.mixmatch.board.domain;

import com.kh.mixmatch.util.DurationFromNow;

public class GBoardReplyCommand {
	private int gbre_no;
	private String gbre_content;
	private String gbre_date;
	private String re_ip;
	private int gbre_seq;
	private String id;
	public int getGbre_no() {
		return gbre_no;
	}
	public void setGbre_no(int gbre_no) {
		this.gbre_no = gbre_no;
	}
	public String getGbre_content() {
		return gbre_content;
	}
	public void setGbre_content(String gbre_content) {
		this.gbre_content = gbre_content;
	}
	public String getGbre_date() {
		return gbre_date;
	}
	public void setGbre_date(String gbre_date) {
		this.gbre_date = DurationFromNow.getTimeDiffLabel(gbre_date);
	}
	public String getRe_ip() {
		return re_ip;
	}
	public void setRe_ip(String re_ip) {
		this.re_ip = re_ip;
	}
	public int getGbre_seq() {
		return gbre_seq;
	}
	public void setGbre_seq(int gbre_seq) {
		this.gbre_seq = gbre_seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "GBoardReplyCommand [gbre_no=" + gbre_no + ", gbre_content=" + gbre_content + ", gbre_date=" + gbre_date
				+ ", re_ip=" + re_ip + ", gbre_seq=" + gbre_seq + ", id=" + id + "]";
	}
	
	
}
