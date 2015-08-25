package com.xyz.trade.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;

/**
 * 订单商品历史
 */
public class TradeGoodsHis implements java.io.Serializable {

	private int recId;
	private String tradeId;
	private String goodsNo;
	private String goodsName;
	private String spec;
	private String unit;
	private BigDecimal price;
	private BigDecimal count;
	private BigDecimal dis;
	private BigDecimal total;
	private BigDecimal profit;
	private BigDecimal countSellback;
	private BigDecimal curStock;
	private String remark;
	private String stockPlace;
	private BigDecimal purchases;
	private BigDecimal orderCount;
	private Integer recNo;
	private Boolean bover;
	private String tradeNo;
	private Boolean bfitGoods;
	private Boolean bgift;

	public TradeGoodsHis() {
	}

	public TradeGoodsHis(int recId) {
		this.recId = recId;
	}

	public TradeGoodsHis(int recId, String tradeId, String goodsNo,
			String goodsName, String spec, String unit, BigDecimal price,
			BigDecimal count, BigDecimal dis, BigDecimal total,
			BigDecimal profit, BigDecimal countSellback, BigDecimal curStock,
			String remark, String stockPlace, BigDecimal purchases,
			BigDecimal orderCount, Integer recNo, Boolean bover,
			String tradeNo, Boolean bfitGoods, Boolean bgift) {
		this.recId = recId;
		this.tradeId = tradeId;
		this.goodsNo = goodsNo;
		this.goodsName = goodsName;
		this.spec = spec;
		this.unit = unit;
		this.price = price;
		this.count = count;
		this.dis = dis;
		this.total = total;
		this.profit = profit;
		this.countSellback = countSellback;
		this.curStock = curStock;
		this.remark = remark;
		this.stockPlace = stockPlace;
		this.purchases = purchases;
		this.orderCount = orderCount;
		this.recNo = recNo;
		this.bover = bover;
		this.tradeNo = tradeNo;
		this.bfitGoods = bfitGoods;
		this.bgift = bgift;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getTradeId() {
		return this.tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
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

	public BigDecimal getProfit() {
		return this.profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public BigDecimal getCountSellback() {
		return this.countSellback;
	}

	public void setCountSellback(BigDecimal countSellback) {
		this.countSellback = countSellback;
	}

	public BigDecimal getCurStock() {
		return this.curStock;
	}

	public void setCurStock(BigDecimal curStock) {
		this.curStock = curStock;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStockPlace() {
		return this.stockPlace;
	}

	public void setStockPlace(String stockPlace) {
		this.stockPlace = stockPlace;
	}

	public BigDecimal getPurchases() {
		return this.purchases;
	}

	public void setPurchases(BigDecimal purchases) {
		this.purchases = purchases;
	}

	public BigDecimal getOrderCount() {
		return this.orderCount;
	}

	public void setOrderCount(BigDecimal orderCount) {
		this.orderCount = orderCount;
	}

	public Integer getRecNo() {
		return this.recNo;
	}

	public void setRecNo(Integer recNo) {
		this.recNo = recNo;
	}

	public Boolean getBover() {
		return this.bover;
	}

	public void setBover(Boolean bover) {
		this.bover = bover;
	}

	public String getTradeNo() {
		return this.tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Boolean getBfitGoods() {
		return this.bfitGoods;
	}

	public void setBfitGoods(Boolean bfitGoods) {
		this.bfitGoods = bfitGoods;
	}

	public Boolean getBgift() {
		return this.bgift;
	}

	public void setBgift(Boolean bgift) {
		this.bgift = bgift;
	}

}
