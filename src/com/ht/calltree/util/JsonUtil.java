package com.ht.calltree.util;

import java.util.Map;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.log4j.Logger;

/**
 * Title: Json处理工具类 Description: Json处理工具类 Copyright: Copyright (c) 2003
 * Company:Hengtian Software Ltd.
 * 
 * @author huironglou(Fyeman)
 * @version 1.0
 */
public class JsonUtil {
	private static Logger log = Logger.getLogger(JsonUtil.class);

	public static String objToJsonString(Object obj) {
		if (obj == null) {
			return null;
		}
		try {
			String[] dateFormats = new String[] { "yyyy-MM-dd HH:mm:ss" };
			JSONUtils.getMorpherRegistry().registerMorpher(
					new DateMorpher(dateFormats));

			JSONObject jsonObject = JSONObject.fromObject(obj);
			return jsonObject.toString();
		} catch (Exception e) {
			return null;
		}
	}

	public static Object stringToJsonObject(String jsonString, Class type) {
		try {
			String[] dateFormats = new String[] { "yyyy-MM-dd HH:mm:ss" };
			JSONUtils.getMorpherRegistry().registerMorpher(
					new DateMorpher(dateFormats));

			JSONObject jsonObject = JSONObject.fromObject(jsonString);
			return JSONObject.toBean(jsonObject, type);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Object stringToJsonList(String jsonString, Class type, Map map) {
		try {
			String[] dateFormats = new String[] { "yyyy-MM-dd HH:mm:ss" };
			JSONUtils.getMorpherRegistry().registerMorpher(
					new DateMorpher(dateFormats));

			JSONObject jsonObject = JSONObject.fromObject(jsonString);
			return JSONObject.toBean(jsonObject, type, map);
		} catch (Exception e) {
			return null;
		}
	}
}
