package com.ht.calltree.sys.model;

public class UserExtend {
	private String staffId;
	private String backupFromId;
	private String backupId;
	private String sendObjectId;

	public String getSendObjectId() {
		return sendObjectId;
	}

	public void setSendObjectId(String sendObjectId) {
		this.sendObjectId = sendObjectId;
	}

	/**
	 * 当前人员上级的ID
	 * 
	 * @return
	 */
	public String getBackupFromId() {
		return backupFromId;
	}

	public void setBackupFromId(String backupFromId) {
		this.backupFromId = backupFromId;
	}

	/**
	 * 当前人员的下级ID
	 * 
	 * @return
	 */
	public String getBackupId() {
		return backupId;
	}

	public void setBackupId(String backupId) {
		this.backupId = backupId;
	}

	/**
	 * 当前人员的ID
	 * 
	 * @return
	 */
	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
}
