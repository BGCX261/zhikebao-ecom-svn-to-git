package com.xyz.stock.client.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.xyz.resources.client.model.BaseModel;

/**
 * @author Val
 *
 */
@Entity
public class ItemImg extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7251642765531379576L;
	
	private String itemimgId;      //商品图片id
	private String url;				//图片链接地址
	private long position;			//图片放在第几张
	private String created;			//商品图片上传时间,图片上传和更新时会返回此字段
	@ManyToOne
	private ItemModel itemModel; //所属商品
	
	public ItemModel getItem() {
		return itemModel;
	}
	public void setItem(ItemModel itemModel) {
		this.itemModel = itemModel;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getPosition() {
		return position;
	}
	public void setPosition(long position) {
		this.position = position;
	}
	public String getItemimgId() {
		return itemimgId;
	}
	public void setItemimgId(String itemimgId) {
		this.itemimgId = itemimgId;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	
	
}
