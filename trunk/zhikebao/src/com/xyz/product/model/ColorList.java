package com.xyz.product.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 商品颜色列表
 */
public class ColorList implements java.io.Serializable {

	private int recId;
	private String color;
	private Integer hot;
	private String extraCode;

	public ColorList() {
	}

	public ColorList(int recId) {
		this.recId = recId;
	}

	public ColorList(int recId, String color, Integer hot, String extraCode) {
		this.recId = recId;
		this.color = color;
		this.hot = hot;
		this.extraCode = extraCode;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getHot() {
		return this.hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public String getExtraCode() {
		return this.extraCode;
	}

	public void setExtraCode(String extraCode) {
		this.extraCode = extraCode;
	}

}
