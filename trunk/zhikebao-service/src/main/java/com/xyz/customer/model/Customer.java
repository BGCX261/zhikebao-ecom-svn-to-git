package com.xyz.customer.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xyz.base.model.BaseModel;

/**
 * 客户
 */
@Entity @Table(name="customer")
public class Customer extends BaseModel implements java.io.Serializable {

	private String class_;
	private String memberGrade;
	private String timeForContact;
	private String remark;
	private Boolean flag;
	private Double amount;
	private Integer times;
	private Double profit;
	private Integer rank;
	private String memberId;
	private Double score;
	private Boolean blacklist;
	private String style;
	private String purchasingPower;
	private String priceSensitive;
	private String feelings;
	private Double preDeposit;
	private Double amountIncludeShip;
	private Boolean bseeback;
	private Integer seebackDays;
	private String seebackSummary;
	private Date seebackDate;
	private String reserved1;
	private String reserved2;
	private String reserved3;
	private String seller;
	private String operator;
	private Boolean hintRemark;
	private BigDecimal arrearageValue;
	private Date lastTradeDate;
	//关联的淘宝用户
    private String tbuserKey;
    //客户所属的卖家
    private String shopKey;

	public Customer() {
	}

	
	public String getTbuserKey() {
		return tbuserKey;
	}


	public void setTbuserKey(String tbuserKey) {
		this.tbuserKey = tbuserKey;
	}


	public String getShopKey() {
		return shopKey;
	}

	public void setShopKey(String shopKey) {
		this.shopKey = shopKey;
	}

	public String getClass_() {
		return class_;
	}

	public void setClass_(String class1) {
		class_ = class1;
	}

	public String getMemberGrade() {
		return memberGrade;
	}

	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}

	public String getTimeForContact() {
		return timeForContact;
	}

	public void setTimeForContact(String timeForContact) {
		this.timeForContact = timeForContact;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Boolean getBlacklist() {
		return blacklist;
	}

	public void setBlacklist(Boolean blacklist) {
		this.blacklist = blacklist;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getPurchasingPower() {
		return purchasingPower;
	}

	public void setPurchasingPower(String purchasingPower) {
		this.purchasingPower = purchasingPower;
	}

	public String getPriceSensitive() {
		return priceSensitive;
	}

	public void setPriceSensitive(String priceSensitive) {
		this.priceSensitive = priceSensitive;
	}

	public String getFeelings() {
		return feelings;
	}

	public void setFeelings(String feelings) {
		this.feelings = feelings;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public Double getProfit() {
		return profit;
	}


	public void setProfit(Double profit) {
		this.profit = profit;
	}


	public Double getScore() {
		return score;
	}


	public void setScore(Double score) {
		this.score = score;
	}


	public Double getPreDeposit() {
		return preDeposit;
	}


	public void setPreDeposit(Double preDeposit) {
		this.preDeposit = preDeposit;
	}


	public Double getAmountIncludeShip() {
		return amountIncludeShip;
	}


	public void setAmountIncludeShip(Double amountIncludeShip) {
		this.amountIncludeShip = amountIncludeShip;
	}


	public Boolean getBseeback() {
		return bseeback;
	}

	public void setBseeback(Boolean bseeback) {
		this.bseeback = bseeback;
	}

	public Integer getSeebackDays() {
		return seebackDays;
	}

	public void setSeebackDays(Integer seebackDays) {
		this.seebackDays = seebackDays;
	}

	public String getSeebackSummary() {
		return seebackSummary;
	}

	public void setSeebackSummary(String seebackSummary) {
		this.seebackSummary = seebackSummary;
	}

	public Date getSeebackDate() {
		return seebackDate;
	}

	public void setSeebackDate(Date seebackDate) {
		this.seebackDate = seebackDate;
	}

	public String getReserved1() {
		return reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public String getReserved2() {
		return reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}

	public String getReserved3() {
		return reserved3;
	}

	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Boolean getHintRemark() {
		return hintRemark;
	}

	public void setHintRemark(Boolean hintRemark) {
		this.hintRemark = hintRemark;
	}

	public BigDecimal getArrearageValue() {
		return arrearageValue;
	}

	public void setArrearageValue(BigDecimal arrearageValue) {
		this.arrearageValue = arrearageValue;
	}

	public Date getLastTradeDate() {
		return lastTradeDate;
	}

	public void setLastTradeDate(Date lastTradeDate) {
		this.lastTradeDate = lastTradeDate;
	}


}
