package com.ht.calltree.sys.service;

import java.util.List;

import com.ht.calltree.sys.model.MisUser;
import com.ht.calltree.sys.model.User;
import com.ht.calltree.sys.model.UserWrapper;

public interface UserService {
	List<User> listData();

	List<UserWrapper> listChildrenUser(String staffId);

	void updateUserFromMIS(List<MisUser> users);
}
