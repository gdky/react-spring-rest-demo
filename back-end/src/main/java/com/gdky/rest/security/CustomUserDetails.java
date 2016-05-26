package com.gdky.rest.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gdky.rest.entity.User;

public class CustomUserDetails extends User implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9094631563587476893L;
	private  Collection<? extends GrantedAuthority> authorities;
	
	public CustomUserDetails(User u) {
        super(u);
    }
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities){
		this.authorities = authorities;
	}
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
		
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
    	if(this.getAccountExpired() == 1){
    		return false;
    	}
    	return true;
        
    }

    @Override
    public boolean isAccountNonLocked() {
    	if(this.getAccountLocked() == 1){
    		return false;
    	}
    	return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
    	if(this.getCredentialsExpired() == 1){
    		return false;
    	}
    	return true;
    }

    @Override
    public boolean isEnabled() {
    	if(this.getAccountEnabled() == 0){
    		return false;
    	}
    	return true;
    }


}
