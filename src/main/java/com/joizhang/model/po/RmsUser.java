package com.joizhang.model.po;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RmsUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RMS_user", schema = "dbo", catalog = "test")
public class RmsUser implements java.io.Serializable {

	// Fields

	private String userid;
	private String username;
	private String password;
	private Timestamp createdtime;
	private Integer enabled;
	private String roleId;
	private String salt;

	// Constructors

	/** default constructor */
	public RmsUser() {
	}

	/** minimal constructor */
	public RmsUser(String userid, String username, String salt) {
		this.userid = userid;
		this.username = username;
		this.salt = salt;
	}

	/** full constructor */
	public RmsUser(String userid, String username, String password,
			Timestamp createdtime, Integer enabled, String roleId, String salt) {
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.createdtime = createdtime;
		this.enabled = enabled;
		this.roleId = roleId;
		this.salt = salt;
	}

	// Property accessors
	@Id
	@Column(name = "userid", unique = true, nullable = false, length = 50)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "username", nullable = false, length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "createdtime", length = 23)
	public Timestamp getCreatedtime() {
		return this.createdtime;
	}

	public void setCreatedtime(Timestamp createdtime) {
		this.createdtime = createdtime;
	}

	@Column(name = "enabled")
	public Integer getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@Column(name = "role_id", length = 50)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "salt", nullable = false, length = 100)
	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

}