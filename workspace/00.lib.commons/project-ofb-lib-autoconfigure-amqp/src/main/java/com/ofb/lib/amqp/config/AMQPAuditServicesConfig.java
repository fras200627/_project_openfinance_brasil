package com.ofb.lib.amqp.config;

import com.ofb.lib.amqp.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration @Slf4j
public class AMQPAuditServicesConfig {

    @Value("${amqp.audit_services.queue}")
    private String AMQP_AUDIT_QUEUE;

    @Value("${amqp.audit_services.exchange}")
    private String AMQP_AUDIT_EXCHANGE;

    @Value("${amqp.audit_services.dlq}")
    private String AMQP_AUDIT_DLQ;

    @Value("${amqp.audit_services.dlx}")
    private String AMQP_AUDIT_DLX;

    //-------------------------------------------------------------------------
    @Bean
    public RabbitAdmin createRabbitAdmin(ConnectionFactory conn) {
        log.info("OFB Messaging Services -->>>  RabbitMQ setting Admin configuration");
        return new RabbitAdmin(conn);
    }
    
    @Bean
    public ApplicationListener<ApplicationReadyEvent> startAdmin(RabbitAdmin rabbitAdmin) {
        //log.info("OFB Messaging Services -->>>  RabbitMQ initialize connection");
        return event -> rabbitAdmin.initialize();
    }

    /// -------------------------------------------------------------------------
    @Bean
    public Queue deadLetterActivityTracking() {
        log.info("OFB Messaging Services -->>>  RabbitMQ setting Dead Letter Queue =[" + AMQP_AUDIT_DLQ + "].");
        return QueueBuilder
                .durable(AMQP_AUDIT_DLQ)
                .build();
    }
    
    @Bean
    public FanoutExchange deadLetterExchange() {
        log.info("OFB Messaging Services -->>>  RabbitMQ setting Dead Letter Exchange =[" + AMQP_AUDIT_DLX + "].");
        return ExchangeBuilder
                .fanoutExchange(AMQP_AUDIT_DLX)
                .build();
    }
    
    @Bean
    public Binding bindDlxTrackingActivity(FanoutExchange fanoutExchange) {
        log.info("OFB Messaging Services -->>>  RabbitMQ setting Bind Dead Letter Exchange");
        return BindingBuilder
                .bind(deadLetterActivityTracking())
                .to(deadLetterExchange());
    }

    //-------------------------------------------------------------------------
    @Bean
    public Queue queueActivityTracking() {
        log.info("OFB Messaging Services -->>>  RabbitMQ setting Dead Letter Queue in Dead Letter Exchange =[" +
                AMQP_AUDIT_QUEUE + "/" + AMQP_AUDIT_DLX + "].");
        return QueueBuilder
                .durable(AMQP_AUDIT_QUEUE)
                .deadLetterExchange(AMQP_AUDIT_DLX)
                .build();
    }
    
    @Bean
    public FanoutExchange fanoutExchange() {
        log.info("OFB Messaging Services -->>>  RabbitMQ setting Fanout Exchange =[" + AMQP_AUDIT_EXCHANGE + "].");
        return ExchangeBuilder
                .fanoutExchange(AMQP_AUDIT_EXCHANGE)
                .build();
    }
    
    @Bean
    public Binding bindTrackingActivity(FanoutExchange fanoutExchange) {
        log.info("OFB Messaging Services -->>>  RabbitMQ setting Bind Fanout Exchange");
        return BindingBuilder
                .bind(queueActivityTracking())
                .to(fanoutExchange());
    }

    //-------------------------------------------------------------------------
    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return  new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter messageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return  rabbitTemplate;
    }

    @Bean
    public MessageService messageService() {
        return new MessageService();
    }

}
