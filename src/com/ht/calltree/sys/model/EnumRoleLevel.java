package com.ht.calltree.sys.model;

public enum EnumRoleLevel {
	/**
	 * 普通员工
	 */
	NORMAL(0),
	/**
	 * PM/AM
	 */
	PM(5),
	/**
	 * Creator
	 */
	CREATOR(9);

	private int id;

	EnumRoleLevel(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public static EnumRoleLevel parse(int id) {
		EnumRoleLevel result = null;
		for (EnumRoleLevel ev : EnumRoleLevel.values()) {
			if (ev.getId() == id) {
				result = ev;
				break;
			}
		}
		return result;
	}
}
