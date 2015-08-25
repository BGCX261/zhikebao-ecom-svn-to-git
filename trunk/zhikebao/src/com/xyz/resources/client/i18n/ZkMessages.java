package com.xyz.resources.client.i18n;

import com.google.gwt.i18n.client.Messages;

public interface ZkMessages  extends Messages {

	String webappName();  
	String webappDesc();  
	String oss();
	String modified();
	String menu();
	String notice();
	String mycustomer();
	String myorder();
	String alert();
	String confirm();
	String confirmMsg();
	String boss();
	
	String query();   //查询
	String clickToExpand();//点击展开
	String operate();
	String create();
	String save();
	String edit();
	String update();
	String delete();
	String assign();
	String cancel();
	String config();
	String success();
	String noticeformerr();
	String fromTo();
	
	String desc();
	String defaultOption();
	String owner();//负责人
	
	//表格空
	String gridEmptyTxt();
	
	/**
	 * 首页
	 */
	String orderNotice();
	String refund();
	String myTask();
	
	/**
	 * 交易
	 */
	String tradeList();
	/**
	 * 左右选择框
	 * @return
	 */
	String addAll();
	String addSelected();
	String removeAll();
	String removeSelected();
}
