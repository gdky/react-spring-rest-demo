package com.gdky.rest.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gdky.rest.security.AuthenticationTokenFilter;
import com.gdky.rest.security.CustomUserDetailsService;
import com.gdky.rest.security.EntryPointUnauthorizedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private EntryPointUnauthorizedHandler unauthorizedHandler;
	
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                // All of Spring Security will ignore the requests
                .antMatchers("/resources/**")
                .antMatchers(HttpMethod.POST, "/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
        	.headers().cacheControl().disable().and()
            .servletApi().and()
            
        	.exceptionHandling().authenticationEntryPoint(this.unauthorizedHandler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            
        	.authorizeRequests()
        	
        	//allow all static resources
        	.antMatchers("/").permitAll()
            .antMatchers("/favicon.ico").permitAll()
            .antMatchers("/**/*.html").permitAll()
            .antMatchers("/**/*.css").permitAll()
            .antMatchers("/**/*.js").permitAll()
            
            // 验证测试用api
            .antMatchers(HttpMethod.GET, "/api/auth/**").authenticated()
            
            //身份验证api允许匿名访问
            .antMatchers(HttpMethod.POST,"/api/auth/**").permitAll()            
            
            // authenticate REST api 
            .antMatchers("/api/**").permitAll()
            
            //对外公开api允许匿名访问
            .antMatchers("/pub/api/**").permitAll()
            
            // 其余连接允许匿名访问
            .anyRequest().permitAll().and()
           // Custom JWT based authentication
            .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }
    
    @Autowired
    @Override
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(
        		shaPasswordEncoder());
        
    }    
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
    }
    
    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
      AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
      authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
      return authenticationTokenFilter;
    }

    @Bean
    public ShaPasswordEncoder shaPasswordEncoder() {
        return new ShaPasswordEncoder();
    }

    @Bean
    public PlaintextPasswordEncoder plainTextPasswordEncoder() {
        return new PlaintextPasswordEncoder();
    }
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
