package com.xyz.serve.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * 回访记录
 */
public class Seeback implements java.io.Serializable {

	private int recId;
	private String customerId;
	private String name;
	private String summary;
	private Date overTime;
	private String days;
	private Date time;
	private String style;
	private String operator;
	private String seeBackMan;
	private String satisfaction;
	private String advice;
	private String status;
	private String tradeId;
	private String remark;
	private String shop;
	private String type;
	private String tradeDate;
	private String rcvDate;
	private String sndDate;
	private String tradeNo;
	private String seebackStyle;

	public Seeback() {
	}

	public Seeback(int recId) {
		this.recId = recId;
	}

	public Seeback(int recId, String customerId, String name, String summary,
			Date overTime, String days, Date time, String style,
			String operator, String seeBackMan, String satisfaction,
			String advice, String status, String tradeId, String remark,
			String shop, String type, String tradeDate, String rcvDate,
			String sndDate, String tradeNo, String seebackStyle) {
		this.recId = recId;
		this.customerId = customerId;
		this.name = name;
		this.summary = summary;
		this.overTime = overTime;
		this.days = days;
		this.time = time;
		this.style = style;
		this.operator = operator;
		this.seeBackMan = seeBackMan;
		this.satisfaction = satisfaction;
		this.advice = advice;
		this.status = status;
		this.tradeId = tradeId;
		this.remark = remark;
		this.shop = shop;
		this.type = type;
		this.tradeDate = tradeDate;
		this.rcvDate = rcvDate;
		this.sndDate = sndDate;
		this.tradeNo = tradeNo;
		this.seebackStyle = seebackStyle;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getOverTime() {
		return this.overTime;
	}

	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}

	public String getDays() {
		return this.days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getStyle() {
		return this.style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getSeeBackMan() {
		return this.seeBackMan;
	}

	public void setSeeBackMan(String seeBackMan) {
		this.seeBackMan = seeBackMan;
	}

	public String getSatisfaction() {
		return this.satisfaction;
	}

	public void setSatisfaction(String satisfaction) {
		this.satisfaction = satisfaction;
	}

	public String getAdvice() {
		return this.advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTradeId() {
		return this.tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTradeDate() {
		return this.tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getRcvDate() {
		return this.rcvDate;
	}

	public void setRcvDate(String rcvDate) {
		this.rcvDate = rcvDate;
	}

	public String getSndDate() {
		return this.sndDate;
	}

	public void setSndDate(String sndDate) {
		this.sndDate = sndDate;
	}

	public String getTradeNo() {
		return this.tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getSeebackStyle() {
		return this.seebackStyle;
	}

	public void setSeebackStyle(String seebackStyle) {
		this.seebackStyle = seebackStyle;
	}

}
