package com.ht.calltree.inter.mis.job;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.ht.calltree.inter.mis.bean.AuthenticationCredentialResult;
import com.ht.calltree.inter.mis.bean.AuthenticationPrinciple;
import com.ht.calltree.inter.util.HttpApi;
import com.ht.calltree.sys.model.MisUser;
import com.ht.calltree.sys.model.MisUserList;
import com.ht.calltree.sys.service.UserService;
import com.ht.calltree.util.CalltreeConstant;

public class MisJob {
	private Logger log = Logger.getLogger(MisJob.class);

	@Autowired
	private UserService userService;
	
	public void execute() throws JobExecutionException {
		HttpApi mis = new HttpApi();
		AuthenticationPrinciple auth = new AuthenticationPrinciple(
				CalltreeConstant.AUTH_USER_NAME,
				CalltreeConstant.AUTH_PASSWORD, CalltreeConstant.AUTH_AGE);
		AuthenticationCredentialResult result = (AuthenticationCredentialResult) mis
				.doPost(CalltreeConstant.MIS_AUTHENTICATION_URL, auth,
						AuthenticationCredentialResult.class);
		mis.setToken(result.getData().getToken()); 
		
		
		Map<String, Class> map = new HashMap<String, Class>();
		map.put("data", MisUser.class);
		MisUserList users = (MisUserList) mis.doGetList(CalltreeConstant.MIS_ALL_STAFF_URL, MisUserList.class, map);
		
		userService.updateUserFromMIS(users.getData());
	}
}
