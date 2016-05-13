package com.gdky.restfull.configuration;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gdky.restfull.security.CustomUserDetailsService;
import com.gdky.restfull.security.EntryPointUnauthorizedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private EntryPointUnauthorizedHandler unauthorizedHandler;
	
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Resource(name="customAuthenticationProvider")
    private AuthenticationProvider authenticationProvider;

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
            
        	//.exceptionHandling().authenticationEntryPoint(this.unauthorizedHandler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            
        	.authorizeRequests()
        	
        	//allow all static resources
        	.antMatchers("/").permitAll()
            .antMatchers("/favicon.ico").permitAll()
            .antMatchers("/**/*.html").permitAll()
            .antMatchers("/**/*.css").permitAll()
            .antMatchers("/**/*.js").permitAll()
            
            // Allow anonymous logins
            .antMatchers("/auth/**").authenticated()
            
            // authenticate REST api 
            .antMatchers("/api/**").permitAll()
            
            // Allow all other request
            .anyRequest().permitAll()
            
            .and()
                .httpBasic()
            .and()
            	.formLogin();
    }
    
    @Autowired
    @Override
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(
        		shaPasswordEncoder());
        // 加载授权信息
        auth.authenticationProvider(authenticationProvider);
        
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(plaintextPasswordEncoder())
                .withUser("admin").password("test").authorities("ROLE_ADMIN")
                .and().withUser("user").password("test").authorities("ROLE_USER");
    }*/

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
