package com.gdky.restfull.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, AuthenticationException e)
			throws IOException, ServletException {
		// 当没经过身份认证的用户访问一个受保护接口时
        // 返回一个401响应代替默认的重定向到登录页面
		httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
				"Access Denied");
	}
}
