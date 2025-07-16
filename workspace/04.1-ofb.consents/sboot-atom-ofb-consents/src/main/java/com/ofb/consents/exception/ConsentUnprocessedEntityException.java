package com.ofb.consents.exception;

public class ConsentUnprocessedEntityException extends RuntimeException {
	public ConsentUnprocessedEntityException(String errorDetails) {
        super(errorDetails);
    }
}
