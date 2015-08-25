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

import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.xyz.resources.model.Area;
import com.xyz.resources.model.BaseCode;
import com.xyz.system.model.Authority;
import com.xyz.system.model.Resource;
import com.xyz.system.model.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GeneralServiceAsync {

  public void getCodes(Integer parentId, AsyncCallback<List<BaseCode>> callback);
  
  public void saveCode(BaseCode BaseCode, AsyncCallback<BaseCode> callback);
  
  public void saveResource(Resource res, AsyncCallback<Resource> callback);
  
  public void delResource(Integer key, AsyncCallback<Boolean> callback);
  
  public void delCode(BaseCode code, AsyncCallback<Boolean> callback);
  
  public void delAuthority(Integer key, AsyncCallback<Boolean> callback) ;
  /**
   * 分页查询功能列表
   * @param loadConfig
   */
  public void getResources(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<Resource>> callback);
  /**
   * 查询岗位拥有的功能列表
   */
  public void getResourcesOfAuth(Integer key, AsyncCallback<List<Resource>> callback);
  
  /**
   * 查询可分配的功能列表
   */
  public void getAvaiAuthRes(AsyncCallback<List<Resource>> callback);
  
  public void getAreaList(Integer parentId, AsyncCallback<List<Area>> callback);
  
  /**
   * 分页查询岗位列表
   * @param loadConfig
   */
  public void getAuthorities(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<Authority>> callback);
  
  /**
   * 分页查询用户列表
   * @param loadConfig
   */
  public void getUsers(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<User>> callback);
  
  /**
   * 查询用户列表，通用方法
   * 获取商家所有的用户，默认查询100个
   */
  public void getUsers(AsyncCallback<List<User>> callback);

  
  public void saveAuthority(Authority auth, AsyncCallback<Authority> callback);
  
  public void saveResOfAuth(Integer authKey,List<Resource> ress, AsyncCallback<Boolean> callback);
  
  public void checkUsername(String name, AsyncCallback<Boolean> callback);
  /**
   * 查询当前用户可分配出去的岗位
   */
  public void getEnassignAuths(AsyncCallback<List<Authority>> callback);
  
  public void saveUser(User user, AsyncCallback<User> callback);
  
  public void delUser(Integer key, AsyncCallback<Boolean> callback) ;
  
  /**
   * 同步交易数据
   */
  public void syncTrades(AsyncCallback<Boolean> callback);
  
  /**
   * 增量同步交易数据
   */
  public void syncInceTrades(AsyncCallback<Boolean> callback);
  
  /**
   * 提取客户数据
   */
  public void syncCuss(AsyncCallback<Boolean> callback);
  /**
   * 同步在线销售商品
   */
  public void synOnSaleItems(AsyncCallback<Boolean> callback);
  
  /**
   * 同步退款申请
   */
  public void syncRefunds(AsyncCallback<Boolean> callback);
  
}
