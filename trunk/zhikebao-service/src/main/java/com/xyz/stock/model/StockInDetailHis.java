package com.xyz.stock.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;

/**
 * 入库单明细历史
 */
public class StockInDetailHis implements java.io.Serializable {

	private int recId;
	private String id;
	private String goodsNo;
	private String spec;
	private String unit;
	private BigDecimal count;
	private String name;
	private BigDecimal price;
	private String reserved2;
	private BigDecimal reserved3;
	private BigDecimal money;
	private String remark;

	public StockInDetailHis() {
	}

	public StockInDetailHis(int recId) {
		this.recId = recId;
	}

	public StockInDetailHis(int recId, String id, String goodsNo,
			String spec, String unit, BigDecimal count, String name,
			BigDecimal price, String reserved2, BigDecimal reserved3,
			BigDecimal money, String remark) {
		this.recId = recId;
		this.id = id;
		this.goodsNo = goodsNo;
		this.spec = spec;
		this.unit = unit;
		this.count = count;
		this.name = name;
		this.price = price;
		this.reserved2 = reserved2;
		this.reserved3 = reserved3;
		this.money = money;
		this.remark = remark;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

}
