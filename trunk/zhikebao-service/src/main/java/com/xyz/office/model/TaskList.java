package com.xyz.office.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xyz.base.model.BaseModel;

/**
 * 任务列表
 */
@Entity @Table(name="taskList")
public class TaskList extends BaseModel implements java.io.Serializable {

	private String recId;//关联业务对象ID
	private String type; //任务类型
	private Date beginDate;//计划开始时间
	private Date endDate; //计划结束时间
	private String establishMan;//任务验收人
	private Date exeDate;    //执行时间
	private String exer;     //执行人
	private Boolean isFinish ;// 是否完成
	private String status;
	private String summary;
	private String completeRate;
	private String taskRemark;
	private String executeRemark;
	private String score;
	private String remark;

	public TaskList() {
	}

	public TaskList(String recId) {
		this.recId = recId;
	}

	public TaskList(String recId, Date beginDate, String establishMan, Date exeDate,
			String exer, String status, String summary, String completeRate,
			String taskRemark, String executeRemark, String score,
			String remark, String id) {
		this.recId = recId;
		this.beginDate = beginDate;
		this.establishMan = establishMan;
		this.exeDate = exeDate;
		this.exer = exer;
		this.status = status;
		this.summary = summary;
		this.completeRate = completeRate;
		this.taskRemark = taskRemark;
		this.executeRemark = executeRemark;
		this.score = score;
		this.remark = remark;
	}

	public String getRecId() {
		return recId;
	}

	public void setRecId(String recId) {
		this.recId = recId;
	}
	public String getEstablishMan() {
		return this.establishMan;
	}

	public void setEstablishMan(String establishMan) {
		this.establishMan = establishMan;
	}

	public Date getExeDate() {
		return this.exeDate;
	}

	public void setExeDate(Date exeDate) {
		this.exeDate = exeDate;
	}

	public String getExer() {
		return this.exer;
	}

	public void setExer(String exer) {
		this.exer = exer;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getCompleteRate() {
		return this.completeRate;
	}

	public void setCompleteRate(String completeRate) {
		this.completeRate = completeRate;
	}

	public String getTaskRemark() {
		return this.taskRemark;
	}

	public void setTaskRemark(String taskRemark) {
		this.taskRemark = taskRemark;
	}

	public String getExecuteRemark() {
		return this.executeRemark;
	}

	public void setExecuteRemark(String executeRemark) {
		this.executeRemark = executeRemark;
	}

	public String getScore() {
		return this.score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(Boolean isFinish) {
		this.isFinish = isFinish;
	}

}
