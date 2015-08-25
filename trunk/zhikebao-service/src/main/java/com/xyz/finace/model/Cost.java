package com.xyz.finace.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.Date;

/**
 * 费用
 */
public class Cost implements java.io.Serializable {

	private int recId;
	private Date date;
	private String operator;
	private BigDecimal money;
	private String item;
	private String summary;

	public Cost() {
	}

	public Cost(int recId) {
		this.recId = recId;
	}

	public Cost(int recId, Date date, String operator, BigDecimal money,
			String item, String summary) {
		this.recId = recId;
		this.date = date;
		this.operator = operator;
		this.money = money;
		this.item = item;
		this.summary = summary;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}
