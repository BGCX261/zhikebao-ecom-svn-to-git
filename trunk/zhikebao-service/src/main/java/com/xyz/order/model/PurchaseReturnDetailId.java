package com.xyz.order.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;

/**
 * 采购退货明细
 */
public class PurchaseReturnDetailId implements java.io.Serializable {

	private int recId;
	private String goodsNo;
	private String name;
	private String spec;
	private String unit;
	private BigDecimal count;
	private BigDecimal price;
	private BigDecimal money;
	private String remark;
	private String id;
	private String providerGoodsNo;

	public PurchaseReturnDetailId() {
	}

	public PurchaseReturnDetailId(int recId) {
		this.recId = recId;
	}

	public PurchaseReturnDetailId(int recId, String goodsNo, String name,
			String spec, String unit, BigDecimal count, BigDecimal price,
			BigDecimal money, String remark, String id, String providerGoodsNo) {
		this.recId = recId;
		this.goodsNo = goodsNo;
		this.name = name;
		this.spec = spec;
		this.unit = unit;
		this.count = count;
		this.price = price;
		this.money = money;
		this.remark = remark;
		this.id = id;
		this.providerGoodsNo = providerGoodsNo;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getGoodsNo() {
		return this.goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpec() {
		return this.spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getCount() {
		return this.count;
	}

	public void setCount(BigDecimal count) {
		this.count = count;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProviderGoodsNo() {
		return this.providerGoodsNo;
	}

	public void setProviderGoodsNo(String providerGoodsNo) {
		this.providerGoodsNo = providerGoodsNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PurchaseReturnDetailId))
			return false;
		PurchaseReturnDetailId castOther = (PurchaseReturnDetailId) other;

		return (this.getRecId() == castOther.getRecId())
				&& ((this.getGoodsNo() == castOther.getGoodsNo()) || (this
						.getGoodsNo() != null
						&& castOther.getGoodsNo() != null && this.getGoodsNo()
						.equals(castOther.getGoodsNo())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getSpec() == castOther.getSpec()) || (this.getSpec() != null
						&& castOther.getSpec() != null && this.getSpec()
						.equals(castOther.getSpec())))
				&& ((this.getUnit() == castOther.getUnit()) || (this.getUnit() != null
						&& castOther.getUnit() != null && this.getUnit()
						.equals(castOther.getUnit())))
				&& ((this.getCount() == castOther.getCount()) || (this
						.getCount() != null
						&& castOther.getCount() != null && this.getCount()
						.equals(castOther.getCount())))
				&& ((this.getPrice() == castOther.getPrice()) || (this
						.getPrice() != null
						&& castOther.getPrice() != null && this.getPrice()
						.equals(castOther.getPrice())))
				&& ((this.getMoney() == castOther.getMoney()) || (this
						.getMoney() != null
						&& castOther.getMoney() != null && this.getMoney()
						.equals(castOther.getMoney())))
				&& ((this.getRemark() == castOther.getRemark()) || (this
						.getRemark() != null
						&& castOther.getRemark() != null && this.getRemark()
						.equals(castOther.getRemark())))
				&& ((this.getId() == castOther.getId()) || (this.getId() != null
						&& castOther.getId() != null && this.getId().equals(
						castOther.getId())))
				&& ((this.getProviderGoodsNo() == castOther
						.getProviderGoodsNo()) || (this.getProviderGoodsNo() != null
						&& castOther.getProviderGoodsNo() != null && this
						.getProviderGoodsNo().equals(
								castOther.getProviderGoodsNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getRecId();
		result = 37 * result
				+ (getGoodsNo() == null ? 0 : this.getGoodsNo().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getSpec() == null ? 0 : this.getSpec().hashCode());
		result = 37 * result
				+ (getUnit() == null ? 0 : this.getUnit().hashCode());
		result = 37 * result
				+ (getCount() == null ? 0 : this.getCount().hashCode());
		result = 37 * result
				+ (getPrice() == null ? 0 : this.getPrice().hashCode());
		result = 37 * result
				+ (getMoney() == null ? 0 : this.getMoney().hashCode());
		result = 37 * result
				+ (getRemark() == null ? 0 : this.getRemark().hashCode());
		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37
				* result
				+ (getProviderGoodsNo() == null ? 0 : this.getProviderGoodsNo()
						.hashCode());
		return result;
	}

}
