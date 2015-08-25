package com.xyz.stock.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 输入表格详细信息
 */
public class GridDetail implements java.io.Serializable {

	private int recId;
	private String fieldName;
	private String grid;
	private Integer nlength;

	public GridDetail() {
	}

	public GridDetail(int recId) {
		this.recId = recId;
	}

	public GridDetail(int recId, String fieldName, String grid,
			Integer nlength) {
		this.recId = recId;
		this.fieldName = fieldName;
		this.grid = grid;
		this.nlength = nlength;
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

	public Integer getNlength() {
		return this.nlength;
	}

	public void setNlength(Integer nlength) {
		this.nlength = nlength;
	}

}
