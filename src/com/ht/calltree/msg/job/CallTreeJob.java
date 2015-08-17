package com.ht.calltree.msg.job;

import org.quartz.JobExecutionContext;  
import org.quartz.JobExecutionException;  
import org.springframework.scheduling.quartz.QuartzJobBean;  
  
public class CallTreeJob extends QuartzJobBean {  
      
    private Task task;  
  
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {     
    	task.run();  
    }

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}  
}  
