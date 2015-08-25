/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.system.client;

import java.util.List;
import java.util.Map;

import org.gwtwidgets.server.spring.GWTRequestMapping;

import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.xyz.system.client.mvc.Authority;
import com.xyz.system.client.mvc.Code;
import com.xyz.system.client.mvc.Resource;
import com.xyz.system.client.mvc.UserModel;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GeneralServiceAsync {

  public void getCodes(Long parentId, AsyncCallback<List<Code>> callback);
  
  public void saveCode(Code code, AsyncCallback<Code> callback);
  
  public void saveResource(Resource code, AsyncCallback<Resource> callback);
  
  public void delResource(String key, AsyncCallback<Boolean> callback);
  
  public void delCode(Code code, AsyncCallback<Boolean> callback);
  
  public void delAuthority(String key, AsyncCallback<Boolean> callback) ;
  /**
   * 分页查询功能列表
   * @param loadConfig
   */
  public void getResources(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<Resource>> callback);
  /**
   * 查询所有的功能列表
   */
  public void getAllResources(String key, AsyncCallback<Map<String,List<Resource>>> callback);
  
  /**
   * 分页查询岗位列表
   * @param loadConfig
   */
  public void getAuthorities(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<Authority>> callback);
  
  /**
   * 分页查询用户列表
   * @param loadConfig
   */
  public void getUsers(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<UserModel>> callback);
  
  /**
   * 查询用户列表，通用方法
   * 获取商家所有的用户，默认查询100个
   */
  public void getUsers(AsyncCallback<List<UserModel>> callback);

  
  public void saveAuthority(Authority auth, AsyncCallback<Authority> callback);
  
  public void saveResOfAuth(String authKey,List<Resource> ress, AsyncCallback<Boolean> callback);
  
  public void checkUsername(String name, AsyncCallback<Boolean> callback);
  /**
   * 查询当前用户可分配出去的岗位
   */
  public void getEnassignAuths(AsyncCallback<List<Authority>> callback);
  
  public void saveUser(UserModel user, AsyncCallback<UserModel> callback);
  
  public void delUser(String key, AsyncCallback<Boolean> callback) ;
  
  /**
   * 同步交易数据
   */
  public void syncTrades(AsyncCallback<Boolean> callback);
  
  /**
   * 提取客户数据
   */
  public void syncCuss(AsyncCallback<Boolean> callback);
  /**
   * 同步在线销售商品
   */
  public void synOnSaleItems(AsyncCallback<Boolean> callback);
}
