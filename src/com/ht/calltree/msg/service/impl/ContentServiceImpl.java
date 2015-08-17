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
import com.ht.calltree.msg.model.Content;
import com.ht.calltree.msg.model.Detail;
import com.ht.calltree.msg.model.back.MobileFullContent;
import com.ht.calltree.msg.model.back.MobilePartContent;
import com.ht.calltree.msg.service.ContentService;
import com.ht.calltree.sys.dao.UserExtendMapper;
import com.ht.calltree.sys.dao.UserMapper;
import com.ht.calltree.sys.model.User;
import com.ht.calltree.sys.model.UserExtend;
import com.ht.calltree.util.CalltreeConstant;

@Service("contentService")
public class ContentServiceImpl implements ContentService {
	@Autowired
	private ContentMapper contentMapper;
	@Autowired
	private DetailMapper detailMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserExtendMapper userExtendMapper;
	
	public Content getContent(Integer contentId) {
		return contentMapper.selectByPrimaryKey(contentId);
	}
	
	public MobileFullContent getMobileFullContent(Integer contentId) {
		return contentMapper.selectMobileFullContentByPrimaryKey(contentId);
	}
	
	/**
	 *更新Call Tree信息
	 */
	@Transactional
	public void update(Content content) {
		contentMapper.updateByPrimaryKeySelective(content);
	}
	
	/**
	 * 删除Call Tree
	 */
	@Transactional
	public void delete(Integer contentId) {
		detailMapper.deleteByForeignKey(contentId);
		contentMapper.deleteByPrimaryKey(contentId);
	}
	
	public List<Content> listData() {
		return contentMapper.listData();
	}
	
	/**
	 * 获取所有未结束Call Tree
	 */
	public List<Content> listNotEndData() {
		return contentMapper.listNotEndData();
	}
	
	public List<MobilePartContent> listMobileData(String staffId, boolean allRecord) {
		// 如果staffId有backup来源，则显示backup人员列表
		UserExtend ue = userExtendMapper.selectByPrimaryKey(staffId);
		if (ue != null) {
			staffId = StringUtil.isEmpty(ue.getBackupFromId()) ? staffId : ue
					.getBackupFromId();
		}
		
		String childrenStaffIds = userMapper.listChildrenDataAsString(staffId);
		List<String> childrenListStaffIds = new ArrayList<String>();
		childrenListStaffIds = Arrays.asList(childrenStaffIds.split(","));
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("staffId", staffId);
		params.put("list", childrenListStaffIds);
		if (allRecord) {
			return contentMapper.listMobileData(params);
		} else {
			return contentMapper.listActivityMobileData(params);
		}
	}
	
	@Transactional
	public int save(Content content) {
		contentMapper.insert(content);
		
		List<Detail> list = new ArrayList<Detail>();
		List<User> users = userMapper.listData();
		for (User user : users) {
			Detail detail = new Detail();
			detail.setContentId(content.getContentId());
			detail.setStaffId(user.getStaffId());
			detail.setDetailStatus(CalltreeConstant.CALLTREE_DETAIL_STATUS_NOREPLY);
			detail.setCreater(content.getCreater());
			detail.setCreateTime(content.getCreateTime());
			list.add(detail);
		}
		detailMapper.insertList(list);
		
		return content.getContentId();
	}
	
	/**
	 * Description: 判断同一时间段内是否有call tree存在
	 * @param content
	 * @return
	 */
	public boolean hasActivityCallTree(Content content) {
		List<Content> contents = contentMapper.listActivityCallTree(content.getSendTime());
		if (contents == null || contents.size() == 0) {
			return false;
		}
		return true;
	}
}
