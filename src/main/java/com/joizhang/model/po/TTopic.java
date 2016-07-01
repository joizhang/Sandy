package com.joizhang.model.po;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TTopic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_topic", schema = "dbo", catalog = "test")
public class TTopic implements java.io.Serializable {

	// Fields

	private String topicId;
	private String topicTitle;
	private String topicContent;
	private Timestamp topicDate;
	private String userid;

	// Constructors

	/** default constructor */
	public TTopic() {
	}

	/** full constructor */
	public TTopic(String topicId, String topicTitle, String topicContent,
			Timestamp topicDate, String userid) {
		this.topicId = topicId;
		this.topicTitle = topicTitle;
		this.topicContent = topicContent;
		this.topicDate = topicDate;
		this.userid = userid;
	}

	// Property accessors
	@Id
	@Column(name = "topic_id", unique = true, nullable = false, length = 50)
	public String getTopicId() {
		return this.topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	@Column(name = "topic_title", nullable = false)
	public String getTopicTitle() {
		return this.topicTitle;
	}

	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}

	@Column(name = "topic_content", nullable = false)
	public String getTopicContent() {
		return this.topicContent;
	}

	public void setTopicContent(String topicContent) {
		this.topicContent = topicContent;
	}

	@Column(name = "topic_date", nullable = false, length = 23)
	public Timestamp getTopicDate() {
		return this.topicDate;
	}

	public void setTopicDate(Timestamp topicDate) {
		this.topicDate = topicDate;
	}

	@Column(name = "userid", nullable = false, length = 50)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}