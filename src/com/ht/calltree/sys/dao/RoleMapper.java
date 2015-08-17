package com.ht.calltree.sys.dao;

import com.ht.calltree.sys.model.Role;

public interface RoleMapper {
	Role getRoleByStaffId(String staffId);

	void updateBackupRole(Role role);

	/**
	 * 重置指定staffId下属的Backup权限
	 * 
	 * @param staffId
	 */
	void removeSubBackupRole(String staffId);
}
