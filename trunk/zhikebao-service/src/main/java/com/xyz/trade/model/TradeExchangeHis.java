package com.xyz.trade.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 订单跟踪历史记录
 */
public class TradeExchangeHis implements java.io.Serializable {

	private int recId;
	private String tradeId;
	private String time;
	private String operator;
	private String summary;
	private String remark;

	public TradeExchangeHis() {
	}

	public TradeExchangeHis(int recId) {
		this.recId = recId;
	}

	public TradeExchangeHis(int recId, String tradeId, String time,
			String operator, String summary, String remark) {
		this.recId = recId;
		this.tradeId = tradeId;
		this.time = time;
		this.operator = operator;
		this.summary = summary;
		this.remark = remark;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
