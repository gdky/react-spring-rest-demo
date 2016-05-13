package com.gdky.restfull.entity;

import java.io.Serializable;

public class MsgText implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6578746787921556311L;
	private Integer id;
	private String content;
	private String title;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
