package com.xyz.system.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xyz.system.task.ISysTask;

@Controller
public class TaskController {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ISysTask sysTask;
    
	@RequestMapping("/renewauth")
	public String renewAuth()
	{
		sysTask.holdAuthorize();
		return "success";
	}
	
	@RequestMapping("/sysnorder")
	public String sysnOrders()
	{
		sysTask.tradeSysn();
		return "success";
	}
}
