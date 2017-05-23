package com.xmdevelopments.connector.oauth.exception;

import org.springframework.security.authentication.BadCredentialsException;

/**
 * Bad request exception.
 *
 */
@SuppressWarnings("serial")
public class CredentialErrorException extends BadCredentialsException {

	/**
	 * Constructor.
	 */
	public CredentialErrorException(String msg) {
		super(msg);
	}
}
