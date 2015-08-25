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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.xyz.system.client.mvc.Authority;
import com.xyz.system.client.mvc.Code;
import com.xyz.system.client.mvc.Resource;
import com.xyz.system.client.mvc.UserModel;

@GWTRequestMapping("/baseservice")
public interface GeneralService extends RemoteService {

  public List<Code> getCodes(Long parentId);
  
  public Code saveCode(Code code);
  
  public Resource saveResource(Resource code);
  
  public boolean delResource(String key);
  
  public boolean delCode(Code code);
  
  public boolean delAuthority(String key) ;
  /**
   * 分页查询功能列表
   * @param loadConfig
   * @return
   */
  public PagingLoadResult<Resource> getResources(PagingLoadConfig loadConfig);
  /**
   * 查询所有的功能列表
   * @return
   */
  public Map<String,List<Resource>> getAllResources(String key);
  
  /**
   * 分页查询岗位列表
   * @param loadConfig
   * @return
   */
  public PagingLoadResult<Authority> getAuthorities(PagingLoadConfig loadConfig);
  
  /**
   * 分页查询用户列表
   * @param loadConfig
   * @return
   */
  public PagingLoadResult<UserModel> getUsers(PagingLoadConfig loadConfig);
  
  /**
   * 查询用户列表，通用方法
   * 获取商家所有的用户，默认查询100个
   * @return
   */
  public List<UserModel> getUsers();

  
  public Authority saveAuthority(Authority auth);
  
  public boolean saveResOfAuth(String authKey,List<Resource> ress);
  
  public boolean checkUsername(String name);
  /**
   * 查询当前用户可分配出去的岗位
   * @return
   */
  public List<Authority> getEnassignAuths();
  
  public UserModel saveUser(UserModel user);
  
  public boolean delUser(String key) ;
  
  /**
   * 同步交易数据
   */
  public boolean syncTrades();
  
  /**
   * 提取客户数据
   */
  public boolean syncCuss();
  /**
   * 同步在线销售商品
   * @return
   */
  public boolean synOnSaleItems();
}
