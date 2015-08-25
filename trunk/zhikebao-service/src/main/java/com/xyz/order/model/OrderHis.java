package com.xyz.order.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购单历史
 */
public class OrderHis implements java.io.Serializable {

	private int recId;
	private String billId;
	private Date date;
	private String operator;
	private String value;
	private String provider;
	private String status;
	private String id;
	private String remark;
	private String orderStatus;
	private String payStyle;
	private BigDecimal payValue;
	private BigDecimal otherFee;
	private Boolean bpay;
	private Boolean bstockIn;

	public OrderHis() {
	}

	public OrderHis(int recId) {
		this.recId = recId;
	}

	public OrderHis(int recId, String billId, Date date, String operator,
			String value, String provider, String status, String id,
			String remark, String orderStatus, String payStyle,
			BigDecimal payValue, BigDecimal otherFee, Boolean bpay,
			Boolean bstockIn) {
		this.recId = recId;
		this.billId = billId;
		this.date = date;
		this.operator = operator;
		this.value = value;
		this.provider = provider;
		this.status = status;
		this.id = id;
		this.remark = remark;
		this.orderStatus = orderStatus;
		this.payStyle = payStyle;
		this.payValue = payValue;
		this.otherFee = otherFee;
		this.bpay = bpay;
		this.bstockIn = bstockIn;
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

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getProvider() {
		return this.provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPayStyle() {
		return this.payStyle;
	}

	public void setPayStyle(String payStyle) {
		this.payStyle = payStyle;
	}

	public BigDecimal getPayValue() {
		return this.payValue;
	}

	public void setPayValue(BigDecimal payValue) {
		this.payValue = payValue;
	}

	public BigDecimal getOtherFee() {
		return this.otherFee;
	}

	public void setOtherFee(BigDecimal otherFee) {
		this.otherFee = otherFee;
	}

	public Boolean getBpay() {
		return this.bpay;
	}

	public void setBpay(Boolean bpay) {
		this.bpay = bpay;
	}

	public Boolean getBstockIn() {
		return this.bstockIn;
	}

	public void setBstockIn(Boolean bstockIn) {
		this.bstockIn = bstockIn;
	}

}
