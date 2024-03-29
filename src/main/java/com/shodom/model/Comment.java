package com.shodom.model;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Comment {

	@Id
	private String id;
	private String entryId;
	private String userName;
	private String comment;
	private String showFlg;
	private String response;

	@CreatedDate
	private DateTime publishDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEntryId() {
		return entryId;
	}

	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public DateTime getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(DateTime publishDate) {
		this.publishDate = publishDate;
	}

	public String getShowFlg() {
		return showFlg;
	}

	public void setShowFlg(String showFlg) {
		this.showFlg = showFlg;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
}
