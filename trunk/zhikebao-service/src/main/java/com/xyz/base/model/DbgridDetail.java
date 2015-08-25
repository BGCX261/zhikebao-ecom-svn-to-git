package com.xyz.base.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 元数据详细信息
 */
public class DbgridDetail implements java.io.Serializable {

	private int recId;
	private String fieldName;
	private String grid;
	private Boolean bshow;
	private Integer nlength;
	private String byName;
	private Integer showSequence;

	public DbgridDetail() {
	}

	public DbgridDetail(int recId) {
		this.recId = recId;
	}

	public DbgridDetail(int recId, String fieldName, String grid,
			Boolean bshow, Integer nlength, String byName, Integer showSequence) {
		this.recId = recId;
		this.fieldName = fieldName;
		this.grid = grid;
		this.bshow = bshow;
		this.nlength = nlength;
		this.byName = byName;
		this.showSequence = showSequence;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getGrid() {
		return this.grid;
	}

	public void setGrid(String grid) {
		this.grid = grid;
	}

	public Boolean getBshow() {
		return this.bshow;
	}

	public void setBshow(Boolean bshow) {
		this.bshow = bshow;
	}

	public Integer getNlength() {
		return this.nlength;
	}

	public void setNlength(Integer nlength) {
		this.nlength = nlength;
	}

	public String getByName() {
		return this.byName;
	}

	public void setByName(String byName) {
		this.byName = byName;
	}

	public Integer getShowSequence() {
		return this.showSequence;
	}

	public void setShowSequence(Integer showSequence) {
		this.showSequence = showSequence;
	}

}
