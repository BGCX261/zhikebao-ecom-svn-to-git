package com.xyz.product.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xyz.resources.model.BaseModel;


/**
 * @author gaoweibin.tw
 *
 */
@Entity @Table(name="item_imgs")
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
	@JoinColumn(name="item_id")
	private Item item; //所属商品
	
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
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
