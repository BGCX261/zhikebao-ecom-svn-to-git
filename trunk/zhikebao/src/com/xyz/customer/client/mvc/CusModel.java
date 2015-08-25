/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.customer.client.mvc;


import java.util.Date;

import com.extjs.gxt.ui.client.data.BaseModel;

public class CusModel extends BaseModel {
  
	private static final long serialVersionUID = -8086338881654320329L;

	public CusModel() {
	}

	public String getNick() {
	    return (String) get("nick");
	}

	public void setNick(String nick) {
		set("nick", nick);
	}
	public String getSex() {
	    return (String) get("sex");
	}

	public void setSex(String sex) {
		set("sex", sex);
	}
	
	public Double getAmount() {
		return (Double) get("amount");
	}

	public void setAmount(Double amount) {
		set("amount", amount);
	}

	public String getStatus() {
		return (String) get("status");
	}

	public void setStatus(String status) {
		set("status", status);
	}

	public Integer getRank() {
		return (Integer) get("rank");
	}

	public void setRank(Integer rank) {
		set("rank", rank);
	}

	public Date getDateEntered() {
		return (Date) get("dateEntered");
	}

	public void setDateEntered(Date dateEntered) {
		set("dateEntered", dateEntered);
	}
}
