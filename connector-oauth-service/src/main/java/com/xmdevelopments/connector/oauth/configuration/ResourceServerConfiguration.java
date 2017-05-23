package com.xmdevelopments.connector.oauth.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.xmdevelopments.connector.core.model.Response;

/**
 * Resource server configuration
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	
	/**
	 * Configure
	 */
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId("eCommerceConnectorApi").stateless(false);
	}

	/**
	 * Configure
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
    	.headers()
    	.frameOptions()
    	.disable()
    	.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.anonymous().disable()
		.requestMatchers().antMatchers("/v1/**")
		.and().authorizeRequests()
		.antMatchers("/v1/**").access("hasRole('ADMIN') or hasRole('USER')")
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(authenticationEntryPoint())
		.accessDeniedHandler(accessDeniedHandler());
	}
	
	/**
	 * Access denied handler
	 * @return {AccessDeniedHandler}
	 */
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
	    return new AccessDeniedHandler () {
	    	/**
	    	 * 
	    	 */
	        @Override
	        public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
	        	ObjectMapper mapper = new ObjectMapper();
	        	Response restResponse = new Response();
	        	restResponse.setResponseCode(HttpStatus.FORBIDDEN.value());
	        	restResponse.setData("Forbidden");
	        	response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	        	response.setStatus(HttpStatus.FORBIDDEN.value());
	        	response.getOutputStream().println(mapper.writeValueAsString(restResponse));
		    }
	    };
	}

	/**
	 * Authentication entry point
	 * @return {AuthenticationEntryPoint}
	 */
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
	    return new AuthenticationEntryPoint() {
	    	/**
	    	 * Commence
	    	 */
	        @Override
	        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
	        	ObjectMapper mapper = new ObjectMapper();
	        	Response restResponse = new Response();
	        	if (request.getServletPath().equals("/")) {
	        		restResponse.setResponseCode(HttpStatus.OK.value());
		        	restResponse.setData("");
		          	response.setStatus(HttpStatus.OK.value());
		  	 
	         	} else {
	         		restResponse.setResponseCode(HttpStatus.UNAUTHORIZED.value());
		        	restResponse.setData("Unauthorized");
		          	response.setStatus(HttpStatus.UNAUTHORIZED.value());
		  	  	}
	        	response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	        	response.getOutputStream().println(mapper.writeValueAsString(restResponse));
	        }
	    };
	}
}


