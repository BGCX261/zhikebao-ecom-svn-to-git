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
import com.google.gwt.user.client.rpc.RemoteService;
import com.xyz.resources.model.Area;
import com.xyz.resources.model.BaseCode;
import com.xyz.system.model.Authority;
import com.xyz.system.model.Resource;
import com.xyz.system.model.User;

@GWTRequestMapping("/baseservice")
public interface GeneralService extends RemoteService {

  public List<BaseCode> getCodes(Integer parentId);
  
  public BaseCode saveCode(BaseCode BaseCode);
  
  public Resource saveResource(Resource res);
  
  public boolean delResource(Integer key);
  
  public boolean delCode(BaseCode code);
  
  public boolean delAuthority(Integer key) ;
  /**
   * 分页查询功能列表
   * @param loadConfig
   * @return
   */
  public PagingLoadResult<Resource> getResources(PagingLoadConfig loadConfig);
  /**
   * 查询岗位拥有的功能列表
   * @return
   */
  public List<Resource> getResourcesOfAuth(Integer key);
  
  /**
   * 查询可分配的功能列表
   * @return
   */
  public List<Resource> getAvaiAuthRes();
  
  public List<Area> getAreaList(Integer parentId);
  
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
  public PagingLoadResult<User> getUsers(PagingLoadConfig loadConfig);
  
  /**
   * 查询用户列表，通用方法
   * 获取商家所有的用户，默认查询100个
   * @return
   */
  public List<User> getUsers();

  
  public Authority saveAuthority(Authority auth);
  
  public boolean saveResOfAuth(Integer authKey,List<Resource> ress);
  
  public boolean checkUsername(String name);
  /**
   * 查询当前用户可分配出去的岗位
   * @return
   */
  public List<Authority> getEnassignAuths();
  
  public User saveUser(User user);
  
  public boolean delUser(Integer key) ;
  
  /**
   * 同步交易数据
   */
  public boolean syncTrades();
  
  /**
   * 增量同步交易数据
   */
  public boolean syncInceTrades();
  
  /**
   * 提取客户数据
   */
  public boolean syncCuss();
  /**
   * 同步在线销售商品
   * @return
   */
  public boolean synOnSaleItems();
  
  /**
   * 同步退款申请
   * @return
   */
  public boolean syncRefunds();
  
}
