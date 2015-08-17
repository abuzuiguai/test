package com.ht.calltree.inter.tt;

import java.util.Date;

import org.apache.log4j.Logger;

import com.ht.calltree.inter.tt.bean.TTMessage;
import com.ht.calltree.inter.util.HttpApi;
import com.ht.calltree.util.CalltreeConstant;

public class TTJob {
	private static Logger log = Logger.getLogger(TTJob.class);

	public static void send(String msg, String alias) {
		HttpApi mis = new HttpApi();

		StringBuffer sb = new StringBuffer(CalltreeConstant.TT_SEND_URL);
		sb.append("?key=pug9tbzjanpvdmbg&secret=hs1jywvn8sspx2tq");

		TTMessage tm = new TTMessage();
		tm.setAlias(alias);
		tm.setMsg(msg);
		Object returnObj = mis.doPost(sb.toString(), tm, Object.class);
		// if (returnObj == null) {
		// return;
		// }
		log.info("消息推送成功，内容：" + msg + "，推送时间：" + new Date());
	}
}
