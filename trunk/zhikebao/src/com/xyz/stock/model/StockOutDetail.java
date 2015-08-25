package com.xyz.stock.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;

/**
 * 出库单明细
 */
public class StockOutDetail implements java.io.Serializable {

	private int recId;
	private String goodsNo;
	private String name;
	private String spec;
	private String unit;
	private BigDecimal count;
	private BigDecimal total;
	private BigDecimal price;
	private String place;
	private String reserved1;
	private String reserved2;
	private BigDecimal reserved3;
	private String id;
	private String remark;

	public StockOutDetail() {
	}

	public StockOutDetail(int recId) {
		this.recId = recId;
	}

	public StockOutDetail(int recId, String goodsNo, String name,
			String spec, String unit, BigDecimal count, BigDecimal total,
			BigDecimal price, String place, String reserved1, String reserved2,
			BigDecimal reserved3, String id, String remark) {
		this.recId = recId;
		this.goodsNo = goodsNo;
		this.name = name;
		this.spec = spec;
		this.unit = unit;
		this.count = count;
		this.total = total;
		this.price = price;
		this.place = place;
		this.reserved1 = reserved1;
		this.reserved2 = reserved2;
		this.reserved3 = reserved3;
		this.id = id;
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

	public BigDecimal getCount() {
		return this.count;
	}

	public void setCount(BigDecimal count) {
		this.count = count;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getReserved1() {
		return this.reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public String getReserved2() {
		return this.reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}

	public BigDecimal getReserved3() {
		return this.reserved3;
	}

	public void setReserved3(BigDecimal reserved3) {
		this.reserved3 = reserved3;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
