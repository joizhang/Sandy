package com.joizhang.model.vo;

import java.sql.Timestamp;

import com.joizhang.model.po.RmsUser;

public class User implements java.io.Serializable {
	private static final long serialVersionUID = -4238133186983292560L;
	private String userid;
	private String username;
	private String password;
	private Timestamp createdtime;
	private Integer enabled;
	private String roleId;
	private String salt;
	
	public User(){}
	
	public User(String userid, String username, String password,
			Timestamp createdtime, Integer enabled, String roleId,
			RmsUser rmsUser) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.createdtime = createdtime;
		this.enabled = enabled;
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username
				+ ", password=" + password + ", createdtime=" + createdtime
				+ ", enabled=" + enabled + ", roleId=" + roleId + ", salt="
				+ salt + "]";
	}

	public String getCredentialsSalt() {
        return username + salt;
    }
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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

	public Timestamp getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(Timestamp createdtime) {
		this.createdtime = createdtime;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

}
