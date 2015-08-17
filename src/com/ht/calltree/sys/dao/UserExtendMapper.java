package com.ht.calltree.sys.dao;

import com.ht.calltree.sys.model.UserExtend;

public interface UserExtendMapper {
	int deleteByPrimaryKey(String staffId);

	int insert(UserExtend record);

	UserExtend selectByPrimaryKey(String staffId);

	int updateByPrimaryKey(UserExtend record);

}