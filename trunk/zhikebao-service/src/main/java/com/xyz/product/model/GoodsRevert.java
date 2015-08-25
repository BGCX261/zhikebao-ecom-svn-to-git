package com.xyz.product.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;

/**
 * (废弃)商品目录
 */
public class GoodsRevert implements java.io.Serializable {

	private int recId;
	private String goodsNo;
	private String class_;
	private String name;
	private String spec;
	private String unit;
	private Integer stocksWarning;
	private String description;
	private Boolean flag;
	private BigDecimal salesMon;
	private Integer rank;
	private BigDecimal salesTotal;
	private BigDecimal priceDetail;
	private BigDecimal priceCost;
	private BigDecimal priceMember;
	private BigDecimal priceWholesale;
	private String barCode;
	private String pyCode;
	private String stockPlace;
	private String status;
	private BigDecimal stock;
	private BigDecimal orderCount;
	private String stockCheck;
	private BigDecimal stockIn;
	private BigDecimal stockOut;
	private BigDecimal priceAver;
	private String remark;
	private String provider;
	private String tradeName;
	private BigDecimal weight;
	private String producingArea;
	private String keepTime;
	private BigDecimal purchases;
	private BigDecimal price1;
	private BigDecimal price2;
	private BigDecimal price3;
	private String tradeName2;
	private BigDecimal nscore;
	private BigDecimal ndedute;
	private String tradeName3;
	private String editId;
	private String providerGoodsNo;
	private Boolean bfit;

	public GoodsRevert() {
	}

	public GoodsRevert(int recId) {
		this.recId = recId;
	}

	public GoodsRevert(int recId, String goodsNo, String class_, String name,
			String spec, String unit, Integer stocksWarning,
			String description, Boolean flag, BigDecimal salesMon,
			Integer rank, BigDecimal salesTotal, BigDecimal priceDetail,
			BigDecimal priceCost, BigDecimal priceMember,
			BigDecimal priceWholesale, String barCode, String pyCode,
			String stockPlace, String status, BigDecimal stock,
			BigDecimal orderCount, String stockCheck, BigDecimal stockIn,
			BigDecimal stockOut, BigDecimal priceAver, String remark,
			String provider, String tradeName, BigDecimal weight,
			String producingArea, String keepTime, BigDecimal purchases,
			BigDecimal price1, BigDecimal price2, BigDecimal price3,
			String tradeName2, BigDecimal nscore, BigDecimal ndedute,
			String tradeName3, String editId, String providerGoodsNo,
			Boolean bfit) {
		this.recId = recId;
		this.goodsNo = goodsNo;
		this.class_ = class_;
		this.name = name;
		this.spec = spec;
		this.unit = unit;
		this.stocksWarning = stocksWarning;
		this.description = description;
		this.flag = flag;
		this.salesMon = salesMon;
		this.rank = rank;
		this.salesTotal = salesTotal;
		this.priceDetail = priceDetail;
		this.priceCost = priceCost;
		this.priceMember = priceMember;
		this.priceWholesale = priceWholesale;
		this.barCode = barCode;
		this.pyCode = pyCode;
		this.stockPlace = stockPlace;
		this.status = status;
		this.stock = stock;
		this.orderCount = orderCount;
		this.stockCheck = stockCheck;
		this.stockIn = stockIn;
		this.stockOut = stockOut;
		this.priceAver = priceAver;
		this.remark = remark;
		this.provider = provider;
		this.tradeName = tradeName;
		this.weight = weight;
		this.producingArea = producingArea;
		this.keepTime = keepTime;
		this.purchases = purchases;
		this.price1 = price1;
		this.price2 = price2;
		this.price3 = price3;
		this.tradeName2 = tradeName2;
		this.nscore = nscore;
		this.ndedute = ndedute;
		this.tradeName3 = tradeName3;
		this.editId = editId;
		this.providerGoodsNo = providerGoodsNo;
		this.bfit = bfit;
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

	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
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

	public Integer getStocksWarning() {
		return this.stocksWarning;
	}

	public void setStocksWarning(Integer stocksWarning) {
		this.stocksWarning = stocksWarning;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getFlag() {
		return this.flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public BigDecimal getSalesMon() {
		return this.salesMon;
	}

	public void setSalesMon(BigDecimal salesMon) {
		this.salesMon = salesMon;
	}

	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public BigDecimal getSalesTotal() {
		return this.salesTotal;
	}

	public void setSalesTotal(BigDecimal salesTotal) {
		this.salesTotal = salesTotal;
	}

	public BigDecimal getPriceDetail() {
		return this.priceDetail;
	}

	public void setPriceDetail(BigDecimal priceDetail) {
		this.priceDetail = priceDetail;
	}

	public BigDecimal getPriceCost() {
		return this.priceCost;
	}

	public void setPriceCost(BigDecimal priceCost) {
		this.priceCost = priceCost;
	}

	public BigDecimal getPriceMember() {
		return this.priceMember;
	}

	public void setPriceMember(BigDecimal priceMember) {
		this.priceMember = priceMember;
	}

	public BigDecimal getPriceWholesale() {
		return this.priceWholesale;
	}

	public void setPriceWholesale(BigDecimal priceWholesale) {
		this.priceWholesale = priceWholesale;
	}

	public String getBarCode() {
		return this.barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getPyCode() {
		return this.pyCode;
	}

	public void setPyCode(String pyCode) {
		this.pyCode = pyCode;
	}

	public String getStockPlace() {
		return this.stockPlace;
	}

	public void setStockPlace(String stockPlace) {
		this.stockPlace = stockPlace;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public BigDecimal getStockIn() {
		return this.stockIn;
	}

	public void setStockIn(BigDecimal stockIn) {
		this.stockIn = stockIn;
	}

	public BigDecimal getStockOut() {
		return this.stockOut;
	}

	public void setStockOut(BigDecimal stockOut) {
		this.stockOut = stockOut;
	}

	public BigDecimal getPriceAver() {
		return this.priceAver;
	}

	public void setPriceAver(BigDecimal priceAver) {
		this.priceAver = priceAver;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProvider() {
		return this.provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getTradeName() {
		return this.tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public BigDecimal getWeight() {
		return this.weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getProducingArea() {
		return this.producingArea;
	}

	public void setProducingArea(String producingArea) {
		this.producingArea = producingArea;
	}

	public String getKeepTime() {
		return this.keepTime;
	}

	public void setKeepTime(String keepTime) {
		this.keepTime = keepTime;
	}

	public BigDecimal getPurchases() {
		return this.purchases;
	}

	public void setPurchases(BigDecimal purchases) {
		this.purchases = purchases;
	}

	public BigDecimal getPrice1() {
		return this.price1;
	}

	public void setPrice1(BigDecimal price1) {
		this.price1 = price1;
	}

	public BigDecimal getPrice2() {
		return this.price2;
	}

	public void setPrice2(BigDecimal price2) {
		this.price2 = price2;
	}

	public BigDecimal getPrice3() {
		return this.price3;
	}

	public void setPrice3(BigDecimal price3) {
		this.price3 = price3;
	}

	public String getTradeName2() {
		return this.tradeName2;
	}

	public void setTradeName2(String tradeName2) {
		this.tradeName2 = tradeName2;
	}

	public BigDecimal getNscore() {
		return this.nscore;
	}

	public void setNscore(BigDecimal nscore) {
		this.nscore = nscore;
	}

	public BigDecimal getNdedute() {
		return this.ndedute;
	}

	public void setNdedute(BigDecimal ndedute) {
		this.ndedute = ndedute;
	}

	public String getTradeName3() {
		return this.tradeName3;
	}

	public void setTradeName3(String tradeName3) {
		this.tradeName3 = tradeName3;
	}

	public String getEditId() {
		return this.editId;
	}

	public void setEditId(String editId) {
		this.editId = editId;
	}

	public String getProviderGoodsNo() {
		return this.providerGoodsNo;
	}

	public void setProviderGoodsNo(String providerGoodsNo) {
		this.providerGoodsNo = providerGoodsNo;
	}

	public Boolean getBfit() {
		return this.bfit;
	}

	public void setBfit(Boolean bfit) {
		this.bfit = bfit;
	}

}
