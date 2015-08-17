package com.ht.calltree.msg.model.back;

import java.util.Date;

/**
 * 移动端Call Tree列表信息Bean
 * @author huironglou
 *
 */
public class MobilePartContent {
	private Integer contentId;
	private String contentDetail;
	private Date sendTime;
	private String status;
	private String staffId;
	private String staffName;
	private int branchStaffCount;
	private int replyCount;
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
	public int getBranchStaffCount() {
		return branchStaffCount;
	}
	public void setBranchStaffCount(int branchStaffCount) {
		this.branchStaffCount = branchStaffCount;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
}
