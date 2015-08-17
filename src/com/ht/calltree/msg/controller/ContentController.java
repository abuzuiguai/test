package com.ht.calltree.msg.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jodd.util.StringUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ht.calltree.msg.job.CallTreeJobUtil;
import com.ht.calltree.msg.job.CallTreeTask;
import com.ht.calltree.msg.job.Task;
import com.ht.calltree.msg.model.Content;
import com.ht.calltree.msg.model.ContentWrapper;
import com.ht.calltree.msg.model.back.MobileFullContent;
import com.ht.calltree.msg.model.back.MobilePartContent;
import com.ht.calltree.msg.model.back.MobileReturn;
import com.ht.calltree.msg.model.param.ParamContent;
import com.ht.calltree.msg.service.ContentService;
import com.ht.calltree.msg.service.DetailService;
import com.ht.calltree.msg.util.CallTreeUtil;
import com.ht.calltree.util.CallTreeMessages;
import com.ht.calltree.util.CalltreeConstant;
import com.ht.calltree.util.CommonHeadJson;
import com.ht.calltree.util.JsonUtil;

/**
 * Title: Call Tree Description: Call Tree基本功能实现 Copyright: Copyright (c) 2003
 * Company:Hengtian Software Ltd.
 * 
 * @author huironglou(Fyeman)
 * @version 1.0
 */
@Controller
@RequestMapping("/msg/content")
public class ContentController extends CallTreeController {
	@Autowired
	private ContentService contentService;
	@Autowired
	private CallTreeJobUtil callTreeJobUtil;

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
	 *            "content":{"contentId":2,"contentDetail":"this is a test"}}
	 * @return Object Json对象
	 */
	@RequestMapping
	@ResponseBody
	public Object execute(HttpServletRequest req, String param) {
		ParamContent paramContent = (ParamContent) JsonUtil.stringToJsonObject(
				param, ParamContent.class);
		try {
			return this
					.getClass()
					.getMethod(
							paramContent.getFunctionName(),
							new Class[] { HttpServletRequest.class,
									ContentWrapper.class })
					.invoke(this,
							new Object[] { req, paramContent.getContent() });
		} catch (Exception e) {
			log.info(
					CallTreeMessages.ERROR_NO_METHOD
							+ paramContent.getFunctionName(), e);
			return CommonHeadJson
					.failedMessage(CallTreeMessages.ERROR_NO_METHOD
							+ paramContent.getFunctionName());
		}
	}

	/**
	 * Description :移动端返回所有Call Tree列表信息
	 * 
	 * @param HttpServletRequest
	 *            req http请求对象
	 * @param String
	 *            param 传入参数，格式{"staffId":"140000"}
	 * @return Call Tree 列表数据，数据格式： { "success": "true", "returnCode": "0",
	 *         "message": "成功", "result": { 列表数据 } }
	 */
	@RequestMapping("/mobileListContentData")
	@ResponseBody
	public Object mobileListContentData(HttpServletRequest req, String param) {
		ContentWrapper content = (ContentWrapper) JsonUtil.stringToJsonObject(
				param, ContentWrapper.class);
		return mobileListContentData(req, content);
	}

	/**
	 * Description :动态方法调用，移动端返回所有Call Tree列表信息
	 * 
	 * @param HttpServletRequest
	 *            req http请求对象
	 * @param Content
	 *            content calltree发送内容对象
	 * @return Call Tree 列表数据，数据格式： { "success": "true", "returnCode": "0",
	 *         "message": "成功", "result": { 列表数据 } }
	 */
	public Object mobileListContentData(HttpServletRequest req,
			ContentWrapper content) {
		MobileReturn mobileContent = new MobileReturn();
		List<MobilePartContent> contents = null;
		try {
			if (StringUtil.isEmpty(content.getStaffId())) {
				return CommonHeadJson
						.failedMessage(CallTreeMessages.ERROR_STAFF_ID_IS_NULL);
			}
			contents = contentService.listMobileData(content.getStaffId(),
					content.getAllRecord());
			mobileContent.setResult(contents);
			return mobileContent;
		} catch (Exception e) {
			log.error(CallTreeMessages.ERROR_CALLTREE_LIST, e);
			return CommonHeadJson
					.failedMessage(CallTreeMessages.ERROR_CALLTREE_LIST);
		}
	}

	/**
	 * Description :移动端获取单个CallTree基本信息（只包含ID、内容、发送时间、有效时间、单位、发送次数、发送间隔、状态）
	 * 
	 * @param HttpServletRequest
	 *            req http请求对象
	 * @param String
	 *            param 传入参数，格式{"contentId":2}
	 * @return Call Tree 列表数据，数据格式： { "success": "true", "returnCode": "0",
	 *         "message": "成功", "result": { 单个Call Tree数据 } }
	 */
	@RequestMapping("/mobileFullContentData")
	@ResponseBody
	public Object mobileFullContentData(HttpServletRequest req, String param) {
		ContentWrapper content = (ContentWrapper) JsonUtil.stringToJsonObject(
				param, ContentWrapper.class);
		return mobileFullContentData(req, content);
	}

	/**
	 * Description
	 * :动态方式方式，移动端获取单个CallTree基本信息（只包含ID、内容、发送时间、有效时间、单位、发送次数、发送间隔、状态）
	 * 
	 * @param HttpServletRequest
	 *            req http请求对象
	 * @param Content
	 *            content calltree发送内容对象
	 * @return Call Tree 列表数据，数据格式： { "success": "true", "returnCode": "0",
	 *         "message": "成功", "result": { 列表数据 } }
	 */
	public Object mobileFullContentData(HttpServletRequest req,
			ContentWrapper content) {
		MobileReturn mobileContent = new MobileReturn();
		try {
			if (content.getContentId() == null
					|| content.getContentId().intValue() == 0) {
				return CommonHeadJson
						.failedMessage(CallTreeMessages.ERROR_CALLTREE_CONTENT_ID_ISNULL);
			}
			MobileFullContent fullContent = contentService
					.getMobileFullContent(content.getContentId());
			mobileContent.setResult(fullContent);
			return mobileContent;
		} catch (Exception e) {
			log.error(CallTreeMessages.ERROR_CALLTREE_INFO, e);
			return CommonHeadJson
					.failedMessage(CallTreeMessages.ERROR_CALLTREE_INFO);
		}
	}

	/**
	 * 新建或更新Call Tree信息，同时更新任务信息
	 * 
	 * @param req
	 *            request请求对象
	 * @param param
	 *            param={"contentDetail":"发送内容","sendTime":"2014-10-24 19:00:00"
	 *            ... ...}
	 * @return
	 */
	@RequestMapping("/mobileSaveContentData")
	@ResponseBody
	public CommonHeadJson mobileSaveContentData(HttpServletRequest req,
			String param) {
		ContentWrapper content = (ContentWrapper) JsonUtil.stringToJsonObject(
				param, ContentWrapper.class);
		return mobileSaveContentData(req, content);
	}

	/**
	 * 新建或更新Call Tree信息，同时更新任务信息
	 * 
	 * @param req
	 *            request请求对象
	 * @param content
	 *            Call Tree内容信息（Post提交方式页面字段名称和该对象定义变量名称一致会自动进行衍射）
	 * @return
	 */
	public CommonHeadJson mobileSaveContentData(HttpServletRequest req,
			ContentWrapper content) {
		try {
			//时间段已存在有效CallTree
			if (hasActivityCallTree(content)) {
				log.info(CallTreeMessages.ERROR_CALLTREE_TIME_EXIST);
				return CommonHeadJson
						.failedMessage(CallTreeMessages.ERROR_CALLTREE_TIME_EXIST);
			}
			//发送时间在当前时间之前，最多超过发送时间5分钟
			Calendar cal = Calendar.getInstance();
			cal.setTime(content.getSendTime());
			cal.add(Calendar.MINUTE, CalltreeConstant.SEND_TIME_ABORT);
			
			if (cal.getTime().before(new Date())) {
				log.info(CallTreeMessages.ERROR_CALLTREE_SEND_TIME_OUT);
				return CommonHeadJson
						.failedMessage(CallTreeMessages.ERROR_CALLTREE_SEND_TIME_OUT);
			}
			//当前时间已在设置的CallTree结束时间之后
			if (content.getEndTime().before(new Date())) {
				log.info(CallTreeMessages.ERROR_CALLTREE_END_TIME_OUT);
				return CommonHeadJson
						.failedMessage(CallTreeMessages.ERROR_CALLTREE_END_TIME_OUT);
			}
			//根据发送间隔和次数计算最后一次发送时间，如果该时间晚于结束时间，返回错误信息
			Date calEndSendTime = CallTreeUtil.calEndSendTime(content.getSendTime(), content.getSendInterval(), content.getSendNum());
			if (calEndSendTime.after(content.getEndTime())) {
				return CommonHeadJson
						.failedMessage(CallTreeMessages.ERROR_CALLTREE_CAL_END_TIME_OUT);
			}
			if (content.getContentId() == null
					|| content.getContentId().intValue() == 0) {
				content.setCreater("louhr");
				content.setCreateTime(new Date());
				content.setIsReply(CalltreeConstant.COMMON_TRUE);
				content.setContentType(CalltreeConstant.COMMON_FALSE);
				content.setStatus(CalltreeConstant.COMMON_FALSE);
				contentService.save(content);
				
				
			} else {
				Content oldContent = contentService.getContent(content
						.getContentId());
				if (StringUtil.isEmpty(oldContent.getStatus())
						|| !CalltreeConstant.CALLTREE_STATUS_NOSEND
								.equals(oldContent.getStatus())) {
					return CommonHeadJson
							.failedMessage(CallTreeMessages.ERROR_CALLTREE_SENT);
				}

				contentService.update(content);
			}
			//添加动态发送任务
			Task task = new CallTreeTask(content.getContentId(), contentService, detailService, callTreeJobUtil);
			if (content.getSendTime() != null
					&& content.getSendTime().after(new Date())) {
				callTreeJobUtil.updateJob(content.getContentId(),
						content.getSendTime(), task);
			} else {
				task.run();
			}
			return CommonHeadJson.successMessage();
		} catch (Exception e) {
			log.error(CallTreeMessages.ERROR_CALLTREE_SAVE, e);
			return CommonHeadJson
					.failedMessage(CallTreeMessages.ERROR_CALLTREE_SAVE);
		}
	}

	/**
	 * 删除Call Tree信息
	 * 
	 * @param req
	 *            request请求对象
	 * @param param
	 *            param={"contentId":2}
	 * @return
	 */
	@RequestMapping("/mobileDelContentData")
	@ResponseBody
	public CommonHeadJson mobileDelContentData(HttpServletRequest req,
			String param) {
		ContentWrapper content = (ContentWrapper) JsonUtil.stringToJsonObject(
				param, ContentWrapper.class);
		return mobileDelContentData(req, content);
	}

	/**
	 * 删除Call Tree信息
	 * 
	 * @param req
	 *            request请求对象
	 * @param content
	 *            写入contentId
	 * @return
	 */
	public CommonHeadJson mobileDelContentData(HttpServletRequest req,
			ContentWrapper content) {
		try {
			if (content.getContentId() == null
					|| content.getContentId().intValue() == 0) {
				return CommonHeadJson
						.failedMessage(CallTreeMessages.ERROR_CALLTREE_CONTENT_ID_ISNULL);
			}
			contentService.delete(content.getContentId());
			callTreeJobUtil.delJob(content.getContentId());
			return CommonHeadJson.successMessage();
		} catch (Exception e) {
			log.error(CallTreeMessages.ERROR_CALLTREE_DEL, e);
			return CommonHeadJson
					.failedMessage(CallTreeMessages.ERROR_CALLTREE_DEL);
		}
	}

	private boolean hasActivityCallTree(ContentWrapper content) {
		return contentService.hasActivityCallTree(content);
	}

	/**
	 * Description :PC端返回Call Tree列表信息
	 * 
	 * @param HttpServletRequest
	 *            req http请求对象
	 * @param Content
	 *            content calltree发送内容对象
	 * @return req请求对象放置Call Tree列表信息
	 */
	@RequestMapping("/pcListContentData")
	public String pcListContentData(HttpServletRequest req,
			ContentWrapper content) {
		req.setAttribute("list", contentService.listData());
		return "msg/content/data";
	}

	/**
	 * Description :PC端跳转新增编辑页面
	 * 
	 * @param HttpServletRequest
	 *            req http请求对象
	 * @param Content
	 *            content calltree发送内容对象
	 * @return
	 */
	@RequestMapping("pcContentAddOrEdit")
	public String pcContentAddOrEdit(HttpServletRequest req, Content content) {
		if (content.getContentId() != null
				&& content.getContentId().intValue() != 0) {
			content = contentService.getContent(content.getContentId());
			req.setAttribute("actionType", "edit");
		} else {
			content.setSendNum(3);
			content.setSendInterval(10);
			req.setAttribute("actionType", "add");
		}
		req.setAttribute("currentContent", content);
		return "msg/content/calltree";
	}

	/**
	 * Description :PC端保存Call Tree
	 * 
	 * @param HttpServletRequest
	 *            req http请求对象
	 * @param Content
	 *            content calltree发送内容对象
	 * @return 返回Call Tree列表页面
	 */
	@RequestMapping("/pcContentSaveOrUpdate")
	public String pcContentSaveOrUpdate(HttpServletRequest req,
			ContentWrapper content) {
		mobileSaveContentData(req, content);
		return pcListContentData(req, content);
	}

	/**
	 * Description :PC端删除Call Tree
	 * 
	 * @param HttpServletRequest
	 *            req http请求对象
	 * @param Content
	 *            content calltree发送内容对象
	 * @return 返回Call Tree列表页面
	 */
	@RequestMapping("/pcContentDelete")
	public String pcContentDelete(HttpServletRequest req, ContentWrapper content) {
		contentService.delete(content.getContentId());
		return pcListContentData(req, content);
	}
}
