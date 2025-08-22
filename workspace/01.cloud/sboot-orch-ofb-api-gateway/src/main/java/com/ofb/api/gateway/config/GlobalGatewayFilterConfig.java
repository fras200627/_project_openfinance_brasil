package com.ofb.api.gateway.config;

import com.ofb.lib.amqp.service.MessageService;
import com.ofb.lib.amqp.model.MessageAuditTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.UUID;

@Component @Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalGatewayFilterConfig implements GlobalFilter, Ordered {

    @Autowired private
    MessageService messageService;

    @Override
    public int getOrder() {
        return -2;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();

        var ticket    = "";
        var xFapiId   = "";
        var message   = "";

        if (!request.getPath().toString().contains("health") &&
            !request.getPath().toString().contains("actuator") &&
            !request.getPath().toString().contains("swagger") &&
            !request.getPath().toString().contains("api-docs") &&
            !request.getPath().toString().contains("/.well-known/openid-configuration") &&
            !request.getPath().toString().contains("/oauth2/jwks")
        ) {

            if (request.getHeaders().containsKey("x-ticket-id") == false) {
                ticket = String.valueOf((int) (Math.random() * 999999999 + 1));
                request.mutate().header("x-ticket-id", ticket).build();
                message = "[ *** WARNING *** ]" + "\n\t" +
                        "The request does not have the 'x-ticket-id' header provided and one was generated." + "\n\t" +
                        "The x-ticket-id: '" + ticket + "' was added to the request header";
            } else {
                message = "[ *** INFO *** ]" + "\n\t" +
                        "The request: have the 'x-ticket-id' header provided and was not generated." + "\n\t" +
                        "The x-ticket-id request is: '" + request.getHeaders().get("x-ticket-id") + "'";
            }
            if (request.getHeaders().containsKey("x-fapi-interaction-id") == false) {
                xFapiId = UUID.randomUUID().toString();
                request.mutate().header("x-fapi-interaction-id", xFapiId);
                if (!message.isBlank()) {
                    message = message + "\n\t";
                }
                message = message +
                        "[ *** WARNING *** ]" + "\n\t" +
                        "The request: does not have the 'x-fapi-interaction-id' header provided and one was generated." + "\n\t" +
                        "The x-fapi-interaction-id: '" + xFapiId + "' was added to the request header" + "'";
            } else {
                if (!message.isBlank()) {
                    message = message + "\n\t";
                }
                message = message +
                        "[ *** INFO *** ]" + "\n\t" +
                        "The request: have the 'x-fapi-interaction-id' header provided and was not generated." + "\n\t" +
                        "The x-fapi-interaction-id request is: '" + request.getHeaders().get("x-fapi-interaction-id") + "'";
            }

            if (!message.isBlank()) {
                message = "\n\t" +
                        "[" + System.getProperty("App.Module.Name") + "] " + "\n\t" +
                        "CALL is: " + request.getURI().getPath() + "\n\t" +
                        "TRACKING ID is: " + ticket + " (x-ticket-id)" + "\n\t" +
                        "Step is: filter (GlobalFilter)" + "\n\t" + "\n\t" +
                        message
                        + "\n\t";

                log.info(message);
            }

            String userRequest = "";
//            if (request.getPath().toString().contains("/oauth2/token") ||
//                    request.getPath().toString().contains("/oauth2/introspect") ||
//                    request.getPath().toString().contains("/oauth2/revoke")) {
//                String[] decodedBasicAuth = new String(Base64.getDecoder().decode(
//                        request.getHeaders()
//                                .get("authorization").toString()
//                                .replaceAll("Basic ", "")
//                                .replaceAll("\\[", "")
//                                .replaceAll("\\]", ""))
//                ).split(":");
//                userRequest = decodedBasicAuth[0];
//            } else {
//                JWT jwt = null;
//                try {
//                    jwt = JWTParser.parse(request.getHeaders().get("authorization").toString().replace("Bearer", ""));
//                } catch (java.text.ParseException e) {
//                    throw new RuntimeException(e);
//                }
//                userRequest = ((SignedJWT) jwt).getPayload().toJSONObject().get("sub").toString();
//            }

            //Post a message Audit in RabbitMQ
            MessageAuditTemplate messageAuditTemplate = new MessageAuditTemplate(
                    request.getHeaders().get("x-ticket-id").toString().replaceAll("\\[", "")
                            .replaceAll("\\]", ""),
                    request.getHeaders().get("x-fapi-interaction-id").toString().replaceAll("\\[", "")
                            .replaceAll("\\]", ""),
                    LocalDateTime.now().atOffset(ZoneOffset.UTC).toString(),
                    request.getURI().getPath(),
                    System.getProperty("App.Module.Name"),
                    request.getMethod().toString(),
                    userRequest,
                    message.replaceAll("\n\t", " | "));

            messageService.sendMessageAuditTemplate(messageAuditTemplate);
        }

        return chain.filter(exchange.mutate().request(request).build());
    }

}