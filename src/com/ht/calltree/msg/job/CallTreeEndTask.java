package com.ht.calltree.msg.job;

import com.ht.calltree.msg.model.Content;
import com.ht.calltree.msg.service.ContentService;
import com.ht.calltree.msg.service.DetailService;
import com.ht.calltree.util.CalltreeConstant;

public class CallTreeEndTask implements Task {
	private Integer contentId;

	private ContentService contentService;

	private DetailService detailService;
	
	private CallTreeJobUtil callTreeJobUtil;
	
	public CallTreeEndTask() {
		
	}

	public CallTreeEndTask(Integer contentId, 
			ContentService contentService, 
			DetailService detailService, CallTreeJobUtil callTreeJobUtil) {
		this.contentId = contentId;
		this.contentService = contentService;
		this.detailService = detailService;
		this.callTreeJobUtil = callTreeJobUtil;
	}
	
	public void run() {
		Content content = contentService.getContent(contentId);
		content.setStatus(CalltreeConstant.CALLTREE_STATUS_SENDOVER);
		contentService.update(content);
		
		callTreeJobUtil.delJob(content.getContentId());
	}
}
