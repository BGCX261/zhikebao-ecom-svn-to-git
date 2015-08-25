package com.xyz.product.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;

/**
 * 商品组合装明细
 */
public class GoodsFitDetail implements java.io.Serializable {

	private int recId;
	private String id;
	private String goodsNo;
	private String name;
	private String spec;
	private String unit;
	private BigDecimal price;
	private BigDecimal count;
	private String py;
	private Boolean bmultiSpec;

	public GoodsFitDetail() {
	}

	public GoodsFitDetail(int recId) {
		this.recId = recId;
	}

	public GoodsFitDetail(int recId, String id, String goodsNo, String name,
			String spec, String unit, BigDecimal price, BigDecimal count,
			String py, Boolean bmultiSpec) {
		this.recId = recId;
		this.id = id;
		this.goodsNo = goodsNo;
		this.name = name;
		this.spec = spec;
		this.unit = unit;
		this.price = price;
		this.count = count;
		this.py = py;
		this.bmultiSpec = bmultiSpec;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public BigDecimal getCount() {
		return this.count;
	}

	public void setCount(BigDecimal count) {
		this.count = count;
	}

	public String getPy() {
		return this.py;
	}

	public void setPy(String py) {
		this.py = py;
	}

	public Boolean getBmultiSpec() {
		return this.bmultiSpec;
	}

	public void setBmultiSpec(Boolean bmultiSpec) {
		this.bmultiSpec = bmultiSpec;
	}

}
