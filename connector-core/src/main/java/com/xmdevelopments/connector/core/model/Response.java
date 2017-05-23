package com.xmdevelopments.connector.core.model;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Response.
 */
@SuppressWarnings("serial")
public class Response implements Serializable {

	private int responseCode;
	private Object data;
	private boolean hasError;
	private ElapseExecutionTime elapsedExecutionTime;

	public Response(Object data, int responseCode) {
		this.data = data;
		this.responseCode = responseCode;
	}

	public Response() {
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
		setHasError(responseCode != HttpStatus.OK.value());
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ElapseExecutionTime getElapsedExecutionTime() {
		if (elapsedExecutionTime == null) {
			elapsedExecutionTime = new ElapseExecutionTime();
			elapsedExecutionTime.setStartTime();
			elapsedExecutionTime.setEndTime();
		}
		return elapsedExecutionTime;
	}

	public void setElapsedExecutionTime(ElapseExecutionTime elapsedExecutionTime) {
		this.elapsedExecutionTime = elapsedExecutionTime;
	}

	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}
	
	@JsonIgnore
	public  HttpStatus getHttpStatus() {
		if (HttpStatus.OK.value() == getResponseCode()) {
			return HttpStatus.OK;
		} else if (HttpStatus.ACCEPTED.value() == getResponseCode()) {
			return HttpStatus.ACCEPTED;
		} else if (HttpStatus.ALREADY_REPORTED.value() == getResponseCode()) {
			return HttpStatus.ALREADY_REPORTED;
		} else if (HttpStatus.BAD_GATEWAY.value() == getResponseCode()) {
			return HttpStatus.BAD_GATEWAY;
		} else if (HttpStatus.BAD_REQUEST.value() == getResponseCode()) {
			return HttpStatus.BAD_REQUEST;
		} else if (HttpStatus.BANDWIDTH_LIMIT_EXCEEDED.value() == getResponseCode()) {
			return HttpStatus.BANDWIDTH_LIMIT_EXCEEDED;
		} else if (HttpStatus.INSUFFICIENT_STORAGE.value() == getResponseCode()) {
			return HttpStatus.INSUFFICIENT_STORAGE;
		} else if (HttpStatus.TOO_MANY_REQUESTS.value() == getResponseCode()) {
			return HttpStatus.TOO_MANY_REQUESTS;
		} else if (HttpStatus.EXPECTATION_FAILED.value() == getResponseCode()) {
			return HttpStatus.EXPECTATION_FAILED;
		} else if (HttpStatus.CREATED.value() == getResponseCode()) {
			return HttpStatus.CREATED;
		} else if (HttpStatus.UNSUPPORTED_MEDIA_TYPE.value() == getResponseCode()) {
			return HttpStatus.UNSUPPORTED_MEDIA_TYPE;
		} else if (HttpStatus.FORBIDDEN.value() == getResponseCode()) {
			return HttpStatus.FORBIDDEN;
		} else if (HttpStatus.FOUND.value() == getResponseCode()) {
			return HttpStatus.FOUND;
		} else if (HttpStatus.GATEWAY_TIMEOUT.value() == getResponseCode()) {
			return HttpStatus.GATEWAY_TIMEOUT;
		} else if (HttpStatus.UNAUTHORIZED.value() == getResponseCode()) {
			return HttpStatus.UNAUTHORIZED;
		} else if (HttpStatus.HTTP_VERSION_NOT_SUPPORTED.value() == getResponseCode()) {
			return HttpStatus.HTTP_VERSION_NOT_SUPPORTED;
		} else if (HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.value() == getResponseCode()) {
			return HttpStatus.NETWORK_AUTHENTICATION_REQUIRED;
		} else if (HttpStatus.NOT_FOUND.value() == getResponseCode()) {
			return HttpStatus.NOT_FOUND;
		} else if (HttpStatus.PAYLOAD_TOO_LARGE.value() == getResponseCode()) {
			return HttpStatus.PAYLOAD_TOO_LARGE;
		} else if (HttpStatus.REQUEST_TIMEOUT.value() == getResponseCode()) {
			return HttpStatus.REQUEST_TIMEOUT;
		} else if (HttpStatus.SERVICE_UNAVAILABLE.value() == getResponseCode()) {
			return HttpStatus.SERVICE_UNAVAILABLE;
		}else if (HttpStatus.PROCESSING.value() == getResponseCode()) {
			return HttpStatus.PROCESSING;
		}
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
}
