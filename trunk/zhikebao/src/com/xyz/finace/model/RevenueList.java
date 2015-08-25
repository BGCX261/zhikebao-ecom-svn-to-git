package com.xyz.finace.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.Date;

/**
 * 查账记录
 */
public class RevenueList implements java.io.Serializable {

	private int recId;
	private Date date;
	private String operator;
	private String account;
	private BigDecimal money;
	private String summary;
	private String status;
	private String engrossed;
	private String tradeId;
	private String customerInfo;
	private String remark;

	public RevenueList() {
	}

	public RevenueList(int recId) {
		this.recId = recId;
	}

	public RevenueList(int recId, Date date, String operator, String account,
			BigDecimal money, String summary, String status, String engrossed,
			String tradeId, String customerInfo, String remark) {
		this.recId = recId;
		this.date = date;
		this.operator = operator;
		this.account = account;
		this.money = money;
		this.summary = summary;
		this.status = status;
		this.engrossed = engrossed;
		this.tradeId = tradeId;
		this.customerInfo = customerInfo;
		this.remark = remark;
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

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEngrossed() {
		return this.engrossed;
	}

	public void setEngrossed(String engrossed) {
		this.engrossed = engrossed;
	}

	public String getTradeId() {
		return this.tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getCustomerInfo() {
		return this.customerInfo;
	}

	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
