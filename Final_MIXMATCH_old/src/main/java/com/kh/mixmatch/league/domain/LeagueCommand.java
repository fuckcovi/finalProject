package com.kh.mixmatch.league.domain;
/*
  l_seq number not null primary key,
  id varchar2(20) not null,
  l_title varchar2(50) not null,
  l_area varchar2(20) not null,
  l_date varchar2(20) not null,
  l_time varchar2(20) not null,
  l_place varchar2(50) not null,
  l_cost number default 0 not null,
  l_content varchar2(300),
  l_number number not null,
  l_team number default 0 not null,
  l_type varchar2(20) not null,
*/
public class LeagueCommand {

	private int l_seq;
	private String id;
	private String l_title;
	private String l_area;
	private String l_date;
	private String l_time;
	private String l_place;
	private int l_cost;
	private String l_content;
	private int l_number;
	private int l_team;
	private String l_type;
	
	public int getL_seq() {
		return l_seq;
	}
	public void setL_seq(int l_seq) {
		this.l_seq = l_seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getL_title() {
		return l_title;
	}
	public void setL_title(String l_title) {
		this.l_title = l_title;
	}
	public String getL_area() {
		return l_area;
	}
	public void setL_area(String l_area) {
		this.l_area = l_area;
	}
	public String getL_date() {
		return l_date;
	}
	public void setL_date(String l_date) {
		this.l_date = l_date;
	}
	public String getL_time() {
		return l_time;
	}
	public void setL_time(String l_time) {
		this.l_time = l_time;
	}
	public String getL_place() {
		return l_place;
	}
	public void setL_place(String l_place) {
		this.l_place = l_place;
	}
	public int getL_cost() {
		return l_cost;
	}
	public void setL_cost(int l_cost) {
		this.l_cost = l_cost;
	}
	public String getL_content() {
		return l_content;
	}
	public void setL_content(String l_content) {
		this.l_content = l_content;
	}
	public int getL_number() {
		return l_number;
	}
	public void setL_number(int l_number) {
		this.l_number = l_number;
	}
	public int getL_team() {
		return l_team;
	}
	public void setL_team(int l_team) {
		this.l_team = l_team;
	}
	public String getL_type() {
		return l_type;
	}
	public void setL_type(String l_type) {
		this.l_type = l_type;
	}
	
	@Override
	public String toString() {
		return "LeagueCommand [l_seq=" + l_seq + ", id=" + id + ", l_title=" + l_title + ", l_area=" + l_area
				+ ", l_date=" + l_date + ", l_time=" + l_time + ", l_place=" + l_place + ", l_cost=" + l_cost
				+ ", l_content=" + l_content + ", l_number=" + l_number + ", l_team=" + l_team + ", l_type=" + l_type
				+ "]";
	}
	
}
