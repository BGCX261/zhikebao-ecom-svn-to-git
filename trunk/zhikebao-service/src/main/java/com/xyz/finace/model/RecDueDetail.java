package com.xyz.finace.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.Date;

/**
 * 应收应付明细
 */
public class RecDueDetail implements java.io.Serializable {

	private int recId;
	private String billId;
	private String tradeId;
	private String type;
	private Date date;
	private BigDecimal money;
	private String operator;
	private String summary;
	private Boolean bwarning;
	private Date warningDate;
	private String traceOver;
	private BigDecimal moneyOver;
	private String id;
	private Integer postage;
	private String item;

	public RecDueDetail() {
	}

	public RecDueDetail(int recId) {
		this.recId = recId;
	}

	public RecDueDetail(int recId, String billId, String tradeId,
			String type, Date date, BigDecimal money, String operator,
			String summary, Boolean bwarning, Date warningDate,
			String traceOver, BigDecimal moneyOver, String id, Integer postage,
			String item) {
		this.recId = recId;
		this.billId = billId;
		this.tradeId = tradeId;
		this.type = type;
		this.date = date;
		this.money = money;
		this.operator = operator;
		this.summary = summary;
		this.bwarning = bwarning;
		this.warningDate = warningDate;
		this.traceOver = traceOver;
		this.moneyOver = moneyOver;
		this.id = id;
		this.postage = postage;
		this.item = item;
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

	public String getTradeId() {
		return this.tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Boolean getBwarning() {
		return this.bwarning;
	}

	public void setBwarning(Boolean bwarning) {
		this.bwarning = bwarning;
	}

	public Date getWarningDate() {
		return this.warningDate;
	}

	public void setWarningDate(Date warningDate) {
		this.warningDate = warningDate;
	}

	public String getTraceOver() {
		return this.traceOver;
	}

	public void setTraceOver(String traceOver) {
		this.traceOver = traceOver;
	}

	public BigDecimal getMoneyOver() {
		return this.moneyOver;
	}

	public void setMoneyOver(BigDecimal moneyOver) {
		this.moneyOver = moneyOver;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getPostage() {
		return this.postage;
	}

	public void setPostage(Integer postage) {
		this.postage = postage;
	}

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

}
