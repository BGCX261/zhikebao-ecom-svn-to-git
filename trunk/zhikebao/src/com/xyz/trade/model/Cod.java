package com.xyz.trade.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * ？？？
 */
public class Cod implements java.io.Serializable {

	private int recId;
	private String tradeId;
	private String packageBill;
	private Double tradeValue;
	private Date sndDate;
	private String freightCorp;
	private Integer chargeStatus;
	private String summary;
	private String remark;
	private String sndTo;
	private String operator;

	public Cod() {
	}

	public Cod(int recId) {
		this.recId = recId;
	}

	public Cod(int recId, String tradeId, String packageBill,
			Double tradeValue, Date sndDate, String freightCorp,
			Integer chargeStatus, String summary, String remark, String sndTo,
			String operator) {
		this.recId = recId;
		this.tradeId = tradeId;
		this.packageBill = packageBill;
		this.tradeValue = tradeValue;
		this.sndDate = sndDate;
		this.freightCorp = freightCorp;
		this.chargeStatus = chargeStatus;
		this.summary = summary;
		this.remark = remark;
		this.sndTo = sndTo;
		this.operator = operator;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getTradeId() {
		return this.tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getPackageBill() {
		return this.packageBill;
	}

	public void setPackageBill(String packageBill) {
		this.packageBill = packageBill;
	}

	public Double getTradeValue() {
		return this.tradeValue;
	}

	public void setTradeValue(Double tradeValue) {
		this.tradeValue = tradeValue;
	}

	public Date getSndDate() {
		return this.sndDate;
	}

	public void setSndDate(Date sndDate) {
		this.sndDate = sndDate;
	}

	public String getFreightCorp() {
		return this.freightCorp;
	}

	public void setFreightCorp(String freightCorp) {
		this.freightCorp = freightCorp;
	}

	public Integer getChargeStatus() {
		return this.chargeStatus;
	}

	public void setChargeStatus(Integer chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSndTo() {
		return this.sndTo;
	}

	public void setSndTo(String sndTo) {
		this.sndTo = sndTo;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}
