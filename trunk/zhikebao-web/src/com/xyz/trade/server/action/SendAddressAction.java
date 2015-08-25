/*
 * Generated by ActionName
 * Template path: templates/java/JavaClass.vtl
 */
package com.xyz.trade.server.action;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xyz.order.model.AddressDatabase;
import com.xyz.order.service.IAddressServ;
import com.xyz.util.JsonUtil;

/** 
 * ActionName
 * 创建日期: 08-27-2009
 * 
 * XDoclet definition:
 * 作者: val 
 * EMail : Valiant.maya@gmail.com 旺旺：valiant_mo
 */
@Controller
public class SendAddressAction  {
	@Autowired @Qualifier("addressServ")
	private IAddressServ facade;
	
	@RequestMapping("/sendAddress/forward.do")
	public String forward(HttpServletRequest request, HttpServletResponse response) {
		List<AddressDatabase> list = facade.findAll();
		if(list.size() > 0){
			request.setAttribute("adb", list.get(0));
		}
		return "/send/addressdb";
	}
	
	@RequestMapping("/sendAddress/save.do")
	public String save(HttpServletRequest request, HttpServletResponse response) {
		AddressDatabase adb = new AddressDatabase();
		String areaid = request.getParameter("areaid");
		String state = request.getParameter("state");
		String city = request.getParameter("city");
		String district = request.getParameter("district");
		String address = request.getParameter("address");
		String zip = request.getParameter("zip");
		String mobile = request.getParameter("mobile");
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		List<AddressDatabase> list = facade.findAll();
		Boolean isUpdate = true;
		if(list.size() > 0){
			isUpdate = false;
			adb = list.get(0);
		}
		adb.setAddress(address);
		adb.setAreaid(areaid);
		adb.setCity(city);
		adb.setDistrict(district);
		adb.setIsDefault(true);
		adb.setMobile(mobile);
		adb.setName(name);
		adb.setPhone(phone);
		adb.setState(state);
		adb.setZip(zip);
		if(isUpdate){
			facade.save(adb);
		}else{
			facade.update(adb);
		}
		JsonUtil.formSubmitResult(response, true, "保存收取货地址成功");
		return null;
	}
}