package com.kh.mixmatch.teamboard.domain;

public class TeamBoardReplyCommand {
	
	private int gtre_no;
	private String gtre_content;
	private String gtre_date;
	private String ip;
	private int gt_seq;
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
		this.gtre_date = gtre_date;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getGt_seq() {
		return gt_seq;
	}
	public void setGt_seq(int gt_seq) {
		this.gt_seq = gt_seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "TeamBoardReplyCommand [gtre_no=" + gtre_no + ", gtre_content=" + gtre_content + ", gtre_date="
				+ gtre_date + ", ip=" + ip + ", gt_seq=" + gt_seq + ", id=" + id + "]";
	}
	
}
