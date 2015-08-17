package com.ht.calltree.msg.model;

public class ContentWrapper extends Content {
	/**
	 * 当前登录员工
	 */
	private String staffId;
	
	private boolean allRecord = true;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public boolean getAllRecord() {
		return allRecord;
	}

	public void setAllRecord(boolean allRecord) {
		this.allRecord = allRecord;
	}
	
}
