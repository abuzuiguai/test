package com.ht.calltree.sys.model;

/**
 * 客户端所拥有的页面
 * 
 * @author Lyon Hu
 *
 */
public enum EnumViewByRole {
	/**
	 * 我的回复
	 */
	MYREPLY,
	/**
	 * CALLTREE
	 */
	CALLTREELIST,
	/**
	 * BACKUP
	 */
	BACKUP,
	/**
	 * 回复动态
	 */
	REPLYDYN;

	public static EnumViewByRole parse(String name) {
		EnumViewByRole result = null;
		for (EnumViewByRole ev : EnumViewByRole.values()) {
			if (ev.name().equalsIgnoreCase(name)) {
				result = ev;
				break;
			}
		}
		return result;
	}
}
