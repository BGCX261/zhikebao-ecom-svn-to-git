package com.xyz.stock.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.Date;

/**
 * 入库单历史
 */
public class StockInHis implements java.io.Serializable {

	private int recId;
	private String billId;
	private Date date;
	private String operator;
	private String reason;
	private String provider;
	private BigDecimal value;
	private String remark;
	private String id;
	private String type;
	private String curStatus;
	private String ckoperator;
	private String operationId;

	public StockInHis() {
	}

	public StockInHis(int recId) {
		this.recId = recId;
	}

	public StockInHis(int recId, String billId, Date date, String operator,
			String reason, String provider, BigDecimal value, String remark,
			String id, String type, String curStatus, String ckoperator,
			String operationId) {
		this.recId = recId;
		this.billId = billId;
		this.date = date;
		this.operator = operator;
		this.reason = reason;
		this.provider = provider;
		this.value = value;
		this.remark = remark;
		this.id = id;
		this.type = type;
		this.curStatus = curStatus;
		this.ckoperator = ckoperator;
		this.operationId = operationId;
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

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getProvider() {
		return this.provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCurStatus() {
		return this.curStatus;
	}

	public void setCurStatus(String curStatus) {
		this.curStatus = curStatus;
	}

	public String getCkoperator() {
		return this.ckoperator;
	}

	public void setCkoperator(String ckoperator) {
		this.ckoperator = ckoperator;
	}

	public String getOperationId() {
		return this.operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

}
