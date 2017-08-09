package com.kh.mixmatch.team.domain;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class BaseCommand {
	private int b_seq;
	private String id;
	private String t_name;
	private int m_seq;
	private int b_bat;
	private int b_hit;
	private int b_rbi;
	private int b_score;
	private double b_avg;
	private int b_win;
	private int b_lose;
	private int b_strike;
	private int b_ip;
	private int b_er;
	private double b_era;
	
	private String name; //회원명 : MemberCommand 조인
	private String profile_name; //프로필명 : MemberCommand 조인
	private byte[] profile;
	private MultipartFile profile_upload;

	private int recordstatus;
	
	
	public int getRecordstatus() {
		return recordstatus;
	}
	public void setRecordstatus(int recordstatus) {
		this.recordstatus = recordstatus;
	}
	public void setProfile_upload(MultipartFile profile_upload) throws IOException {
		this.profile_upload = profile_upload;
		setProfile(profile_upload.getBytes());
		setProfile_name(profile_upload.getOriginalFilename());
	}
	public byte[] getProfile() {
		return profile;
	}
	public void setProfile(byte[] profile) {
		this.profile = profile;
	}
	public MultipartFile getProfile_upload() {
		return profile_upload;
	}
	public String getProfile_name() {
		return profile_name;
	}
	public void setProfile_name(String profile_name) {
		this.profile_name = profile_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getB_seq() {
		return b_seq;
	}
	public void setB_seq(int b_seq) {
		this.b_seq = b_seq;
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
	public int getB_bat() {
		return b_bat;
	}
	public void setB_bat(int b_bat) {
		this.b_bat = b_bat;
	}
	public int getB_hit() {
		return b_hit;
	}
	public void setB_hit(int b_hit) {
		this.b_hit = b_hit;
	}
	public int getB_rbi() {
		return b_rbi;
	}
	public void setB_rbi(int b_rbi) {
		this.b_rbi = b_rbi;
	}
	public int getB_score() {
		return b_score;
	}
	public void setB_score(int b_score) {
		this.b_score = b_score;
	}
	public double getB_avg() {
		return b_avg;
	}
	public void setB_avg(double b_avg) {
		this.b_avg = b_avg;
	}
	public int getB_win() {
		return b_win;
	}
	public void setB_win(int b_win) {
		this.b_win = b_win;
	}
	public int getB_lose() {
		return b_lose;
	}
	public void setB_lose(int b_lose) {
		this.b_lose = b_lose;
	}
	public int getB_strike() {
		return b_strike;
	}
	public void setB_strike(int b_strike) {
		this.b_strike = b_strike;
	}
	public int getB_ip() {
		return b_ip;
	}
	public void setB_ip(int b_ip) {
		this.b_ip = b_ip;
	}
	public int getB_er() {
		return b_er;
	}
	public void setB_er(int b_er) {
		this.b_er = b_er;
	}
	public double getB_era() {
		return b_era;
	}
	public void setB_era(double b_era) {
		this.b_era = b_era;
	}
	@Override
	public String toString() {
		return "BaseCommand [b_seq=" + b_seq + ", id=" + id + ", t_name=" + t_name + ", m_seq=" + m_seq + ", b_bat="
				+ b_bat + ", b_hit=" + b_hit + ", b_rbi=" + b_rbi + ", b_score=" + b_score + ", b_avg=" + b_avg
				+ ", b_win=" + b_win + ", b_lose=" + b_lose + ", b_strike=" + b_strike + ", b_ip=" + b_ip + ", b_er="
				+ b_er + ", b_era=" + b_era + "]";
	}
	
}
