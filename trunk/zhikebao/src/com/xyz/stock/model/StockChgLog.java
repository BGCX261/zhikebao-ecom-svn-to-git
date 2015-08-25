package com.xyz.stock.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.Date;

/**
 * 库存变动日志
 */
public class StockChgLog implements java.io.Serializable {

	private int recId;
	private String goodsNo;
	private BigDecimal curStock;
	private String chgType;
	private BigDecimal chgCount;
	private String summary;
	private Date date;
	private String operator;
	private String spec;

	public StockChgLog() {
	}

	public StockChgLog(int recId) {
		this.recId = recId;
	}

	public StockChgLog(int recId, String goodsNo, BigDecimal curStock,
			String chgType, BigDecimal chgCount, String summary, Date date,
			String operator, String spec) {
		this.recId = recId;
		this.goodsNo = goodsNo;
		this.curStock = curStock;
		this.chgType = chgType;
		this.chgCount = chgCount;
		this.summary = summary;
		this.date = date;
		this.operator = operator;
		this.spec = spec;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getGoodsNo() {
		return this.goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public BigDecimal getCurStock() {
		return this.curStock;
	}

	public void setCurStock(BigDecimal curStock) {
		this.curStock = curStock;
	}

	public String getChgType() {
		return this.chgType;
	}

	public void setChgType(String chgType) {
		this.chgType = chgType;
	}

	public BigDecimal getChgCount() {
		return this.chgCount;
	}

	public void setChgCount(BigDecimal chgCount) {
		this.chgCount = chgCount;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
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

	public String getSpec() {
		return this.spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

}
