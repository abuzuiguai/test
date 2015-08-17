package com.ht.calltree.interceptors;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import jodd.util.StringUtil;

import org.springframework.core.convert.converter.Converter;

/**
 * Title: 日期格式化
 * Company:Hengtian Software Ltd.
 * 
 * @author huironglou(Fyeman)
 * @version 1.0
 */
public class DateConverter implements Converter<String, Date> {  
	
	public Date convert(String source) {  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);  
	    dateFormat.setLenient(false);  
	    try {  
	    	if (StringUtil.isEmpty(source)) {
	    		return null;
	    	}
	        return dateFormat.parse(source);  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }         
	    return null;  
	}
}