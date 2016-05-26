package com.gdky.rest.entity;


import java.io.Serializable;
import java.util.List;

import org.hashids.Hashids;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;

import com.gdky.rest.configuration.Constants;

public class AuthResponse implements Serializable {

	private static final long serialVersionUID = 2652559529529474758L;
	private String token;
	private List<GrantedAuthority> roles;
	private String names;
	private String userId;
	private String tokenhash;
	private String permission;


	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getTokenhash() {
		return tokenhash;
	}

	public void setTokenhash(String token) {
		Md5PasswordEncoder  encoder = new Md5PasswordEncoder();
		//MD5不加盐hash
		String pass = encoder.encodePassword(token, null);
		pass = pass + Constants.SALT;
		pass = encoder.encodePassword(pass, null);
		this.tokenhash = pass;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		Hashids hashids = new Hashids(Constants.HASHID_SALT, Constants.HASHID_LEN);
		this.userId = hashids.encode(userId.longValue());
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public List<GrantedAuthority> getRoles() {
		return roles;
	}

	public void setRoles(List<GrantedAuthority> roles) {
		this.roles = roles;
	}

	public AuthResponse() {
		super();
	}

	public AuthResponse(String token) {
		this.setToken(token);
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
