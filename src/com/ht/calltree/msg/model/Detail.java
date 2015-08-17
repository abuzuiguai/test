package com.ht.calltree.msg.model;

import java.util.Date;

public class Detail {
    private Integer detailId;

    private Integer contentId;

    private String staffId;

    private String detailStatus;

    private Integer sendNum;

    private Date lastSendTime;

    private Date replyTime;

    private String replyContent;

    private Date callTime;

    private String creater;

    private Date createTime;

    private String remark;
    /**
	 * 明细ID
	 */
    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }
    /**
	 * Call Tree内容ID
	 */
    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }
    /**
	 * staff ID
	 */
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }
    /**
	 * 明细状态
	 */
    public String getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(String detailStatus) {
        this.detailStatus = detailStatus == null ? null : detailStatus.trim();
    }
    /**
	 * 发送次数
	 */
    public Integer getSendNum() {
        return sendNum;
    }

    public void setSendNum(Integer sendNum) {
        this.sendNum = sendNum;
    }
    /**
	 * 最后一次发送时间
	 */
    public Date getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(Date lastSendTime) {
        this.lastSendTime = lastSendTime;
    }
    /**
	 * 回复时间
	 */
    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }
    /**
	 * 回复内容
	 */
    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }
    /**
	 * 电话告知时间
	 */
    public Date getCallTime() {
        return callTime;
    }

    public void setCallTime(Date callTime) {
        this.callTime = callTime;
    }
    /**
	 * 创建人
	 */
    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }
    /**
	 * 创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
	 * 备注
	 */
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}