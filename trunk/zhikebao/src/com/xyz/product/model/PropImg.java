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
@Entity @Table(name="prop_imgs")
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
