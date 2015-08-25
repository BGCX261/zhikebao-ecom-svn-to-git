package com.xyz.util;

public class QueryDate {
	private String fieldName;
	private String startDate;
	private String endDate;
	private String queryFormat;
	
	public String getQueryFormat() {
		return queryFormat;
	}
	public void setQueryFormat(String queryFormat) {
		this.queryFormat = queryFormat;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
