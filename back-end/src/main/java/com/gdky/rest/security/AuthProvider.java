package com.gdky.restfull.security;

import javax.annotation.Resource;


import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthProvider implements AuthenticationProvider {

	@Resource
	private CustomUserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {
		String username = auth.getName();
		String password = auth.getCredentials().toString();


		UserDetails user = userDetailsService.loadUserByUsername(username);

		if (user != null) {
			Authentication token = new UsernamePasswordAuthenticationToken(
					username, password, user.getAuthorities());
			return token;
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
