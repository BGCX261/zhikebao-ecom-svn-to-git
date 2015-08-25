package com.xyz.base.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;

/**
 * 汇率
 */
public class Currency implements java.io.Serializable {

	private int recId;
	private String currency;
	private BigDecimal rate;
	private Integer hot;

	public Currency() {
	}

	public Currency(int recId) {
		this.recId = recId;
	}

	public Currency(int recId, String currency, BigDecimal rate, Integer hot) {
		this.recId = recId;
		this.currency = currency;
		this.rate = rate;
		this.hot = hot;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Integer getHot() {
		return this.hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

}
