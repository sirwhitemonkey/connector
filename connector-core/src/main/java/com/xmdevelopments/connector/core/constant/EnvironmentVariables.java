package com.xmdevelopments.connector.core.constant;

import java.io.Serializable;

/**
 * Environment variables.
 *
 */
public enum EnvironmentVariables implements Serializable{
	OAuth2Server("OAUTH2_SERVER");
	
	
	private String value;
	/**
	 * Constructor
	 */
	private EnvironmentVariables(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	
}
