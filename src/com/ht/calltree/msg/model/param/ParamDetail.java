package com.ht.calltree.msg.model.param;

import com.ht.calltree.msg.model.DetailWrapper;

public class ParamDetail extends Param {
	private DetailWrapper detail;

	public DetailWrapper getDetailWrapper() {
		return detail;
	}

	public void setDetail(DetailWrapper detail) {
		this.detail = detail;
	}
}
