package com.ht.calltree.msg.model.back;

import java.util.Date;

public class MobileReplyDetail {
	private Integer contentId;
	private String contentDetail;
	private String status;
	private Date sendTime;
	private String staffId;
    private String detailStatus;
	private Date replyTime;
    private String replyContent;
    private String staffName;
	public Integer getContentId() {
		return contentId;
	}
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	public String getContentDetail() {
		return contentDetail;
	}
	public void setContentDetail(String contentDetail) {
		this.contentDetail = contentDetail;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getDetailStatus() {
		return detailStatus;
	}
	public void setDetailStatus(String detailStatus) {
		this.detailStatus = detailStatus;
	}
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	} 
}
