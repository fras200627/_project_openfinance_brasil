package com.ofb.consents.exception;

public class ConsentResponseErrorException extends RuntimeException {
	public ConsentResponseErrorException(String errorDetails) {
        super(errorDetails);
    }
}
