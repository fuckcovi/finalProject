package com.kh.mixmatch.match.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class TotoCommand {
	
	private int t_seq;
	private int m_seq;
	@NotEmpty
	private String id;
	private int t_point;
	@NotEmpty
	private String t_winteam;
	private int t_score;
	private double t_rate;
	
	public int getT_seq() {
		return t_seq;
	}
	public void setT_seq(int t_seq) {
		this.t_seq = t_seq;
	}
	public int getM_seq() {
		return m_seq;
	}
	public void setM_seq(int m_seq) {
		this.m_seq = m_seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getT_point() {
		return t_point;
	}
	public void setT_point(int t_point) {
		this.t_point = t_point;
	}
	public String getT_winteam() {
		return t_winteam;
	}
	public void setT_winteam(String t_winteam) {
		this.t_winteam = t_winteam;
	}
	public int getT_score() {
		return t_score;
	}
	public void setT_score(int t_score) {
		this.t_score = t_score;
	}
	public double getT_rate() {
		return t_rate;
	}
	public void setT_rate(double t_rate) {
		this.t_rate = t_rate;
	}
	
	@Override
	public String toString() {
		return "TotoCommand [t_seq=" + t_seq + ", m_seq=" + m_seq + ", id=" + id + ", t_point=" + t_point
				+ ", t_winteam=" + t_winteam + ", t_score=" + t_score + ", t_rate=" + t_rate + "]";
	}

}
