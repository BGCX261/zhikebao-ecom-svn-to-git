package com.xyz.stock.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;

/**
 * E桥网--更新库存
 */
public class EqGoodsStock implements java.io.Serializable {

	private int recId;
	private String goodsNo;
	private String goodsSpec;
	private BigDecimal stock;
	private String curStatus;
	private String createTime;

	public EqGoodsStock() {
	}

	public EqGoodsStock(int recId) {
		this.recId = recId;
	}

	public EqGoodsStock(int recId, String goodsNo, String goodsSpec,
			BigDecimal stock, String curStatus, String createTime) {
		this.recId = recId;
		this.goodsNo = goodsNo;
		this.goodsSpec = goodsSpec;
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

	public String getGoodsSpec() {
		return this.goodsSpec;
	}

	public void setGoodsSpec(String goodsSpec) {
		this.goodsSpec = goodsSpec;
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
