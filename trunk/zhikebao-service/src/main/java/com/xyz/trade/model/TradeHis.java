package com.xyz.trade.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单历史
 */
public class TradeHis implements java.io.Serializable {

	private int recId;
	private String shop;
	private String id;
	private String operator;
	private String tradeNo;
	private Date tradeTime;
	private String tradeStatus;
	private String tradeStyle;
	private String customerId;
	private String customerName;
	private String city;
	private String sndTo;
	private String adr;
	private String zip;
	private String phone;
	private String customerNeeds;
	private String sndStyle;
	private BigDecimal goodsCost;
	private BigDecimal total;
	private BigDecimal tradeValue;
	private String rcvTime;
	private BigDecimal postFee;
	private String sndTime;
	private BigDecimal postFee2;
	private BigDecimal otherFee;
	private BigDecimal rcvValue;
	private String postId;
	private String rcvStyle;
	private String overTime;
	private Boolean brcv;
	private Boolean bsnd;
	private Boolean bnotify;
	private String days;
	private String regTime;
	private String actionUrl;
	private Integer rank;
	private Boolean bwarning;
	private Boolean bwarningCancel;
	private Boolean breach;
	private Boolean bevaluateGet;
	private Boolean bevaluatePost;
	private Boolean btrouble;
	private String profitCalc;
	private String status;
	private BigDecimal drawbackValue;
	private Boolean bdrawback;
	private Boolean bnoconfirm;
	private String rcvOperator;
	private String sndOperator;
	private BigDecimal profit;
	private String flag;
	private String remark;
	private String goodsList;
	private String currency;
	private BigDecimal rate;
	private String packaging;
	private BigDecimal packagingCost;
	private BigDecimal weight;
	private String rcvId;
	private String country;
	private String province;
	private String area;
	private String seller;
	private String reserved1;
	private String reserved2;
	private String priceSpec;
	private String packageOperator;
	private Boolean bpackage;
	private Integer stockOutChk;
	private Integer printSndBillChk;
	private Boolean barrearage;
	private BigDecimal priceDis;
	private Integer printExpressChk;
	private Boolean beq;
	private String stradeNo;
	private Integer ntraceDay;
	private String rmbValue;
	private Boolean beditSort;
	private Boolean bsndReg;
	private String chargeType;

	public TradeHis() {
	}

	public TradeHis(int recId) {
		this.recId = recId;
	}

	public TradeHis(int recId, String shop, String id, String operator,
			String tradeNo, Date tradeTime, String tradeStatus,
			String tradeStyle, String customerId, String customerName,
			String city, String sndTo, String adr, String zip, String phone,
			String customerNeeds, String sndStyle, BigDecimal goodsCost,
			BigDecimal total, BigDecimal tradeValue, String rcvTime,
			BigDecimal postFee, String sndTime, BigDecimal postFee2,
			BigDecimal otherFee, BigDecimal rcvValue, String postId,
			String rcvStyle, String overTime, Boolean brcv, Boolean bsnd,
			Boolean bnotify, String days, String regTime, String actionUrl,
			Integer rank, Boolean bwarning, Boolean bwarningCancel,
			Boolean breach, Boolean bevaluateGet, Boolean bevaluatePost,
			Boolean btrouble, String profitCalc, String status,
			BigDecimal drawbackValue, Boolean bdrawback, Boolean bnoconfirm,
			String rcvOperator, String sndOperator, BigDecimal profit,
			String flag, String remark, String goodsList, String currency,
			BigDecimal rate, String packaging, BigDecimal packagingCost,
			BigDecimal weight, String rcvId, String country, String province,
			String area, String seller, String reserved1, String reserved2,
			String priceSpec, String packageOperator, Boolean bpackage,
			Integer stockOutChk, Integer printSndBillChk, Boolean barrearage,
			BigDecimal priceDis, Integer printExpressChk, Boolean beq,
			String stradeNo, Integer ntraceDay, String rmbValue,
			Boolean beditSort, Boolean bsndReg, String chargeType) {
		this.recId = recId;
		this.shop = shop;
		this.id = id;
		this.operator = operator;
		this.tradeNo = tradeNo;
		this.tradeTime = tradeTime;
		this.tradeStatus = tradeStatus;
		this.tradeStyle = tradeStyle;
		this.customerId = customerId;
		this.customerName = customerName;
		this.city = city;
		this.sndTo = sndTo;
		this.adr = adr;
		this.zip = zip;
		this.phone = phone;
		this.customerNeeds = customerNeeds;
		this.sndStyle = sndStyle;
		this.goodsCost = goodsCost;
		this.total = total;
		this.tradeValue = tradeValue;
		this.rcvTime = rcvTime;
		this.postFee = postFee;
		this.sndTime = sndTime;
		this.postFee2 = postFee2;
		this.otherFee = otherFee;
		this.rcvValue = rcvValue;
		this.postId = postId;
		this.rcvStyle = rcvStyle;
		this.overTime = overTime;
		this.brcv = brcv;
		this.bsnd = bsnd;
		this.bnotify = bnotify;
		this.days = days;
		this.regTime = regTime;
		this.actionUrl = actionUrl;
		this.rank = rank;
		this.bwarning = bwarning;
		this.bwarningCancel = bwarningCancel;
		this.breach = breach;
		this.bevaluateGet = bevaluateGet;
		this.bevaluatePost = bevaluatePost;
		this.btrouble = btrouble;
		this.profitCalc = profitCalc;
		this.status = status;
		this.drawbackValue = drawbackValue;
		this.bdrawback = bdrawback;
		this.bnoconfirm = bnoconfirm;
		this.rcvOperator = rcvOperator;
		this.sndOperator = sndOperator;
		this.profit = profit;
		this.flag = flag;
		this.remark = remark;
		this.goodsList = goodsList;
		this.currency = currency;
		this.rate = rate;
		this.packaging = packaging;
		this.packagingCost = packagingCost;
		this.weight = weight;
		this.rcvId = rcvId;
		this.country = country;
		this.province = province;
		this.area = area;
		this.seller = seller;
		this.reserved1 = reserved1;
		this.reserved2 = reserved2;
		this.priceSpec = priceSpec;
		this.packageOperator = packageOperator;
		this.bpackage = bpackage;
		this.stockOutChk = stockOutChk;
		this.printSndBillChk = printSndBillChk;
		this.barrearage = barrearage;
		this.priceDis = priceDis;
		this.printExpressChk = printExpressChk;
		this.beq = beq;
		this.stradeNo = stradeNo;
		this.ntraceDay = ntraceDay;
		this.rmbValue = rmbValue;
		this.beditSort = beditSort;
		this.bsndReg = bsndReg;
		this.chargeType = chargeType;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
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

	public Date getTradeTime() {
		return this.tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getTradeStatus() {
		return this.tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getTradeStyle() {
		return this.tradeStyle;
	}

	public void setTradeStyle(String tradeStyle) {
		this.tradeStyle = tradeStyle;
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

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSndTo() {
		return this.sndTo;
	}

	public void setSndTo(String sndTo) {
		this.sndTo = sndTo;
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

	public String getCustomerNeeds() {
		return this.customerNeeds;
	}

	public void setCustomerNeeds(String customerNeeds) {
		this.customerNeeds = customerNeeds;
	}

	public String getSndStyle() {
		return this.sndStyle;
	}

	public void setSndStyle(String sndStyle) {
		this.sndStyle = sndStyle;
	}

	public BigDecimal getGoodsCost() {
		return this.goodsCost;
	}

	public void setGoodsCost(BigDecimal goodsCost) {
		this.goodsCost = goodsCost;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getTradeValue() {
		return this.tradeValue;
	}

	public void setTradeValue(BigDecimal tradeValue) {
		this.tradeValue = tradeValue;
	}

	public String getRcvTime() {
		return this.rcvTime;
	}

	public void setRcvTime(String rcvTime) {
		this.rcvTime = rcvTime;
	}

	public BigDecimal getPostFee() {
		return this.postFee;
	}

	public void setPostFee(BigDecimal postFee) {
		this.postFee = postFee;
	}

	public String getSndTime() {
		return this.sndTime;
	}

	public void setSndTime(String sndTime) {
		this.sndTime = sndTime;
	}

	public BigDecimal getPostFee2() {
		return this.postFee2;
	}

	public void setPostFee2(BigDecimal postFee2) {
		this.postFee2 = postFee2;
	}

	public BigDecimal getOtherFee() {
		return this.otherFee;
	}

	public void setOtherFee(BigDecimal otherFee) {
		this.otherFee = otherFee;
	}

	public BigDecimal getRcvValue() {
		return this.rcvValue;
	}

	public void setRcvValue(BigDecimal rcvValue) {
		this.rcvValue = rcvValue;
	}

	public String getPostId() {
		return this.postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getRcvStyle() {
		return this.rcvStyle;
	}

	public void setRcvStyle(String rcvStyle) {
		this.rcvStyle = rcvStyle;
	}

	public String getOverTime() {
		return this.overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	public Boolean getBrcv() {
		return this.brcv;
	}

	public void setBrcv(Boolean brcv) {
		this.brcv = brcv;
	}

	public Boolean getBsnd() {
		return this.bsnd;
	}

	public void setBsnd(Boolean bsnd) {
		this.bsnd = bsnd;
	}

	public Boolean getBnotify() {
		return this.bnotify;
	}

	public void setBnotify(Boolean bnotify) {
		this.bnotify = bnotify;
	}

	public String getDays() {
		return this.days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getRegTime() {
		return this.regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	public String getActionUrl() {
		return this.actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Boolean getBwarning() {
		return this.bwarning;
	}

	public void setBwarning(Boolean bwarning) {
		this.bwarning = bwarning;
	}

	public Boolean getBwarningCancel() {
		return this.bwarningCancel;
	}

	public void setBwarningCancel(Boolean bwarningCancel) {
		this.bwarningCancel = bwarningCancel;
	}

	public Boolean getBreach() {
		return this.breach;
	}

	public void setBreach(Boolean breach) {
		this.breach = breach;
	}

	public Boolean getBevaluateGet() {
		return this.bevaluateGet;
	}

	public void setBevaluateGet(Boolean bevaluateGet) {
		this.bevaluateGet = bevaluateGet;
	}

	public Boolean getBevaluatePost() {
		return this.bevaluatePost;
	}

	public void setBevaluatePost(Boolean bevaluatePost) {
		this.bevaluatePost = bevaluatePost;
	}

	public Boolean getBtrouble() {
		return this.btrouble;
	}

	public void setBtrouble(Boolean btrouble) {
		this.btrouble = btrouble;
	}

	public String getProfitCalc() {
		return this.profitCalc;
	}

	public void setProfitCalc(String profitCalc) {
		this.profitCalc = profitCalc;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getDrawbackValue() {
		return this.drawbackValue;
	}

	public void setDrawbackValue(BigDecimal drawbackValue) {
		this.drawbackValue = drawbackValue;
	}

	public Boolean getBdrawback() {
		return this.bdrawback;
	}

	public void setBdrawback(Boolean bdrawback) {
		this.bdrawback = bdrawback;
	}

	public Boolean getBnoconfirm() {
		return this.bnoconfirm;
	}

	public void setBnoconfirm(Boolean bnoconfirm) {
		this.bnoconfirm = bnoconfirm;
	}

	public String getRcvOperator() {
		return this.rcvOperator;
	}

	public void setRcvOperator(String rcvOperator) {
		this.rcvOperator = rcvOperator;
	}

	public String getSndOperator() {
		return this.sndOperator;
	}

	public void setSndOperator(String sndOperator) {
		this.sndOperator = sndOperator;
	}

	public BigDecimal getProfit() {
		return this.profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGoodsList() {
		return this.goodsList;
	}

	public void setGoodsList(String goodsList) {
		this.goodsList = goodsList;
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

	public String getPackaging() {
		return this.packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}

	public BigDecimal getPackagingCost() {
		return this.packagingCost;
	}

	public void setPackagingCost(BigDecimal packagingCost) {
		this.packagingCost = packagingCost;
	}

	public BigDecimal getWeight() {
		return this.weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getRcvId() {
		return this.rcvId;
	}

	public void setRcvId(String rcvId) {
		this.rcvId = rcvId;
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

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSeller() {
		return this.seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getReserved1() {
		return this.reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public String getReserved2() {
		return this.reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}

	public String getPriceSpec() {
		return this.priceSpec;
	}

	public void setPriceSpec(String priceSpec) {
		this.priceSpec = priceSpec;
	}

	public String getPackageOperator() {
		return this.packageOperator;
	}

	public void setPackageOperator(String packageOperator) {
		this.packageOperator = packageOperator;
	}

	public Boolean getBpackage() {
		return this.bpackage;
	}

	public void setBpackage(Boolean bpackage) {
		this.bpackage = bpackage;
	}

	public Integer getStockOutChk() {
		return this.stockOutChk;
	}

	public void setStockOutChk(Integer stockOutChk) {
		this.stockOutChk = stockOutChk;
	}

	public Integer getPrintSndBillChk() {
		return this.printSndBillChk;
	}

	public void setPrintSndBillChk(Integer printSndBillChk) {
		this.printSndBillChk = printSndBillChk;
	}

	public Boolean getBarrearage() {
		return this.barrearage;
	}

	public void setBarrearage(Boolean barrearage) {
		this.barrearage = barrearage;
	}

	public BigDecimal getPriceDis() {
		return this.priceDis;
	}

	public void setPriceDis(BigDecimal priceDis) {
		this.priceDis = priceDis;
	}

	public Integer getPrintExpressChk() {
		return this.printExpressChk;
	}

	public void setPrintExpressChk(Integer printExpressChk) {
		this.printExpressChk = printExpressChk;
	}

	public Boolean getBeq() {
		return this.beq;
	}

	public void setBeq(Boolean beq) {
		this.beq = beq;
	}

	public String getStradeNo() {
		return this.stradeNo;
	}

	public void setStradeNo(String stradeNo) {
		this.stradeNo = stradeNo;
	}

	public Integer getNtraceDay() {
		return this.ntraceDay;
	}

	public void setNtraceDay(Integer ntraceDay) {
		this.ntraceDay = ntraceDay;
	}

	public String getRmbValue() {
		return this.rmbValue;
	}

	public void setRmbValue(String rmbValue) {
		this.rmbValue = rmbValue;
	}

	public Boolean getBeditSort() {
		return this.beditSort;
	}

	public void setBeditSort(Boolean beditSort) {
		this.beditSort = beditSort;
	}

	public Boolean getBsndReg() {
		return this.bsndReg;
	}

	public void setBsndReg(Boolean bsndReg) {
		this.bsndReg = bsndReg;
	}

	public String getChargeType() {
		return this.chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

}
