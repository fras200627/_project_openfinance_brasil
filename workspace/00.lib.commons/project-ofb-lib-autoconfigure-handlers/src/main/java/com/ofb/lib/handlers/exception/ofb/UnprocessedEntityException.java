package com.ofb.lib.handlers.exception.ofb;

public class UnprocessedEntityException extends RuntimeException {
	public UnprocessedEntityException(String errorDetails) {
        super(errorDetails);
    }
}
