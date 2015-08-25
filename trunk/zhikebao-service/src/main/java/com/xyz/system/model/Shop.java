package com.xyz.system.model;

import java.util.Date;

import javax.jdo.annotations.Persistent;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xyz.base.model.BaseModel;

/**
 * TaobaoUser entity. @author sea
 */
@Entity @Table(name="shop")
public class Shop extends BaseModel implements java.io.Serializable {

	private static final long serialVersionUID = 1721373311237668657L;

	@Persistent(column="appKey")
	private String appKey;
	@Persistent
	private String appSecret;
	@Basic
	private String tbAccount;
	@Persistent
	private String email;
	@Persistent
	private Boolean isAuthorize;
	@Persistent
	private Date authorizeTime;
	@Persistent
	private String sessionKey;
	@Persistent
	private Date taobaoTradesSoldGetTime;
	@Persistent
	private Date synchrPriceTime;
	@Persistent
	private Date synchrStockTime;

	//private Set<ProductStockHistory> productStockHistories = new HashSet<ProductStockHistory>(0);

	//private Set<ProductPriceHistory> productPriceHistories = new HashSet<ProductPriceHistory>(0);
	//EAGER的FetchType需要修正，防止加载过多数据
	//@OneToMany(cascade=CascadeType.ALL,mappedBy="tbUser",fetch=FetchType.EAGER)
	//private List<User> users;

	// Constructors

	/** default constructor */
	public Shop() {
	}

	/** minimal constructor */
	public Shop(String appKey, String appSecret, Boolean isAuthorize) {
		this.appKey = appKey;
		this.appSecret = appSecret;
		this.isAuthorize = isAuthorize;
	}
	
	/** main constructor */
	public Shop(String appKey, String appSecret, String tbAccount, String email, Boolean isAuthorize, Date authorizeTime) {
		this.appKey = appKey;
		this.appSecret = appSecret;
		this.tbAccount = tbAccount;
		this.email = email;
		this.isAuthorize = isAuthorize;
		this.authorizeTime = authorizeTime;
	}


	/** full constructor */
	public Shop(String appKey, String appSecret, String tbAccount, String email, Boolean isAuthorize, Date authorizeTime, String sessionKey
			) {
		this.appKey = appKey;
		this.appSecret = appSecret;
		this.tbAccount = tbAccount;
		this.email = email;
		this.isAuthorize = isAuthorize;
		this.authorizeTime = authorizeTime;
		this.sessionKey = sessionKey;
	
	}

	public String getAppKey() {
		return this.appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return this.appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getTbAccount() {
		return this.tbAccount;
	}

	public void setTbAccount(String tbAccount) {
		this.tbAccount = tbAccount;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsAuthorize() {
		return this.isAuthorize;
	}

	public void setIsAuthorize(Boolean isAuthorize) {
		this.isAuthorize = isAuthorize;
	}

	public Date getAuthorizeTime() {
		return this.authorizeTime;
	}

	public void setAuthorizeTime(Date authorizeTime) {
		this.authorizeTime = authorizeTime;
	}

	public String getSessionKey() {
		return this.sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	
	public Date getTaobaoTradesSoldGetTime() {
		return taobaoTradesSoldGetTime;
	}

	public Date getSynchrPriceTime() {
		return synchrPriceTime;
	}

	public void setSynchrPriceTime(Date synchrPriceTime) {
		this.synchrPriceTime = synchrPriceTime;
	}

	public Date getSynchrStockTime() {
		return synchrStockTime;
	}

	public void setSynchrStockTime(Date synchrStockTime) {
		this.synchrStockTime = synchrStockTime;
	}

	public void setTaobaoTradesSoldGetTime(Date taobaoTradesSoldGetTime) {
		this.taobaoTradesSoldGetTime = taobaoTradesSoldGetTime;
	}

}