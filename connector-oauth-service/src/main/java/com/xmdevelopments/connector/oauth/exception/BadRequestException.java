package com.xmdevelopments.connector.oauth.exception;

/**
 * Bad request exception.
 *
 */
@SuppressWarnings("serial")
public class BadRequestException extends RuntimeException {

	/**
	 * Constructor.
	 */
	public BadRequestException() {
		super("Bad request exception");
	}
	
	/**
	 * Constructor.
	 */
	public BadRequestException(String message) {
		super(message);
	}
}
