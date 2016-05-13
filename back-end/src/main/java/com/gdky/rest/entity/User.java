package com.gdky.restfull.entity;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3365405747302906236L;
	private Integer id;
	private String username;
	private String password;
	private String passwordHint;
	private String names;
	private Integer accountEnabled;
	private Integer accountExpired;
	private Integer accountLocked;
	private Integer credentialsExpired;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordHint() {
		return passwordHint;
	}
	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}
	public Integer getAccountEnabled() {
		return accountEnabled;
	}
	public void setAccountEnabled(Integer accountEnabled) {
		this.accountEnabled = accountEnabled;
	}
	public Integer getAccountExpired() {
		return accountExpired;
	}
	public void setAccountExpired(Integer accountExpired) {
		this.accountExpired = accountExpired;
	}
	public Integer getAccountLocked() {
		return accountLocked;
	}
	public void setAccountLocked(Integer accountLocked) {
		this.accountLocked = accountLocked;
	}
	public Integer getCredentialsExpired() {
		return credentialsExpired;
	}
	public void setCredentialsExpired(Integer credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String name) {
		this.names = name;
	}
	
	
}
