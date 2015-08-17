package com.ht.calltree.sys.model;

public class User {
	private String staffId;

	private String staffLoginName;

	private String staffSpell;

	private String staffName;

	private String staffMobile;

	private String staffRtId;

	private String primaryGroupId;

	private String primaryGroupName;

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
	 * 姓名拼音
	 */
	public String getStaffSpell() {
		return staffSpell;
	}

	public void setStaffSpell(String staffSpell) {
		this.staffSpell = staffSpell == null ? null : staffSpell.trim();
	}

	/**
	 * 登录账号
	 */
	public String getStaffLoginName() {
		return staffLoginName;
	}

	public void setStaffLoginName(String staffLoginName) {
		this.staffLoginName = staffLoginName;
	}

	/**
	 * 姓名
	 */
	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName == null ? null : staffName.trim();
	}

	/**
	 * 手机号码
	 */
	public String getStaffMobile() {
		return staffMobile;
	}

	public void setStaffMobile(String staffMobile) {
		this.staffMobile = staffMobile == null ? null : staffMobile.trim();
	}

	/**
	 * 主Report To
	 */
	public String getStaffRtId() {
		return staffRtId;
	}

	public void setStaffRtId(String staffRtId) {
		this.staffRtId = staffRtId == null ? null : staffRtId.trim();
	}

	/**
	 * 主项目组ID
	 */
	public String getPrimaryGroupId() {
		return primaryGroupId;
	}

	public void setPrimaryGroupId(String primaryGroupId) {
		this.primaryGroupId = primaryGroupId == null ? null : primaryGroupId
				.trim();
	}

	/**
	 * 主项目组名称
	 */
	public String getPrimaryGroupName() {
		return primaryGroupName;
	}

	public void setPrimaryGroupName(String primaryGroupName) {
		this.primaryGroupName = primaryGroupName == null ? null
				: primaryGroupName.trim();
	}
}