package com.xyz.product.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 规格目录
 */
public class SpecList implements java.io.Serializable {

	private int recId;
	private String spec;
	private Integer hot;
	private String extraCode;

	public SpecList() {
	}

	public SpecList(int recId) {
		this.recId = recId;
	}

	public SpecList(int recId, String spec, Integer hot, String extraCode) {
		this.recId = recId;
		this.spec = spec;
		this.hot = hot;
		this.extraCode = extraCode;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getSpec() {
		return this.spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
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
