package com.gdky.restfull.entity;

import java.io.Serializable;

public class AsideMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7824929431409711325L;
	private Integer id;
	private Integer pid;
	private String name;
	private String href;
	private Integer orderNo;
	private String path;
	private Integer visble;
	private String icon;
	private String lx;
	
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Integer getVisble() {
		return visble;
	}
	public void setVisble(Integer visble) {
		this.visble = visble;
	}
}
