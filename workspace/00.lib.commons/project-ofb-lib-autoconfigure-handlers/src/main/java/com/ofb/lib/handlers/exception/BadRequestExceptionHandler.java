package com.ofb.lib.handlers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestExceptionHandler extends RuntimeException{

	public BadRequestExceptionHandler(String message) {

        super(message);
    }
	
}
