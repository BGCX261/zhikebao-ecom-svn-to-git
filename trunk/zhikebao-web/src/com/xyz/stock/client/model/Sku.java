package com.xyz.stock.client.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.xyz.resources.client.model.BaseModel;

/**
 * @author gaoweibin.tw
 *
 */
@Entity
public class Sku extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4997844192829378903L;
	private String skuId;
	private String iid;
	private String properties;
	private long quantity;
	private String price;
	private String outerId;
	private Date created;
	private Date modified;
	private String extraId;
	private String status;
	
	@ManyToOne
	private ItemModel itemModel; //所属商品
	
	public ItemModel getItem() {
		return itemModel;
	}
	public void setItem(ItemModel itemModel) {
		this.itemModel = itemModel;
	}

	/* --- delete by jeck 2009-04-15 

	private String memo;
 	
	--- */

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getOuterId() {
		return outerId;
	}
	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public String getExtraId() {
		return extraId;
	}
	public void setExtraId(String extraId) {
		this.extraId = extraId;
	}
	
}
