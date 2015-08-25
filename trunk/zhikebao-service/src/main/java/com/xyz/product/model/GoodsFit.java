package com.xyz.product.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;

/**
 *  商品组合装(主)
 */
public class GoodsFit implements java.io.Serializable {

	private int recId;
	private String id;
	private String goodsNo;
	private String name;
	private String tradeName;
	private String unit;
	private String spec;
	private String remark;
	private String py;
	private String tradeName2;
	private String tradeName3;
	private BigDecimal nscore;
	private BigDecimal ndedute;
	private BigDecimal price1;
	private BigDecimal price2;
	private BigDecimal price3;
	private BigDecimal priceMember;
	private BigDecimal priceDetail;
	private BigDecimal priceWholesale;
	private BigDecimal stock;
	private BigDecimal orderCount;
	private Boolean bupdate;
	private Boolean borderChg;

	public GoodsFit() {
	}

	public GoodsFit(int recId) {
		this.recId = recId;
	}

	public GoodsFit(int recId, String id, String goodsNo, String name,
			String tradeName, String unit, String spec, String remark,
			String py, String tradeName2, String tradeName3, BigDecimal nscore,
			BigDecimal ndedute, BigDecimal price1, BigDecimal price2,
			BigDecimal price3, BigDecimal priceMember, BigDecimal priceDetail,
			BigDecimal priceWholesale, BigDecimal stock, BigDecimal orderCount,
			Boolean bupdate, Boolean borderChg) {
		this.recId = recId;
		this.id = id;
		this.goodsNo = goodsNo;
		this.name = name;
		this.tradeName = tradeName;
		this.unit = unit;
		this.spec = spec;
		this.remark = remark;
		this.py = py;
		this.tradeName2 = tradeName2;
		this.tradeName3 = tradeName3;
		this.nscore = nscore;
		this.ndedute = ndedute;
		this.price1 = price1;
		this.price2 = price2;
		this.price3 = price3;
		this.priceMember = priceMember;
		this.priceDetail = priceDetail;
		this.priceWholesale = priceWholesale;
		this.stock = stock;
		this.orderCount = orderCount;
		this.bupdate = bupdate;
		this.borderChg = borderChg;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTradeName() {
		return this.tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSpec() {
		return this.spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPy() {
		return this.py;
	}

	public void setPy(String py) {
		this.py = py;
	}

	public String getTradeName2() {
		return this.tradeName2;
	}

	public void setTradeName2(String tradeName2) {
		this.tradeName2 = tradeName2;
	}

	public String getTradeName3() {
		return this.tradeName3;
	}

	public void setTradeName3(String tradeName3) {
		this.tradeName3 = tradeName3;
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

	public BigDecimal getPriceMember() {
		return this.priceMember;
	}

	public void setPriceMember(BigDecimal priceMember) {
		this.priceMember = priceMember;
	}

	public BigDecimal getPriceDetail() {
		return this.priceDetail;
	}

	public void setPriceDetail(BigDecimal priceDetail) {
		this.priceDetail = priceDetail;
	}

	public BigDecimal getPriceWholesale() {
		return this.priceWholesale;
	}

	public void setPriceWholesale(BigDecimal priceWholesale) {
		this.priceWholesale = priceWholesale;
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

}
