package com.xmdevelopments.connector.oauth.configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;

import com.xmdevelopments.connector.oauth.controller.OAuthController;
import com.xmdevelopments.connector.oauth.exception.CredentialErrorException;

/**
 * Security provider
 *
 */
public class SecurityProvider implements AuthenticationProvider {
	
	/**
	 * Authenticate.
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String token = (String) authentication.getCredentials();
		OAuthController oauthController = new OAuthController();
		try {
			if (!oauthController.isAuthenticatedToken(token)) {
				throw new CredentialErrorException("Token not authenticated");
			}
		} catch (Exception exc) {
			throw new CredentialErrorException(exc.getMessage());
		}
		return new UsernamePasswordAuthenticationToken(token, null,
				AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN,USER"));
	}
	
	/**
	 * Supports.
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(SecurityToken.class);
	}
}
