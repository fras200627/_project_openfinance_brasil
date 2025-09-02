package com.ofb.authorization.config;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Slf4j
public class CustomFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest  httpRequest  = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        var ticket    = "";
        var xFapiId   = "";
        var message   = "";

        if (!httpRequest.getRequestURI().contains("health") &&
            !httpRequest.getRequestURI().toString().contains("actuator") &&
            !httpRequest.getRequestURI().toString().contains("swagger") &&
            !httpRequest.getRequestURI().toString().contains("api-docs") 
        ) {

            /* ****************************************************************************************************
            At this point, a final service check is made of the x-ticket-id and x-fapi-interaction-id information.
            If they are not in the REQUEST Headers, they are inserted in the RESPONSE and ensure that the call
            will have the response identified and the AUDIT records will be made correctly. ******************** */
            if (httpRequest.getHeader("x-ticket-id") == null) {
                ticket = String.valueOf((int) (Math.random() * 999999999 + 1));
                ((HttpServletResponse) response).addHeader("x-ticket-id", ticket);
                message = "[ *** WARNING *** ]" + "\n\t" +
                        "The request does not have the 'x-ticket-id' header provided and one was generated." + "\n\t" +
                        "The x-ticket-id: '" + ticket + "' was added to the request header";
            } else {
                ((HttpServletResponse) response).addHeader("x-ticket-id", httpRequest.getHeader("x-ticket-id"));
                message = "[ *** INFO *** ]" + "\n\t" +
                        "The request: have the 'x-ticket-id' header provided and was not generated." + "\n\t" +
                        "The x-ticket-id request is: '" + httpRequest.getHeader("x-ticket-id") + "'";                   
            }

            if (httpRequest.getHeader("x-fapi-interaction-id") == null) {
                xFapiId = UUID.randomUUID().toString();
                ((HttpServletResponse) response).addHeader("x-fapi-interaction-id", xFapiId);

                if (!message.isBlank()) {
                    message = message + "\n\t";
                }
                message = message +
                        "[ *** WARNING *** ]" + "\n\t" +
                        "The request: does not have the 'x-fapi-interaction-id' header provided and one was generated." + "\n\t" +
                        "The x-fapi-interaction-id: '" + xFapiId + "' was added to the request header" + "'";
            } else {
                ((HttpServletResponse) response).addHeader("x-fapi-interaction-id", httpRequest.getHeader("x-fapi-interaction-id"));
                if (!message.isBlank()) {
                    message = message + "\n\t";
                }
                message = message +
                        "[ *** INFO *** ]" + "\n\t" +
                        "The request: have the 'x-fapi-interaction-id' header provided and was not generated." + "\n\t" +
                        "The x-fapi-interaction-id request is: '" + httpRequest.getHeader("x-fapi-interaction-id") + "'";
            }

            if (!message.isBlank()) {
                message = "\n\t" +
                        "[" + System.getProperty("App.Module.Name") + "] " + "\n\t" +
                        "CALL is: " + httpRequest.getRequestURI() + "\n\t" +
                        "TRACKING ID is: " + ticket + " (x-ticket-id)" + "\n\t" +
                        "Step is: filter (javax.servlet.doFilter)" + "\n\t" + "\n\t" +
                        message
                        + "\n\t";

                log.info(message);
            }

            //Create a atrributes for register a message Audit in RabbitMQ
            request.setAttribute("x-ticket-id", ((HttpServletResponse) response).getHeader("x-ticket-id").toString().replaceAll("\\[", "")
                    .replaceAll("\\]", ""));
            request.setAttribute("x-fapi-interaction-id", httpResponse.getHeader("x-fapi-interaction-id").toString().replaceAll("\\[", "")
                    .replaceAll("\\]", ""));
            request.setAttribute("requestDateTime", LocalDateTime.now().atOffset(ZoneOffset.UTC).toString());
            request.setAttribute("requestURI", httpRequest.getRequestURI());
            request.setAttribute("requestSource", System.getProperty("App.Module.Name"));
            request.setAttribute("requestMethod", httpRequest.getMethod().toString());
            request.setAttribute("requestUserName", httpRequest.getUserPrincipal().getName());
            request.setAttribute("requestPayload", message.replaceAll("\n\t", " | "));
            
        }
        
        chain.doFilter(request, response);
    }

}