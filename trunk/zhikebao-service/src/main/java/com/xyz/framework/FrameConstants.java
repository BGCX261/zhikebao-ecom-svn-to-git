package com.xyz.framework;

public class FrameConstants {
	
	//系统默认的POJO属性
    public final static String SYS_POJO_ID= "id";
    public final static String SYS_POJO_DE= "dateEntered";  //创建时间
    public final static String SYS_POJO_DM= "dateModified"; //最后修改时间
    public final static String SYS_POJO_MU= "modifiedUserId";//修改人
    public final static String SYS_POJO_CU= "createdBy";     //创建人
    public final static String SYS_POJO_DD= "deleted";       //是否已删除
    //常量值
    public final static boolean SYS_TRUE=  true;       //是否已删除
    public final static boolean SYS_FALSE= false;       //是否已删除
    
    //WebApp ContextRoot
    public final static String SYS_CONTEXT_ROOT = "/"; 
    
    public final static String SYS_MENU_ROOT = "1";  //所有菜单的根parentId
    
    public final static String SYS_TAOBAO_SERVICE_URL = "http://gw.api.tbsandbox.com/router/rest";

}
