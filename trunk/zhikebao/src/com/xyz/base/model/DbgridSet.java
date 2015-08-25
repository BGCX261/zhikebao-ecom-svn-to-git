package com.xyz.base.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 元数据
 */
public class DbgridSet implements java.io.Serializable {

	private int recId;
	private String gridName;
	private Boolean custom;

	public DbgridSet() {
	}

	public DbgridSet(int recId) {
		this.recId = recId;
	}

	public DbgridSet(int recId, String gridName, Boolean custom) {
		this.recId = recId;
		this.gridName = gridName;
		this.custom = custom;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getGridName() {
		return this.gridName;
	}

	public void setGridName(String gridName) {
		this.gridName = gridName;
	}

	public Boolean getCustom() {
		return this.custom;
	}

	public void setCustom(Boolean custom) {
		this.custom = custom;
	}

}
