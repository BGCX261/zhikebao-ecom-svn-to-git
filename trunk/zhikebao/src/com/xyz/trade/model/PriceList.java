package com.xyz.trade.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 价格目录
 */
public class PriceList implements java.io.Serializable {

	private int recId;
	private String priceId;
	private String priceName;

	public PriceList() {
	}

	public PriceList(int recId) {
		this.recId = recId;
	}

	public PriceList(int recId, String priceId, String priceName) {
		this.recId = recId;
		this.priceId = priceId;
		this.priceName = priceName;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getPriceId() {
		return this.priceId;
	}

	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}

	public String getPriceName() {
		return this.priceName;
	}

	public void setPriceName(String priceName) {
		this.priceName = priceName;
	}

}
