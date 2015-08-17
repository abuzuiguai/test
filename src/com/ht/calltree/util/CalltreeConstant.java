package com.ht.calltree.util;

/**Title: 常数类，定义系统常量 
* Description: 常数类，定义系统常量 
* Copyright: Copyright (c) 2003 
* Company:Hengtian Software Ltd. 
* @author huironglou(Fyeman) 
* @version 1.0 
*/
public class CalltreeConstant {
	//MIS对接URL
	public static final String MIS_AUTHENTICATION_URL = "http://172.16.129.19:9899/api/v1/auth";
	public static final String MIS_ALL_STAFF_URL = "http://172.16.129.19:9899/api/v1/users/allstaffproject";
	//天天对接URL
	public static final String TT_SEND_URL = "http://172.16.128.142:9090/plugins/offlinepush/remoteinterface";
	
	public static final String POST_CONTENT_TYPE_JSON = "application/json";
	
	//通用类别
	public static final String COMMON_FALSE	= "0";
	public static final String COMMON_TRUE	= "1";
	
	//移动端数据读取成功
	public static final String RETURN_CODE_SUCCESS = "0";
	public static final String RETURN_CODE_FAILED = "1";
	
	//Call Tree发送状态
	public static final String CALLTREE_STATUS_NOSEND = "0";
	public static final String CALLTREE_STATUS_SENDING = "1";
	public static final String CALLTREE_STATUS_SENDOVER = "99";
	
	//Call Tree回复状态
	public static final String CALLTREE_DETAIL_STATUS_NOREPLY = "0";
	public static final String CALLTREE_DETAIL_STATUS_TTREPLY = "1";
	public static final String CALLTREE_DETAIL_STATUS_PHONEREPLY = "2";
	
	
	public static final String AUTH_USER_NAME	= "huironglou";
	public static final String AUTH_PASSWORD	= "Louhr@11212014";
	public static final int AUTH_AGE	= 1800000;
	
	public static final int SEND_TIME_ABORT	= 5;
}
