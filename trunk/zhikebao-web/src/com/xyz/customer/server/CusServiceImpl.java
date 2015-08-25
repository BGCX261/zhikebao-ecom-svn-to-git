package com.xyz.customer.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.taobao.api.model.Trade;
import com.xyz.customer.client.CusService;
import com.xyz.customer.client.mvc.CusModel;
import com.xyz.customer.model.Customer;
import com.xyz.customer.service.ICustomerService;
import com.xyz.system.service.IUserService;
import com.xyz.trade.client.model.TradeModel;
import com.xyz.util.Page;
import com.xyz.util.PropertyFilter;

@Service
public class CusServiceImpl extends RemoteServiceServlet implements CusService {
    
	private static final long serialVersionUID = 1L;
	@Autowired
	private ICustomerService cs ;
	@Autowired
	private IUserService us;

	@Override
	public PagingLoadResult<CusModel> getCuss(PagingLoadConfig config) {
		Page<Customer> page = new Page<Customer>(config.getLimit());
		page.setStart(config.getOffset());
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		if(config.get("query")!=null)
		{
			filters.add(new PropertyFilter("LIKE_nick",config.get("query")));
		}
		page = cs.queryPage(page, filters);
		List<CusModel> lf = new ArrayList<CusModel>();
		
		if(page.getResult()!=null&&page.getResult().size()>0)
		{
			for(Customer cus : page.getResult())
			{
				CusModel f = new CusModel();
				BeanUtils.copyProperties(cus, f);
				com.taobao.api.model.User tbuser = us.findByKey(cus.getTbuserKey());
				BeanUtils.copyProperties(tbuser, f);
				lf.add(f);
			}
	    }
		return new BasePagingLoadResult<CusModel>(lf, config.getOffset(),
				page.getTotalCount());
	}
	
}
