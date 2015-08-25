package com.xyz.finace.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.Date;

/**
 * 收支流水
 */
public class InOut implements java.io.Serializable {

	private int recId;
	private Date date;
	private String type;
	private BigDecimal money;
	private String summary;
	private String item;
	private String account;
	private String customer;
	private String tradeId;
	private BigDecimal balance;
	private String id;
	private String operator;
	private String inValue;
	private String outValue;
	private Boolean bcost;
	private String status;
	private String shop;
	private String customerType;
	private Double chktime;

	public InOut() {
	}

	public InOut(int recId) {
		this.recId = recId;
	}

	public InOut(int recId, Date date, String type, BigDecimal money,
			String summary, String item, String account, String customer,
			String tradeId, BigDecimal balance, String id, String operator,
			String inValue, String outValue, Boolean bcost, String status,
			String shop, String customerType, Double chktime) {
		this.recId = recId;
		this.date = date;
		this.type = type;
		this.money = money;
		this.summary = summary;
		this.item = item;
		this.account = account;
		this.customer = customer;
		this.tradeId = tradeId;
		this.balance = balance;
		this.id = id;
		this.operator = operator;
		this.inValue = inValue;
		this.outValue = outValue;
		this.bcost = bcost;
		this.status = status;
		this.shop = shop;
		this.customerType = customerType;
		this.chktime = chktime;
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getTradeId() {
		return this.tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getInValue() {
		return this.inValue;
	}

	public void setInValue(String inValue) {
		this.inValue = inValue;
	}

	public String getOutValue() {
		return this.outValue;
	}

	public void setOutValue(String outValue) {
		this.outValue = outValue;
	}

	public Boolean getBcost() {
		return this.bcost;
	}

	public void setBcost(Boolean bcost) {
		this.bcost = bcost;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getShop() {
		return this.shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public Double getChktime() {
		return this.chktime;
	}

	public void setChktime(Double chktime) {
		this.chktime = chktime;
	}

}
