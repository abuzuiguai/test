package com.ht.calltree.inter.util;

import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.ht.calltree.util.CallTreeMessages;
import com.ht.calltree.util.CalltreeConstant;
import com.ht.calltree.util.JsonUtil;

public class HttpApi {

	private Logger log = Logger.getLogger(HttpApi.class);

	private String token;

	public HttpApi() {

	}

	public HttpApi(String token) {
		this.token = token;
	}

	/**
	 * Description : HttpPost方式访问远程URL
	 * 
	 * @param httpUrl
	 *            URL
	 * @param obj
	 *            post方式传入参数对象
	 * @param type
	 *            返回Json构造对象类
	 * @return post访问返回数据对象
	 */
	public Object doPost(String httpUrl, Object obj, Class type) {
		try {
			/* post向服务器请求数据 */
			HttpPost request = new HttpPost(httpUrl);
			StringEntity se = new StringEntity(JsonUtil.objToJsonString(obj),
					HTTP.UTF_8);
			se.setContentType(CalltreeConstant.POST_CONTENT_TYPE_JSON);
			se.setContentEncoding("utf-8");
			request.setEntity(se);
			HttpResponse response = new DefaultHttpClient().execute(request);
			int code = response.getStatusLine().getStatusCode();
			log.info(CallTreeMessages.INFO_HTTPCLIENT_POST + code);
			HttpEntity entity = response.getEntity();
			String respContent = EntityUtils.toString(entity, "UTF-8").trim();
			return JsonUtil.stringToJsonObject(respContent, type);
		} catch (Exception e) {
			log.info(CallTreeMessages.ERROR_HTTPCLIENT_POST, e);
			return null;
		}

	}

	/**
	 * Description : HttpGet方式访问远程URL
	 * 
	 * @param httpUrl
	 *            URL
	 * @param type
	 *            返回Json构造对象类
	 * @return get访问返回数据对象
	 */
	public Object doGet(String httpUrl, Class type) {
		try {
			/* get向服务器请求数据 */
			HttpGet request = new HttpGet(httpUrl);
			request.setHeader("mis-staff-token", token);
			HttpResponse response = new DefaultHttpClient().execute(request);
			int code = response.getStatusLine().getStatusCode();
			log.info(CallTreeMessages.INFO_HTTPCLIENT_GET + code);

			HttpEntity entity = response.getEntity();
			String respContent = EntityUtils.toString(entity, "UTF-8").trim();
			return JsonUtil.stringToJsonObject(respContent, type);
		} catch (Exception e) {
			log.info(CallTreeMessages.ERROR_HTTPCLIENT_GET, e);
			return null;
		}

	}

	/**
	 * Description : HttpGet方式访问远程URL
	 * 
	 * @param httpUrl
	 *            URL
	 * @param type
	 *            返回Json构造对象类
	 * @return get访问返回数据对象
	 */
	public Object doGetList(String httpUrl, Class type, Map map) {
		try {
			/* get向服务器请求数据 */
			HttpGet request = new HttpGet(httpUrl);
			request.setHeader("mis-staff-token", token);
			HttpResponse response = new DefaultHttpClient().execute(request);
			int code = response.getStatusLine().getStatusCode();
			log.info(CallTreeMessages.INFO_HTTPCLIENT_GET + code);

			HttpEntity entity = response.getEntity();
			String respContent = EntityUtils.toString(entity, "UTF-8").trim();
			return JsonUtil.stringToJsonList(respContent, type, map);
		} catch (Exception e) {
			log.info(CallTreeMessages.ERROR_HTTPCLIENT_GET, e);
			return null;
		}

	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
