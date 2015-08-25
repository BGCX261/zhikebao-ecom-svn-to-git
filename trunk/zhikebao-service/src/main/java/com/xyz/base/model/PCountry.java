package com.xyz.base.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 国家列表
 */
public class PCountry implements java.io.Serializable {

	private int recId;
	private String country;

	public PCountry() {
	}

	public PCountry(int recId) {
		this.recId = recId;
	}

	public PCountry(int recId, String country) {
		this.recId = recId;
		this.country = country;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
