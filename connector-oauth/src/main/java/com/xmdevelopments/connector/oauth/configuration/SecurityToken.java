package com.xmdevelopments.connector.oauth.configuration;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Security token
 *
 */
@SuppressWarnings("serial")
public class SecurityToken extends UsernamePasswordAuthenticationToken {
	
	/**
	 * Constructor.
	 * @param principal - principal
	 * @param credentials - credentials
	 */
    public SecurityToken(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
