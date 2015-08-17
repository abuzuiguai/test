package com.ht.calltree.sys.dao;

import java.util.List;
import java.util.Map;

import com.ht.calltree.sys.model.User;
import com.ht.calltree.sys.model.UserWrapper;
import com.ht.calltree.sys.model.Userrole;

public interface UserMapper {
    int deleteByPrimaryKey(String staffId);
    
    void deleteAll();

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String staffId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> listData();
    
    List<UserWrapper> listChildrenData(String staffId);
    
    String listChildrenDataAsString(String staffId);
    
    void deleteTest(Map params);
    void deleteAllRoleSet();
    int insertRoleSet(Userrole ur);
}