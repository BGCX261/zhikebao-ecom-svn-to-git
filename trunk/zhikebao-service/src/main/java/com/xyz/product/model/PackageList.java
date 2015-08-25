package com.xyz.product.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;

/**
 * 包装目录
 */
public class PackageList implements java.io.Serializable {

	private int recId;
	private String package_;
	private BigDecimal cost;
	private String remark;
	private BigDecimal weight;

	public PackageList() {
	}

	public PackageList(int recId) {
		this.recId = recId;
	}

	public PackageList(int recId, String package_, BigDecimal cost,
			String remark, BigDecimal weight) {
		this.recId = recId;
		this.package_ = package_;
		this.cost = cost;
		this.remark = remark;
		this.weight = weight;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getPackage_() {
		return this.package_;
	}

	public void setPackage_(String package_) {
		this.package_ = package_;
	}

	public BigDecimal getCost() {
		return this.cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getWeight() {
		return this.weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

}
