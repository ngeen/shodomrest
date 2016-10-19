package com.shodom.model;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Entry {

	@Id
	private String id;
	private String title;
	private String content;
	private String link;
	
	@CreatedDate
	private DateTime publishDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public DateTime getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(DateTime publishDate) {
		this.publishDate = publishDate;
	}

}
