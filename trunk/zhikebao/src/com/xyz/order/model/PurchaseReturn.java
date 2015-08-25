package com.xyz.order.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购退货单
 */
public class PurchaseReturn implements java.io.Serializable {

	private int recId;
	private Date sdate;
	private String operator;
	private String provider;
	private BigDecimal moneyTotal;
	private Boolean bmoneyTrace;
	private String payAccount;
	private String id;
	private String remark;
	private String billId;

	public PurchaseReturn() {
	}

	public PurchaseReturn(int recId) {
		this.recId = recId;
	}

	public PurchaseReturn(int recId, Date sdate, String operator,
			String provider, BigDecimal moneyTotal, Boolean bmoneyTrace,
			String payAccount, String id, String remark, String billId) {
		this.recId = recId;
		this.sdate = sdate;
		this.operator = operator;
		this.provider = provider;
		this.moneyTotal = moneyTotal;
		this.bmoneyTrace = bmoneyTrace;
		this.payAccount = payAccount;
		this.id = id;
		this.remark = remark;
		this.billId = billId;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public Date getSdate() {
		return this.sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getProvider() {
		return this.provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public BigDecimal getMoneyTotal() {
		return this.moneyTotal;
	}

	public void setMoneyTotal(BigDecimal moneyTotal) {
		this.moneyTotal = moneyTotal;
	}

	public Boolean getBmoneyTrace() {
		return this.bmoneyTrace;
	}

	public void setBmoneyTrace(Boolean bmoneyTrace) {
		this.bmoneyTrace = bmoneyTrace;
	}

	public String getPayAccount() {
		return this.payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBillId() {
		return this.billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

}
