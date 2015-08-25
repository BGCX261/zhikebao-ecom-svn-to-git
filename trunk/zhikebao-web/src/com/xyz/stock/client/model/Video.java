package com.xyz.stock.client.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.xyz.resources.client.model.BaseModel;

/**
 * @author Val
 * @since 1.0, 2009-8-4
 */
@Entity
public class Video extends BaseModel {
	private static final long serialVersionUID = 870177203685713401L;
	private String id;
	private String videoId;
	private String url;
	private Date modified;
	private Date created;
	
	@ManyToOne
	private ItemModel itemModel; //所属商品
	
	public ItemModel getItem() {
		return itemModel;
	}
	public void setItem(ItemModel itemModel) {
		this.itemModel = itemModel;
	}
	
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVideoId() {
		return this.videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getModified() {
		return this.modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public Date getCreated() {
		return this.created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
}
