package com.xyz.customer.client.i18n;

import com.xyz.resources.client.i18n.ZkMessages;

/**
 * 客户管理模块
 * @author val
 *
 */
public interface CusMessages  extends ZkMessages {

	/**
	 *客户
	 */
	String cuslist();
	String cusName();
	String sex();
	String male();
	String famale();
	String status();
	String level();
	String amount();
    String creatime();
}
