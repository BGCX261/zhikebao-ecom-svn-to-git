package com.xyz.finace.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.Date;

/**
 * 预存款
 */
public class PreDeposit implements java.io.Serializable {

	private int recId;
	private String type;
	private Date date;
	private BigDecimal value;
	private BigDecimal remaining;
	private String remark;
	private String customer;
	private String account;
	private String tradeId;
	private String operator;

	public PreDeposit() {
	}

	public PreDeposit(int recId) {
		this.recId = recId;
	}

	public PreDeposit(int recId, String type, Date date, BigDecimal value,
			BigDecimal remaining, String remark, String customer,
			String account, String tradeId, String operator) {
		this.recId = recId;
		this.type = type;
		this.date = date;
		this.value = value;
		this.remaining = remaining;
		this.remark = remark;
		this.customer = customer;
		this.account = account;
		this.tradeId = tradeId;
		this.operator = operator;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getRemaining() {
		return this.remaining;
	}

	public void setRemaining(BigDecimal remaining) {
		this.remaining = remaining;
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

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTradeId() {
		return this.tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}
