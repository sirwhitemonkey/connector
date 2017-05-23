package com.xmdevelopments.connector.oauth.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Security config.
 *
 */

@SuppressWarnings("deprecation")
@Configuration
@EnableWebMvcSecurity
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static Logger logger = LogManager.getLogger(SecurityConfig.class);
	
	/**
	 * Configure http security.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("HttpSecurity configured");
		http
    	.headers()
    	.frameOptions()
    	.disable()
    	.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.anonymous().disable()
		.requestMatchers().antMatchers("/**")
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(unauthorizedEntryPoint());
		//http.headers().frameOptions().sameOrigin().httpStrictTransportSecurity().disable();
		http.addFilterBefore(new SecurityFilter(authenticationManager()), BasicAuthenticationFilter.class);
	}

	/**
	 * Configure web security.
	 */
	//@Override
	//public void configure(WebSecurity web) throws Exception {
	//	String[] ignoreUris = new String[] {""};
	//	   web.ignoring().antMatchers(ignoreUris);
	//}

	
	/**
	 * Configure authentication manager.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("AuthenticationManagerBuilder configured");
		auth.authenticationProvider(securityProvider());
	}

	/**
	 * Wsse authentidation provider bean.
	 * @return {AuthenticationProvider}
	 */
	@Bean
	public AuthenticationProvider securityProvider() {
		return new SecurityProvider();
	}

	/**
	 * Unauthorized entry point bean.
	 * @return {AuthenticationEntryPoint}
	 */
	@Bean
	public AuthenticationEntryPoint unauthorizedEntryPoint() {
		return new AuthenticationEntryPoint() {
			/**
			 * Commence.
			 */
			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException authException) throws IOException, ServletException {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		};
	}

	/**
	 * Authentication manger bean.
	 * @return {AuthenticationManager}
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
}
