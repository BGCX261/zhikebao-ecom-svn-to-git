package com.xyz.system.client.i18n;

import com.xyz.resources.client.i18n.ZkMessages;

/**
 * 基础模块
 * @author val
 *
 */
public interface BaseMessages  extends ZkMessages {

   String noticeSelect();
   
   String baseCode();
   
   //字段标签
   String name();
   
   String inx();
   
   String hasChiled();
   
   String cantDelete();
   
   //功能权限
   String resource();
   String serial();
   String parentId();
   String resourceType();
   String value();
   String authandres();
   
   String resourcelist();
   String assresource();
   String selectres();
 
   //用户
   String userinfo();
   String pwdchange();
   String username();
   String userexist();
   String password();
   String repassword();
   String pwderrmsg();
   String pwdrequired();
   String isAdmin();
   String usertitle();
   String phoneMobile();
   String realName();
   String messengerId();
   String messengerType();
   String userauths();
   
   //系统工具
   String synctrades();
   String synccuss();
   String syncositem();
}
