package com.ofb.resources.camel.processors;

import com.ofb.lib.handlers.exception.ofb.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component @Slf4j
public class IllegalArgumentExceptionThrowingProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        log.error("Exception Thrown");
        exchange.getMessage().setBody(exchange.getProperty(Exchange.EXCEPTION_CAUGHT, BadRequestException.class));
//        throw new BadRequestException("An exception happened on purpose");
    }
}