package com.ht.calltree.msg.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jodd.util.StringUtil;

import org.apache.log4j.Logger;

import com.ht.calltree.inter.tt.TTJob;
import com.ht.calltree.msg.model.Content;
import com.ht.calltree.msg.model.DetailWrapper;
import com.ht.calltree.msg.service.ContentService;
import com.ht.calltree.msg.service.DetailService;
import com.ht.calltree.msg.util.CallTreeUtil;
import com.ht.calltree.util.CalltreeConstant;

public class CallTreeTask implements Task {
	private Integer contentId;

	private ContentService contentService;

	private DetailService detailService;
	
	private CallTreeJobUtil callTreeJobUtil;

	private Logger log = Logger.getLogger(CallTreeTask.class);

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public CallTreeTask() {
		
	}
	
	public CallTreeTask(Integer contentId, 
			ContentService contentService, 
			DetailService detailService, CallTreeJobUtil callTreeJobUtil) {
		this.contentId = contentId;
		this.contentService = contentService;
		this.detailService = detailService;
		this.callTreeJobUtil = callTreeJobUtil;
	}

	public void run() {
		System.out.println("***********************Run task: " + contentId
				+ ".");
		log.debug("准备推送消息：消息ID = " + contentId);
		// 更新最后一次发送时间
		Content content = contentService.getContent(contentId);
		/********** 调用天天推送消息接口 **************/
		// 所有需要发送人员信息
		List<DetailWrapper> details = detailService.listNoReplyData(contentId);
		for (DetailWrapper detail : details) {
			if (StringUtil.isNotEmpty(detail.getStaffLoginName())) {
//				TTJob.send(content.getContentDetail(),
//						detail.getStaffLoginName());
			}

		}

		if (content.getLastSendTime() == null) {
			// 第一次发送
			content.setLastSendTime(content.getSendTime());
		} else {
			// 剩余次数发送，更新最后一次发送时间为上次最后发送时间加间隔时间
			content.setLastSendTime(CallTreeUtil.nextSendTime(content.getSendTime(), content.getSendInterval()));
		}
		content.setStatus(CalltreeConstant.CALLTREE_STATUS_SENDING);
		// 更新最后一次发送时间
		contentService.update(content);
		// 计算下次发送时间，追加发送任务
		Date nextSendTime = CallTreeUtil.nextSendTime(content.getSendTime(), content.getSendInterval());
		if (nextSendTime == null) {
			// 发送结束后删除任务
//			callTreeJobUtil.delJob(content.getContentId());
			callTreeJobUtil.updateJob(content.getContentId(), content.getEndTime(), new CallTreeEndTask(content.getContentId(), contentService, detailService, callTreeJobUtil));
			return;
		} else {
			if (nextSendTime.after(content.getEndTime())) {
				callTreeJobUtil.updateJob(content.getContentId(), content.getEndTime(), new CallTreeEndTask(content.getContentId(), contentService, detailService, callTreeJobUtil));
				return;
			}
		}
		callTreeJobUtil.updateJob(content.getContentId(), nextSendTime, this);
		log.debug("下次Call Tree发送时间：" + sdf.format(nextSendTime));

		System.out.println("***********************Run task: " + contentId
				+ "." + sdf.format(nextSendTime));
	}

	public Integer getContentId() {
		return contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public ContentService getContentService() {
		return contentService;
	}

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	public DetailService getDetailService() {
		return detailService;
	}

	public void setDetailService(DetailService detailService) {
		this.detailService = detailService;
	}

}
