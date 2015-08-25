package com.xyz.product.model;

/**
 * 包装目录
 */
public class PackageList implements java.io.Serializable {

	private int recId;
	private String package_;
	private Double cost;
	private String remark;
	private Double weight;

	public PackageList() {
	}

	public PackageList(int recId) {
		this.recId = recId;
	}

	public PackageList(int recId, String package_, Double cost,
			String remark, Double weight) {
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

	public Double getCost() {
		return this.cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

}
