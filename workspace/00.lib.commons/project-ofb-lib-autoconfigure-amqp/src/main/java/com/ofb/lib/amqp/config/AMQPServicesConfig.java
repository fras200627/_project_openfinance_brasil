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
public class AMQPServicesConfig {

    @Value("${amqp.audit_services.audit_queue}")
    private String AMQP_AUDIT_QUEUE;

    @Value("${amqp.audit_services.audit_exchange}")
    private String AMQP_AUDIT_EXCHANGE;

    @Value("${amqp.audit_services.audit_dlq}")
    private String AMQP_AUDIT_DLQ;

    @Value("${amqp.audit_services.audit_dlx}")
    private String AMQP_AUDIT_DLX;

    //-------------------------------------------------------------------------
    @Bean
    public RabbitAdmin createRabbitAdmin(ConnectionFactory conn) {
        log.info("Tican Messaging Services -->>>  RabbitMQ setting Admin configuration");
        return new RabbitAdmin(conn);
    }
    
    @Bean
    public ApplicationListener<ApplicationReadyEvent> startAdmin(RabbitAdmin rabbitAdmin) {
        //log.info("Tican Messaging Services -->>>  RabbitMQ initialize connection");
        return event -> rabbitAdmin.initialize();
    }

    //-------------------------------------------------------------------------
    @Bean
    public Queue deadLetterActivityTracking() {
        log.info("Tican Messaging Services -->>>  RabbitMQ setting Dead Letter Queue =[" + AMQP_AUDIT_DLQ + "].");
        return QueueBuilder
                .nonDurable(AMQP_AUDIT_DLQ)
                .build();
    }
    
    @Bean
    public FanoutExchange deadLetterExchange() {
        log.info("Tican Messaging Services -->>>  RabbitMQ setting Dead Letter Exchange =[" + AMQP_AUDIT_DLX + "].");
        return ExchangeBuilder
                .fanoutExchange(AMQP_AUDIT_DLX)
                .build();
    }
    
    @Bean
    public Binding bindDlxTrackingActivity(FanoutExchange fanoutExchange) {
        log.info("Tican Messaging Services -->>>  RabbitMQ setting Bind Dead Letter Exchange");
        return BindingBuilder
                .bind(deadLetterActivityTracking())
                .to(deadLetterExchange());
    }

    //-------------------------------------------------------------------------
    @Bean
    public Queue queueActivityTracking() {
        log.info("Tican Messaging Services -->>>  RabbitMQ setting Dead Letter Queue in Dead Letter Exchange =[" +
                AMQP_AUDIT_QUEUE + "/" + AMQP_AUDIT_DLX + "].");
        return QueueBuilder
                .nonDurable(AMQP_AUDIT_QUEUE)
                .deadLetterExchange(AMQP_AUDIT_DLX)
                .build();
    }
    
    @Bean
    public FanoutExchange fanoutExchange() {
        log.info("Tican Messaging Services -->>>  RabbitMQ setting Fanout Exchange =[" + AMQP_AUDIT_EXCHANGE + "].");
        return ExchangeBuilder
                .fanoutExchange(AMQP_AUDIT_EXCHANGE)
                .build();
    }
    
    @Bean
    public Binding bindTrackingActivity(FanoutExchange fanoutExchange) {
        log.info("Tican Messaging Services -->>>  RabbitMQ setting Bind Fanout Exchange");
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
