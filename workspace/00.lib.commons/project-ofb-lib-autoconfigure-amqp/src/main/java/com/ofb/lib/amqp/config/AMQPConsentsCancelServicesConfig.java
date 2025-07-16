package com.ofb.lib.amqp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration @Slf4j
public class AMQPConsentsCancelServicesConfig {

    @Value("${amqp.ofb_consents_cancel.queue}")
    private String AMQP_OFB_CONSENTS_CANCEL_QUEUE;

    @Value("${amqp.ofb_consents_cancel.exchange}")
    private String AMQP_OFB_CONSENTS_CANCEL_EXCHANGE;

    @Value("${amqp.ofb_consents_cancel.dlq}")
    private String AMQP_OFB_CONSENTS_CANCEL_DLQ;

    @Value("${amqp.ofb_consents_cancel.dlx}")
    private String AMQP_OFB_CONSENTS_CANCEL_DLX;

    /// -------------------------------------------------------------------------
    @Bean
    public Queue deadLetterConsentsCancelActivityTracking() {
        log.info("OFB Messaging Services -->>>  RabbitMQ setting Dead LetterConsentsCancel Queue =[" + AMQP_OFB_CONSENTS_CANCEL_DLQ + "].");
        return QueueBuilder
                .durable(AMQP_OFB_CONSENTS_CANCEL_DLQ)
                .build();
    }
    
    @Bean
    public FanoutExchange deadLetterConsentsCancelExchange() {
        log.info("OFB Messaging Services -->>>  RabbitMQ setting Dead LetterConsentsCancel Exchange =[" + AMQP_OFB_CONSENTS_CANCEL_DLX + "].");
        return ExchangeBuilder
                .fanoutExchange(AMQP_OFB_CONSENTS_CANCEL_DLX)
                .build();
    }
    
    @Bean
    public Binding bindConsentsCancelTrackingActivityDLX(FanoutExchange fanoutExchange) {
        log.info("OFB Messaging Services -->>>  RabbitMQ setting Bind Dead LetterConsentsCancel Exchange");
        return BindingBuilder
                .bind(deadLetterConsentsCancelActivityTracking())
                .to(deadLetterConsentsCancelExchange());
    }

    //-------------------------------------------------------------------------
    @Bean
    public Queue queueActivityConsentsCancelTracking() {
        log.info("OFB Messaging Services -->>>  RabbitMQ setting Dead LetterConsentsCancel Queue in Dead LetterConsentsCancel Exchange =[" +
                AMQP_OFB_CONSENTS_CANCEL_QUEUE + "/" + AMQP_OFB_CONSENTS_CANCEL_DLX + "].");
        return QueueBuilder
                .durable(AMQP_OFB_CONSENTS_CANCEL_QUEUE)
                .deadLetterExchange(AMQP_OFB_CONSENTS_CANCEL_DLX)
                .build();
    }
    
    @Bean
    public FanoutExchange fanoutConsentsCancelExchange() {
        log.info("OFB Messaging Services -->>>  RabbitMQ setting Fanout Exchange =[" + AMQP_OFB_CONSENTS_CANCEL_EXCHANGE + "].");
        return ExchangeBuilder
                .fanoutExchange(AMQP_OFB_CONSENTS_CANCEL_EXCHANGE)
                .build();
    }
    
    @Bean
    public Binding bindConsentsCancelTrackingActivity(FanoutExchange fanoutExchange) {
        log.info("OFB Messaging Services -->>>  RabbitMQ setting Bind Fanout Exchange");
        return BindingBuilder
                .bind(queueActivityConsentsCancelTracking())
                .to(fanoutConsentsCancelExchange());
    }
    //-------------------------------------------------------------------------
    
}
