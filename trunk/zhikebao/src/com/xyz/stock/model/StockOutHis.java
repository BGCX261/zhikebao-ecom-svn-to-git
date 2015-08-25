package com.xyz.stock.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * 出库单历史
 */
public class StockOutHis implements java.io.Serializable {

	private int recId;
	private String billId;
	private Date date;
	private String operator;
	private String reason;
	private String remark;
	private String id;
	private String tradeId;
	private String status;
	private String ckOperator;
	private String type;

	public StockOutHis() {
	}

	public StockOutHis(int recId) {
		this.recId = recId;
	}

	public StockOutHis(int recId, String billId, Date date, String operator,
			String reason, String remark, String id, String tradeId,
			String status, String ckOperator, String type) {
		this.recId = recId;
		this.billId = billId;
		this.date = date;
		this.operator = operator;
		this.reason = reason;
		this.remark = remark;
		this.id = id;
		this.tradeId = tradeId;
		this.status = status;
		this.ckOperator = ckOperator;
		this.type = type;
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

	public String getTradeId() {
		return this.tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCkOperator() {
		return this.ckOperator;
	}

	public void setCkOperator(String ckOperator) {
		this.ckOperator = ckOperator;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
