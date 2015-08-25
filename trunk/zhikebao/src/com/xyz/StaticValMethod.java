package com.xyz;

public class StaticValMethod {

	public static String METHOD_TAOBAO_USER_GET = "taobao.user.get";
	public static String FIELDS_TAOBAO_USER_GET = "user_id,nick,sex,buyer_credit,seller_credit,location.city,location.state,location.country,created,last_visit,location.zip,birthday,type,has_more_pic,item_img_num,item_img_size,prop_img_num,prop_img_size,auto_repost,promoted_type,status,alipay_bind,consumer_protection";

    public static String METHOD_TAOBAO_SHOP_GET = "taobao.shop.get";
	public static String FIELDS_TAOBAO_SHOP_GET = "sid,cid,nick,title,desc,bulletin,pic_path,create,modified";
	                                               
	public static String METHOD_TAOBAO_ITEMS_ONSALE_GET = "taobao.items.onsale.get";
	public static String FIELDS_TAOBAO_ITEMS_ONSALE_GET = "approve_status,iid,num_iid,title,nick,type,cid,pic_path,num,props,valid_thru,list_time,price,has_discount,has_invoice,has_warranty,has_showcase,modified,delist_time,postage_id,seller_cids,outer_id";


	public static String METHOD_TAOBAO_TRADERATES_GET = "taobao.traderates.get";
	public static String FIELDS_TAOBAO_TRADERATES_GET = "content,created,item_price,nick,item_title,reated_nick;result,reply,role,tid,order_id";

	public static String METHOD_TAOBAO_ITEM_GET = "taobao.item.get";
	public static String FIELDS_TAOBAO_ITEM_GET = "iid,detail_url,num_iid,title,nick,type,cid,seller_cids,props,input_pids,input_str,desc,pic_path,num,valid_thru,list_time,delist_time,stuff_status,location,price,post_fee,express_fee,ems_fee,has_discount,freight_payer,has_invoice,has_warranty,has_showcase,modified,increment,auto_repost,approve_status,postage_id,product_id,auction_point,property_alias,itemimg.itemimg_id, itemimg.url, itemimg.position,propimg.propimg_id,propimg.url,propimg.properties, propimg.position,sku.sku_id,sku.properties, sku.quantity, sku.price, sku.outer_id, sku.created, sku.modified,outer_id,is_virtural,is_taobao,is_ex";

	public static String METHOD_TAOBAO_ITEMS_GET = "taobao.items.get";
	public static String FIELDS_TAOBAO_ITEMS_GET = "iid,title,nick,pic_path,cid,price,type,delist_time,post_fee";

	public static String METHOD_TAOBAO_TRADES_SOLD_GET = "taobao.trades.sold.get";
	public static String FIELDS_TAOBAO_TRADES_SOLD_GET = "tid,modified,created";
	
	public static String FIELDS_TAOBAO_TRADES_SOLD_GET_TRADE = "seller_nick,buyer_nick,title,type,created,sid,tid,seller_rate,buyer_rate,status,payment,discount_fee,adjust_fee,post_fee,total_fee,pay_time,end_time,modified,consign_time,buyer_obtain_point_fee,point_fee,real_point_fee,received_payment,commission_fee,alipay_no,buyer_message,pic_path,iid,num,price,cod_fee,cod_status,shipping_type";
	
	public static String FIELDS_TAOBAO_TRADES_SOLD_GET_ORDERS = "orders.title, orders.pic_path, orders.price, orders.num, orders.iid, orders.sku_id, orders.refund_status, orders.status, orders.tid, orders.total_fee, orders.payment, orders.discount_fee, orders.adjust_fee, orders.sku_properties_name, orders.item_meal_name";
	
	public static String FIELDS_TAOBAO_TRADES_SOLD_GET_FULL_TRADE = "seller_nick,buyer_nick,title,type,created,sid,tid,seller_rate,buyer_rate,status,payment,discount_fee,adjust_fee,post_fee,total_fee,pay_time,end_time,modified,consign_time,buyer_obtain_point_fee,point_fee,real_point_fee,received_payment,commission_fee,buyer_memo,seller_memo,alipay_no,buyer_message,pic_path,iid,num,price,buyer_alipay_no,receiver_name,receiver_state,receiver_city,receiver_district,receiver_address,receiver_zip,receiver_mobile,receiver_phone,buyer_email,seller_alipay_no,seller_mobile,seller_phone,seller_name,seller_email,available_confirm_fee,has_postFee,timeout_action_time,snapshot_url,cod_fee,cod_status,shipping_type,trade_memo,is_3D";
	
	public static String METHOD_TAOBAO_TRADE_GET = "taobao.trade.get";
	public static String FIELDS_TAOBAO_TRADE_GET = "seller_nick,buyer_nick,title,type,created,buyer_message,sid,tid,seller_rate,buyer_rate,status,payment,discount_fee,adjust_fee, post_fee,total_fee,pay_time,end_time,modified,consign_time,buyer_obtain_point_fee,point_fee,real_point_fee, received_payment,commission_fee,buyer_memo,seller_memo,alipay_no,pic_path,iid,num,price,cod_fee,shipping_type";

	public static String METHOD_TAOBAO_REFUNDS_APPLY_GET = "taobao.refunds.apply.get";
	public static String FIELDS_TAOBAO_REFUNDS_APPLY_GET = "refund_id,tid,title,buyer_nick,seller_nick,total_fee,status,created,refund_fee";

	public static String METHOD_TAOBAO_REFUNDS_RECEIVE_GET = "taobao.refunds.receive.get";
	public static String FIELDS_TAOBAO_REFUNDS_RECEIVE_GET = "refund_id,alipay_no,tid,oid,buyer_nick,seller_nick,total_fee,status,created,refund_fee,good_status,has_good_return,payment,reason,desc,iid,title,price,num,good_return_time,company_name,sid,address";
	public static String FIELDS_TAOBAO_REFUNDS_RECEIVE_BASE_GET = "refund_id,modified";

	public static String METHOD_TAOBAO_REFUND_GET = "taobao.refund.get";
	public static String FIELDS_TAOBAO_REFUND_GET = "refund_id,alipay_no,tid,oid,buyer_nick,seller_nick,total_fee,status,created,refund_fee,good_status,has_good_return,payment,reason,desc,iid,title,price,num,good_return_time,company_name,sid,address";

	public static String FIELDS_TAOBAO_AREAS_GET = "area_id,area_type,area_name,parent_id,zip";
}

