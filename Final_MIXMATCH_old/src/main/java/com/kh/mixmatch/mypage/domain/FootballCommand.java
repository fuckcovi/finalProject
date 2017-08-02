package com.kh.mixmatch.mypage.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class FootballCommand {
	@NotEmpty
	private int f_seq;
	@NotEmpty
	private String id;
	@NotEmpty
	private String t_name;
	@NotEmpty
	private int m_seq;
	private int f_shoot;
	private int f_assist;
	private int f_goal;
	private int f_attack;
	
	public int getF_seq() {
		return f_seq;
	}
	public void setF_seq(int f_seq) {
		this.f_seq = f_seq;
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
	public int getM_seq() {
		return m_seq;
	}
	public void setM_seq(int m_seq) {
		this.m_seq = m_seq;
	}
	public int getF_shoot() {
		return f_shoot;
	}
	public void setF_shoot(int f_shoot) {
		this.f_shoot = f_shoot;
	}
	public int getF_assist() {
		return f_assist;
	}
	public void setF_assist(int f_assist) {
		this.f_assist = f_assist;
	}
	public int getF_goal() {
		return f_goal;
	}
	public void setF_goal(int f_goal) {
		this.f_goal = f_goal;
	}
	public int getF_attack() {
		return f_attack;
	}
	public void setF_attack(int f_attack) {
		this.f_attack = f_attack;
	}
	@Override
	public String toString() {
		return "FootballCommand [f_seq=" + f_seq + ", id=" + id + ", t_name=" + t_name + ", m_seq=" + m_seq
				+ ", f_shoot=" + f_shoot + ", f_assist=" + f_assist + ", f_goal=" + f_goal + ", f_attack=" + f_attack
				+ "]";
	}
	
	
}
