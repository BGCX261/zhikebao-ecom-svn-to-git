package com.xyz.customer.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;

/**
 * 会员级别
 */
public class MemberGrade implements java.io.Serializable {

	private int recId;
	private String grade;
	private String style;
	private BigDecimal dis;
	private Integer score1;
	private Integer score2;
	private Boolean bscore;
	private Integer hot;

	public MemberGrade() {
	}

	public MemberGrade(int recId) {
		this.recId = recId;
	}

	public MemberGrade(int recId, String grade, String style, BigDecimal dis,
			Integer score1, Integer score2, Boolean bscore, Integer hot) {
		this.recId = recId;
		this.grade = grade;
		this.style = style;
		this.dis = dis;
		this.score1 = score1;
		this.score2 = score2;
		this.bscore = bscore;
		this.hot = hot;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getStyle() {
		return this.style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public BigDecimal getDis() {
		return this.dis;
	}

	public void setDis(BigDecimal dis) {
		this.dis = dis;
	}

	public Integer getScore1() {
		return this.score1;
	}

	public void setScore1(Integer score1) {
		this.score1 = score1;
	}

	public Integer getScore2() {
		return this.score2;
	}

	public void setScore2(Integer score2) {
		this.score2 = score2;
	}

	public Boolean getBscore() {
		return this.bscore;
	}

	public void setBscore(Boolean bscore) {
		this.bscore = bscore;
	}

	public Integer getHot() {
		return this.hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

}
