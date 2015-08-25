package com.xyz.product.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;

/**
 * E桥网--新建商品
 */
public class EqGoodsInfo implements java.io.Serializable {

	private int recId;
	private String goodsNo;
	private String goodsName;
	private String goodsSpec;
	private String unit;
	private BigDecimal dpriceDetail;
	private BigDecimal dpricewholesale1;
	private BigDecimal dpricewholesale2;
	private BigDecimal dpricewholesale3;
	private String sremark;
	private BigDecimal stock;
	private String curStatus;
	private String createTime;

	public EqGoodsInfo() {
	}

	public EqGoodsInfo(int recId) {
		this.recId = recId;
	}

	public EqGoodsInfo(int recId, String goodsNo, String goodsName,
			String goodsSpec, String unit, BigDecimal dpriceDetail,
			BigDecimal dpricewholesale1, BigDecimal dpricewholesale2,
			BigDecimal dpricewholesale3, String sremark, BigDecimal stock,
			String curStatus, String createTime) {
		this.recId = recId;
		this.goodsNo = goodsNo;
		this.goodsName = goodsName;
		this.goodsSpec = goodsSpec;
		this.unit = unit;
		this.dpriceDetail = dpriceDetail;
		this.dpricewholesale1 = dpricewholesale1;
		this.dpricewholesale2 = dpricewholesale2;
		this.dpricewholesale3 = dpricewholesale3;
		this.sremark = sremark;
		this.stock = stock;
		this.curStatus = curStatus;
		this.createTime = createTime;
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

	public String getGoodsSpec() {
		return this.goodsSpec;
	}

	public void setGoodsSpec(String goodsSpec) {
		this.goodsSpec = goodsSpec;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getDpriceDetail() {
		return this.dpriceDetail;
	}

	public void setDpriceDetail(BigDecimal dpriceDetail) {
		this.dpriceDetail = dpriceDetail;
	}

	public BigDecimal getDpricewholesale1() {
		return this.dpricewholesale1;
	}

	public void setDpricewholesale1(BigDecimal dpricewholesale1) {
		this.dpricewholesale1 = dpricewholesale1;
	}

	public BigDecimal getDpricewholesale2() {
		return this.dpricewholesale2;
	}

	public void setDpricewholesale2(BigDecimal dpricewholesale2) {
		this.dpricewholesale2 = dpricewholesale2;
	}

	public BigDecimal getDpricewholesale3() {
		return this.dpricewholesale3;
	}

	public void setDpricewholesale3(BigDecimal dpricewholesale3) {
		this.dpricewholesale3 = dpricewholesale3;
	}

	public String getSremark() {
		return this.sremark;
	}

	public void setSremark(String sremark) {
		this.sremark = sremark;
	}

	public BigDecimal getStock() {
		return this.stock;
	}

	public void setStock(BigDecimal stock) {
		this.stock = stock;
	}

	public String getCurStatus() {
		return this.curStatus;
	}

	public void setCurStatus(String curStatus) {
		this.curStatus = curStatus;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
