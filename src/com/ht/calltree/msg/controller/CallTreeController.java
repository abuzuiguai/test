package com.ht.calltree.msg.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ht.calltree.sys.model.User;
import com.ht.calltree.util.CallTreeMessages;
import com.ht.calltree.util.CommonHeadJson;

/**
 * 
 * @author lianghu
 */
@Controller
@RequestMapping("/callTreeController")
public class CallTreeController {
	private Logger log = Logger.getLogger(CallTreeController.class);

	protected User loginUser(HttpServletRequest req) {
		Object obj = req.getSession().getAttribute("loginUser");
		if (obj == null) {
			log.info("无法获取登录用户信息!");
			return new User();
		}
		return (User) obj;
	}

	@RequestMapping(params = "errorNullParam")
	@ResponseBody
	public CommonHeadJson errorNullParam() {
		return CommonHeadJson
				.failedMessage(CallTreeMessages.ERROR_PARAM_IS_NULL);
	}

	@RequestMapping(params = "errorStringToJsonObj")
	@ResponseBody
	public CommonHeadJson errorStringToJsonObj() {
		return CommonHeadJson
				.failedMessage(CallTreeMessages.ERROR_JSON_STRING_TO_OBJ);
	}

	@RequestMapping(params = "errorJsonObjToString")
	@ResponseBody
	public CommonHeadJson errorJsonObjToString() {
		return CommonHeadJson
				.failedMessage(CallTreeMessages.ERROR_JSON_OBJ_TO_STRING);
	}

	protected Object execute(HttpServletRequest req, String param) {
		return null;
	}
}
