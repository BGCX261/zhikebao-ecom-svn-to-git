package com.xyz.finace.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 收支项目
 */
public class InOutItem implements java.io.Serializable {

	private int recId;
	private String item;
	private String type;
	private Integer hot;

	public InOutItem() {
	}

	public InOutItem(int recId) {
		this.recId = recId;
	}

	public InOutItem(int recId, String item, String type, Integer hot) {
		this.recId = recId;
		this.item = item;
		this.type = type;
		this.hot = hot;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getHot() {
		return this.hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

}
