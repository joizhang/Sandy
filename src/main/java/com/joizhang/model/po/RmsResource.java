package com.joizhang.model.po;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RmsResource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RMS_resource", schema = "dbo", catalog = "test")
public class RmsResource implements java.io.Serializable {

	// Fields

	private String resourceId;
	private String resourceName;
	private String resourceUrl;
	private Integer enabled;
	private Timestamp createdtime;
	private String remarks;

	// Constructors

	/** default constructor */
	public RmsResource() {
	}

	/** minimal constructor */
	public RmsResource(String resourceId, String resourceName,
			String resourceUrl, Timestamp createdtime) {
		this.resourceId = resourceId;
		this.resourceName = resourceName;
		this.resourceUrl = resourceUrl;
		this.createdtime = createdtime;
	}

	/** full constructor */
	public RmsResource(String resourceId, String resourceName,
			String resourceUrl, Integer enabled, Timestamp createdtime,
			String remarks) {
		this.resourceId = resourceId;
		this.resourceName = resourceName;
		this.resourceUrl = resourceUrl;
		this.enabled = enabled;
		this.createdtime = createdtime;
		this.remarks = remarks;
	}

	// Property accessors
	@Id
	@Column(name = "resource_id", unique = true, nullable = false, length = 50)
	public String getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	@Column(name = "resource_name", nullable = false, length = 16)
	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@Column(name = "resource_url", nullable = false, length = 32)
	public String getResourceUrl() {
		return this.resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	@Column(name = "enabled")
	public Integer getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@Column(name = "createdtime", nullable = false, length = 23)
	public Timestamp getCreatedtime() {
		return this.createdtime;
	}

	public void setCreatedtime(Timestamp createdtime) {
		this.createdtime = createdtime;
	}

	@Column(name = "remarks")
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}