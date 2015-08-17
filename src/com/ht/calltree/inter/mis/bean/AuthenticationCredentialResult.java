package com.ht.calltree.inter.mis.bean;

import java.io.Serializable;

public class AuthenticationCredentialResult implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code;
	private String msg;
	AuthenticationCredential data = new AuthenticationCredential();

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

	public AuthenticationCredential getData() {
		return data;
	}

	public void setData(AuthenticationCredential data) {
		this.data = data;
	}
}
