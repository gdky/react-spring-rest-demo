package com.gdky.rest.security;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException e)
			throws IOException, ServletException {
		// 当没经过身份认证的用户访问一个受保护接口时
        // 返回一个401响应代替默认的重定向到登录页面
		HashMap<String,Object> obj = new HashMap<String,Object>();
		 Long timestamp = System.currentTimeMillis();
		  obj.put("path", request.getRequestURI());
		  obj.put("timestamp", timestamp);
		  obj.put("status", "401");
		  obj.put("error", "Unauthorized");
		  obj.put("message", "Access Denied");
		  
		  response.setContentType("application/json");
		  response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		  
		  ObjectMapper mapper = new ObjectMapper(); 
		  mapper.writeValue(response.getOutputStream(), obj);
	}
}
