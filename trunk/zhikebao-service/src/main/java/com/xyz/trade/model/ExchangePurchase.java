package com.xyz.trade.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.Date;

/**
 * 换货交易
 */
public class ExchangePurchase implements java.io.Serializable {

	private int recId;
	private String operationNo;
	private String shop;
	private String id;
	private String operator;
	private String tradeNo;
	private Date regTime;
	private String curStatus;
	private String customerId;
	private String customerName;
	private String country;
	private String province;
	private String city;
	private String area;
	private String adr;
	private String zip;
	private String phone;
	private String qq;
	private String remark;
	private String days;
	private BigDecimal weight;
	private BigDecimal moneyIn;
	private BigDecimal moneyOut;
	private BigDecimal postageIn;
	private BigDecimal moneyTotal;
	private String chargeStyle;
	private BigDecimal goodsCostIn;
	private BigDecimal goodsCostOut;
	private BigDecimal packagingCost;
	private String currency;
	private BigDecimal rate;
	private BigDecimal otherFee;
	private BigDecimal profit;
	private Boolean bpackage;
	private String packaging;
	private String packageOperator;
	private Integer printSndBillChk;
	private Date sndDate;
	private String sndOperator;
	private String sndStyle;
	private BigDecimal postFee;
	private String postId;
	private String chargeDate;
	private String chargeOperator;
	private String chargeAccount;
	private Integer stockOutChk;
	private Date rcvDate;
	private String rcvOperator;
	private String rcvStock;
	private String tradeId;
	private BigDecimal moneyFact;
	private String payId;
	private Boolean barrearage;
	private BigDecimal nscore;
	private BigDecimal postFee2;
	private String sflag;
	private String seller;
	private String chargeType;

	public ExchangePurchase() {
	}

	public ExchangePurchase(int recId) {
		this.recId = recId;
	}

	public ExchangePurchase(int recId, String operationNo, String shop,
			String id, String operator, String tradeNo, Date regTime,
			String curStatus, String customerId, String customerName,
			String country, String province, String city, String area,
			String adr, String zip, String phone, String qq, String remark,
			String days, BigDecimal weight, BigDecimal moneyIn,
			BigDecimal moneyOut, BigDecimal postageIn, BigDecimal moneyTotal,
			String chargeStyle, BigDecimal goodsCostIn,
			BigDecimal goodsCostOut, BigDecimal packagingCost, String currency,
			BigDecimal rate, BigDecimal otherFee, BigDecimal profit,
			Boolean bpackage, String packaging, String packageOperator,
			Integer printSndBillChk, Date sndDate, String sndOperator,
			String sndStyle, BigDecimal postFee, String postId,
			String chargeDate, String chargeOperator, String chargeAccount,
			Integer stockOutChk, Date rcvDate, String rcvOperator,
			String rcvStock, String tradeId, BigDecimal moneyFact,
			String payId, Boolean barrearage, BigDecimal nscore,
			BigDecimal postFee2, String sflag, String seller, String chargeType) {
		this.recId = recId;
		this.operationNo = operationNo;
		this.shop = shop;
		this.id = id;
		this.operator = operator;
		this.tradeNo = tradeNo;
		this.regTime = regTime;
		this.curStatus = curStatus;
		this.customerId = customerId;
		this.customerName = customerName;
		this.country = country;
		this.province = province;
		this.city = city;
		this.area = area;
		this.adr = adr;
		this.zip = zip;
		this.phone = phone;
		this.qq = qq;
		this.remark = remark;
		this.days = days;
		this.weight = weight;
		this.moneyIn = moneyIn;
		this.moneyOut = moneyOut;
		this.postageIn = postageIn;
		this.moneyTotal = moneyTotal;
		this.chargeStyle = chargeStyle;
		this.goodsCostIn = goodsCostIn;
		this.goodsCostOut = goodsCostOut;
		this.packagingCost = packagingCost;
		this.currency = currency;
		this.rate = rate;
		this.otherFee = otherFee;
		this.profit = profit;
		this.bpackage = bpackage;
		this.packaging = packaging;
		this.packageOperator = packageOperator;
		this.printSndBillChk = printSndBillChk;
		this.sndDate = sndDate;
		this.sndOperator = sndOperator;
		this.sndStyle = sndStyle;
		this.postFee = postFee;
		this.postId = postId;
		this.chargeDate = chargeDate;
		this.chargeOperator = chargeOperator;
		this.chargeAccount = chargeAccount;
		this.stockOutChk = stockOutChk;
		this.rcvDate = rcvDate;
		this.rcvOperator = rcvOperator;
		this.rcvStock = rcvStock;
		this.tradeId = tradeId;
		this.moneyFact = moneyFact;
		this.payId = payId;
		this.barrearage = barrearage;
		this.nscore = nscore;
		this.postFee2 = postFee2;
		this.sflag = sflag;
		this.seller = seller;
		this.chargeType = chargeType;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getOperationNo() {
		return this.operationNo;
	}

	public void setOperationNo(String operationNo) {
		this.operationNo = operationNo;
	}

	public String getShop() {
		return this.shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getTradeNo() {
		return this.tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Date getRegTime() {
		return this.regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public String getCurStatus() {
		return this.curStatus;
	}

	public void setCurStatus(String curStatus) {
		this.curStatus = curStatus;
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

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAdr() {
		return this.adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDays() {
		return this.days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public BigDecimal getWeight() {
		return this.weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getMoneyIn() {
		return this.moneyIn;
	}

	public void setMoneyIn(BigDecimal moneyIn) {
		this.moneyIn = moneyIn;
	}

	public BigDecimal getMoneyOut() {
		return this.moneyOut;
	}

	public void setMoneyOut(BigDecimal moneyOut) {
		this.moneyOut = moneyOut;
	}

	public BigDecimal getPostageIn() {
		return this.postageIn;
	}

	public void setPostageIn(BigDecimal postageIn) {
		this.postageIn = postageIn;
	}

	public BigDecimal getMoneyTotal() {
		return this.moneyTotal;
	}

	public void setMoneyTotal(BigDecimal moneyTotal) {
		this.moneyTotal = moneyTotal;
	}

	public String getChargeStyle() {
		return this.chargeStyle;
	}

	public void setChargeStyle(String chargeStyle) {
		this.chargeStyle = chargeStyle;
	}

	public BigDecimal getGoodsCostIn() {
		return this.goodsCostIn;
	}

	public void setGoodsCostIn(BigDecimal goodsCostIn) {
		this.goodsCostIn = goodsCostIn;
	}

	public BigDecimal getGoodsCostOut() {
		return this.goodsCostOut;
	}

	public void setGoodsCostOut(BigDecimal goodsCostOut) {
		this.goodsCostOut = goodsCostOut;
	}

	public BigDecimal getPackagingCost() {
		return this.packagingCost;
	}

	public void setPackagingCost(BigDecimal packagingCost) {
		this.packagingCost = packagingCost;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getOtherFee() {
		return this.otherFee;
	}

	public void setOtherFee(BigDecimal otherFee) {
		this.otherFee = otherFee;
	}

	public BigDecimal getProfit() {
		return this.profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public Boolean getBpackage() {
		return this.bpackage;
	}

	public void setBpackage(Boolean bpackage) {
		this.bpackage = bpackage;
	}

	public String getPackaging() {
		return this.packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}

	public String getPackageOperator() {
		return this.packageOperator;
	}

	public void setPackageOperator(String packageOperator) {
		this.packageOperator = packageOperator;
	}

	public Integer getPrintSndBillChk() {
		return this.printSndBillChk;
	}

	public void setPrintSndBillChk(Integer printSndBillChk) {
		this.printSndBillChk = printSndBillChk;
	}

	public Date getSndDate() {
		return this.sndDate;
	}

	public void setSndDate(Date sndDate) {
		this.sndDate = sndDate;
	}

	public String getSndOperator() {
		return this.sndOperator;
	}

	public void setSndOperator(String sndOperator) {
		this.sndOperator = sndOperator;
	}

	public String getSndStyle() {
		return this.sndStyle;
	}

	public void setSndStyle(String sndStyle) {
		this.sndStyle = sndStyle;
	}

	public BigDecimal getPostFee() {
		return this.postFee;
	}

	public void setPostFee(BigDecimal postFee) {
		this.postFee = postFee;
	}

	public String getPostId() {
		return this.postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getChargeDate() {
		return this.chargeDate;
	}

	public void setChargeDate(String chargeDate) {
		this.chargeDate = chargeDate;
	}

	public String getChargeOperator() {
		return this.chargeOperator;
	}

	public void setChargeOperator(String chargeOperator) {
		this.chargeOperator = chargeOperator;
	}

	public String getChargeAccount() {
		return this.chargeAccount;
	}

	public void setChargeAccount(String chargeAccount) {
		this.chargeAccount = chargeAccount;
	}

	public Integer getStockOutChk() {
		return this.stockOutChk;
	}

	public void setStockOutChk(Integer stockOutChk) {
		this.stockOutChk = stockOutChk;
	}

	public Date getRcvDate() {
		return this.rcvDate;
	}

	public void setRcvDate(Date rcvDate) {
		this.rcvDate = rcvDate;
	}

	public String getRcvOperator() {
		return this.rcvOperator;
	}

	public void setRcvOperator(String rcvOperator) {
		this.rcvOperator = rcvOperator;
	}

	public String getRcvStock() {
		return this.rcvStock;
	}

	public void setRcvStock(String rcvStock) {
		this.rcvStock = rcvStock;
	}

	public String getTradeId() {
		return this.tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public BigDecimal getMoneyFact() {
		return this.moneyFact;
	}

	public void setMoneyFact(BigDecimal moneyFact) {
		this.moneyFact = moneyFact;
	}

	public String getPayId() {
		return this.payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public Boolean getBarrearage() {
		return this.barrearage;
	}

	public void setBarrearage(Boolean barrearage) {
		this.barrearage = barrearage;
	}

	public BigDecimal getNscore() {
		return this.nscore;
	}

	public void setNscore(BigDecimal nscore) {
		this.nscore = nscore;
	}

	public BigDecimal getPostFee2() {
		return this.postFee2;
	}

	public void setPostFee2(BigDecimal postFee2) {
		this.postFee2 = postFee2;
	}

	public String getSflag() {
		return this.sflag;
	}

	public void setSflag(String sflag) {
		this.sflag = sflag;
	}

	public String getSeller() {
		return this.seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getChargeType() {
		return this.chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

}
