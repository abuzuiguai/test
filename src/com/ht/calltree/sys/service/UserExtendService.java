package com.ht.calltree.sys.service;

import com.ht.calltree.sys.model.UserExtend;

public interface UserExtendService {
	/**
	 * Description: 设置Backup人员
	 * 
	 * @param content
	 * @return
	 */
	public boolean setBackup(UserExtend userExtendWrapper);

	/**
	 * Description: 获取指定人员的Backup人员
	 * 
	 * @param staffId
	 * @return
	 */
	public UserExtend getBackup(String staffId);
}
