package com.xyz.customer.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xyz.resources.model.BaseModel;

@Entity @Table(name="guests")
public class Guest extends BaseModel {

	private String nick;

	private String state;

	private String city;
	
	private String goods;
	
	public Guest(){
		
	}
	
	public Guest(String nick, String state, String city, String goods) {
		super();
		this.nick = nick;
		this.state = state;
		this.city = city;
		this.goods = goods;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
