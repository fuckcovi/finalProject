package com.kh.mixmatch.league.domain;
/*
  ld_seq number not null primary key,
  l_seq number not null,
  t_name varchar2(30) not null,
  ld_check varchar2(5) default N,
 */
public class LeagueDtlCommand {

	private int ld_seq;
	private int l_seq;
	private String t_name;
	private String ld_check;
	
	public int getLd_seq() {
		return ld_seq;
	}
	public void setLd_seq(int ld_seq) {
		this.ld_seq = ld_seq;
	}
	public int getL_seq() {
		return l_seq;
	}
	public void setL_seq(int l_seq) {
		this.l_seq = l_seq;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public String getLd_check() {
		return ld_check;
	}
	public void setLd_check(String ld_check) {
		this.ld_check = ld_check;
	}
	
	@Override
	public String toString() {
		return "LeagueDtlCommand [ld_seq=" + ld_seq + ", l_seq=" + l_seq + ", t_name=" + t_name + ", ld_check="
				+ ld_check + "]";
	}
	
}
