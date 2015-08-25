package com.xyz.trade.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 订单日志
 */
public class TradeLog implements java.io.Serializable {

	private int recId;
	private String tradeId;
	private String time;
	private String user;
	private String event;

	public TradeLog() {
	}

	public TradeLog(int recId) {
		this.recId = recId;
	}

	public TradeLog(int recId, String tradeId, String time, String user,
			String event) {
		this.recId = recId;
		this.tradeId = tradeId;
		this.time = time;
		this.user = user;
		this.event = event;
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

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

}
