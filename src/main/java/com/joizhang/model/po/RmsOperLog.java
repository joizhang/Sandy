package com.joizhang.model.po;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RmsOperLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RMS_oper_log", schema = "dbo", catalog = "test")
public class RmsOperLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4533907816764721681L;
	private String operationId;
	private String operationMan;
	private String operationTable;
	private String operationContent;
	private Timestamp operationDate;
	private String ipAddress;

	// Constructors

	/** default constructor */
	public RmsOperLog() {
	}

	/** minimal constructor */
	public RmsOperLog(String operationId, String operationMan,
			String operationTable, String operationContent,
			Timestamp operationDate) {
		this.operationId = operationId;
		this.operationMan = operationMan;
		this.operationTable = operationTable;
		this.operationContent = operationContent;
		this.operationDate = operationDate;
	}

	/** full constructor */
	public RmsOperLog(String operationId, String operationMan,
			String operationTable, String operationContent,
			Timestamp operationDate, String ipAddress) {
		this.operationId = operationId;
		this.operationMan = operationMan;
		this.operationTable = operationTable;
		this.operationContent = operationContent;
		this.operationDate = operationDate;
		this.ipAddress = ipAddress;
	}

	// Property accessors
	@Id
	@Column(name = "operation_id", unique = true, nullable = false, length = 50)
	public String getOperationId() {
		return this.operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	@Column(name = "operation_man", nullable = false, length = 32)
	public String getOperationMan() {
		return this.operationMan;
	}

	public void setOperationMan(String operationMan) {
		this.operationMan = operationMan;
	}

	@Column(name = "operation_table", nullable = false, length = 10)
	public String getOperationTable() {
		return this.operationTable;
	}

	public void setOperationTable(String operationTable) {
		this.operationTable = operationTable;
	}

	@Column(name = "operation_content", nullable = false)
	public String getOperationContent() {
		return this.operationContent;
	}

	public void setOperationContent(String operationContent) {
		this.operationContent = operationContent;
	}

	@Column(name = "operation_date", nullable = false, length = 23)
	public Timestamp getOperationDate() {
		return this.operationDate;
	}

	public void setOperationDate(Timestamp operationDate) {
		this.operationDate = operationDate;
	}

	@Column(name = "ip_address")
	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime
				* result
				+ ((operationContent == null) ? 0 : operationContent.hashCode());
		result = prime * result
				+ ((operationDate == null) ? 0 : operationDate.hashCode());
		result = prime * result
				+ ((operationId == null) ? 0 : operationId.hashCode());
		result = prime * result
				+ ((operationMan == null) ? 0 : operationMan.hashCode());
		result = prime * result
				+ ((operationTable == null) ? 0 : operationTable.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RmsOperLog other = (RmsOperLog) obj;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		if (operationContent == null) {
			if (other.operationContent != null)
				return false;
		} else if (!operationContent.equals(other.operationContent))
			return false;
		if (operationDate == null) {
			if (other.operationDate != null)
				return false;
		} else if (!operationDate.equals(other.operationDate))
			return false;
		if (operationId == null) {
			if (other.operationId != null)
				return false;
		} else if (!operationId.equals(other.operationId))
			return false;
		if (operationMan == null) {
			if (other.operationMan != null)
				return false;
		} else if (!operationMan.equals(other.operationMan))
			return false;
		if (operationTable == null) {
			if (other.operationTable != null)
				return false;
		} else if (!operationTable.equals(other.operationTable))
			return false;
		return true;
	}

}