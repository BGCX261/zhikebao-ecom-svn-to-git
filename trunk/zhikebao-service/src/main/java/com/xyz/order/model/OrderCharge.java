package com.xyz.order.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购单换货记录
 */
public class OrderCharge implements java.io.Serializable {

	private int recId;
	private String billId;
	private Date chargeDate;
	private String operator;
	private String chargeType;
	private BigDecimal chargeValue;
	private String account;
	private String chargeId;
	private String remark;

	public OrderCharge() {
	}

	public OrderCharge(int recId) {
		this.recId = recId;
	}

	public OrderCharge(int recId, String billId, Date chargeDate,
			String operator, String chargeType, BigDecimal chargeValue,
			String account, String chargeId, String remark) {
		this.recId = recId;
		this.billId = billId;
		this.chargeDate = chargeDate;
		this.operator = operator;
		this.chargeType = chargeType;
		this.chargeValue = chargeValue;
		this.account = account;
		this.chargeId = chargeId;
		this.remark = remark;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getBillId() {
		return this.billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public Date getChargeDate() {
		return this.chargeDate;
	}

	public void setChargeDate(Date chargeDate) {
		this.chargeDate = chargeDate;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getChargeType() {
		return this.chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public BigDecimal getChargeValue() {
		return this.chargeValue;
	}

	public void setChargeValue(BigDecimal chargeValue) {
		this.chargeValue = chargeValue;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getChargeId() {
		return this.chargeId;
	}

	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
