package com.ht.calltree.msg.service;

import java.util.List;

import com.ht.calltree.msg.model.Detail;
import com.ht.calltree.msg.model.DetailWrapper;
import com.ht.calltree.msg.model.back.MobileReplyList;
import com.ht.calltree.msg.model.back.MobileReplyDetail;

/**
 * 
 * @author lianghu
 */
public interface DetailService {
	Detail getDetail(Integer detailId);

	void update(Detail detail);

	int save(Detail detail);

	void delete(Integer detailId);

	List<DetailWrapper> listNoReplyData(Integer contentId);

	/**
	 * Description: 获取员工个人回复信息
	 * 
	 * @param contentId
	 * @param staffId
	 * @return
	 */
	MobileReplyDetail getMobileReplyDetail(Integer contentId, String staffId);

	MobileReplyList getMobileReplyList(Integer contentId, String staffId,
			boolean isBackUp);
}
