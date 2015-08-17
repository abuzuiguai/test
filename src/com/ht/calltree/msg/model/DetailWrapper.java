package com.ht.calltree.msg.model;

public class DetailWrapper extends Detail {
	private String replyType;
	
	private String staffLoginName;
	
	private String staffName;
    
    private String staffSpell;
    
    private String staffRtId;
    
    private String staffRtName;
    
    private String staffRtSpell;
    
    private String primaryGroupId;
    
    private String primaryGroupName;
    
    private int noReplyCount;
    
    private boolean isBackup = false;
    
	public String getReplyType() {
		return replyType;
	}

	public void setReplyType(String replyType) {
		this.replyType = replyType;
	}
	
	public String getStaffLoginName() {
		return staffLoginName;
	}

	public void setStaffLoginName(String staffLoginName) {
		this.staffLoginName = staffLoginName;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffSpell() {
		return staffSpell;
	}

	public void setStaffSpell(String staffSpell) {
		this.staffSpell = staffSpell;
	}

	public String getStaffRtId() {
		return staffRtId;
	}

	public void setStaffRtId(String staffRtId) {
		this.staffRtId = staffRtId;
	}

	public String getStaffRtName() {
		return staffRtName;
	}

	public void setStaffRtName(String staffRtName) {
		this.staffRtName = staffRtName;
	}

	public String getStaffRtSpell() {
		return staffRtSpell;
	}

	public void setStaffRtSpell(String staffRtSpell) {
		this.staffRtSpell = staffRtSpell;
	}

	public String getPrimaryGroupId() {
		return primaryGroupId;
	}

	public void setPrimaryGroupId(String primaryGroupId) {
		this.primaryGroupId = primaryGroupId;
	}

	public String getPrimaryGroupName() {
		return primaryGroupName;
	}

	public void setPrimaryGroupName(String primaryGroupName) {
		this.primaryGroupName = primaryGroupName;
	}

	public int getNoReplyCount() {
		return noReplyCount;
	}

	public void setNoReplyCount(int noReplyCount) {
		this.noReplyCount = noReplyCount;
	}

	public boolean getIsBackup() {
		return isBackup;
	}

	public void setIsBackup(boolean isBackup) {
		this.isBackup = isBackup;
	}
}
