package com.joizhang.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RmsPermission entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RMS_permission", schema = "dbo", catalog = "test")
public class RmsPermission implements java.io.Serializable {

	// Fields

	private String permissionId;
	private String userid;
	private String resourceId;

	// Constructors

	/** default constructor */
	public RmsPermission() {
	}

	/** full constructor */
	public RmsPermission(String permissionId, String userid, String resourceId) {
		this.permissionId = permissionId;
		this.userid = userid;
		this.resourceId = resourceId;
	}

	// Property accessors
	@Id
	@Column(name = "permission_id", unique = true, nullable = false, length = 50)
	public String getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	@Column(name = "userid", nullable = false, length = 50)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "resource_id", nullable = false, length = 50)
	public String getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

}