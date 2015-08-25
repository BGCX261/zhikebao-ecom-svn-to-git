package com.xyz.order.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;

/**
 * 供货商商品目录
 */
public class ProviderGoods implements java.io.Serializable {

	private int recId;
	private String goodsNo;
	private String name;
	private String spec;
	private String unit;
	private BigDecimal price;
	private String provider;
	private String remark;

	public ProviderGoods() {
	}

	public ProviderGoods(int recId) {
		this.recId = recId;
	}

	public ProviderGoods(int recId, String goodsNo, String name, String spec,
			String unit, BigDecimal price, String provider, String remark) {
		this.recId = recId;
		this.goodsNo = goodsNo;
		this.name = name;
		this.spec = spec;
		this.unit = unit;
		this.price = price;
		this.provider = provider;
		this.remark = remark;
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

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getProvider() {
		return this.provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
