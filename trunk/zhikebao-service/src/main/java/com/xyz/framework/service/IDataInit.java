package com.xyz.framework.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

public interface IDataInit {

	//~ Methods ========================================================================================================
	/**
	 * 生成各种初始化数据
	 */
	@RequestMapping("/datainit")
	public abstract void dataInit() throws Exception;

}