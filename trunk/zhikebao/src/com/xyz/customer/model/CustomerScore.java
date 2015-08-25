package com.xyz.customer.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * 客户积分
 */
public class CustomerScore implements java.io.Serializable {

	private int recId;
	private Integer score;
	private Date date;
	private String remark;
	private String customer;

	public CustomerScore() {
	}

	public CustomerScore(int recId) {
		this.recId = recId;
	}

	public CustomerScore(int recId, Integer score, Date date, String remark,
			String customer) {
		this.recId = recId;
		this.score = score;
		this.date = date;
		this.remark = remark;
		this.customer = customer;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

}
