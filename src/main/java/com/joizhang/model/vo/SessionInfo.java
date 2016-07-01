package com.joizhang.model.vo;

/**
 * sessionInfo model
 * 
 */
public class SessionInfo implements java.io.Serializable {
	private static final long serialVersionUID = 6889178301387055271L;
	private String userId;// user ID
	private String loginName;// loginName
	private String loginPassword;// loginName
	private String ip;// IP address

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return loginName;
	}

}
