package com.ht.calltree.msg.model.back;

import java.util.Date;

public class MobileFullContent {
	private Integer contentId;
	private String contentDetail;
	private Date sendTime;
//	private Integer effectTime;
//    private Integer effectUnit;
	private Date endTime;
    private Integer sendNum;
    private Integer sendInterval;
	private String status;
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
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	// public Integer getEffectTime() {
	// return effectTime;
	// }
	// public void setEffectTime(Integer effectTime) {
	// this.effectTime = effectTime;
	// }
	// public Integer getEffectUnit() {
	// return effectUnit;
	// }
	// public void setEffectUnit(Integer effectUnit) {
	// this.effectUnit = effectUnit;
	// }
	public Integer getSendNum() {
		return sendNum;
	}
	public void setSendNum(Integer sendNum) {
		this.sendNum = sendNum;
	}
	public Integer getSendInterval() {
		return sendInterval;
	}
	public void setSendInterval(Integer sendInterval) {
		this.sendInterval = sendInterval;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
