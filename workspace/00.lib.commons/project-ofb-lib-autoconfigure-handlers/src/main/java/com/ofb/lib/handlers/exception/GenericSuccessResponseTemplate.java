package com.ofb.lib.handlers.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public record GenericSuccessResponseTemplate(

        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        LocalDateTime timestamp,
        String ticket,
        String xFapiInteractionId,
        String status,
        Object message) {

        public GenericSuccessResponseTemplate(HttpServletRequest request, HttpStatus httpStatus, Object message) {
                this(
                        LocalDateTime.now(),
                        request.getHeader("x-ticket-id").toString(),
                        request.getHeader("x-fapi-interaction-id").toString(),
                        httpStatus.toString(),
                        message
                );
        }

}
