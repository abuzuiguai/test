package com.ht.calltree.msg.dao;

import java.util.List;
import java.util.Map;

import com.ht.calltree.msg.model.Content;
import com.ht.calltree.msg.model.back.MobileFullContent;
import com.ht.calltree.msg.model.back.MobilePartContent;
import com.ht.calltree.msg.model.back.MobileReplyList;

public interface ContentMapper {
    int deleteByPrimaryKey(Integer contentId);
    
    MobileFullContent selectMobileFullContentByPrimaryKey(Integer contentId);

    int insert(Content record);

    int insertSelective(Content record);

    Content selectByPrimaryKey(Integer contentId);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKey(Content record);
    
    List<Content> listData();
    
    /**
	 * 获取所有未结束Call Tree
	 */
	public List<Content> listNotEndData();
    
    List<MobilePartContent> listMobileData(Map params);
    
    List<MobilePartContent> listActivityMobileData(Map params);
    
    MobileReplyList getMobilePartContent(Map params);
    /**
	 * Description: 查询所有结束时间大于最新calltree发送时间列表，如果列表数量大于0则表示该时间段内以后calltree
	 * @param sendTime calltree发送时间
	 * @return calltree列表
	 */
    List<Content> listActivityCallTree(java.util.Date sendTime);
}