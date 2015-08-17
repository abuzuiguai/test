package com.ht.calltree.inter.mis.bean;

import java.io.Serializable;

public class AuthenticationPrinciple implements Serializable {
	private String userName;
    private String password;
    private int age = 180000;
    
    public AuthenticationPrinciple(String userName, String password) {
    	this.userName = userName;
    	this.password = password;
    }
    
    public AuthenticationPrinciple(String userName, String password, int age) {
    	this.userName = userName;
    	this.password = password;
    	this.age = age;
    }
    
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
