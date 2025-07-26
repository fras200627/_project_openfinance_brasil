package com.ofb.lib.amqp.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration @Slf4j
public class AMQPQueuesConfig {

	@Value("${amqp.ofb.exchange-direct}")
	private String OFB_EXCHANGE_DIRECT;

	/// -----------------------------------------------------------------------------------
	@Value("${amqp.ofb.audit.http-requests.queue}")
	private String AUDIT_HTTP_REQUESTS_QUEUE;

	@Value("${amqp.ofb.audit.http-requests.routing-key}")
	private String AUDIT_HTTP_REQUESTS_ROUTING_KEY;

	@Value("${amqp.ofb.audit.consents-authorization.queue}")
	private String AUDIT_CONSENTS_AUTHORIZATION_QUEUE;

	@Value("${amqp.ofb.audit.consents-authorization.routing-key}")
	private String AUDIT_CONSENTS_AUTHORIZATION_ROUTING_KEY;

	@Value("${amqp.ofb.audit.consents-cancellation.queue}")
	private String AUDIT_CONSENTS_CANCELLATION_QUEUE;

	@Value("${amqp.ofb.audit.consents-cancellation.routing-key}")
	private String AUDIT_CONSENTS_CANCELLATION_ROUTING_KEY;

	@Value("${amqp.ofb.audit.consents-revoke.queue}")
	private String AUDIT_CONSENTS_REVOKED_QUEUE;

	@Value("${amqp.ofb.audit.consents-revoke.routing-key}")
	private String AUDIT_CONSENTS_REVOKED_ROUTING_KEY;
	
	/// -----------------------------------------------------------------------------------
	@Value("${amqp.ofb.consents.authorization.queue}")
	private String CONSENTS_AUTHORIZATION_QUEUE;

	@Value("${amqp.ofb.consents.authorization.routing-key}")
	private String CONSENTS_AUTHORIZATION_ROUTING_KEY;

	@Value("${amqp.ofb.consents.cancellation.queue}")
	private String CONSENTS_CANCELLATION_QUEUE;

	@Value("${amqp.ofb.consents.cancellation.routing-key}")
	private String CONSENTS_CANCELLATION_ROUTING_KEY;

    @Bean @Qualifier("direct")
	public DirectExchange direct() {
		return new DirectExchange(OFB_EXCHANGE_DIRECT);
	}

	/// ----------------------------------------------------------------------------------
	@Bean
	public Queue ofbAuditHttpRequestsQueue() {
		return new Queue(AUDIT_HTTP_REQUESTS_QUEUE);
	}
	@Bean
	public Binding binding1(DirectExchange direct, Queue ofbAuditHttpRequestsQueue) {
		return BindingBuilder.bind(ofbAuditHttpRequestsQueue).to(direct).with(AUDIT_HTTP_REQUESTS_ROUTING_KEY);
	}

	/// ----------------------------------------------------------------------------------
	@Bean
	public Queue ofbAuditConsentsAuthorization() {
		return new Queue(AUDIT_CONSENTS_AUTHORIZATION_QUEUE);
	}
	@Bean
	public Binding binding2(DirectExchange direct, Queue ofbAuditConsentsAuthorization) {
		return BindingBuilder.bind(ofbAuditConsentsAuthorization).to(direct).with(AUDIT_CONSENTS_AUTHORIZATION_ROUTING_KEY);
	}

	/// ----------------------------------------------------------------------------------
	@Bean
	public Queue ofbAuditConsentsCancellation() {
		return new Queue(AUDIT_CONSENTS_CANCELLATION_QUEUE);
	}
	@Bean
	public Binding binding3(DirectExchange direct, Queue ofbAuditConsentsCancellation) {
		return BindingBuilder.bind(ofbAuditConsentsCancellation).to(direct).with(AUDIT_CONSENTS_CANCELLATION_ROUTING_KEY);
	}

	/// ----------------------------------------------------------------------------------
	@Bean
	public Queue ofbConsentsAuthorization() {
		return new Queue(CONSENTS_AUTHORIZATION_QUEUE);
	}
	@Bean
	public Binding binding5(DirectExchange direct, Queue ofbConsentsAuthorization) {
		return BindingBuilder.bind(ofbConsentsAuthorization).to(direct).with(CONSENTS_AUTHORIZATION_ROUTING_KEY);
	}

	/// ----------------------------------------------------------------------------------
	@Bean
	public Queue ofbConsentsCancellation() {
		return new Queue(CONSENTS_CANCELLATION_QUEUE);
	}
	@Bean
	public Binding binding4(DirectExchange direct, Queue ofbConsentsCancellation) {
		return BindingBuilder.bind(ofbConsentsCancellation).to(direct).with(CONSENTS_CANCELLATION_ROUTING_KEY);
	}

	/// ----------------------------------------------------------------------------------
	@Bean
	public Queue ofbAuditConsentsRevoked() {
		return new Queue(AUDIT_CONSENTS_REVOKED_QUEUE);
	}
	@Bean
	public Binding binding6(DirectExchange direct, Queue ofbAuditConsentsRevoked) {
		return BindingBuilder.bind(ofbAuditConsentsRevoked).to(direct).with(CONSENTS_CANCELLATION_ROUTING_KEY);
	}
	/// ----------------------------------------------------------------------------------

}
