package com.xmdevelopments.connector.core.model;

import java.io.Serializable;

/**
 * Calculate the elapse execution time
 */
@SuppressWarnings("serial")
public class ElapseExecutionTime implements Serializable {

	private long startTime;
	private long endTime;
	private String elapsed;
	
	/**
	 * Constructor.
	 */
	public ElapseExecutionTime() {
		
	}

	public void setStartTime() {
		this.startTime = System.currentTimeMillis();
	}

	public void setEndTime() {
		this.endTime = System.currentTimeMillis();
	}
	
	public void setElapsed(String elapsed) {
		this.elapsed = elapsed;
	}

	/**
	 * Show elapsed execution time
	 */
	public String getElapsed() {
		long difference = endTime - startTime;
		this.elapsed = (" @@@ {Execution time} sec:" + (difference / 1000.0) + " ms.:" + difference);
		return this.elapsed;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
		
}
