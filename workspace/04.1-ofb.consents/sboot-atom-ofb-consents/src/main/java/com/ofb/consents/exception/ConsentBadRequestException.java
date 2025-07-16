package com.ofb.consents.exception;

public class ConsentBadRequestException extends RuntimeException {
	public ConsentBadRequestException(String errorDetails) {
        super(errorDetails);
    }
}
