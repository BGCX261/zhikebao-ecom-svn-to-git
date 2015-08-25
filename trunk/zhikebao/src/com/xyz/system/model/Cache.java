package com.xyz.system.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 缓存
 */
public class Cache implements java.io.Serializable {

	private int recId;
	private String type;
	private String value;

	public Cache() {
	}

	public Cache(int recId) {
		this.recId = recId;
	}

	public Cache(int recId, String type, String value) {
		this.recId = recId;
		this.type = type;
		this.value = value;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
