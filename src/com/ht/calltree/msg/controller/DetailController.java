package com.ht.calltree.msg.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ht.calltree.common.model.MobileReturn;
import com.ht.calltree.inter.tt.TTJob;
import com.ht.calltree.msg.model.DetailWrapper;
import com.ht.calltree.msg.model.back.MobileReplyDetail;
import com.ht.calltree.msg.model.back.MobileReplyList;
import com.ht.calltree.msg.model.param.ParamDetail;
import com.ht.calltree.msg.service.DetailService;
import com.ht.calltree.sys.model.User;
import com.ht.calltree.util.CallTreeMessages;
import com.ht.calltree.util.CalltreeConstant;
import com.ht.calltree.util.CommonHeadJson;
import com.ht.calltree.util.JsonUtil;

/**
 * Title: 发送日志处理 Company:Hengtian Software Ltd.
 * 
 * @author huironglou(Fyeman)
 * @version 1.0
 */
@Controller
@RequestMapping("/msg/detail")
public class DetailController extends CallTreeController {

	@Autowired
	private DetailService detailService;

	private Logger log = Logger.getLogger(ContentController.class);

	/**
	 * Description :执行指定方法
	 * 
	 * @param HttpServletRequest
	 *            req http请求对象
	 * @param String
	 *            param 传入参数，格式{"funcitonName":"execute",
	 *            "detail":{"contentId":2,"staffId":"140669"}}
	 * @return Object Json对象
	 */
	@RequestMapping
	@ResponseBody
	public Object execute(HttpServletRequest req, String param) {
		ParamDetail paramDetail = (ParamDetail) JsonUtil.stringToJsonObject(
				param, ParamDetail.class);
		try {
			return this
					.getClass()
					.getMethod(
							paramDetail.getFunctionName(),
							new Class[] { HttpServletRequest.class,
									DetailWrapper.class })
					.invoke(this,
							new Object[] { req, paramDetail.getDetailWrapper() });
		} catch (Exception e) {
			log.info(
					CallTreeMessages.ERROR_NO_METHOD
							+ paramDetail.getFunctionName(), e);
			return CommonHeadJson
					.failedMessage(CallTreeMessages.ERROR_NO_METHOD
							+ paramDetail.getFunctionName());
		}
	}

	/**
	 * Description : 获取人员回复明细
	 * 
	 * @param req
	 *            request请求对象
	 * @param param
	 *            calltree主键id和登录员工id
	 * @return 成功或失败信息 { "success": "true", "returnCode": "0", "message": "成功" }
	 */
	@RequestMapping("/mobileReplyDetail")
	@ResponseBody
	public Object mobileReplyDetail(HttpServletRequest req, String param) {
		DetailWrapper detail = (DetailWrapper) JsonUtil.stringToJsonObject(
				param, DetailWrapper.class);
		return mobileReplyDetail(req, detail);
	}

	public Object mobileReplyDetail(HttpServletRequest req, DetailWrapper detail) {
		try {
			if (detail == null) {
				throw new Exception();
			}

			MobileReturn<MobileReplyDetail> mobileReturn = new MobileReturn<MobileReplyDetail>();
			mobileReturn.setResult(detailService.getMobileReplyDetail(
					detail.getContentId(), detail.getStaffId()));
			return mobileReturn;
		} catch (Exception e) {
			log.info(CallTreeMessages.ERROR_CALLTREE_DETAIL_UPDATE, e);
			return CommonHeadJson
					.failedMessage(CallTreeMessages.ERROR_CALLTREE_DETAIL_UPDATE);
		}
	}

	/**
	 * Description : 更新人员回复明细
	 * 
	 * @param req
	 *            request请求对象
	 * @param param
	 *            calltree主键id和登录员工id
	 * @return 成功或失败信息 { "success": "true", "returnCode": "0", "message": "成功" }
	 */
	@RequestMapping("/mobileReplyUpdate")
	@ResponseBody
	public CommonHeadJson mobileReplyUpdate(HttpServletRequest req, String param) {
		DetailWrapper detail = (DetailWrapper) JsonUtil.stringToJsonObject(
				param, DetailWrapper.class);
		return mobileReplyUpdate(req, detail);
	}

	public CommonHeadJson mobileReplyUpdate(HttpServletRequest req,
			DetailWrapper detail) {
		try {
			if (detail == null) {
				throw new Exception();
			}
			detailService.update(detail);
		} catch (Exception e) {
			log.info(CallTreeMessages.ERROR_CALLTREE_DETAIL_UPDATE, e);
			return CommonHeadJson
					.failedMessage(CallTreeMessages.ERROR_CALLTREE_DETAIL_UPDATE);
		}
		return CommonHeadJson.successMessage();
	}

	/**
	 * Description : 获取指定staffId下所有人员回去统计信息.
	 * 
	 * @param req
	 *            request请求对象
	 * @param param
	 *            param={"contentId":2,"staffId":"140000"}
	 * @return 指定staffId下所有人员回去统计信息 列表数据，数据格式： { "success": "true",
	 *         "returnCode": "0", "message": "成功", "result": { 列表数据 } }
	 */
	@RequestMapping("/mobileReplyList")
	@ResponseBody
	public Object mobileReplyList(HttpServletRequest req, String param) {
		DetailWrapper detail = (DetailWrapper) JsonUtil.stringToJsonObject(
				param, DetailWrapper.class);
		return mobileReplyList(req, detail);
	}

	public Object mobileReplyList(HttpServletRequest req, DetailWrapper detail) {
		try {
			MobileReturn<MobileReplyList> mobileReturn = new MobileReturn<MobileReplyList>();

			MobileReplyList mobileReplyList = detailService.getMobileReplyList(
					detail.getContentId(), detail.getStaffId(),
					detail.getIsBackup());

			mobileReturn.setResult(mobileReplyList);
			mobileReturn.setMessage("成功");
			mobileReturn.setReturnCode(CalltreeConstant.RETURN_CODE_SUCCESS);
			return mobileReturn;
		} catch (Exception e) {
			log.info(CallTreeMessages.ERROR_CALLTREE_REPLY_LIST, e);
			return CommonHeadJson
					.failedMessage(CallTreeMessages.ERROR_CALLTREE_REPLY_LIST);
		}
	}

	/**
	 * 返回传入staffId所有下属人员信息.
	 * 
	 * @param staffId
	 * @return 传入staffId下所有人员信息，数据格式： { "success": "true", "returnCode": "0",
	 *         "message": "成功", "result": { 列表数据 } }
	 */
	@RequestMapping("/httptest")
	@ResponseBody
	public MobileReturn<List<User>> httptest(HttpServletRequest req,
			DetailWrapper detail) {
		try {
			TTJob.send("这是测试", "huironglou");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * PC端获取Call Tree发送日志信息.
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "listData")
	public String listData(HttpServletRequest req, Integer contentId) {
		// List<DetailWrapper> details = detailService.listData();
		// req.setAttribute("list", details);
		return "msg/detail/data";
	}

	/**
	 * 
	 * @return Call Tree 发送日志数据，数据格式： { "success": "true", "returnCode": "0",
	 *         "message": "成功", "result": { 列表数据 } }
	 */
	// @RequestMapping(params = "listMobileData")
	// @ResponseBody
	// public MobileDetail listMobileData() {
	// MobileDetail mobileDetail = new MobileDetail();
	// List<DetailWrapper> details = null;
	// try {
	// details = detailService.listData();
	// mobileDetail.setResult(details);
	// return mobileDetail;
	// } catch (Exception e) {
	// log.info("获取Call Tree列表数据出错", e);
	// mobileDetail.setSuccess(false);
	// mobileDetail.setReturnCode(CalltreeConstant.COMMON_TRUE);
	// mobileDetail.setMessage("获取Call Tree列表数据出错");
	// return mobileDetail;
	// }
	// }

}
