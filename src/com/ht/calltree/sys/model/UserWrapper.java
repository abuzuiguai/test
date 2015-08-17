package com.ht.calltree.sys.model;

public class UserWrapper extends User {
	
	private boolean isBackup;
	
	private String staffRtName;
    
    private String staffRtSpell;

    private String staffRtMobile;

	public boolean isBackup() {
		return isBackup;
	}

	public void setBackup(boolean isBackup) {
		this.isBackup = isBackup;
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

	public String getStaffRtMobile() {
		return staffRtMobile;
	}

	public void setStaffRtMobile(String staffRtMobile) {
		this.staffRtMobile = staffRtMobile;
	}
}
