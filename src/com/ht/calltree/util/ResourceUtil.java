package com.ht.calltree.util;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

/**
 * Title: 上下文、资源文件处理工具 Description: 上下文、资源文件处理工具 Copyright: Copyright (c) 2003
 * Company:Hengtian Software Ltd.
 * 
 * @author huironglou(Fyeman)
 * @version 1.0
 */
public class ResourceUtil {
	private static ResourceBundle bundle = null;

	/**
	 * 获得请求路径
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestPath(HttpServletRequest request) {
		String requestPath = request.getRequestURI();
		// 去掉项目路径
		requestPath = requestPath
				.substring(request.getContextPath().length() + 1);
		return requestPath;
	}

	/**
	 * 获取配置文件参数
	 * 
	 * @param name
	 * @return
	 */
	public static final String getConfigByName(String name) {
		if (bundle == null) {
			bundle = java.util.ResourceBundle.getBundle("config");
		}
		return bundle.getString(name);
	}
}
