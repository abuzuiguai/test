package com.ht.calltree.sys.model;

import java.util.ArrayList;
import java.util.List;

public class MisUserList {
	private String code;
	private String msg;
	private List<MisUser> data = new ArrayList<MisUser>();
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<MisUser> getData() {
		return data;
	}
	public void setData(List<MisUser> data) {
		this.data = data;
	}
}
