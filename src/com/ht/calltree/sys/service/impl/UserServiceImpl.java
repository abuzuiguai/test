package com.ht.calltree.sys.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jodd.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ht.calltree.sys.dao.UserExtendMapper;
import com.ht.calltree.sys.dao.UserMapper;
import com.ht.calltree.sys.model.MisUser;
import com.ht.calltree.sys.model.User;
import com.ht.calltree.sys.model.UserExtend;
import com.ht.calltree.sys.model.UserWrapper;
import com.ht.calltree.sys.model.Userrole;
import com.ht.calltree.sys.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserExtendMapper userExtendMapper;

	public List<User> listData() {
		return userMapper.listData();
	}

	public List<UserWrapper> listChildrenUser(String staffId) {

//		//如果staffId有backup来源，则显示backup人员列表
//		UserExtend ue = userExtendMapper.selectByPrimaryKey(staffId);
//		if (ue != null) {
//			staffId = StringUtil.isEmpty(ue.getBackupFromId())?staffId:ue.getBackupFromId();
//		}
		
		UserExtend userExtend = userExtendMapper.selectByPrimaryKey(staffId);
		List<UserWrapper> users = userMapper.listChildrenData(staffId);
		if (userExtend == null) {
			return users;
		}

		UserWrapper backUpUser = new UserWrapper();
		
		boolean isBackup = false;
		for (UserWrapper user : users) {
			if (StringUtil.isEmpty(user.getStaffId())
					|| StringUtil.isEmpty(userExtend.getBackupId())) {
				continue;
			}
			if (user.getStaffId().equals(userExtend.getBackupId())) {
				user.setBackup(true);
				isBackup = true;
				backUpUser = user;
			}
		}
		if (isBackup) {
			users.remove(backUpUser);
			users.add(0, backUpUser);
		}
		return users;
	}

	@Transactional
	public void updateUserFromMIS(List<MisUser> users) {
		userMapper.deleteAll();
		// 测试数据有误，重复数据剔除
		List<String> list = new ArrayList<String>();

		User user = new User();
		for (MisUser misUser : users) {
			if (list.contains(misUser.getStaffId())) {
				continue;
			}
			list.add(misUser.getStaffId());
			user.setStaffId(misUser.getStaffId());
			user.setStaffLoginName(misUser.getAccountName());
			user.setStaffName(misUser.getChineseName());
			user.setStaffMobile(misUser.getPhoneNo());
			user.setStaffSpell(misUser.getPinYin());
			user.setStaffRtId(misUser.getPrimaryRT());
			user.setPrimaryGroupId(misUser.getPrimaryProjectID());
			user.setPrimaryGroupName(misUser.getPrimaryProjectName());

			userMapper.insert(user);
		}

		// 测试数据
		String childrenStaffIds = userMapper.listChildrenDataAsString("060004");
		List<String> childrenListStaffIds = new ArrayList<String>();
		childrenListStaffIds = Arrays.asList(childrenStaffIds.split(","));

		Map params = new HashMap();
		params.put("list", childrenListStaffIds);
		userMapper.deleteTest(params);

		userMapper.deleteAllRoleSet();

		String staffIds = "";
		List<User> userList = userMapper.listData();
		for (User u : userList) {
			staffIds = userMapper.listChildrenDataAsString(u.getStaffId());
			Userrole ur = new Userrole();
			if ("060004".equals(u.getStaffId())) {
				ur.setRoleId(9);
				ur.setStaffId("060004");
			} else if (staffIds.length() > 12) {
				ur.setRoleId(5);
				ur.setStaffId(u.getStaffId());
			} else {
				ur.setRoleId(0);
				ur.setStaffId(u.getStaffId());
			}
			userMapper.insertRoleSet(ur);
		}
	}
}
