package com.kh.mixmatch.match.domain;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class MatchCommand {

	private int m_seq;
	@NotEmpty
	private String t_name;
	private String m_area;
	private String m_date;
	@NotEmpty
	private String m_time;
	@NotEmpty
	private String m_place;
	private int m_cost;
	private String m_content;
	private String m_type;
	private String m_challenger;
	private int m_home;
	private int m_away;
	private String m_mvp;
	private String m_league;
	
	
	// 조인
	private String home_team;
	private String away_team;
	private MultipartFile t_logo_upload;
	private String t_logo_name;
	private byte[] t_logo;
	
	
	
	
	public String getHome_team() {
		return home_team;
	}
	public void setHome_team(String home_team) {
		this.home_team = home_team;
	}
	public String getAway_team() {
		return away_team;
	}
	public void setAway_team(String away_team) {
		this.away_team = away_team;
	}
	public MultipartFile getT_logo_upload() {
		return t_logo_upload;
	}
	public void setT_logo_upload(MultipartFile t_logo_upload) {
		this.t_logo_upload = t_logo_upload;
	}
	public String getT_logo_name() {
		return t_logo_name;
	}
	public void setT_logo_name(String t_logo_name) {
		this.t_logo_name = t_logo_name;
	}
	public byte[] getT_logo() {
		return t_logo;
	}
	public void setT_logo(byte[] t_logo) {
		this.t_logo = t_logo;
	}
	public int getM_seq() {
		return m_seq;
	}
	public void setM_seq(int m_seq) {
		this.m_seq = m_seq;
	}
	
	public String getM_challenger() {
		return m_challenger;
	}
	public void setM_challenger(String m_challenger) {
		this.m_challenger = m_challenger;
	}
	public String getM_area() {
		return m_area;
	}
	public void setM_area(String m_area) {
		this.m_area = m_area;
	}
	public String getM_date() {
		return m_date;
	}
	public void setM_date(String m_date) {
		this.m_date = m_date;
	}
	public String getM_time() {
		return m_time;
	}
	public void setM_time(String m_time) {
		this.m_time = m_time;
	}
	public String getM_place() {
		return m_place;
	}
	public void setM_place(String m_place) {
		this.m_place = m_place;
	}
	public int getM_cost() {
		return m_cost;
	}
	public void setM_cost(int m_cost) {
		this.m_cost = m_cost;
	}
	public String getM_content() {
		return m_content;
	}
	public void setM_content(String m_content) {
		this.m_content = m_content;
	}
	public String getM_type() {
		return m_type;
	}
	public void setM_type(String m_type) {
		this.m_type = m_type;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public int getM_home() {
		return m_home;
	}
	public void setM_home(int m_home) {
		this.m_home = m_home;
	}
	public int getM_away() {
		return m_away;
	}
	public void setM_away(int m_away) {
		this.m_away = m_away;
	}
	public String getM_mvp() {
		return m_mvp;
	}
	public void setM_mvp(String m_mvp) {
		this.m_mvp = m_mvp;
	}
	public String getM_league() {
		return m_league;
	}
	public void setM_league(String m_league) {
		this.m_league = m_league;
	}
	@Override
	public String toString() {
		return "MatchCommand [m_seq=" + m_seq + ", t_name=" + t_name + ", m_area=" + m_area + ", m_date=" + m_date
				+ ", m_time=" + m_time + ", m_place=" + m_place + ", m_cost=" + m_cost + ", m_content=" + m_content
				+ ", m_type=" + m_type + ", m_challenger=" + m_challenger + ", m_home=" + m_home + ", m_away=" + m_away
				+ ", m_mvp=" + m_mvp + ", m_league=" + m_league + ", home_team=" + home_team + ", away_team="
				+ away_team + "]";
	}
	
}
