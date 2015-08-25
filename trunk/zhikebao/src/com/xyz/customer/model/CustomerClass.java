package com.xyz.customer.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 客户分类
 */
public class CustomerClass implements java.io.Serializable {

	private int recId;
	private String class_;
	private Integer hot;

	public CustomerClass() {
	}

	public CustomerClass(int recId) {
		this.recId = recId;
	}

	public CustomerClass(int recId, String class_, Integer hot) {
		this.recId = recId;
		this.class_ = class_;
		this.hot = hot;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public Integer getHot() {
		return this.hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

}
