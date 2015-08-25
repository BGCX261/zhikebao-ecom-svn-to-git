package com.xyz.order.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.taobao.api.extra.BaseModel;
import com.xyz.system.model.Shop;

/**
 * ProductStockHistory entity. @author sea
 */
@Entity
@Table(name = "product_stock_histories")
public class ProductStockHistory extends BaseModel {

	private static final long serialVersionUID = 7676826464193901162L;
	private Integer pstockId;
	private Shop shop;
	private String productKey;
	private Integer quantity;
	private String tbiid;
	private Date synchrTime;
	private Boolean isSuccess;
	private String errorMsg;

	// Constructors

	/** default constructor */
	public ProductStockHistory() {
	}

	/** minimal constructor *//*
	public ProductStockHistory(Key pstockId, Shop shop) {
		this.pstockId = pstockId;
		this.shop = shop;
	}

	*//** full constructor *//*
	public ProductStockHistory(Key pstockId, Shop shop,
			String productKey, Integer quantity, String tbiid,
			Timestamp synchrTime, Boolean isSuccess, String errorMsg) {
		this.pstockId = pstockId;
		this.shop = shop;
		this.productKey = productKey;
		this.quantity = quantity;
		this.tbiid = tbiid;
		this.synchrTime = synchrTime;
		this.isSuccess = isSuccess;
		this.errorMsg = errorMsg;
	}

	// Property accessors
	@Id
	@Column(name = "pStockId", nullable = false, precision = 18, scale = 0)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Key getPstockId() {
		return this.pstockId;
	}

	public void setPstockId(Key pstockId) {
		this.pstockId = pstockId;
	}
*/
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

	@Column(name = "quantity")
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "tbiid", length = 50)
	public String getTbiid() {
		return this.tbiid;
	}

	public void setTbiid(String tbiid) {
		this.tbiid = tbiid;
	}

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