package com.joizhang.model.vo;

import java.sql.Timestamp;

public class OperLog implements java.io.Serializable {

	private static final long serialVersionUID = -5063786899432301034L;
	private String operationId;
	private String operationMan;
	private String operationTable;
	private String operationContent;
	private Timestamp operationDate;
	private String ipAddress;
	
	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	public String getOperationMan() {
		return operationMan;
	}

	public void setOperationMan(String operationMan) {
		this.operationMan = operationMan;
	}

	public String getOperationTable() {
		return operationTable;
	}

	public void setOperationTable(String operationTable) {
		this.operationTable = operationTable;
	}

	public String getOperationContent() {
		return operationContent;
	}

	public void setOperationContent(String operationContent) {
		this.operationContent = operationContent;
	}

	public Timestamp getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Timestamp operationDate) {
		this.operationDate = operationDate;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

}
