package com.joizhang.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RmsRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RMS_role", schema = "dbo", catalog = "test")
public class RmsRole implements java.io.Serializable {

	// Fields

	private String roleId;
	private String roleName;
	private String parentRoleId;
	private Integer enabled;

	// Constructors

	/** default constructor */
	public RmsRole() {
	}

	/** minimal constructor */
	public RmsRole(String roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}

	/** full constructor */
	public RmsRole(String roleId, String roleName, String parentRoleId,
			Integer enabled) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.parentRoleId = parentRoleId;
		this.enabled = enabled;
	}

	// Property accessors
	@Id
	@Column(name = "role_id", unique = true, nullable = false, length = 2)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "role_name", nullable = false, length = 16)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "parent_role_id", length = 2)
	public String getParentRoleId() {
		return this.parentRoleId;
	}

	public void setParentRoleId(String parentRoleId) {
		this.parentRoleId = parentRoleId;
	}

	@Column(name = "enabled")
	public Integer getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

}