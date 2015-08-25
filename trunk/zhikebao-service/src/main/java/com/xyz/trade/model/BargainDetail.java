package com.xyz.trade.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;

/**
 * 合同细则
 */
public class BargainDetail implements java.io.Serializable {

	private int recId;
	private String goodsNo;
	private String goodsName;
	private String spec;
	private String unit;
	private BigDecimal price;
	private BigDecimal count;
	private BigDecimal dis;
	private BigDecimal total;
	private String tradeId;
	private BigDecimal profit;
	private String remark;
	private Boolean bfitGoods;
	private Integer recNo;

	public BargainDetail() {
	}

	public BargainDetail(int recId) {
		this.recId = recId;
	}

	public BargainDetail(int recId, String goodsNo, String goodsName,
			String spec, String unit, BigDecimal price, BigDecimal count,
			BigDecimal dis, BigDecimal total, String tradeId,
			BigDecimal profit, String remark, Boolean bfitGoods, Integer recNo) {
		this.recId = recId;
		this.goodsNo = goodsNo;
		this.goodsName = goodsName;
		this.spec = spec;
		this.unit = unit;
		this.price = price;
		this.count = count;
		this.dis = dis;
		this.total = total;
		this.tradeId = tradeId;
		this.profit = profit;
		this.remark = remark;
		this.bfitGoods = bfitGoods;
		this.recNo = recNo;
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

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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

	public BigDecimal getDis() {
		return this.dis;
	}

	public void setDis(BigDecimal dis) {
		this.dis = dis;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getTradeId() {
		return this.tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public BigDecimal getProfit() {
		return this.profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getBfitGoods() {
		return this.bfitGoods;
	}

	public void setBfitGoods(Boolean bfitGoods) {
		this.bfitGoods = bfitGoods;
	}

	public Integer getRecNo() {
		return this.recNo;
	}

	public void setRecNo(Integer recNo) {
		this.recNo = recNo;
	}

}
