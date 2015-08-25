package com.xyz.finace.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;

/**
 * 应收应付明细
 */
public class RecDue implements java.io.Serializable {

	private int recId;
	private String billId;
	private String customerId;
	private String customerName;
	private String linkMan;
	private String tel;
	private BigDecimal rec;
	private BigDecimal due;
	private String traceOver;
	private String remark;
	private BigDecimal recOver;
	private BigDecimal dueOver;
	private String py;
	private String customerType;

	public RecDue() {
	}

	public RecDue(int recId) {
		this.recId = recId;
	}

	public RecDue(int recId, String billId, String customerId,
			String customerName, String linkMan, String tel, BigDecimal rec,
			BigDecimal due, String traceOver, String remark,
			BigDecimal recOver, BigDecimal dueOver, String py,
			String customerType) {
		this.recId = recId;
		this.billId = billId;
		this.customerId = customerId;
		this.customerName = customerName;
		this.linkMan = linkMan;
		this.tel = tel;
		this.rec = rec;
		this.due = due;
		this.traceOver = traceOver;
		this.remark = remark;
		this.recOver = recOver;
		this.dueOver = dueOver;
		this.py = py;
		this.customerType = customerType;
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

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getLinkMan() {
		return this.linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public BigDecimal getRec() {
		return this.rec;
	}

	public void setRec(BigDecimal rec) {
		this.rec = rec;
	}

	public BigDecimal getDue() {
		return this.due;
	}

	public void setDue(BigDecimal due) {
		this.due = due;
	}

	public String getTraceOver() {
		return this.traceOver;
	}

	public void setTraceOver(String traceOver) {
		this.traceOver = traceOver;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getRecOver() {
		return this.recOver;
	}

	public void setRecOver(BigDecimal recOver) {
		this.recOver = recOver;
	}

	public BigDecimal getDueOver() {
		return this.dueOver;
	}

	public void setDueOver(BigDecimal dueOver) {
		this.dueOver = dueOver;
	}

	public String getPy() {
		return this.py;
	}

	public void setPy(String py) {
		this.py = py;
	}

	public String getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

}
