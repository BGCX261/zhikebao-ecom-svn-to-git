package com.xyz.stock.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 仓位
 */
public class Place implements java.io.Serializable {

	private int recId;
	private String place;
	private Integer hot;

	public Place() {
	}

	public Place(int recId) {
		this.recId = recId;
	}

	public Place(int recId, String place, Integer hot) {
		this.recId = recId;
		this.place = place;
		this.hot = hot;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Integer getHot() {
		return this.hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

}
