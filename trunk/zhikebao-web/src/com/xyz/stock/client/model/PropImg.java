package com.xyz.stock.client.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.xyz.resources.client.model.BaseModel;

/**
 * @author gaoweibin.tw
 *
 */
@Entity
public class PropImg extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9180857371850463266L;
	private String propimgId;   //属性图片的id
	private String url;			//图片链接地址
	private String properties;  //图片所对应的属性组合的字符串
	private long position;      //图片放在第几张
	private String created;     //商品属性图片上传时间,图片上传和更新时会返回此字段
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
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public long getPosition() {
		return position;
	}
	public void setPosition(long position) {
		this.position = position;
	}
	public String getPropimgId() {
		return propimgId;
	}
	public void setPropimg_id(String propimgId) {
		this.propimgId = propimgId;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
}
