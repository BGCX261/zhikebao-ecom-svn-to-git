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
package com.xyz.system.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xyz.framework.service.IDataInit;


/**
 * 生成初始化数据
 * @author Val
 * @version $Id: DataSourcePopulator.java 3650 2009-11-04 $
 */
@Controller
public class DataInit  {
    //~ Instance fields ================================================================================================
    @Autowired
	private IDataInit dataInit;


    //~ Methods ========================================================================================================
    /**
     * 生成各种初始化数据
     */
    @RequestMapping("/datainit")
    public void dataInit() throws Exception {
    	dataInit.dataInit();
    }

    
}
