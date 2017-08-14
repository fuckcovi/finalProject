package com.kh.mixmatch.pointshop.domain;

import java.sql.Date;

public class PointShopCartCommand {
	
	private int p_seq;
	private String id;
	private String p_name;
	private int amount;
	private int p_price;
	private Date p_date;
	
	public int getP_seq() {
		return p_seq;
	}
	public void setP_seq(int p_seq) {
		this.p_seq = p_seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public Date getP_date() {
		return p_date;
	}
	public void setP_date(Date p_date) {
		this.p_date = p_date;
	}
	
	@Override
	public String toString() {
		return "PointShopCartCommand [p_seq=" + p_seq + ", id=" + id + ", p_name=" + p_name + ", amount=" + amount
				+ ", p_price=" + p_price + ", p_date=" + p_date + "]";
	}
	
}
