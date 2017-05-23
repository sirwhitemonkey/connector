package com.xmdevelopments.connector.core.factory;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Camel template factory.
 */
public final class CamelTemplateFactory {

	private static CamelTemplateFactory instance;
	private transient ApplicationContext context;

	private CamelTemplateFactory() {
		 context = new ClassPathXmlApplicationContext("camel-template.xml");
	}

	/**
	 * Instance
	 */
	public static CamelTemplateFactory factory() {
		if (instance == null) {
			instance = new CamelTemplateFactory();
		}
		return instance;
	}
	
	/**
	 * Producer template
	 */
	public ProducerTemplate getProducerTemplate() {
		return context.getBean("producerTemplate", ProducerTemplate.class);
	}
	
	/**
	 * Consumer template
	 * @return
	 */
	public ConsumerTemplate getConsumerTemplate() {
		return context.getBean("consumerTemplate", ConsumerTemplate.class);
	}
}
