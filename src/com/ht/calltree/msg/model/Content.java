package com.ht.calltree.msg.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Content {

	private Integer contentId;

	private String contentTitle;

	private String contentDetail;

	private Date sendTime;

	private Integer effectTime;

	private Integer effectUnit;

	private Date endTime;

	private Date lastSendTime;

	private Integer sendNum;

	private Integer sendInterval;

	private String isSendNow;

	private Integer replyTime;

	private String contentType;

	private String sendObject;

	private String status;

	private String isReply;

	private String creater;

	private Date createTime;

	private String remark;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * Call Tree内容信息ID.
	 */
	public Integer getContentId() {
		return contentId;
	}

	/**
	 * Call Tree内容信息ID.
	 */
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	/**
	 * 标题.
	 */
	public String getContentTitle() {
		return contentTitle;
	}

	/**
	 * 标题.
	 */
	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle == null ? null : contentTitle.trim();
	}

	/**
	 * 发送内容.
	 */
	public String getContentDetail() {
		return contentDetail;
	}

	/**
	 * 发送内容.
	 */
	public void setContentDetail(String contentDetail) {
		this.contentDetail = contentDetail == null ? null : contentDetail
				.trim();
	}

	/**
	 * 发送时间.
	 */
	public Date getSendTime() {
		return sendTime;
	}

	/**
	 * 发送时间.
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * 有效时间.
	 */
	public Integer getEffectTime() {
		return effectTime;
	}

	/**
	 * 有效时间.
	 */
	public void setEffectTime(Integer effectTime) {
		this.effectTime = effectTime;
	}

	/**
	 * 有效时间-单位.
	 */
	public Integer getEffectUnit() {
		return effectUnit;
	}

	/**
	 * 有效时间-单位.
	 */
	public void setEffectUnit(Integer effectUnit) {
		this.effectUnit = effectUnit;
	}

	/**
	 * 结束时间.
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 最后一次发送时间.
	 */
	public Date getLastSendTime() {
		return lastSendTime;
	}

	/**
	 * 最后一次发送时间.
	 */
	public void setLastSendTime(Date lastSendTime) {
		this.lastSendTime = lastSendTime;
	}

	/**
	 * 发送次数.
	 */
	public Integer getSendNum() {
		return sendNum;
	}

	/**
	 * 发送次数.
	 */
	public void setSendNum(Integer sendNum) {
		this.sendNum = sendNum;
	}

	/**
	 * 发送间隔.
	 */
	public Integer getSendInterval() {
		return sendInterval;
	}

	/**
	 * 发送间隔.
	 */
	public void setSendInterval(Integer sendInterval) {
		this.sendInterval = sendInterval;
	}

	/**
	 * 内容类别.
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * 内容类别.
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType == null ? null : contentType.trim();
	}

	/**
	 * 发送对象.
	 */
	public String getSendObject() {
		return sendObject;
	}

	/**
	 * 发送对象.
	 */
	public void setSendObject(String sendObject) {
		this.sendObject = sendObject == null ? null : sendObject.trim();
	}

	/**
	 * 状态.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 状态.
	 */
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	/**
	 * 回复要求.
	 */
	public String getIsReply() {
		return isReply;
	}

	/**
	 * 回复要求.
	 */
	public void setIsReply(String isReply) {
		this.isReply = isReply == null ? null : isReply.trim();
	}

	/**
	 * 创建人.
	 */
	public String getCreater() {
		return creater;
	}

	/**
	 * 创建人.
	 */
	public void setCreater(String creater) {
		this.creater = creater == null ? null : creater.trim();
	}

	/**
	 * 创建时间.
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间.
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 备注.
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注.
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getContentDetailFormat() {
		if (contentDetail.length() > 15) {
			return contentDetail.substring(0, 15) + "...";
		} else {
			return contentDetail;
		}
	}

	public String getSendTimeFormat() {
		return sdf.format(sendTime);
	}

	public String getEndTimeFormat() {
		return sdf.format(endTime);
	}

	public String getCreateTimeFormat() {
		return sdf.format(createTime);
	}
}