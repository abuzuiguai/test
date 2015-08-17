package com.ht.calltree.interceptors;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.util.StringUtil;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ht.calltree.util.CallTreeMessages;
import com.ht.calltree.util.JsonUtil;
import com.ht.calltree.util.ResourceUtil;

/**
 * Title: Mobile接口访问参数拦截 Description: Mobile接口访问参数拦截 Copyright: Copyright (c)
 * 2003 Company:Hengtian Software Ltd.
 * 
 * @author huironglou(Fyeman)
 * @version 1.0
 */
public class ParamInterceptor implements HandlerInterceptor {
	private List<String> includeUrls;

	private static final Logger log = Logger.getLogger(ParamInterceptor.class);

	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse res, Object object, Exception exception)
			throws Exception {
	}

	public void postHandle(HttpServletRequest req, HttpServletResponse res,
			Object object, ModelAndView modelAndView) throws Exception {

	}

	/**
	 * Description :拦截页面请求，处理传入参数
	 * 
	 * @param HttpServletRequest
	 *            req http请求对象
	 * @param HttpServletResponse
	 *            res 返回请求对象
	 * @param Object
	 *            object
	 * @return
	 */
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object object) throws Exception {
		String requestPath = ResourceUtil.getRequestPath(req);
		if (requestPath.indexOf("/") < 0) {
			return true;
		}
		// 截取最后调用方法
		requestPath = requestPath.substring(requestPath.lastIndexOf("/") + 1);
		String regEx = "[a-zA-Z0-9]*";
		String temp;
		String prefix = "";
		String suffix = "";

		try {
			// 所有满足定义规则的连接都需要进行拦截
			for (String includeUrl : includeUrls) {
				if (StringUtil.isEmpty(includeUrl)) {
					continue;
				}
				// 如果定义链接相同，直接进行拦截
				if (includeUrl.indexOf("*") < 0) {
					if (includeUrl.equals(requestPath)) {
						return urlHandle(req, res);
					}
				} else {
					// 模糊匹配 如mobile*.do，但不拦截多个*号匹配符链接
					temp = includeUrl.replace("*", "");
					if (temp.indexOf("*") >= 0) {
						return true;
					}
					prefix = includeUrl.substring(0, includeUrl.indexOf("*"));
					suffix = includeUrl.substring(includeUrl.indexOf("*") + 1);
					StringBuffer sb = new StringBuffer(prefix);
					sb.append(regEx);
					sb.append(suffix);
					Pattern pat = Pattern.compile(sb.toString());
					Matcher mat = pat.matcher(requestPath);
					boolean rs = mat.find();
					// 匹配则进行拦截
					if (rs) {
						return urlHandle(req, res);
					}
				}
			}
		} catch (Exception e) {
			log.info("拦截器出错！", e);
		}
		return true;
	}

	private boolean urlHandle(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		// 所有mobile
		String contentPath = req.getContextPath();
		String param = req.getParameter("param");
		if (StringUtil.isEmpty(param)) {
			StringBuffer sb = new StringBuffer(contentPath);
			sb.append("/callTreeController.do?errorNullParam");
			res.sendRedirect(sb.toString());
			log.info(CallTreeMessages.ERROR_PARAM_IS_NULL);
			return false;
		}

		Object obj = JsonUtil.stringToJsonObject(param, Object.class);
		if (obj == null) {
			StringBuffer sb = new StringBuffer(contentPath);
			sb.append("/callTreeController.do?errorStringToJsonObj");
			res.sendRedirect(sb.toString());
			log.info(CallTreeMessages.ERROR_PARAM_CONVERT);
			return false;
		}
		return true;
	}

	public List<String> getIncludeUrls() {
		return includeUrls;
	}

	public void setIncludeUrls(List<String> includeUrls) {
		this.includeUrls = includeUrls;
	}
}
