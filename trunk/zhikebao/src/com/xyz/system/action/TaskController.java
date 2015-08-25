package com.xyz.system.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xyz.system.task.ISysTask;
import com.xyz.trade.service.ITradeService;

@Controller
public class TaskController {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ISysTask sysTask;
	
	@Autowired
	private ITradeService ts;
    
	@RequestMapping("/renewauth")
	public String renewAuth()
	{
		sysTask.holdAuthorize();
		return "success";
	}
	
	@RequestMapping("/sysnorder.htm")
	public String sysnOrders()
	{
		ts.tradeSysn();
		return "success";
	}
}
