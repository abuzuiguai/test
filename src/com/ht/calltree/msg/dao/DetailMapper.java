package com.ht.calltree.msg.dao;

import java.util.List;
import java.util.Map;

import com.ht.calltree.msg.model.Detail;
import com.ht.calltree.msg.model.DetailWrapper;
import com.ht.calltree.msg.model.back.MobileFullDetail;
import com.ht.calltree.msg.model.back.MobileReplyDetail;

public interface DetailMapper {
    int deleteByPrimaryKey(Integer detailId);

    int insert(Detail record);
    
    int insertList(List<Detail> list);

    int insertSelective(Detail record);

    Detail selectByPrimaryKey(Integer detailId);

    int updateByPrimaryKeySelective(Detail record);

    int updateByPrimaryKey(Detail record);
    
    List<DetailWrapper> listNoReplyData(Integer contentId);
    
    void deleteByForeignKey(Integer contentId);
    
    /**
	 * Description: 获取员工个人回复信息
	 * @param contentId
	 * @param staffId
	 * @return
	 */
	MobileReplyDetail getMobileReplyDetail(Integer contentId, String staffId);
    
    MobileFullDetail getBranchReplyDetail(Map params);
}