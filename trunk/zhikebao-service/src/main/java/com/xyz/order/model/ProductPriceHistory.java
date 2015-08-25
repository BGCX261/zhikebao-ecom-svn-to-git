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

import com.google.appengine.api.datastore.Key;
import com.xyz.system.model.Shop;

/**
 * ProductPriceHistory entity. @author sea
 */
@Entity
@Table(name = "productPriceHistory")
public class ProductPriceHistory implements java.io.Serializable {

	private static final long serialVersionUID = -1552428514068479725L;
	private Key ppriceId;
	private Shop shop;
	private String productKey;
	private Double price;
	private String tbiid;
	private Date synchrTime;
	private Boolean isSuccess;
	private String errorMsg;

	// Constructors

	/** default constructor */
	public ProductPriceHistory() {
	}

	/** minimal constructor */
	public ProductPriceHistory(Key ppriceId, Shop shop) {
		this.ppriceId = ppriceId;
		this.shop = shop;
	}

	/** full constructor */
	public ProductPriceHistory(Key ppriceId, Shop shop,
			String productKey, Double price, String tbiid,
			Timestamp synchrTime, Boolean isSuccess, String errorMsg) {
		this.ppriceId = ppriceId;
		this.shop = shop;
		this.productKey = productKey;
		this.price = price;
		this.tbiid = tbiid;
		this.synchrTime = synchrTime;
		this.isSuccess = isSuccess;
		this.errorMsg = errorMsg;
	}

	// Property accessors
	@Id
	@Column(name = "pPriceId",  nullable = false, precision = 18, scale = 0)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Key getPpriceId() {
		return this.ppriceId;
	}

	public void setPpriceId(Key ppriceId) {
		this.ppriceId = ppriceId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tbUserId", nullable = false)
	public Shop getTaobaoUser() {
		return this.shop;
	}

	public void setTaobaoUser(Shop shop) {
		this.shop = shop;
	}

	@Column(name = "productKey", length = 50)
	public String getProductKey() {
		return this.productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	@Column(name = "price", scale = 2)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "tbiid", length = 50)
	public String getTbiid() {
		return this.tbiid;
	}

	public void setTbiid(String tbiid) {
		this.tbiid = tbiid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "synchrTime", length = 23)
	public Date getSynchrTime() {
		return this.synchrTime;
	}

	public void setSynchrTime(Date synchrTime) {
		this.synchrTime = synchrTime;
	}

	@Column(name = "isSuccess")
	public Boolean getIsSuccess() {
		return this.isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	@Column(name = "errorMsg", length = 200)
	public String getErrorMsg() {
		return this.errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}