package com.reallycare.cn.upload.controlller;

import java.io.Serializable;

import org.springframework.data.annotation.Id;



public class MongoListDate implements Serializable {

	@Id
	private String id;

	private String createTime;

	private String status;

	private String option;

	private String userId;

	private Long time;

	private String updateTime;

	private String requestContent;

	private String responseContent;

	private String openId;

	// from(1,sql;2,procedure;3,socket)
	private String from;

	public MongoListDate(String id, String createTime, String status,
			String option, String userId, Long time, String updateTime,
			String requestContent, String responseContent, String openId,
			String from) {
		super();
		this.id = id;
		this.createTime = createTime;
		this.status = status;
		this.option = option;
		this.userId = userId;
		this.time = time;
		this.updateTime = updateTime;
		this.requestContent = requestContent;
		this.responseContent = responseContent;
		this.openId = openId;
		this.from = from;
	}

	@Override
	public String toString() {
		return "MongoListDate [id=" + id + ", createTime=" + createTime
				+ ", status=" + status + ", option=" + option + ", userId="
				+ userId + ", time=" + time + ", updateTime=" + updateTime
				+ ", requestContent=" + requestContent + ", responseContent="
				+ responseContent + ", openId=" + openId + ", from=" + from
				+ "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getRequestContent() {
		return requestContent;
	}

	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}

	public String getResponseContent() {
		return responseContent;
	}

	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public MongoListDate() {
	}
}
