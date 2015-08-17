package com.ht.calltree.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ht.calltree.common.model.MobileReturn;
import com.ht.calltree.msg.controller.ContentController;
import com.ht.calltree.sys.model.User;
import com.ht.calltree.sys.model.UserWrapper;
import com.ht.calltree.sys.service.UserService;
import com.ht.calltree.util.CalltreeConstant;
import com.ht.calltree.util.JsonUtil;

@Controller
@RequestMapping("/sys/user")
public class UserController {
	@Autowired
	private UserService userService;

	private Logger log = Logger.getLogger(ContentController.class);

	/**
	 * PC查询所有人员信息
	 * 
	 * @param req
	 *            request请求对象
	 * @return 返回所有人员信息
	 */
	@RequestMapping(params = "listData")
	public String listData(HttpServletRequest req) {
		List<User> users = userService.listData();
		req.setAttribute("list", users);
		return "sys/user/data";
	}

	/**
	 * PC查询指定staff ID下所有人员信息
	 * 
	 * @param req
	 *            request请求对象
	 * @return staff ID下所有人员信息
	 */
	@RequestMapping(params = "listPcChildrenData")
	public String listPcChildrenData(HttpServletRequest req, String staffId) {
		List<UserWrapper> users = userService.listChildrenUser(staffId);
		req.setAttribute("list", users);
		return "sys/user/data";
	}

	/**
	 * 返回传入staffId所有下属人员信息
	 * 
	 * @param staffId
	 * @return 传入staffId下所有人员信息，数据格式： { "success": "true", "returnCode": "0",
	 *         "message": "成功", "result": { 列表数据 } }
	 */
	@RequestMapping("/mobileChildrenListData")
	@ResponseBody
	public MobileReturn<List<UserWrapper>> mobileChildrenListData(String param) {
		User user = (User) JsonUtil.stringToJsonObject(param, User.class);
		MobileReturn<List<UserWrapper>> mobileUser = new MobileReturn<List<UserWrapper>>();
		List<UserWrapper> users = null;
		try {
			users = userService.listChildrenUser(user.getStaffId());
			mobileUser.setResult(users);
			return mobileUser;
		} catch (Exception e) {
			log.info("获取下属人员信息出错！", e);
			mobileUser.setSuccess(false);
			mobileUser.setReturnCode(CalltreeConstant.COMMON_TRUE);
			mobileUser.setMessage("获取下属人员信息出错！");
			return mobileUser;
		}
	}
}
