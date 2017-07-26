package com.kh.mixmatch.team.domain;

import java.sql.Date;

public class TeamMemCommand {
	private int t_mem_seq;
	private String id;
	private String t_name;
	private Date t_mem_regdate;
	
	
	public int getT_mem_seq() {
		return t_mem_seq;
	}
	public void setT_mem_seq(int t_mem_seq) {
		this.t_mem_seq = t_mem_seq;
	}
	public Date getT_mem_regdate() {
		return t_mem_regdate;
	}
	public void setT_mem_regdate(Date t_mem_regdate) {
		this.t_mem_regdate = t_mem_regdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	@Override
	public String toString() {
		return "TeamMemCommand [t_mem_seq=" + t_mem_seq + ", id=" + id + ", t_name=" + t_name + ", t_mem_regdate="
				+ t_mem_regdate + "]";
	}
	
	
}
