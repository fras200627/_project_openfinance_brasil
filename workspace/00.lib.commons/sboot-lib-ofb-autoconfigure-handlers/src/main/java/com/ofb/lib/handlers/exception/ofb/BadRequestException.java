package com.ofb.lib.handlers.exception.ofb;

public class BadRequestException extends RuntimeException {
	public BadRequestException(String errorDetails) {
        super(errorDetails);
    }
}
