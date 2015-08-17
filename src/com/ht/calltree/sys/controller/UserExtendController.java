package com.ht.calltree.sys.controller;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ht.calltree.common.model.MobileReturn;
import com.ht.calltree.sys.model.UserExtend;
import com.ht.calltree.sys.service.UserExtendService;
import com.ht.calltree.util.CallTreeMessages;
import com.ht.calltree.util.CommonHeadJson;

@Controller
@RequestMapping("/sys/userex")
public class UserExtendController {

	@Autowired
	private UserExtendService userExtendService;

	private Logger log = Logger.getLogger(UserExtendController.class);

	/**
	 * Description :移动端设置Backup信息
	 * 
	 * @param HttpServletRequest
	 *            req http请求对象
	 * @param String
	 *            param
	 *            传入参数，格式{"staffId":"123","backupId":"234","backupFromId":"12"
	 *            ,"sendObjectId":"??"}
	 * @return Call Tree 列表数据，数据格式： { "success": "true", "returnCode": "0",
	 *         "message": "成功" }
	 */
	@RequestMapping("/getBackup")
	@ResponseBody
	public CommonHeadJson getBackup(HttpServletRequest req, String param) {
		MobileReturn<UserExtend> mobileUserExtend = null;
		try {
			if (!StringUtils.hasLength(param)) {
				return CommonHeadJson.failedMessage("传入参数param不能为空");
			}
			JSONObject jsonObject = JSONObject.fromObject(param);
			if (jsonObject == null) {
				return CommonHeadJson.failedMessage("传入参数param格式不正确");
			}
			String staffId = jsonObject.getString("staffId");
			if (!StringUtils.hasLength(staffId)) {
				return CommonHeadJson.failedMessage("传入参数staffId不能为空");
			}

			mobileUserExtend = new MobileReturn<UserExtend>();
			UserExtend result = userExtendService.getBackup(staffId);
			if (result != null) {
				mobileUserExtend.setResult(result);
			}
			return mobileUserExtend;
		} catch (Exception e) {
			log.info("获取Backup数据出错", e);
			return CommonHeadJson.failedMessage("获取Backup数据出错");
		}
	}

	/**
	 * Description :移动端设置Backup信息
	 * 
	 * @param HttpServletRequest
	 *            req http请求对象
	 * @param String
	 *            param
	 *            传入参数，格式{"staffId":"123","backupId":"234","backupFromId":"12"
	 *            ,"sendObjectId":"??"}
	 * @return Call Tree 列表数据，数据格式： { "success": "true", "returnCode": "0",
	 *         "message": "成功" }
	 */
	@RequestMapping("/setBackup")
	@ResponseBody
	public CommonHeadJson setBackup(HttpServletRequest req, String param) {
		JSONObject jsonObject = JSONObject.fromObject(param);
		UserExtend content = (UserExtend) JSONObject.toBean(jsonObject,
				UserExtend.class);
		return setBackup(req, content);
	}

	/**
	 * Description :动态方法调用，移动端设置Backup信息
	 * 
	 * @param HttpServletRequest
	 *            req http请求对象
	 * @param Content
	 *            content calltree用户扩展对象
	 * @return Call Tree 列表数据，数据格式： { "success": "true", "returnCode": "0",
	 *         "message": "成功", "result": { 列表数据 } }
	 */
	public CommonHeadJson setBackup(HttpServletRequest req, UserExtend content) {
		try {
			if (userExtendService.setBackup(content)) {
				return CommonHeadJson.successMessage();
			} else {
				return CommonHeadJson
						.failedMessage(CallTreeMessages.ERROR_SET_BACKUP);
			}
		} catch (Exception e) {
			log.info(CallTreeMessages.ERROR_SET_BACKUP, e);
			return CommonHeadJson
					.failedMessage(CallTreeMessages.ERROR_SET_BACKUP);
		}
	}
}
