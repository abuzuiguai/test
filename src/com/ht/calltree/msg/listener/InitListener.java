package com.ht.calltree.msg.listener;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContextEvent;

import org.quartz.Scheduler;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ht.calltree.msg.job.CallTreeEndTask;
import com.ht.calltree.msg.job.CallTreeJobUtil;
import com.ht.calltree.msg.job.CallTreeTask;
import com.ht.calltree.msg.job.Task;
import com.ht.calltree.msg.model.Content;
import com.ht.calltree.msg.service.ContentService;
import com.ht.calltree.msg.service.DetailService;
import com.ht.calltree.msg.util.CallTreeUtil;
import com.ht.calltree.util.CalltreeConstant;

/**
 * Title: 初始化Call Tree发送任务 Company:Hengtian Software Ltd.
 * 
 * @author huironglou(Fyeman)
 * @version 1.0
 */
public class InitListener implements javax.servlet.ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent event) {
		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(event.getServletContext());
		ContentService contentService = (ContentService) webApplicationContext
				.getBean("contentService");
		DetailService detailService = (DetailService) webApplicationContext
				.getBean("detailService");
		Scheduler scheduler = (Scheduler) webApplicationContext
				.getBean("scheduler");

		CallTreeJobUtil callTreeJobUtil = new CallTreeJobUtil();
		callTreeJobUtil.setScheduler(scheduler);

		List<Content> contents = contentService.listNotEndData();
		/**
		 * 重新加载动态调度任务
		 */
		for (Content content : contents) {
			// 如还未到发送时间，添加发送任务
			if (content.getSendTime().after(new Date())) {
				Task task = new CallTreeTask(content.getContentId(), contentService, detailService, callTreeJobUtil);
				callTreeJobUtil.addJob(content.getContentId(),
						content.getSendTime(), task);
			} else {
				//如果CallTree结束时间已过，修改calltree状态
				if (content.getEndTime().before(new Date())) {
					content.setStatus(CalltreeConstant.CALLTREE_STATUS_SENDOVER);
					contentService.update(content);
					callTreeJobUtil.delJob(content.getContentId());
				} else {
					// 根据发送间隔判断最后一次发送时间，如果当前时间已经超过该时间，则不需要追加发送任务
					Date endSendTime = CallTreeUtil.calEndSendTime(content.getSendTime(),
							content.getSendInterval(), content.getSendNum());
					if (endSendTime.before(new Date())) {
						//添加结束任务
						callTreeJobUtil.addJob(content.getContentId(), content.getEndTime(), new CallTreeEndTask(content.getContentId(), contentService, detailService, callTreeJobUtil));
						continue;
					}
					// 根据发送时间和发送次数计算每次发送时间，如果当前时间在该时间段内，添加发送任务并更新最后一次发送时间
					int num = 1;
					Date lastSendTime = content.getSendTime();
					// 计算下次发送时间
					Date nextSendTime = CallTreeUtil.nextSendTime(content.getSendTime(),
							content.getSendInterval());
					while (nextSendTime.before(new Date())
							&& num <= content.getSendNum()) {
						lastSendTime = nextSendTime;
						nextSendTime = CallTreeUtil.nextSendTime(nextSendTime,
								content.getSendInterval());
						num++;
					}
					if (nextSendTime.after(content.getEndTime())) {
						callTreeJobUtil.addJob(content.getContentId(), content.getEndTime(), new CallTreeEndTask(content.getContentId(), contentService, detailService, callTreeJobUtil));
						continue;
					}
					callTreeJobUtil.addJob(content.getContentId(), nextSendTime, new CallTreeTask(content.getContentId(), contentService, detailService, callTreeJobUtil));
					content.setLastSendTime(lastSendTime);
					contentService.update(content);
				}
			}
		}
	}
}
