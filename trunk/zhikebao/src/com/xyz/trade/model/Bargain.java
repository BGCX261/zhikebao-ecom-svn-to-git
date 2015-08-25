package com.xyz.trade.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA
import java.util.Date;

/**
 * 合同
 */
public class Bargain implements java.io.Serializable {

	private int recId;
	private String tradeNo; //交易编号
	private Date date;
	private String customerId;   //客户编号
	private String customerName;  //客户姓名
	private String country;       //国家
	private String province;      //省份
	private String city;          //城市
	private String area;          //地区
	private String adr;           //地址
	private String tel;           //电话
	private String email;         //邮件
	private String zip;           //邮编
	private String qq;            //QQ
	private String sndStyle;      //发送方式
	private String otherNeeds;    //其他需要
	private Double postageRcv; //
	private Double postageSnd;
	private Double goodsTotal; 
	private Double profit;
	private String remark;
	private String shop;
	private String status;
	private String operator;
	private String tradeStyle;
	private String id;
	private Double weight;
	private String curState;
	private Double tradeValue;
	private String priceSpec;
	private String customerGrade;

	public Bargain() {
	}

	public Bargain(int recId) {
		this.recId = recId;
	}

	public Bargain(int recId, String tradeNo, Date date, String customerId,
			String customerName, String country, String province, String city,
			String area, String adr, String tel, String email, String zip,
			String qq, String sndStyle, String otherNeeds,
			Double postageRcv, Double postageSnd,
			Double goodsTotal, Double profit, String remark,
			String shop, String status, String operator, String tradeStyle,
			String id, Double weight, String curState,
			Double tradeValue, String priceSpec, String customerGrade) {
		this.recId = recId;
		this.tradeNo = tradeNo;
		this.date = date;
		this.customerId = customerId;
		this.customerName = customerName;
		this.country = country;
		this.province = province;
		this.city = city;
		this.area = area;
		this.adr = adr;
		this.tel = tel;
		this.email = email;
		this.zip = zip;
		this.qq = qq;
		this.sndStyle = sndStyle;
		this.otherNeeds = otherNeeds;
		this.postageRcv = postageRcv;
		this.postageSnd = postageSnd;
		this.goodsTotal = goodsTotal;
		this.profit = profit;
		this.remark = remark;
		this.shop = shop;
		this.status = status;
		this.operator = operator;
		this.tradeStyle = tradeStyle;
		this.id = id;
		this.weight = weight;
		this.curState = curState;
		this.tradeValue = tradeValue;
		this.priceSpec = priceSpec;
		this.customerGrade = customerGrade;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getTradeNo() {
		return this.tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getSndStyle() {
		return this.sndStyle;
	}

	public void setSndStyle(String sndStyle) {
		this.sndStyle = sndStyle;
	}

	public String getOtherNeeds() {
		return this.otherNeeds;
	}

	public void setOtherNeeds(String otherNeeds) {
		this.otherNeeds = otherNeeds;
	}

	public Double getPostageRcv() {
		return this.postageRcv;
	}

	public void setPostageRcv(Double postageRcv) {
		this.postageRcv = postageRcv;
	}

	public Double getPostageSnd() {
		return this.postageSnd;
	}

	public void setPostageSnd(Double postageSnd) {
		this.postageSnd = postageSnd;
	}

	public Double getGoodsTotal() {
		return this.goodsTotal;
	}

	public void setGoodsTotal(Double goodsTotal) {
		this.goodsTotal = goodsTotal;
	}

	public Double getProfit() {
		return this.profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getShop() {
		return this.shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getTradeStyle() {
		return this.tradeStyle;
	}

	public void setTradeStyle(String tradeStyle) {
		this.tradeStyle = tradeStyle;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getCurState() {
		return this.curState;
	}

	public void setCurState(String curState) {
		this.curState = curState;
	}

	public Double getTradeValue() {
		return this.tradeValue;
	}

	public void setTradeValue(Double tradeValue) {
		this.tradeValue = tradeValue;
	}

	public String getPriceSpec() {
		return this.priceSpec;
	}

	public void setPriceSpec(String priceSpec) {
		this.priceSpec = priceSpec;
	}

	public String getCustomerGrade() {
		return this.customerGrade;
	}

	public void setCustomerGrade(String customerGrade) {
		this.customerGrade = customerGrade;
	}

}
