package com.ht.calltree.sys.model;

public class Role {
	private String staffId;
	private EnumRoleLevel roleLevel;
	private EnumRoleLevel backupRoleLevel;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public EnumRoleLevel getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(EnumRoleLevel roleLevel) {
		this.roleLevel = roleLevel;
	}

	public EnumRoleLevel getBackupRoleLevel() {
		return backupRoleLevel;
	}

	public void setBackupRoleLevel(EnumRoleLevel backupRoleLevel) {
		this.backupRoleLevel = backupRoleLevel;
	}

}
