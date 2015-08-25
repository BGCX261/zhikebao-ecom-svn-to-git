package com.xyz.stock.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.Date;

/**
 * 库存盘点
 */
public class StockCheck implements java.io.Serializable {

	private int recId;
	private String billId;
	private Date date;
	private String operator;
	private BigDecimal money;
	private BigDecimal count;
	private String remark;
	private String id;

	public StockCheck() {
	}

	public StockCheck(int recId) {
		this.recId = recId;
	}

	public StockCheck(int recId, String billId, Date date, String operator,
			BigDecimal money, BigDecimal count, String remark, String id) {
		this.recId = recId;
		this.billId = billId;
		this.date = date;
		this.operator = operator;
		this.money = money;
		this.count = count;
		this.remark = remark;
		this.id = id;
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

	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getCount() {
		return this.count;
	}

	public void setCount(BigDecimal count) {
		this.count = count;
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

}
