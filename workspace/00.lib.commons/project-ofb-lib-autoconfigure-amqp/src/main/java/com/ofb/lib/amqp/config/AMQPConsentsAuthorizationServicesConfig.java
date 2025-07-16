package com.ofb.lib.amqp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration @Slf4j
public class AMQPConsentsAuthorizationServicesConfig {

    @Value("${amqp.ofb_consents_authorization.queue}")
    private String AMQP_OFB_CONSENTS_AUTH_QUEUE;

    @Value("${amqp.ofb_consents_authorization.exchange}")
    private String AMQP_OFB_CONSENTS_AUTH_EXCHANGE;

    @Value("${amqp.ofb_consents_authorization.dlq}")
    private String AMQP_OFB_CONSENTS_AUTH_DLQ;

    @Value("${amqp.ofb_consents_authorization.dlx}")
    private String AMQP_OFB_CONSENTS_AUTH_DLX;

    /// -------------------------------------------------------------------------
    @Bean
    public Queue deadLetterConsentsAuthActivityTracking() {
        log.info("OFB Messaging Services -->>>  RabbitMQ setting Dead LetterConsentsAuth Queue =[" + AMQP_OFB_CONSENTS_AUTH_DLQ + "].");
        return QueueBuilder
                .durable(AMQP_OFB_CONSENTS_AUTH_DLQ)
                .build();
    }
    
    @Bean
    public FanoutExchange deadLetterConsentsAuthExchange() {
        log.info("OFB Messaging Services -->>>  RabbitMQ setting Dead LetterConsentsAuth Exchange =[" + AMQP_OFB_CONSENTS_AUTH_DLX + "].");
        return ExchangeBuilder
                .fanoutExchange(AMQP_OFB_CONSENTS_AUTH_DLX)
                .build();
    }
    
    @Bean
    public Binding bindConsentsAuthTrackingActivityDLX(FanoutExchange fanoutExchange) {
        log.info("OFB Messaging Services -->>>  RabbitMQ setting Bind Dead LetterConsentsAuth Exchange");
        return BindingBuilder
                .bind(deadLetterConsentsAuthActivityTracking())
                .to(deadLetterConsentsAuthExchange());
    }

    //-------------------------------------------------------------------------
    @Bean
    public Queue queueActivityConsentsAuthTracking() {
        log.info("OFB Messaging Services -->>>  RabbitMQ setting Dead LetterConsentsAuth Queue in Dead LetterConsentsAuth Exchange =[" +
                AMQP_OFB_CONSENTS_AUTH_QUEUE + "/" + AMQP_OFB_CONSENTS_AUTH_DLX + "].");
        return QueueBuilder
                .durable(AMQP_OFB_CONSENTS_AUTH_QUEUE)
                .deadLetterExchange(AMQP_OFB_CONSENTS_AUTH_DLX)
                .build();
    }
    
    @Bean
    public FanoutExchange fanoutConsentsAuthExchange() {
        log.info("OFB Messaging Services -->>>  RabbitMQ setting Fanout Exchange =[" + AMQP_OFB_CONSENTS_AUTH_EXCHANGE + "].");
        return ExchangeBuilder
                .fanoutExchange(AMQP_OFB_CONSENTS_AUTH_EXCHANGE)
                .build();
    }
    
    @Bean
    public Binding bindConsentsAuthTrackingActivity(FanoutExchange fanoutExchange) {
        log.info("OFB Messaging Services -->>>  RabbitMQ setting Bind Fanout Exchange");
        return BindingBuilder
                .bind(queueActivityConsentsAuthTracking())
                .to(fanoutConsentsAuthExchange());
    }
    //-------------------------------------------------------------------------
    
}
