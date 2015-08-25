package com.xyz.trade.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.Date;

/**
 * 交易积分
 */
public class TradeScore implements java.io.Serializable {

	private int recId;
	private String tradeId;
	private Date date;
	private BigDecimal nscore;

	public TradeScore() {
	}

	public TradeScore(int recId) {
		this.recId = recId;
	}

	public TradeScore(int recId, String tradeId, Date date, BigDecimal nscore) {
		this.recId = recId;
		this.tradeId = tradeId;
		this.date = date;
		this.nscore = nscore;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getTradeId() {
		return this.tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getNscore() {
		return this.nscore;
	}

	public void setNscore(BigDecimal nscore) {
		this.nscore = nscore;
	}

}
