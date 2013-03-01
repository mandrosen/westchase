package com.westchase.jobs.beans;

import java.io.Serializable;

/**
 * @author marc
 *
 */
public class NewsInfo implements Serializable {
	private String dateStr;
	private String title;
	private String url;
	
	public NewsInfo(String dateStr, String title, String url) {
		super();
		this.dateStr = dateStr;
		this.title = title;
		this.url = url;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}