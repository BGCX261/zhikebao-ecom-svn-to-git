package com.xyz.serve.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;

/**
 * 维修记录
 */
public class Repair implements java.io.Serializable {

	private int recId;
	private String repairId;
	private String regDate;
	private String repairDate;
	private String sndDate;
	private String overDate;
	private String customerId;
	private String customerName;
	private String customerTel;
	private String customerAdr;
	private String customerZip;
	private String customerMail;
	private String customerCity;
	private String goodsNo;
	private String goodsName;
	private String trouble;
	private String sbx;
	private String sellDate;
	private String status;
	private BigDecimal rcvFee;
	private BigDecimal cost;
	private BigDecimal sndFee;
	private BigDecimal profit;
	private String rcvStyle;
	private String sndStyle;
	private String postId;
	private String regOperator;
	private String repOperator;
	private String sndOperator;
	private String remark;
	private String repairDis;
	private String repairStatus;
	private BigDecimal bargainValue;
	private String reserved2;
	private String reserved3;
	private String repairStype;
	private String expendAccounts;
	private String chargeOperator;
	private String reserved1;

	public Repair() {
	}

	public Repair(int recId) {
		this.recId = recId;
	}

	public Repair(int recId, String repairId, String regDate,
			String repairDate, String sndDate, String overDate,
			String customerId, String customerName, String customerTel,
			String customerAdr, String customerZip, String customerMail,
			String customerCity, String goodsNo, String goodsName,
			String trouble, String sbx, String sellDate, String status,
			BigDecimal rcvFee, BigDecimal cost, BigDecimal sndFee,
			BigDecimal profit, String rcvStyle, String sndStyle, String postId,
			String regOperator, String repOperator, String sndOperator,
			String remark, String repairDis, String repairStatus,
			BigDecimal bargainValue, String reserved2, String reserved3,
			String repairStype, String expendAccounts, String chargeOperator,
			String reserved1) {
		this.recId = recId;
		this.repairId = repairId;
		this.regDate = regDate;
		this.repairDate = repairDate;
		this.sndDate = sndDate;
		this.overDate = overDate;
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerTel = customerTel;
		this.customerAdr = customerAdr;
		this.customerZip = customerZip;
		this.customerMail = customerMail;
		this.customerCity = customerCity;
		this.goodsNo = goodsNo;
		this.goodsName = goodsName;
		this.trouble = trouble;
		this.sbx = sbx;
		this.sellDate = sellDate;
		this.status = status;
		this.rcvFee = rcvFee;
		this.cost = cost;
		this.sndFee = sndFee;
		this.profit = profit;
		this.rcvStyle = rcvStyle;
		this.sndStyle = sndStyle;
		this.postId = postId;
		this.regOperator = regOperator;
		this.repOperator = repOperator;
		this.sndOperator = sndOperator;
		this.remark = remark;
		this.repairDis = repairDis;
		this.repairStatus = repairStatus;
		this.bargainValue = bargainValue;
		this.reserved2 = reserved2;
		this.reserved3 = reserved3;
		this.repairStype = repairStype;
		this.expendAccounts = expendAccounts;
		this.chargeOperator = chargeOperator;
		this.reserved1 = reserved1;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getRepairId() {
		return this.repairId;
	}

	public void setRepairId(String repairId) {
		this.repairId = repairId;
	}

	public String getRegDate() {
		return this.regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getRepairDate() {
		return this.repairDate;
	}

	public void setRepairDate(String repairDate) {
		this.repairDate = repairDate;
	}

	public String getSndDate() {
		return this.sndDate;
	}

	public void setSndDate(String sndDate) {
		this.sndDate = sndDate;
	}

	public String getOverDate() {
		return this.overDate;
	}

	public void setOverDate(String overDate) {
		this.overDate = overDate;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerTel() {
		return this.customerTel;
	}

	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}

	public String getCustomerAdr() {
		return this.customerAdr;
	}

	public void setCustomerAdr(String customerAdr) {
		this.customerAdr = customerAdr;
	}

	public String getCustomerZip() {
		return this.customerZip;
	}

	public void setCustomerZip(String customerZip) {
		this.customerZip = customerZip;
	}

	public String getCustomerMail() {
		return this.customerMail;
	}

	public void setCustomerMail(String customerMail) {
		this.customerMail = customerMail;
	}

	public String getCustomerCity() {
		return this.customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
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

	public String getTrouble() {
		return this.trouble;
	}

	public void setTrouble(String trouble) {
		this.trouble = trouble;
	}

	public String getSbx() {
		return this.sbx;
	}

	public void setSbx(String sbx) {
		this.sbx = sbx;
	}

	public String getSellDate() {
		return this.sellDate;
	}

	public void setSellDate(String sellDate) {
		this.sellDate = sellDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getRcvFee() {
		return this.rcvFee;
	}

	public void setRcvFee(BigDecimal rcvFee) {
		this.rcvFee = rcvFee;
	}

	public BigDecimal getCost() {
		return this.cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getSndFee() {
		return this.sndFee;
	}

	public void setSndFee(BigDecimal sndFee) {
		this.sndFee = sndFee;
	}

	public BigDecimal getProfit() {
		return this.profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public String getRcvStyle() {
		return this.rcvStyle;
	}

	public void setRcvStyle(String rcvStyle) {
		this.rcvStyle = rcvStyle;
	}

	public String getSndStyle() {
		return this.sndStyle;
	}

	public void setSndStyle(String sndStyle) {
		this.sndStyle = sndStyle;
	}

	public String getPostId() {
		return this.postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getRegOperator() {
		return this.regOperator;
	}

	public void setRegOperator(String regOperator) {
		this.regOperator = regOperator;
	}

	public String getRepOperator() {
		return this.repOperator;
	}

	public void setRepOperator(String repOperator) {
		this.repOperator = repOperator;
	}

	public String getSndOperator() {
		return this.sndOperator;
	}

	public void setSndOperator(String sndOperator) {
		this.sndOperator = sndOperator;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRepairDis() {
		return this.repairDis;
	}

	public void setRepairDis(String repairDis) {
		this.repairDis = repairDis;
	}

	public String getRepairStatus() {
		return this.repairStatus;
	}

	public void setRepairStatus(String repairStatus) {
		this.repairStatus = repairStatus;
	}

	public BigDecimal getBargainValue() {
		return this.bargainValue;
	}

	public void setBargainValue(BigDecimal bargainValue) {
		this.bargainValue = bargainValue;
	}

	public String getReserved2() {
		return this.reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}

	public String getReserved3() {
		return this.reserved3;
	}

	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}

	public String getRepairStype() {
		return this.repairStype;
	}

	public void setRepairStype(String repairStype) {
		this.repairStype = repairStype;
	}

	public String getExpendAccounts() {
		return this.expendAccounts;
	}

	public void setExpendAccounts(String expendAccounts) {
		this.expendAccounts = expendAccounts;
	}

	public String getChargeOperator() {
		return this.chargeOperator;
	}

	public void setChargeOperator(String chargeOperator) {
		this.chargeOperator = chargeOperator;
	}

	public String getReserved1() {
		return this.reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

}
