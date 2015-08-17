package com.ht.calltree.msg.service;

import java.util.List;

import com.ht.calltree.msg.model.Content;
import com.ht.calltree.msg.model.back.MobileFullContent;
import com.ht.calltree.msg.model.back.MobilePartContent;

/**
 * 
 * @author lianghu
 */
public interface ContentService {
	Content getContent(Integer contentId);

	MobileFullContent getMobileFullContent(Integer contentId);

	void update(Content record);

	void delete(Integer contentId);

	List<Content> listData();

	List<Content> listNotEndData();

	List<MobilePartContent> listMobileData(String staffId, boolean allRecord);

	int save(Content record);

	/**
	 * Description: 判断同一时间段内是否有call tree存在
	 * 
	 * @param content
	 * @return
	 */
	boolean hasActivityCallTree(Content content);
}
