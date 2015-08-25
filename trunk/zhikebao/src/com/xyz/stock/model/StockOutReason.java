package com.xyz.stock.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 出库原因
 */
public class StockOutReason implements java.io.Serializable {

	private int recId;
	private String cause;

	public StockOutReason() {
	}

	public StockOutReason(int recId) {
		this.recId = recId;
	}

	public StockOutReason(int recId, String cause) {
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
