package com.xyz.order.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购单明细历史
 */
public class OrderDetailHis implements java.io.Serializable {

	private int recId;
	private String billId;
	private String goodsNo;
	private String name;
	private String spec;
	private String unit;
	private BigDecimal count;
	private BigDecimal price;
	private BigDecimal total;
	private String provider;
	private String remark;
	private BigDecimal curStock;
	private BigDecimal orderCount;
	private Boolean bover;
	private String curStatus;
	private BigDecimal rcvCount;
	private String operator;
	private Date createDate;
	private Boolean bstockIn;
	private Boolean bpay;
	private String providerGoodsNo;

	public OrderDetailHis() {
	}

	public OrderDetailHis(int recId) {
		this.recId = recId;
	}

	public OrderDetailHis(int recId, String billId, String goodsNo,
			String name, String spec, String unit, BigDecimal count,
			BigDecimal price, BigDecimal total, String provider, String remark,
			BigDecimal curStock, BigDecimal orderCount, Boolean bover,
			String curStatus, BigDecimal rcvCount, String operator,
			Date createDate, Boolean bstockIn, Boolean bpay,
			String providerGoodsNo) {
		this.recId = recId;
		this.billId = billId;
		this.goodsNo = goodsNo;
		this.name = name;
		this.spec = spec;
		this.unit = unit;
		this.count = count;
		this.price = price;
		this.total = total;
		this.provider = provider;
		this.remark = remark;
		this.curStock = curStock;
		this.orderCount = orderCount;
		this.bover = bover;
		this.curStatus = curStatus;
		this.rcvCount = rcvCount;
		this.operator = operator;
		this.createDate = createDate;
		this.bstockIn = bstockIn;
		this.bpay = bpay;
		this.providerGoodsNo = providerGoodsNo;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getBillId() {
		return this.billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
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

	public BigDecimal getCount() {
		return this.count;
	}

	public void setCount(BigDecimal count) {
		this.count = count;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getProvider() {
		return this.provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getCurStock() {
		return this.curStock;
	}

	public void setCurStock(BigDecimal curStock) {
		this.curStock = curStock;
	}

	public BigDecimal getOrderCount() {
		return this.orderCount;
	}

	public void setOrderCount(BigDecimal orderCount) {
		this.orderCount = orderCount;
	}

	public Boolean getBover() {
		return this.bover;
	}

	public void setBover(Boolean bover) {
		this.bover = bover;
	}

	public String getCurStatus() {
		return this.curStatus;
	}

	public void setCurStatus(String curStatus) {
		this.curStatus = curStatus;
	}

	public BigDecimal getRcvCount() {
		return this.rcvCount;
	}

	public void setRcvCount(BigDecimal rcvCount) {
		this.rcvCount = rcvCount;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Boolean getBstockIn() {
		return this.bstockIn;
	}

	public void setBstockIn(Boolean bstockIn) {
		this.bstockIn = bstockIn;
	}

	public Boolean getBpay() {
		return this.bpay;
	}

	public void setBpay(Boolean bpay) {
		this.bpay = bpay;
	}

	public String getProviderGoodsNo() {
		return this.providerGoodsNo;
	}

	public void setProviderGoodsNo(String providerGoodsNo) {
		this.providerGoodsNo = providerGoodsNo;
	}

}
