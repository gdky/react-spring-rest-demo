package com.gdky.restfull.security;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;
import java.util.regex.Pattern;


public class CsrfSecurityRequestMatcher implements RequestMatcher {
	private Pattern allowedMethods = Pattern
            .compile("^(GET|HEAD|TRACE|OPTIONS)$");

    public boolean matches(HttpServletRequest request) {

        if (execludeUrls != null && execludeUrls.size() > 0) {
            String servletPath = request.getServletPath();
            for (String url : execludeUrls) {
                if (servletPath.contains(url)) {
                    return false;
                }
            }
        }
        return !allowedMethods.matcher(request.getMethod()).matches();
    }

    /**
     * 需要排除的url列表
     */
    private List<String> execludeUrls;

    public List<String> getExecludeUrls() {
        return execludeUrls;
    }

    public void setExecludeUrls(List<String> execludeUrls) {
        this.execludeUrls = execludeUrls;
    }
}
