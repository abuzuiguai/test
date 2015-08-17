package com.ht.calltree.msg.model.back;

import java.util.ArrayList;
import java.util.List;

public class MobileReplyList extends MobilePartContent {
	private List<MobileFullDetail> detail = new ArrayList<MobileFullDetail>();

	public List<MobileFullDetail> getDetail() {
		return detail;
	}

	public void setDetail(List<MobileFullDetail> detail) {
		this.detail = detail;
	}
}
