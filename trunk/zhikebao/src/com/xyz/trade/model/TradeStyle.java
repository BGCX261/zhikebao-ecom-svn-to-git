package com.xyz.trade.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 交易方式
 */
public class TradeStyle implements java.io.Serializable {

	private int recId;
	private String style;
	private Integer hot;

	public TradeStyle() {
	}

	public TradeStyle(int recId) {
		this.recId = recId;
	}

	public TradeStyle(int recId, String style, Integer hot) {
		this.recId = recId;
		this.style = style;
		this.hot = hot;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getStyle() {
		return this.style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Integer getHot() {
		return this.hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

}
