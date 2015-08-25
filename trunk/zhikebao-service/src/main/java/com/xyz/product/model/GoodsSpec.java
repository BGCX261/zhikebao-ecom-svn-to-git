package com.xyz.product.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.Date;

/**
 * 多规格商品
 */
public class GoodsSpec implements java.io.Serializable {

	private int recId;
	private String goodsNo;
	private String spec;
	private BigDecimal stock;
	private BigDecimal orderCount;
	private String stockCheck;
	private String spec1;
	private String spec2;
	private BigDecimal purchases;
	private String stauts;
	private BigDecimal stocksWarning;
	private Boolean bupdate;
	private Boolean borderChg;
	private Integer nrecNo;
	private String extraCode;
	private Date chg0;
	private Date chg;

	public GoodsSpec() {
	}

	public GoodsSpec(int recId) {
		this.recId = recId;
	}

	public GoodsSpec(int recId, String goodsNo, String spec,
			BigDecimal stock, BigDecimal orderCount, String stockCheck,
			String spec1, String spec2, BigDecimal purchases, String stauts,
			BigDecimal stocksWarning, Boolean bupdate, Boolean borderChg,
			Integer nrecNo, String extraCode, Date chg0, Date chg) {
		this.recId = recId;
		this.goodsNo = goodsNo;
		this.spec = spec;
		this.stock = stock;
		this.orderCount = orderCount;
		this.stockCheck = stockCheck;
		this.spec1 = spec1;
		this.spec2 = spec2;
		this.purchases = purchases;
		this.stauts = stauts;
		this.stocksWarning = stocksWarning;
		this.bupdate = bupdate;
		this.borderChg = borderChg;
		this.nrecNo = nrecNo;
		this.extraCode = extraCode;
		this.chg0 = chg0;
		this.chg = chg;
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

	public String getSpec() {
		return this.spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public BigDecimal getStock() {
		return this.stock;
	}

	public void setStock(BigDecimal stock) {
		this.stock = stock;
	}

	public BigDecimal getOrderCount() {
		return this.orderCount;
	}

	public void setOrderCount(BigDecimal orderCount) {
		this.orderCount = orderCount;
	}

	public String getStockCheck() {
		return this.stockCheck;
	}

	public void setStockCheck(String stockCheck) {
		this.stockCheck = stockCheck;
	}

	public String getSpec1() {
		return this.spec1;
	}

	public void setSpec1(String spec1) {
		this.spec1 = spec1;
	}

	public String getSpec2() {
		return this.spec2;
	}

	public void setSpec2(String spec2) {
		this.spec2 = spec2;
	}

	public BigDecimal getPurchases() {
		return this.purchases;
	}

	public void setPurchases(BigDecimal purchases) {
		this.purchases = purchases;
	}

	public String getStauts() {
		return this.stauts;
	}

	public void setStauts(String stauts) {
		this.stauts = stauts;
	}

	public BigDecimal getStocksWarning() {
		return this.stocksWarning;
	}

	public void setStocksWarning(BigDecimal stocksWarning) {
		this.stocksWarning = stocksWarning;
	}

	public Boolean getBupdate() {
		return this.bupdate;
	}

	public void setBupdate(Boolean bupdate) {
		this.bupdate = bupdate;
	}

	public Boolean getBorderChg() {
		return this.borderChg;
	}

	public void setBorderChg(Boolean borderChg) {
		this.borderChg = borderChg;
	}

	public Integer getNrecNo() {
		return this.nrecNo;
	}

	public void setNrecNo(Integer nrecNo) {
		this.nrecNo = nrecNo;
	}

	public String getExtraCode() {
		return this.extraCode;
	}

	public void setExtraCode(String extraCode) {
		this.extraCode = extraCode;
	}

	public Date getChg0() {
		return this.chg0;
	}

	public void setChg0(Date chg0) {
		this.chg0 = chg0;
	}

	public Date getChg() {
		return this.chg;
	}

	public void setChg(Date chg) {
		this.chg = chg;
	}

}
