package com.xyz.serve.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 客户来电类别
 */
public class CcTelType implements java.io.Serializable {

	private int recId;
	private String telType;

	public CcTelType() {
	}

	public CcTelType(int recId) {
		this.recId = recId;
	}

	public CcTelType(int recId, String telType) {
		this.recId = recId;
		this.telType = telType;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getTelType() {
		return this.telType;
	}

	public void setTelType(String telType) {
		this.telType = telType;
	}

}
