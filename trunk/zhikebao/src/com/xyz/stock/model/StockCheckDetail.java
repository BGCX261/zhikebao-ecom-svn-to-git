package com.xyz.stock.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;

/**
 * 库存盘点明细
 */
public class StockCheckDetail implements java.io.Serializable {

	private int recId;
	private String goodsNo;
	private String name;
	private String spec;
	private String place;
	private String unit;
	private BigDecimal stock;
	private BigDecimal price;
	private BigDecimal count;
	private BigDecimal money;
	private String stockCheck;
	private String billId;

	public StockCheckDetail() {
	}

	public StockCheckDetail(int recId) {
		this.recId = recId;
	}

	public StockCheckDetail(int recId, String goodsNo, String name,
			String spec, String place, String unit, BigDecimal stock,
			BigDecimal price, BigDecimal count, BigDecimal money,
			String stockCheck, String billId) {
		this.recId = recId;
		this.goodsNo = goodsNo;
		this.name = name;
		this.spec = spec;
		this.place = place;
		this.unit = unit;
		this.stock = stock;
		this.price = price;
		this.count = count;
		this.money = money;
		this.stockCheck = stockCheck;
		this.billId = billId;
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

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getStock() {
		return this.stock;
	}

	public void setStock(BigDecimal stock) {
		this.stock = stock;
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

	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getStockCheck() {
		return this.stockCheck;
	}

	public void setStockCheck(String stockCheck) {
		this.stockCheck = stockCheck;
	}

	public String getBillId() {
		return this.billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

}
