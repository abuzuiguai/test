package com.ht.calltree.msg.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jodd.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ht.calltree.msg.dao.ContentMapper;
import com.ht.calltree.msg.dao.DetailMapper;
import com.ht.calltree.msg.model.Detail;
import com.ht.calltree.msg.model.DetailWrapper;
import com.ht.calltree.msg.model.back.MobileFullDetail;
import com.ht.calltree.msg.model.back.MobileReplyDetail;
import com.ht.calltree.msg.model.back.MobileReplyList;
import com.ht.calltree.msg.service.DetailService;
import com.ht.calltree.sys.dao.UserExtendMapper;
import com.ht.calltree.sys.dao.UserMapper;
import com.ht.calltree.sys.model.User;
import com.ht.calltree.sys.model.UserExtend;
import com.ht.calltree.sys.model.UserWrapper;

@Service("detailService")
public class DetailServiceImpl implements DetailService {
	@Autowired
	private ContentMapper contentMapper;
	@Autowired
	private DetailMapper detailMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserExtendMapper userExtendMapper;

	public Detail getDetail(Integer detailId) {
		return detailMapper.selectByPrimaryKey(detailId);
	}

	@Transactional
	public void update(Detail detail) {
		detailMapper.updateByPrimaryKeySelective(detail);
	}

	@Transactional
	public int save(Detail detail) {
		return detailMapper.insert(detail);
	}

	@Transactional
	public void delete(Integer detailId) {
		detailMapper.deleteByPrimaryKey(detailId);
	}

	public List<DetailWrapper> listNoReplyData(Integer contentId) {
		return detailMapper.listNoReplyData(contentId);
	}

	/**
	 * Description: 获取员工个人回复信息
	 * 
	 * @param contentId
	 * @param staffId
	 * @return
	 */
	public MobileReplyDetail getMobileReplyDetail(Integer contentId,
			String staffId) {
		return detailMapper.getMobileReplyDetail(contentId, staffId);
	}

	public MobileReplyList getMobileReplyList(Integer contentId,
			String staffId, boolean isBackup) {
		// 如果staffId有backup来源，则显示backup人员列表
		UserExtend ue = userExtendMapper.selectByPrimaryKey(staffId);
		if (ue != null && isBackup) {
			staffId = StringUtil.isEmpty(ue.getBackupFromId()) ? staffId : ue
					.getBackupFromId();
		}

		// 查询当前员工所有下属 "060004,060005,060006"，保存至List
		String childrenStaffIds = userMapper.listChildrenDataAsString(staffId);
		List<String> childrenListStaffIds = new ArrayList<String>();
		childrenListStaffIds = Arrays.asList(childrenStaffIds.split(","));
		// 查询参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("contentId", contentId);
		params.put("staffId", staffId);
		params.put("list", childrenListStaffIds);
		// 获取Call Tree信息
		MobileReplyList mobileReplyList = contentMapper
				.getMobilePartContent(params);
		// 当前用户所有直属人员
		User currentUser = userMapper.selectByPrimaryKey(staffId);
		mobileReplyList.setStaffId(currentUser.getStaffId());
		mobileReplyList.setStaffName(currentUser.getStaffName());
		List<UserWrapper> users = userMapper.listChildrenData(staffId);
		List<MobileFullDetail> list = new ArrayList<MobileFullDetail>();

		for (User user : users) {
			// 直属人员所有下属人员 "060004,060005,060006"，保存至List
			childrenStaffIds = userMapper.listChildrenDataAsString(user
					.getStaffId());
			childrenListStaffIds = new ArrayList<String>();
			childrenListStaffIds = Arrays.asList(childrenStaffIds.split(","));
			// 查询参数
			params = new HashMap<String, Object>();
			params.put("contentId", contentId);
			params.put("staffId", user.getStaffId());
			params.put("list", childrenListStaffIds);
			list.add(detailMapper.getBranchReplyDetail(params));
		}
		mobileReplyList.setDetail(list);
		return mobileReplyList;
	}

}
