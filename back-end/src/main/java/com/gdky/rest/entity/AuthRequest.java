package com.gdky.rest.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class AuthRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1605805443134109970L;
	private String username;
	private String password;
	
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

	@Override
	  public String toString() {
	    return ReflectionToStringBuilder.toString(this);
	  }

}
