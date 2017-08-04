package com.kh.mixmatch.board.domain;

import com.kh.mixmatch.util.DurationFromNow;

public class GNoticeReplyCommand {
	private int gnre_no;
	private String gnre_content;
	private String gnre_date;
	private String re_ip;
	private int gnre_seq;
	private String id;
	public int getGnre_no() {
		return gnre_no;
	}
	public void setGnre_no(int gnre_no) {
		this.gnre_no = gnre_no;
	}
	public String getGnre_content() {
		return gnre_content;
	}
	public void setGnre_content(String gnre_content) {
		this.gnre_content = gnre_content;
	}
	public String getGnre_date() {
		return gnre_date;
	}
	public void setGnre_date(String gnre_date) {
		this.gnre_date = DurationFromNow.getTimeDiffLabel(gnre_date);
	}
	public String getRe_ip() {
		return re_ip;
	}
	public void setRe_ip(String re_ip) {
		this.re_ip = re_ip;
	}
	public int getGnre_seq() {
		return gnre_seq;
	}
	public void setGnre_seq(int gnre_seq) {
		this.gnre_seq = gnre_seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "GNoticeReplyCommand [gnre_no=" + gnre_no + ", gnre_content=" + gnre_content + ", gnre_date=" + gnre_date
				+ ", re_ip=" + re_ip + ", gnre_seq=" + gnre_seq + ", id=" + id + "]";
	}
	
	
}
