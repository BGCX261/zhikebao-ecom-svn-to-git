package com.xyz.account.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;

/**
 * 收支账户
 */
public class Account implements java.io.Serializable {

	private int recId;
	private String name;
	private BigDecimal value;
	private String type;
	private String remark;
	private Boolean bsnd;
	private Integer hot;
	private Boolean bck;
	private Integer ntraceDay;

	public Account() {
	}

	public Account(int recId) {
		this.recId = recId;
	}

	public Account(int recId, String name, BigDecimal value, String type,
			String remark, Boolean bsnd, Integer hot, Boolean bck,
			Integer ntraceDay) {
		this.recId = recId;
		this.name = name;
		this.value = value;
		this.type = type;
		this.remark = remark;
		this.bsnd = bsnd;
		this.hot = hot;
		this.bck = bck;
		this.ntraceDay = ntraceDay;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getBsnd() {
		return this.bsnd;
	}

	public void setBsnd(Boolean bsnd) {
		this.bsnd = bsnd;
	}

	public Integer getHot() {
		return this.hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public Boolean getBck() {
		return this.bck;
	}

	public void setBck(Boolean bck) {
		this.bck = bck;
	}

	public Integer getNtraceDay() {
		return this.ntraceDay;
	}

	public void setNtraceDay(Integer ntraceDay) {
		this.ntraceDay = ntraceDay;
	}

}
