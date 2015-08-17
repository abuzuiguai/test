package com.ht.calltree.msg.job;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ht.calltree.msg.service.ContentService;
import com.ht.calltree.msg.service.DetailService;

/**
 * 调度任务工具类，动态加载任务.
 * 
 * @author huironglou
 *
 */
@Component("callTreeJobUtil")
public class CallTreeJobUtil {
	private Logger log = Logger.getLogger(CallTreeJobUtil.class);

	@Autowired
	private Scheduler scheduler;

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
	
	/**
	 * 添加任务.
	 * 
	 * @param contentId
	 * @param sendTime
	 */
	public void addJob(Integer contentId, Date sendTime, Task task) {
		try {
			// 任务已存在，直接返回
			if (isExistJob(contentId)) {
				return;
			}

			JobDetail jobDetail = new JobDetail();
			jobDetail.setName("job_" + contentId);
			jobDetail.getJobDataMap().put("task", task);
			jobDetail.setJobClass(CallTreeJob.class);
			scheduler.addJob(jobDetail, true);
			CronTrigger cronTrigger = new CronTrigger("cron_" + contentId,
					Scheduler.DEFAULT_GROUP, jobDetail.getName(),
					Scheduler.DEFAULT_GROUP);
			cronTrigger.setCronExpression(getCronExpression(sendTime));
			scheduler.scheduleJob(cronTrigger);
		} catch (SchedulerException se) {
			log.info("调度任务加载出错!", se);
		} catch (ParseException pe) {
			log.info("调度任务设置加载时间出错!", pe);
		} catch (Exception e) {
			log.info("调度任务加载出错!", e);
		}
	}

	/**
	 * 更新任务.
	 * 
	 * @param contentId
	 * @param sendTime
	 */
	public void updateJob(Integer contentId, Date sendTime, Task task) {
		if (isExistJob(contentId)) {
			delJob(contentId);
		}
		addJob(contentId, sendTime, task);

	}

	public void delJob(Integer contentId) {
		try {
			scheduler.deleteJob("job_" + contentId, Scheduler.DEFAULT_GROUP);
			scheduler.unscheduleJob("cron_" + contentId,
					Scheduler.DEFAULT_GROUP);
		} catch (SchedulerException se) {
			log.info("卸载调度任务出错!", se);
		}
	}

	/**
	 * 判断是否已经存在任务.
	 * 
	 * @param contentId
	 * @return
	 */
	public boolean isExistJob(Integer contentId) {
		try {
			String[] triggerNames = scheduler
					.getTriggerNames(Scheduler.DEFAULT_GROUP);
			if (triggerNames.length <= 0) {
				return false;
			}
			for (int i = 0; i < triggerNames.length; i++) {
				if (triggerNames[i].equals("cron_" + contentId)) {
					return true;
				}
			}
		} catch (SchedulerException se) {
			log.info("读取所有调度任务出错!", se);
		}
		return false;
	}

	public String getCronExpression(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
		return sdf.format(time);
	}
}
