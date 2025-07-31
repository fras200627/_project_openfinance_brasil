package com.ofb.lib.amqp.config;

import com.ofb.lib.amqp.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration @Slf4j
public class AMQPDefaultConfig {

//	@Bean
//	public RabbitAdmin createRabbitAdmin(ConnectionFactory conn) {
//		log.info("OFB Messaging Services -->>>  RabbitMQ setting Admin configuration");
//		return new RabbitAdmin((org.springframework.amqp.rabbit.connection.ConnectionFactory) conn);
//	}
//	@Bean
//	public ConnectionFactory connectionFactory() {
//		return new ConnectionFactory();
//	}
//
//	@Bean
//	public ApplicationListener<ApplicationReadyEvent> startAdmin(RabbitAdmin rabbitAdmin) {
//		log.info("OFB Messaging Services -->>>  RabbitMQ initialize connection");
//		return event -> rabbitAdmin.initialize();
//	}

//	@Bean
//	public Jackson2JsonMessageConverter messageConverter(){
//		return  new Jackson2JsonMessageConverter();
//	}
//
//	@Bean
//	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
//										 Jackson2JsonMessageConverter messageConverter){
//		RabbitTemplate rabbitTemplate = new RabbitTemplate((org.springframework.amqp.rabbit.connection.ConnectionFactory) connectionFactory);
//		rabbitTemplate.setMessageConverter(messageConverter);
//		return  rabbitTemplate;
//	}

	@Bean
	public MessageService messageService() {
		return new MessageService();
	}

}
