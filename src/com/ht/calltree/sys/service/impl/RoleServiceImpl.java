package com.ht.calltree.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.calltree.sys.dao.RoleMapper;
import com.ht.calltree.sys.model.Role;
import com.ht.calltree.sys.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public Role getRoleByStaffId(String staffId) {
		return roleMapper.getRoleByStaffId(staffId);
	}

}
