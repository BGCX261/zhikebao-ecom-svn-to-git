package com.xyz;

import java.util.HashMap;
import java.util.Map;

import com.google.apphosting.api.ApiProxy;

/**
 * 创建执行环境
 * @author val
 *
 */
public class TestEnvironment implements ApiProxy.Environment {
    public String getAppId() {
        return "Zhikebao";
    }

    public String getVersionId() {
        return "1.0";
    }

    public void setDefaultNamespace(String s) { }

    public String getRequestNamespace() {
        return null;
    }

    public String getDefaultNamespace() { 
        return null;
    }

    public String getAuthDomain() {
      return null;
    }

    public boolean isLoggedIn() {
      return false;
    }

    public String getEmail() {
      return "valiant.maya@gmail.com";
    }

    public boolean isAdmin() {
      return true;
    }

	@Override
	public Map<String, Object> getAttributes() {
		Map<String, Object> attrs = new HashMap<String, Object>();
		//attrs.put("appId", "zhikebao");
		//attrs.put("email", "valiant.maya@gmail.com");
		
		return attrs;
	}
}

