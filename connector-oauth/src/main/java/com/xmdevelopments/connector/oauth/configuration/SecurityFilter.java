package com.xmdevelopments.connector.oauth.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmdevelopments.connector.core.model.Response;

/**
 * Wsse authentication filter.
 *
 */
public class SecurityFilter extends GenericFilterBean {

	private static Logger logger = LogManager.getLogger(SecurityFilter.class);
	private transient final AuthenticationManager authenticationManager;

	/**
	 * Constructor.
	 * @param authenticationManager - authentication manager
	 */
	public SecurityFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	/**
	 * Filter servlet.
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String prefix = "doFilter()";

		logger.info(prefix);

		try {
			HttpServletRequest httpRequest = asHttp(request);
			String token = httpRequest.getHeader("token");
			authenticationProcessor(request, response, chain, token);

		} catch (IllegalStateException isExc) {
			logger.error(isExc);
		}
	}
	
	/**
	 * Authentication
	 * @param request
	 * @param response
	 * @param chain
	 * @param token
	 * @throws IOException
	 * @throws ServletException
	 */
	private void authenticationProcessor(ServletRequest request, ServletResponse response, FilterChain chain, String token)
			throws IOException, ServletException {

		String prefix = "authenticationProcessor() ";
		com.xmdevelopments.connector.core.model.Response restResponse = null;
		ObjectMapper mapper = null;
		HttpServletRequest httpRequest = asHttp(request);
		HttpServletResponse httpResponse = asHttp(response);
	
		logger.info(prefix);

		try {
			authenticationProcessor(token, httpRequest);
			chain.doFilter(request, response);

		} catch (AuthenticationException aexc) {
			logger.error(aexc);
			
			SecurityContextHolder.clearContext();
			restResponse = new Response(aexc.getMessage(), HttpServletResponse.SC_UNAUTHORIZED);
			mapper = new ObjectMapper();
			httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
			httpResponse.getOutputStream().println(mapper.writeValueAsString(restResponse));
		} catch (IllegalStateException isExc) {
			SecurityContextHolder.clearContext();
			logger.error(isExc);
			mapper = new ObjectMapper();
			httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			restResponse = new Response(isExc.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
			httpResponse.getOutputStream().println(mapper.writeValueAsString(restResponse));
		} catch (NullPointerException npex) {
			SecurityContextHolder.clearContext();
			logger.error(npex);
			mapper = new ObjectMapper();
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			restResponse = new Response(npex.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
			httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
			httpResponse.getOutputStream().println(mapper.writeValueAsString(restResponse));

		}
	}

	private HttpServletRequest asHttp(ServletRequest request) {
		return (HttpServletRequest) request;
	}

	private HttpServletResponse asHttp(ServletResponse response) {
		return (HttpServletResponse) response;
	}

	/**
	 * Authentication
	 * @param token
	 * @param request
	 * @throws IOException
	 */
 	private void authenticationProcessor(String token, HttpServletRequest request) throws IOException {
		Authentication resultOfAuthentication = authentication(token, request);
		SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
	}

	/**
	 * Authentication
	 * @param token
	 * @param request
	 * @return
	 */
	private Authentication authentication(String token, HttpServletRequest request) {
		String prefix = "authentication() ";
		Authentication responseAuthentication = authenticationManager
				.authenticate(new SecurityToken(token, token));

		if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
			throw new InternalAuthenticationServiceException("Unable to authenticate for provided credentials");
		}
		logger.info(prefix + "Successfully authenticated");
		return responseAuthentication;
	}
}
