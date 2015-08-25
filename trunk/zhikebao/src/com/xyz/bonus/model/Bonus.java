package com.xyz.bonus.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;

/**
 * 积分返券 
 */
public class Bonus implements java.io.Serializable {

	private int recId;
	private String seller;
	private String billId;
	private String date;
	private String summary;
	private BigDecimal money;
	private String reserved;
	private String id;

	public Bonus() {
	}

	public Bonus(int recId) {
		this.recId = recId;
	}

	public Bonus(int recId, String seller, String billId, String date,
			String summary, BigDecimal money, String reserved, String id) {
		this.recId = recId;
		this.seller = seller;
		this.billId = billId;
		this.date = date;
		this.summary = summary;
		this.money = money;
		this.reserved = reserved;
		this.id = id;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getSeller() {
		return this.seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getBillId() {
		return this.billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getReserved() {
		return this.reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
