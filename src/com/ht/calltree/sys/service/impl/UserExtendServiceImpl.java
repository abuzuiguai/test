package com.ht.calltree.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ht.calltree.sys.dao.RoleMapper;
import com.ht.calltree.sys.dao.UserExtendMapper;
import com.ht.calltree.sys.model.Role;
import com.ht.calltree.sys.model.UserExtend;
import com.ht.calltree.sys.service.UserExtendService;

/**
 * 
 * @author lianghu
 */
@Service("userExtendService")
public class UserExtendServiceImpl implements UserExtendService {
	@Autowired
	private UserExtendMapper userExtendMapper;
	@Autowired
	private RoleMapper roleMapper;

	public UserExtend select(String staffId) {
		return userExtendMapper.selectByPrimaryKey(staffId);
	}

	public void update(UserExtend userExtend) {
		userExtendMapper.updateByPrimaryKey(userExtend);
	}

	public int insert(UserExtend userExtend) {
		return userExtendMapper.insert(userExtend);
	}

	public void delete(String staffId) {
		userExtendMapper.deleteByPrimaryKey(staffId);
	}

	/**
	 * 设置backup
	 */
	@Override
	@Transactional
	public boolean setBackup(UserExtend content) {
		String myId = content.getStaffId();
		String subId = content.getBackupId();

		// 设置自己的Backup To信息
		UserExtend u = this.select(myId);
		if (u == null) {
			this.insert(content);
		} else {
			this.update(content);
		}
		// 设置下一级人员的Backup From信息
		u = this.select(subId);
		if (u == null) {
			u = new UserExtend();
			u.setBackupFromId(myId);
			u.setStaffId(subId);
			this.insert(u);
		} else {
			u.setBackupFromId(myId);
			this.update(u);
		}
		// ******** 设置Backup权限***********
		roleMapper.removeSubBackupRole(myId);
		// 获取staffId的Role
		Role myRole = roleMapper.getRoleByStaffId(myId);
		// 获取指定下属的Role
		Role subRole = roleMapper.getRoleByStaffId(subId);
		subRole.setBackupRoleLevel(myRole.getRoleLevel());
		roleMapper.updateBackupRole(subRole);
		return true;
	}

	@Override
	public UserExtend getBackup(String staffId) {
		return this.select(staffId);
	}

}
