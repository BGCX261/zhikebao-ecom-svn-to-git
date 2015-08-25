package com.xyz.trade.model;

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
	private Double price;
	private Double count;
	private Double dis;
	private Double total;
	private Double profit;
	private Double countSellback;
	private Double curStock;
	private String remark;
	private String stockPlace;
	private Double purchases;
	private Double orderCount;
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
			String goodsName, String spec, String unit, Double price,
			Double count, Double dis, Double total,
			Double profit, Double countSellback, Double curStock,
			String remark, String stockPlace, Double purchases,
			Double orderCount, Integer recNo, Boolean bover,
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

	public Double getProfit() {
		return this.profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public Double getCountSellback() {
		return this.countSellback;
	}

	public void setCountSellback(Double countSellback) {
		this.countSellback = countSellback;
	}

	public Double getCurStock() {
		return this.curStock;
	}

	public void setCurStock(Double curStock) {
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
