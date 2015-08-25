package com.xyz.base.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 计量单位
 */
public class Unit implements java.io.Serializable {

	private int recId;
	private String unit;
	private Integer hot;

	public Unit() {
	}

	public Unit(int recId) {
		this.recId = recId;
	}

	public Unit(int recId, String unit, Integer hot) {
		this.recId = recId;
		this.unit = unit;
		this.hot = hot;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getHot() {
		return this.hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

}
