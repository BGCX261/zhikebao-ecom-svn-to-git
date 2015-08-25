/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xyz.framework.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.xyz.framework.log.Logger;
import com.xyz.framework.service.IDataInit;
import com.xyz.system.dao.IAuthorityDao;
import com.xyz.system.dao.IResourceDao;
import com.xyz.system.dao.IUserDao;
import com.xyz.system.model.Authority;
import com.xyz.system.model.Resource;
import com.xyz.system.model.Shop;
import com.xyz.system.model.User;
import com.xyz.system.service.ISecurityService;
import com.xyz.system.service.IUserService;


/**
 * 生成初始化数据
 * @author Val
 * @version $Id: DataSourcePopulator.java 3650 2009-11-04 $
 */
@Service
public class DataSourcePopulator implements IDataInit  {
    //~ Instance fields ================================================================================================
    @Autowired
	private ISecurityService ss;
    @Autowired
    private IUserService us;
    
    @Autowired @Qualifier("authorityCache")
	private IAuthorityDao adao;
    @Autowired @Qualifier("resourceCache")
	private IResourceDao rdao;
    @Autowired
	private IUserDao udao;
    
    Random rnd = new Random();
    
    private int createEntities = 50;

    //~ Methods ========================================================================================================
    /* (non-Javadoc)
	 * @see com.xyz.framework.data.init.IDataInit#dataInit()
	 */
    public void dataInit() throws Exception {
    	//File dbfile = new File("war/WEB-INF/appengine-generated/local_db.bin");
    	//Logger.debug("删除已有数据文件");
    	//dbfile.delete();

    	udao.deleteAll();
    	adao.deleteAll();
    	rdao.deleteAll();
    	Date now = new Date();
    	/**权限:A_USER、A_ADMIN、A_MGR、A_BIZ、A_STOCK、A_SALE、A_FINACE、A_SERVICE、A_IT
    	 * 资源
    	 * Resource(String name,String serial, String parentId, String resourceType,
			String value, double position, String description,String authorities)*/
    	Resource[] res = new Resource[29];
    	//全局权限，所有功能都必须先登录
    	res[0] = new Resource("只有登录用户才可访问系统",0, 0, "url","/**", 1, "全局资源","A_USER");
    	res[22] = new Resource("首页链接",0, 0, "url","/mainservice", 1, "全局资源","A_USER");
    	//销售模块
    	res[1] = new Resource("销售",100, 1, "menu","/salemain", 1, "销售管理","A_SALE,A_SERVICE");
    	res[2] = new Resource("新建订单",101, 100, "menu","/salenew", 1, "销售管理","A_SALE,A_ADMIN");
    	res[3] = new Resource("确认订单",102, 100, "menu","/salecfm", 2, "销售管理","A_SALE,A_ADMIN");
    	res[4] = new Resource("收款发货",103, 100, "menu","/salesnd", 3, "销售管理","A_SALE,A_ADMIN");
        res[5] = new Resource("订单查询",104, 100, "menu","/saleqry", 4, "销售管理","A_SALE,A_ADMIN");
        res[6] = new Resource("售后服务",105, 100, "menu","/salesrv", 5, "销售管理","A_SALE,A_SERVICE,A_ADMIN");
        //库存管理
        res[7] = new Resource("库存",500, 1, "menu","/stockmain", 5, "库存管理","A_STOCK");
    	res[8] = new Resource("商品入库",501, 500, "menu","/stockin", 1, "库存管理","A_STOCK,A_ADMIN");
    	res[9] = new Resource("商品出库",502, 500, "menu","/stockout", 2, "库存管理","A_STOCK,A_ADMIN");
    	res[10] = new Resource("库存查询",503,500, "menu","/stockqry", 3, "库存管理","A_STOCK,A_ADMIN");
        res[11] = new Resource("库存状态",504, 500, "menu","/stocksta", 4, "库存管理","A_STOCK,A_ADMIN");
        //客户管理
        res[12] = new Resource("客户",300, 1, "menu","/custmain", 3, "客户管理","A_MGR,A_BIZ,A_SALE,A_SERVICE");
    	res[13] = new Resource("客户管理",301, 300, "menu","/custmng", 1, "客户管理","A_MGR,A_BIZ,A_SALE,A_ADMIN");
    	res[14] = new Resource("客户分析",302, 300, "menu","/custalz", 2, "客户管理","A_MGR,A_SALE,A_ADMIN");
    	res[15] = new Resource("客户报价",303, 300, "menu","/custopp", 3, "客户管理","A_MGR,A_SALE,A_ADMIN");
        res[16] = new Resource("客户关怀",304, 300, "menu","/custcon", 4, "客户管理","A_MGR,A_SALE,A_ADMIN");
        
        //采购管理
        res[17] = new Resource("采购",400, 1, "menu","/buymain", 4, "采购管理","A_MGR,A_BIZ,A_STOCK");
    	res[18] = new Resource("新建采购",401, 400, "menu","/buynew", 1, "采购管理","A_BIZ,A_STOCK,A_ADMIN");
    	res[19] = new Resource("收货入库",402, 400, "menu","/buyin", 2, "采购管理","A_BIZ,A_STOCK,A_ADMIN");
    	res[20] = new Resource("商品目录",403, 400, "menu","", 3, "采购管理","A_MGR,A_SALE,A_ADMIN");
        res[21] = new Resource("商品列表",404, 400, "menu","/pdtlist", 4, "采购管理","A_MGR,A_SALE,A_ADMIN");
        
        //系统管理
        res[23] = new Resource("管理",200, 1, "menu","/buymain", 2, "系统管理","A_ADMIN");
    	res[24] = new Resource("员工维护",201, 200, "menu","/buynew", 1, "员工维护","A_ADMIN,A_SYS_ADMIN");
    	res[25] = new Resource("用户管理",202, 200, "menu","/buyin", 2, "用户管理","A_ADMIN,A_SYS_ADMIN");
    	res[26] = new Resource("权限维护",203, 200, "menu","", 3, "权限维护","A_ADMIN,A_SYS_ADMIN");
    	res[27] = new Resource("功能维护",204, 200, "menu","", 4, "权限维护","A_ADMIN,A_SYS_ADMIN");
        res[28] = new Resource("代码维护",205, 200, "menu","/pdtlist", 5, "代码维护","A_ADMIN,A_SYS_ADMIN");
        
    	for(Resource r : res)
    	{
    		ss.saveResource(r);
    	}
    	Logger.info(this.getClass(), "资源信息保存成功");
    	//tx.commit();
    	/**
    	 * 权限:A_USER、A_ADMIN、A_MGR、A_BIZ、A_STOCK、A_SALE、A_FINACE、A_SERVICE、A_IT
    	 */
    	Authority[] as = new Authority[4];
    	HashSet<String> sks[] = new HashSet[4];
    	for(int i=0 ; i<4;i++)
    		sks[i] = new HashSet();
    	//A_USER
    	as[0] = new Authority("A_USER","用户");
    	sks[0].add(res[0].getKey());
    	sks[0].add(res[22].getKey());
    	as[0].setResourceKeys(sks[0]);
        //A_SYS_ADMIN
    	as[3] = new Authority("A_SYS_ADMIN","系统管理员");
    	sks[3].add(res[23].getKey());
    	sks[3].add(res[24].getKey());
    	sks[3].add(res[25].getKey());
    	sks[3].add(res[26].getKey());
    	sks[3].add(res[27].getKey());
    	sks[3].add(res[28].getKey());
    	as[3].setResourceKeys(sks[3]);
    	//A_ADMIN
    	as[1] = new Authority("A_ADMIN","管理员");
    	sks[1].add(res[25].getKey());
    	as[1].setResourceKeys(sks[1]);
    	
    	//A_SALE
    	as[2] = new Authority("A_SALE","销售经理");
    	for(int i=1;i<7;i++)
    	{
    		sks[2].add(res[i].getKey());
    	}
    	as[2].setResourceKeys(sks[2]);
    	for(Authority a : as)
    		ss.saveAuth(a);
    	
    	//atx.commit();
    	/** 用户
    	User(String username, String password,
				String authenticateId, boolean accountNonLocked, String realName,
				String nickName,  Boolean isAdmin,Date dateEntered,
				Date dateModified, String modifiedUserId, String createdBy,
				boolean accountNonExpired,  boolean credentialsNonExpired)
    	*/
    	//PersistenceManager upm = udao.getPm();
    	//Transaction utx = upm.currentTransaction();
    	//utx.begin();

    	User admin = new User("admin","root",true,true,"文浪","valiant",true,now,now,"God","God",true,true);
    	Set<String> akeys = new HashSet<String>();
    	akeys.add(as[1].getKey());
    	akeys.add(as[0].getKey());
    	akeys.add(as[2].getKey());
    	akeys.add(as[3].getKey());
    	admin.setAuthorityKeys(akeys);
    	ss.saveUser(admin);
    	
    	/**User user = new User("user","user",true,true,"user","valiant",true,now,now,"God","God",true,true);
    	Set<String> us = new HashSet<String>();
    	us.add(as[0].getKey());

    	user.setAuthorityKeys(us);
    	ss.saveUser(user);
    	
    	User sale = new User("meimei","meimei",true,true,"user","valiant",true,now,now,"God","God",true,true);
    	Set<String> st = new HashSet<String>();
    	st.add(as[2].getKey());
    	st.add(as[0].getKey());
    	sale.setAuthorityKeys(st);
    	ss.saveUser(sale);
    	*/
      
    }

    
}
