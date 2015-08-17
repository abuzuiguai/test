package com.ht.calltree.util;

/**
 * Title: 常数类，定义系统提示信息 Description: 常数类，定义系统提示信息 Copyright: Copyright (c) 2003
 * Company:Hengtian Software Ltd.
 * 
 * @author huironglou(Fyeman)
 * @version 1.0
 */
public class CallTreeMessages {
	public static final String ERROR_PARAM_IS_NULL = "传入参数为Null或空！";
	public static final String ERROR_PARAM_CONVERT = "传入参数格式有误！";
	public static final String ERROR_STAFF_ID_IS_NULL = "staffId为Null或空！";

	public static final String ERROR_NO_METHOD = "执行方法出错，不存在该方法或执行方法出错！";

	public static final String ERROR_CALLTREE_DETAIL_UPDATE = "更新发送日志信息出错!";
	public static final String ERROR_CALLTREE_REPLY_LIST = "获取动态回复信息出错!";
	public static final String ERROR_HTTPCLIENT_POST = "http post 请求出错!";
	public static final String ERROR_HTTPCLIENT_GET = "http get 请求出错!";

	public static final String ERROR_JSON_STRING_TO_OBJ = "字符串转JSON对象出错！";
	public static final String ERROR_JSON_OBJ_TO_STRING = "对象转JSON字符串出错！";

	public static final String INFO_HTTPCLIENT_POST = "请求返回码----";
	public static final String INFO_HTTPCLIENT_GET = "请求返回码----";
	public static final String INFO_MIS_JOB_RETURN_NULL = "权限认证失败!";

	public static final String ERROR_CALLTREE_LIST = "获取Call Tree列表数据出错！";
	public static final String ERROR_CALLTREE_INFO = "获取Call Tree内容信息出错！";
	public static final String ERROR_CALLTREE_TIME_EXIST = "该时间段内以后Call Tree信息！";
	public static final String ERROR_CALLTREE_SAVE = "保存Call Tree数据出错！";
	public static final String ERROR_CALLTREE_DEL = "删除Call Tree失败！";
	public static final String ERROR_CALLTREE_SENT = "已发送Call Tree不允许编辑、删除！";
	public static final String ERROR_CALLTREE_CONTENT_ID_ISNULL = "Call Tree ID为Null或空！";
	public static final String ERROR_CALLTREE_SEND_TIME_OUT = "发送时间已过，请重现设置！";
	public static final String ERROR_CALLTREE_END_TIME_OUT = "结束时间已过，请重现设置！";
	public static final String ERROR_CALLTREE_CAL_END_TIME_OUT = "最后一次发送时间晚于结束时间，请重新设置！";
	public static final String ERROR_SET_BACKUP = "设置Backup出错！";
}
