package com.xyz.trade.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 换货出库明细
 */
public class ExchangePurchaseDetailOut implements java.io.Serializable {

	private int recId;
	private String goodsNo;
	private String goodsName;
	private String spec;
	private String unit;
	private Double price;
	private Double count;
	private Double total;
	private String tradeId;
	private Double profit;
	private Integer curStock;
	private String remark;
	private String stockPlace;
	private Double purchases;
	private Double orderCount;
	private Integer recNo;
	private Boolean bover;
	private Boolean bfitGoods;

	public ExchangePurchaseDetailOut() {
	}

	public ExchangePurchaseDetailOut(int recId) {
		this.recId = recId;
	}

	public ExchangePurchaseDetailOut(int recId, String goodsNo,
			String goodsName, String spec, String unit, Double price,
			Double count, Double total, String tradeId,
			Double profit, Integer curStock, String remark,
			String stockPlace, Double purchases, Double orderCount,
			Integer recNo, Boolean bover, Boolean bfitGoods) {
		this.recId = recId;
		this.goodsNo = goodsNo;
		this.goodsName = goodsName;
		this.spec = spec;
		this.unit = unit;
		this.price = price;
		this.count = count;
		this.total = total;
		this.tradeId = tradeId;
		this.profit = profit;
		this.curStock = curStock;
		this.remark = remark;
		this.stockPlace = stockPlace;
		this.purchases = purchases;
		this.orderCount = orderCount;
		this.recNo = recNo;
		this.bover = bover;
		this.bfitGoods = bfitGoods;
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

	public Integer getCurStock() {
		return this.curStock;
	}

	public void setCurStock(Integer curStock) {
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

	public Double getPurchases() {
		return this.purchases;
	}

	public void setPurchases(Double purchases) {
		this.purchases = purchases;
	}

	public Double getOrderCount() {
		return this.orderCount;
	}

	public void setOrderCount(Double orderCount) {
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

	public Boolean getBfitGoods() {
		return this.bfitGoods;
	}

	public void setBfitGoods(Boolean bfitGoods) {
		this.bfitGoods = bfitGoods;
	}

}
