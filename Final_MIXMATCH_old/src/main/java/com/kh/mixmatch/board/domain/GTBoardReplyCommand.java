package com.kh.mixmatch.board.domain;

import com.kh.mixmatch.util.DurationFromNow;

public class GTBoardReplyCommand {
	private int gtre_no;
	private String gtre_content;
	private String gtre_date;
	private String re_ip;
	private int gtre_seq;
	private String id;
	public int getGtre_no() {
		return gtre_no;
	}
	public void setGtre_no(int gtre_no) {
		this.gtre_no = gtre_no;
	}
	public String getGtre_content() {
		return gtre_content;
	}
	public void setGtre_content(String gtre_content) {
		this.gtre_content = gtre_content;
	}
	public String getGtre_date() {
		return gtre_date;
	}
	public void setGtre_date(String gtre_date) {
		this.gtre_date = DurationFromNow.getTimeDiffLabel(gtre_date);
	}
	public String getRe_ip() {
		return re_ip;
	}
	public void setRe_ip(String re_ip) {
		this.re_ip = re_ip;
	}
	public int getGtre_seq() {
		return gtre_seq;
	}
	public void setGtre_seq(int gtre_seq) {
		this.gtre_seq = gtre_seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "GTBoardReplyCommand [gtre_no=" + gtre_no + ", gtre_content=" + gtre_content + ", gtre_date=" + gtre_date
				+ ", re_ip=" + re_ip + ", gtre_seq=" + gtre_seq + ", id=" + id + "]";
	}
	
	
}
