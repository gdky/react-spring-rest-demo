package com.gdky.restfull.security;

import javax.annotation.Resource;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {  
    
	@Resource(name="customUserDetailsService")
	private  UserDetailsService userDetailsService;  
	
    
    @Override  
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {  
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;  
        String username = token.getName();  
        System.out.println(username);
        //从数据库找到的用户  
        UserDetails userDetails = null;  
        if(username != null) {  
            userDetails = userDetailsService.loadUserByUsername(username);  
        }  
        //  
        if(userDetails == null) {  
            throw new UsernameNotFoundException("用户名/密码无效");  
        }else if (!userDetails.isEnabled()){  
            throw new DisabledException("用户已被禁用");  
        }else if (!userDetails.isAccountNonExpired()) {  
            throw new AccountExpiredException("账号已过期");  
        }else if (!userDetails.isAccountNonLocked()) {  
            throw new LockedException("账号已被锁定");  
        }else if (!userDetails.isCredentialsNonExpired()) {  
            throw new LockedException("凭证已过期");  
        }  
        //数据库用户的密码  
        String password = userDetails.getPassword();  
        //与authentication里面的credentials相比较  
        if(!password.equals(token.getCredentials())) {  
            throw new BadCredentialsException("Invalid username/password");  
        }  
        //授权  
        return new UsernamePasswordAuthenticationToken(userDetails, password,userDetails.getAuthorities());  
    }  
  
    @Override  
    public boolean supports(Class<?> authentication) {  
        //返回true后才会执行上面的authenticate方法,这步能确保authentication能正确转换类型  
        return UsernamePasswordAuthenticationToken.class.equals(authentication);  
    }  
}