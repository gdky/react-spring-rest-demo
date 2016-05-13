package com.gdky.restfull.entity;

import java.io.Serializable;

public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6477197398963016786L;
    private Integer id;
    private String name;
    private String description;
    private String backtable;
    
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBacktable() {
		return backtable;
	}
	public void setBacktable(String backtable) {
		this.backtable = backtable;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}    
    
}
