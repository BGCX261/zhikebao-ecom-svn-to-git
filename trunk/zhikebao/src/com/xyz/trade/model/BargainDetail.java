package com.xyz.trade.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA


/**
 * 合同细则
 */
public class BargainDetail implements java.io.Serializable {

	private int recId;
	private String goodsNo;
	private String goodsName;
	private String spec;
	private String unit;
	private Double price;
	private Double count;
	private Double dis;
	private Double total;
	private String tradeId;
	private Double profit;
	private String remark;
	private Boolean bfitGoods;
	private Integer recNo;

	public BargainDetail() {
	}

	public BargainDetail(int recId) {
		this.recId = recId;
	}

	public BargainDetail(int recId, String goodsNo, String goodsName,
			String spec, String unit, Double price, Double count,
			Double dis, Double total, String tradeId,
			Double profit, String remark, Boolean bfitGoods, Integer recNo) {
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

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getCount() {
		return this.count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	public Double getDis() {
		return this.dis;
	}

	public void setDis(Double dis) {
		this.dis = dis;
	}

	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getTradeId() {
		return this.tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public Double getProfit() {
		return this.profit;
	}

	public void setProfit(Double profit) {
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
