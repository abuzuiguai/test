package com.ht.calltree.msg.util;

import java.util.Calendar;
import java.util.Date;

public class CallTreeUtil {
	/**
	 * 计算下次发送时间
	 * 
	 * @param content
	 * @return
	 */
	public static Date nextSendTime(Date sendTime, int sendInterVal) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(sendTime);
		cal.add(Calendar.MINUTE, sendInterVal);
		return cal.getTime();
	}
	/**
	 * 计算Call Tree发送结束时间
	 * 
	 * @param content
	 * @return
	 */
	public static Date calEndSendTime(Date sendTime, int sendInterVal, int sendNum) {
		int interval = sendInterVal * (sendNum - 1);

		Calendar cal = Calendar.getInstance();
		cal.setTime(sendTime);
		cal.add(Calendar.MINUTE, interval);

		return cal.getTime();
	}
}
