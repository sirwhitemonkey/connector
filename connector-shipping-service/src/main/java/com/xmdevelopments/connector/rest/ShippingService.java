package com.xmdevelopments.connector.rest;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xmdevelopments.connector.core.model.ElapseExecutionTime;
import com.xmdevelopments.connector.core.model.Response;
/**
 * Shipping service.
 *
 */
@RestController
public class ShippingService {
	private static Logger logger = LogManager.getLogger(ShippingService.class);
    
    /**
	 * Constructor.
	 */
	public ShippingService() {
		logger.info("<ShippingService> initialised");
	}
	
	/**
	 * Test
	 * @param userAgent
	 * @return {String}
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1/test", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Response> getTest(
			@RequestHeader(value = "user-agent", required = false) final String userAgent)
	
			throws Exception {
		ElapseExecutionTime eet = new ElapseExecutionTime();
		eet.setStartTime();
		String prefix = "getTest() ";
		logger.info(prefix + " start request user-agent:" + userAgent);
		Response response = new Response();
		response.setResponseCode(HttpStatus.OK.value());
		response.setData("Test");
		eet.setEndTime();
		response.setElapsedExecutionTime(eet);
		logger.info(prefix + " end request " + eet.getElapsed());
		ResponseEntity<Response> responseEntity = new ResponseEntity<>(response, response.getHttpStatus());
		return responseEntity;
	}

	
}


