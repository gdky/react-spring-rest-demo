package com.gdky.restfull.entity;

import java.io.Serializable;
import java.util.Date;

public class MsgInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7436915800903981465L;
	private Integer id;
	private Integer sendId;
	private Integer recId;
	private Integer state;
	private Integer textId;
	private Date createTime;
	private Date expiredTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSendId() {
		return sendId;
	}
	public void setSendId(Integer sendId) {
		this.sendId = sendId;
	}
	public Integer getRecId() {
		return recId;
	}
	public void setRecId(Integer recId) {
		this.recId = recId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getTextId() {
		return textId;
	}
	public void setTextId(Integer textId) {
		this.textId = textId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getExpiredTime() {
		return expiredTime;
	}
	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}
	
}
