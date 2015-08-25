package com.xyz;

import java.io.File;
import java.util.Date;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import com.google.appengine.tools.development.ApiProxyLocalImpl;
import com.google.apphosting.api.ApiProxy;
import com.xyz.system.model.Shop;
import com.xyz.system.model.User;
import com.xyz.system.service.ISecurityService;

/**
 * 基础 TestCase
 */
public class LocalServiceTestCase extends TestCase
{
	protected ApplicationContext ac ;
	@Override
    public void setUp() throws Exception {
        super.setUp();
        ApiProxy.setEnvironmentForCurrentThread(new TestEnvironment());
        ApiProxy.setDelegate(new ApiProxyLocalImpl(new File("war")){});
        ac = new ClassPathXmlApplicationContext(new String[]{
        		"applicationContext.xml",
        		"data-context.xml",
                "system-context.xml"});
        //模拟登录
        initSecurityContext();
        /* String url = "http://localhost:8080/j_spring_security_check";
		String params = "?j_username=admin&j_password=root";
		try {
			URLFetchService ufs = URLFetchServiceFactory.getURLFetchService();
			URL u = new URL(url+params);
			ufs.fetch(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }
	/**
	 * 创建一个测试用的SecurityContext		
	 */
	public void initSecurityContext()
	{
		ISecurityService ss = (ISecurityService)getAc().getBean("securityManager");
		User admin = new User("admin","63a9f0ea7bb98050796b649e85481845",true,true,"文浪","valiant",true,new Date(),new Date(),"God","God",true,true);
    	Shop shop = new Shop();
    	shop.setTbAccount("alipublic04");
    	shop.setIsAuthorize(true);
    	shop.setAppKey("12012509");
    	shop.setSessionKey("15ee53d7dab228ee556c2e3e1e17e315a");
    	admin.setShop(shop);
		Authentication auth = new TestingAuthenticationToken(admin,"root");
        SecurityContext sc = new SecurityContextImpl();
        sc.setAuthentication(auth);
        SecurityContextHolder.setContext(sc);
	}

    @Override
    public void tearDown() throws Exception {
        // not strictly necessary to null these out but there's no harm either
        ApiProxy.setDelegate(null);
        ApiProxy.setEnvironmentForCurrentThread(null);
        ac = null;
        super.tearDown();
    }

	public ApplicationContext getAc() {
		return ac;
	}

}
