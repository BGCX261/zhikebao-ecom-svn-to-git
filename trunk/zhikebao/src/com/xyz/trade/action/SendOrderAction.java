/*
 * Generated by ActionName
 * Template path: templates/java/JavaClass.vtl
 */
package com.xyz.trade.action;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.taobao.api.TaobaoApiException;
import com.taobao.api.TaobaoJsonRestClient;
import com.taobao.api.TaobaoRestClient;
import com.xyz.order.model.AddressDatabase;
import com.xyz.order.model.Shipping;
import com.xyz.order.service.IAddressServ;
import com.xyz.order.service.IOrderService;
import com.xyz.order.service.IShippingServ;
import com.xyz.system.model.Shop;
import com.xyz.system.service.IUserService;
import com.xyz.trade.model.OrderModel;
import com.xyz.trade.model.TradeModel;
import com.xyz.trade.service.ITradeService;
import com.xyz.util.DateUtil;
import com.xyz.util.Page;
import com.xyz.util.QueryDate;
import com.xyz.util.StringUtil;

/** 
 * ActionName
 * 创建日期: 08-27-2009
 * 
 * XDoclet definition:
 * 作者: 文浪
 */
@Controller
public class SendOrderAction  {
	@Autowired @Qualifier("taobaoUserServ")
	IUserService facade;
	@Autowired
	private ITradeService tradeFacade ;
	@Autowired @Qualifier("orderServ")
	private IOrderService orderFacade;
	@Autowired @Qualifier("addressServ")
	IAddressServ addressFacade ;
	@Autowired @Qualifier("shippingServ")
	IShippingServ shippingFacade;
	
	/**
	 * 待发货订单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/sendorder/forward.do")
	public String forward() {
		return "/send/sendorder";
	}
	@RequestMapping("/sendorder/data.do")
	public String data(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		List<String> fields = StringUtil.toStringArray(request.getParameter("fields"));
		List<String> querys = StringUtil.toStringArray(request.getParameter("query"));
		Integer start = Integer.parseInt(request.getParameter("start"));
		Integer limit = Integer.parseInt(request.getParameter("limit"));

		Page<TradeModel> page = new Page<TradeModel>(20);
		page.setStart(start);
		Integer total = null;
		QueryDate queryDate = new QueryDate();// 间隔日期查询必须
		if (fields.size() == 0) {
			page = tradeFacade.findByStatus("WAIT_SELLER_SEND_GOODS", page);
			total = tradeFacade.findManyByPropertySize(null, null);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < fields.size(); i++) {
				String field = fields.get(i).trim();
				String query = querys.get(i).trim();
				if (StringUtil.isNullOrEmpty(query)) {
					if (field.equals("start_created")) {
						queryDate.setStartDate(query);
					} else if (field.equals("end_created")) {
						queryDate.setEndDate(query);
					}  else if (field.equals("shipping_type")) {
						if (!query.equals("all")) {
							map.put(field, query);
						}
					}else
						map.put(field, query);
				}
			}
			map.put("status", "WAIT_SELLER_SEND_GOODS");
			map.put("pay_time", queryDate);
			page = tradeFacade.findManyByProperty(map.keySet().toArray(), map.values().toArray(), page);
			total = tradeFacade.findManyByPropertySize(map.keySet().toArray(), map.values().toArray());
		}
		if (page.getResult() != null && page.getResult().size() > 0) {
			Document doc = new Document();
			Element rootElement = new Element("rows");
			doc.addContent(rootElement);
			Element totalElement = new Element("total");
			totalElement.setText(total.toString());
			rootElement.addContent(totalElement);
			for (TradeModel trade : page.getResult()) {
				Element childElement = new Element("record");
				Element id = new Element("id");
				id.setText(trade.getTid());
				childElement.addContent(id);

				Element buyerNick = new Element("buyer_nick");
				buyerNick.setText(trade.getBuyerNick());
				childElement.addContent(buyerNick);

				Element status = new Element("status");
				status.setText(trade.getStatus());
				childElement.addContent(status);
				
				Element shippingType = new Element("shipping_type");
				shippingType.setText(trade.getShippingType());
				childElement.addContent(shippingType);

				Element payment = new Element("payment");
				payment.setText(trade.getPayment().toString());
				childElement.addContent(payment);

				Element postFee = new Element("post_fee");
				postFee.setText(trade.getPostFee().toString());
				childElement.addContent(postFee);

				Element payTime = new Element("pay_time");
				payTime.setText(DateUtil.format(trade.getPayTime(), "yyyy-MM-dd HH:mm"));
				childElement.addContent(payTime);

				rootElement.addContent(childElement);
			}
			XMLOutputter xml = new XMLOutputter();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml");
			response.getWriter().write(xml.outputString(doc));
			return null;
		}
		return null;
	}
	/**
	 * 已发货订单详情
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/sendorder/detail.do")
	public String detail(HttpServletRequest request, HttpServletResponse response) {
		String tid = request.getParameter("tid");
		TradeModel trade = tradeFacade.findById(tid);
		List<Shipping> list = shippingFacade.findByProperty("bossiniTrade", trade);
		Shipping shipping = new Shipping();
		if(list.size() > 0){
			shipping = list.get(0);
		}
		request.setAttribute("trade", trade);
		request.setAttribute("shipping", shipping);
		return "/send/sendorderdetail";
	}
	/**
	 * 已发货订单详情数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/sendorder/order.do")
	public String order(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tid = request.getParameter("tid");
		TradeModel trade = tradeFacade.findById(tid);
		Page<OrderModel> page = new Page<OrderModel>(20);
		
		page = orderFacade.findByProperty("trade", trade,page);
		if (page.getResult() != null && page.getResult().size() > 0) {
			Document doc = new Document();
			Element rootElement = new Element("rows");
			doc.addContent(rootElement);
			Element totalElement = new Element("total");
			totalElement.setText(page.getResult().size()+"");
			rootElement.addContent(totalElement);
			for (OrderModel obj : page.getResult()) {
				
				Element childElement = new Element("record");
				Element id = new Element("id");
				id.setText(obj.getPid().toString());
				childElement.addContent(id);
				
				Element picPath = new Element("picPath");
				picPath.setText(obj.getPicPath());
				childElement.addContent(picPath);
				
				Element title = new Element("title");
				//title.setText(obj.getName());
				childElement.addContent(title);
				
				Element price = new Element("price");
				price.setText(obj.getPrice().toString());
				childElement.addContent(price);

				Element outerIid = new Element("outerIid");
				outerIid.setText(obj.getOuterIid());
				childElement.addContent(outerIid);
				
				Element colorName = new Element("colorName");
				//colorName.setText(obj.getColorName());
				childElement.addContent(colorName);
				
				Element sizeName = new Element("sizeName");
				//sizeName.setText(obj.getSizeName());
				childElement.addContent(sizeName);

				Element num = new Element("num");
				num.setText(obj.getNum().toString());
				childElement.addContent(num);

				Element payment = new Element("payment");
				payment.setText(obj.getPayment().toString());
				childElement.addContent(payment);

				Element totalFee = new Element("totalFee");
				totalFee.setText(obj.getTotalFee().toString());
				childElement.addContent(totalFee);

				Element discountFee = new Element("discountFee");
				discountFee.setText(obj.getDiscountFee().toString());
				childElement.addContent(discountFee);
				
				Element adjustFee = new Element("adjustFee");
				adjustFee.setText(obj.getAdjustFee().toString());
				childElement.addContent(adjustFee);
				
				Element refundStatus = new Element("refundStatus");
				refundStatus.setText(obj.getRefundStatus());
				childElement.addContent(refundStatus);
				
				Element refundDesc = new Element("refundDesc");
				//refundDesc.setText(obj.getRefundDesc());
				childElement.addContent(refundDesc);

				rootElement.addContent(childElement);
			}
			XMLOutputter xml = new XMLOutputter();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml");
			response.getWriter().write(xml.outputString(doc));
			return null;
		}
		return null;
	}
	@RequestMapping("/sendorder/phipping.do")
	public String phipping(HttpServletRequest request, HttpServletResponse response) {
		return "/send/sendShipping";
	}
	/**
	 * 订单批量发货
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws TaobaoApiException
	 */
	@RequestMapping("/sendorder/sendPhipping.do")
	public String sendPhipping(HttpServletRequest request, HttpServletResponse response) throws IOException, TaobaoApiException {
		String jsonPhipping = request.getParameter("json");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");
		//判断是否有发货人资料
		List<AddressDatabase> addressList = addressFacade.findAll();
		if(addressList.size() <= 0){
			/*JSONObject json = new JSONObject();
			json.put("success", new JSONValue(false));
	    	json.put("errorMsg", "请填写发货地址,才能发货!");
			response.getWriter().write(json.toString());
			return null;*/
		}
		/*List<Object> jsonList = JsonUtil.getList4Json(jsonPhipping, SendShipping.class);
		List<SendShipping> sendShippingList = new ArrayList<SendShipping>();
		
		for(Object obj : jsonList){
			SendShipping sendShipping = (SendShipping)obj;
			sendShipping.setOutSid(sendShipping.getOutSid().trim());
			if(sendShipping.getOutSid().equals("")){
				JSONObject json = new JSONObject();
		    	json.put("success", false);
		    	json.put("errorMsg", "请检查是否有未填写物流单号的交易订单!");
				response.getWriter().write(json.toString());
				return null;
			}
			sendShippingList.add(sendShipping);
		}*/
		
		Map<String,String> successList = new HashMap<String,String>();
		Map<String,String> failList = new HashMap<String,String>();
		
		AddressDatabase addressDatabase = addressList.get(0);
		Shop tbuser = facade.findAll().get(0);
		TaobaoRestClient client = new TaobaoJsonRestClient(tbuser.getAppKey(), tbuser.getAppSecret());
		
		/*for(SendShipping sendShipping : sendShippingList){
			DeliverySendRequest req = new DeliverySendRequest();
			req.setTid(sendShipping.getId());     				 //交易IDtaobao.trades.sold.get
			req.setCompanyCode(sendShipping.getCompanyCode());   //可以通过taobao.logisticcompanies.get查询物流公司代码
			req.setOutSid(sendShipping.getOutSid().trim());  			 //运单号 具体一个物流公司的运单号码
			req.setOrderType("delivery_needed");
			req.setSellerAddress(addressDatabase.getAddress());  //卖家地址(详细地址)省、市、区不需要提供
			req.setSellerAreaId(addressDatabase.getAreaid());    //卖家所在地国家公布标准地区码通过taobao.areas.get获取
			req.setSellerMobile(addressDatabase.getMobile());  
			req.setSellerName(addressDatabase.getName());
			req.setSellerPhone(addressDatabase.getPhone());
			req.setSellerZip(addressDatabase.getZip());
			DeliverySendResponse result = client.deliverySend(req, tbuser.getSessionKey());
			if(result.isSuccess()){
				BossiniTrade trade = tradeFacade.findById(sendShipping.getId());
				if(result.isDeliverSuccess()){
					successList.put(sendShipping.getId(), "成功");
					trade.setStatus("WAIT_BUYER_CONFIRM_GOODS");
					trade.setConsignTime(new Date());
					tradeFacade.update(trade);
				}else{
					failList.put(sendShipping.getId(), result.getMsg());
				}
				List<Shipping> list = shippingFacade.findByProperty("bossiniTrade", trade);
				Shipping shipping = new Shipping();
				if(list.size() > 0){
					shipping = list.get(0);
				}
				shipping.setOutSid(sendShipping.getOutSid().trim());
				shipping.setBossiniTrade(trade);
				shipping.setCompanyCode(sendShipping.getCompanyCode());
				shipping.setDeliveryTime(new Date());
				shipping.setIsSuccess(result.isDeliverSuccess());
				shipping.setErrorMsg(result.getMsg());
				if(list.size() > 0){
					shippingFacade.update(shipping);
				}else{
					shippingFacade.save(shipping);
				}
				
			}else{
				failList.put(sendShipping.getId(), result.getMsg().split(":")[1]);
			}
		}*/
		
		JSONArray resultList = new JSONArray();
		Set<String> successKeys = successList.keySet();
		for(String key : successKeys){
			JSONObject json = new JSONObject();
			/*json.put("id", key);
	    	json.put("success", successList.get(key));
			resultList.add(json);*/
		}
		
		Set<String> failKeys = failList.keySet();
		for(String key : failKeys){
			/*JSONObject json = new JSONObject();
			json.put("id", key);
	    	json.put("success", false);
	    	json.put("errorMsg",failList.get(key));
			resultList.add(json);*/
		}
		
		/*JSONObject json = new JSONObject();
    	json.put("success", true);
		json.put("successSize", successKeys.size());
		json.put("failureSize", failKeys.size());
		json.put("resultList", resultList);
		response.getWriter().write(json.toString());*/
		return null;
	}
	
	/**
	 * 对已发货的订单退货处理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/sendorder/refund.do")
	public String refund(HttpServletRequest request, HttpServletResponse response) {
		String tid = request.getParameter("tid");
		TradeModel trade = tradeFacade.findById(tid);
		request.setAttribute("trade", trade);
		return "/send/sendorderrefund";
	}
	@RequestMapping("/sendorder/refundData.do")
	public String refundData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tid = request.getParameter("tid");
		TradeModel trade = tradeFacade.findById(tid);
		Page<OrderModel> page = new Page<OrderModel>(20);
		page = orderFacade.findByProperty("trade", trade,page);
		List<OrderModel>  result = page.getResult();
		if ( result != null && result.size() > 0) {
			Document doc = new Document();
			Element rootElement = new Element("rows");
			doc.addContent(rootElement);
			Element totalElement = new Element("total");
			totalElement.setText(result.size()+"");
			rootElement.addContent(totalElement);
			for (OrderModel obj : result) {
				
				Element childElement = new Element("record");
				Element id = new Element("id");
				id.setText(obj.getPid().toString());
				childElement.addContent(id);
				
				Element picPath = new Element("picPath");
				picPath.setText(obj.getPicPath());
				childElement.addContent(picPath);
				
				Element title = new Element("title");
				title.setText(obj.getTitle());
				childElement.addContent(title);

				Element outerIid = new Element("outerIid");
				outerIid.setText(obj.getOuterIid());
				childElement.addContent(outerIid);
				
				Element skuPropertiesName = new Element("skuPropertiesName");
				skuPropertiesName.setText(obj.getSkuPropertiesName());
				childElement.addContent(skuPropertiesName);
				
				Element refundStatus = new Element("refundStatus");
				refundStatus.setText(obj.getRefundStatus());
				childElement.addContent(refundStatus);
				
				Element refundDesc = new Element("refundDesc");
				//refundDesc.setText(obj.getRefundDesc());
				childElement.addContent(refundDesc);

				rootElement.addContent(childElement);
			}
			XMLOutputter xml = new XMLOutputter();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml");
			response.getWriter().write(xml.outputString(doc));
			return null;
		}
		return null;
	}
	@RequestMapping("/sendorder/refundSave.do")
	public String refundSave(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] oids = request.getParameter("ids").split(",");
		String refundDesc = request.getParameter("refundDesc");
		for(String oid : oids){
			OrderModel order = orderFacade.findById(Long.parseLong(oid));
			//order.setRefundDesc(refundDesc);
			//order.setRefundTime(new Date());
			order.setRefundStatus("WAIT_SELLER_CONFIRM_GOODS5");
			orderFacade.update(order); 
		}
		JSONObject json = new JSONObject();
		/*json.put("success", true);
		json.put("msg", "退货成功");*/
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");
		response.getWriter().write(json.toString());
		return null;
	}
}