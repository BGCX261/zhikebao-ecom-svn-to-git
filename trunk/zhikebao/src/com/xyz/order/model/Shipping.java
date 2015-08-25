package com.xyz.order.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.taobao.api.model.Trade;

/**
 * Shipping entity. @author sea
 */
@Entity
@Table(name = "Shipping" )
public class Shipping implements java.io.Serializable {

	private static final long serialVersionUID = 8447180149342350273L;
	private Long sid;
	private Trade tradeInfo;
	private String outSid;
	private String companyName;
	private String companyCode;
	private Date deliveryTime;
	private Boolean isSuccess;
	private String errorMsg;

	// Constructors

	/** default constructor */
	public Shipping() {
	}

	/** minimal constructor */
	public Shipping(Long sid) {
		this.sid = sid;
	}

	/** full constructor */
	public Shipping(Long sid, Trade tradeInfo, String outSid, String companyName, String companyCode, Timestamp deliveryTime, Boolean isSuccess, String errorMsg) {
		this.sid = sid;
		this.tradeInfo = tradeInfo;
		this.outSid = outSid;
		this.companyName = companyName;
		this.companyCode = companyCode;
		this.deliveryTime = deliveryTime;
		this.isSuccess = isSuccess;
		this.errorMsg = errorMsg;
	}

	// Property accessors
	@Id
	@Column(name = "sid", nullable = false, precision = 18, scale = 0)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getSid() {
		return this.sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	@Column(name = "out_sid", length = 50)
	public String getOutSid() {
		return this.outSid;
	}

	public void setOutSid(String outSid) {
		this.outSid = outSid;
	}

	@Column(name = "company_name", length = 50)
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "company_code", length = 50)
	public String getCompanyCode() {
		return this.companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delivery_time", length = 23)
	public Date getDeliveryTime() {
		return this.deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	@Column(name = "is_success")
	public Boolean getIsSuccess() {
		return this.isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	@Column(name = "errorMsg", length = 500)
	public String getErrorMsg() {
		return this.errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}