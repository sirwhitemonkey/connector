package com.xmdevelopments.connector.oauth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.xmdevelopments.connector.core.model.Response;

/**
 * ExceptionService.
 *
 */
@ControllerAdvice
public class ExceptionService extends ResponseEntityExceptionHandler {
	
	/**
	 * Exception service handler
	 * @param exc
	 * @param request
	 * @return {ResponseEntity<Response>}
	 */
    @ExceptionHandler({ Exception.class })
	protected ResponseEntity<Response> handleInvalidRequest(Exception exc, WebRequest request) {
		com.xmdevelopments.connector.core.model.Response response = new com.xmdevelopments.connector.core.model.Response();
		response.setResponseCode(HttpStatus.BAD_REQUEST.value());
		response.setData(exc);
		ResponseEntity<Response> responseEntity = new ResponseEntity<Response>(response,
				response.getHttpStatus());
		return responseEntity;
	}
}
