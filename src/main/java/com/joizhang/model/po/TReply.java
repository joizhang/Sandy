package com.joizhang.model.po;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TReply entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_reply", schema = "dbo", catalog = "test")
public class TReply implements java.io.Serializable {

	// Fields

	private String replyId;
	private String topicId;
	private String userid;
	private String replyContent;
	private Timestamp replyTime;

	// Constructors

	/** default constructor */
	public TReply() {
	}

	/** full constructor */
	public TReply(String replyId, String topicId, String userid,
			String replyContent, Timestamp replyTime) {
		this.replyId = replyId;
		this.topicId = topicId;
		this.userid = userid;
		this.replyContent = replyContent;
		this.replyTime = replyTime;
	}

	// Property accessors
	@Id
	@Column(name = "reply_id", unique = true, nullable = false, length = 50)
	public String getReplyId() {
		return this.replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	@Column(name = "topic_id", nullable = false, length = 50)
	public String getTopicId() {
		return this.topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	@Column(name = "userid", nullable = false, length = 50)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "reply_content", nullable = false)
	public String getReplyContent() {
		return this.replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	@Column(name = "reply_time", nullable = false, length = 23)
	public Timestamp getReplyTime() {
		return this.replyTime;
	}

	public void setReplyTime(Timestamp replyTime) {
		this.replyTime = replyTime;
	}

}