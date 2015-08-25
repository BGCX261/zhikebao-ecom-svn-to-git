package com.xyz.stock.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 入库原因
 */
public class StockInReason implements java.io.Serializable {

	private int recId;
	private String cause;

	public StockInReason() {
	}

	public StockInReason(int recId) {
		this.recId = recId;
	}

	public StockInReason(int recId, String cause) {
		this.recId = recId;
		this.cause = cause;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getCause() {
		return this.cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

}
