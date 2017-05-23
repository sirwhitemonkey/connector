package com.xmdevelopments.connector.oauth.controller;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.springframework.http.HttpStatus;

import com.xmdevelopments.connector.core.constant.EnvironmentVariables;
import com.xmdevelopments.connector.core.factory.CamelTemplateFactory;


/**
 * Rest auth controller
 *
 */
public class OAuthController {

	
	/**
	 * Authenticated token
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public boolean isAuthenticatedToken(String token) throws Exception {
		String route = getRoute();
		route = route + "/oauth/check_token";
		CamelTemplateFactory camelTemplateFactory = CamelTemplateFactory.factory();
		ProducerTemplate template = camelTemplateFactory.getProducerTemplate();
		Exchange exchange = template.send(route, new Processor() {
			public void process(Exchange exchange) throws Exception {
				Message in = exchange.getIn();
				in.setHeader(Exchange.CONTENT_TYPE, "application/json");
				in.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
				in.setHeader(Exchange.HTTP_METHOD, "POST");
				in.setHeader(Exchange.HTTP_QUERY, "token=" + token);
			}
		});
		//Exception exception = exchange.getException();
		Integer responseCode = exchange.getOut().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
		return (responseCode != null && responseCode == HttpStatus.OK.value());
	}
	
	protected String getRoute() {
		String route = "http4:" + System.getenv(EnvironmentVariables.OAuth2Server.getValue());
		route = route + "/service";
		return route;
	}

}
