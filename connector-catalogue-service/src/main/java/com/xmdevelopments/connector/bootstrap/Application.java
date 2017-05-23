package com.xmdevelopments.connector.bootstrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Connector application
 *
 */

@SpringBootApplication
@ComponentScan({ "com.xmdevelopments.connector.configuration", "com.xmdevelopments.connector.service",
		"com.xmdevelopments.connector.rest", "com.xmdevelopments.connector.oauth.configuration" })

public class Application  {
	
	
	/**
	 * Main
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
}

