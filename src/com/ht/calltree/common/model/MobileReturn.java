package com.ht.calltree.common.model;

import com.ht.calltree.util.CommonHeadJson;

public class MobileReturn<T> extends CommonHeadJson {
	T result = null;

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
}
