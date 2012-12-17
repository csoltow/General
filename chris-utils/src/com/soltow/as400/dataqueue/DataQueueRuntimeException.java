package com.soltow.as400.dataqueue;

public class DataQueueRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DataQueueRuntimeException(String message, Throwable t) {
		super(message, t);
	}
}
