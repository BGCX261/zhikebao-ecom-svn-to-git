package com.xyz.product.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xyz.resources.model.BaseModel;


/**
 * 
 * @author moling
 * @since 1.0, 2009-8-4
 */
@Entity @Table(name="videos")
public class Video extends BaseModel {
	private static final long serialVersionUID = 870177203685713401L;
	private String id;
	private String videoId;
	private String url;
	private Date modified;
	private Date created;
	
	@ManyToOne
	@JoinColumn(name="item_id")
	private Item item; //所属商品
	
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
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
