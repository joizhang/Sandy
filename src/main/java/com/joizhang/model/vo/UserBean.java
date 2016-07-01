package com.joizhang.model.vo;

import java.io.Serializable;

public class UserBean implements Serializable {
	private static final long serialVersionUID = 6154980939422833186L;
	private String username;
	private String password;
	
	public UserBean(){}

	public UserBean(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserBean [username=" + username + ", password=" + password
				+ "]";
	}
	
	
}
