package com.ofb.lib.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Order(2) //see note above
@Slf4j
public class AppServetFilterConfig implements Filter {

    /* **********************************************************************************
        NOTE about not using @Component and configuring the class with @Order(2):
        The APIs create a @Bean in the 'StartApplication' class to safely
        inject java.servlet.doFilter through the 'FilterRegistrationBean'.

        The 'AppServetFilterConfig' class is injected by the
        LIB dependency 'ofb-lib-autoconfigure-service' in projects.
    *********************************************************************************** */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(httpServletRequest);
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        var ticket    = "";
        var xFapiId   = "";
        var message   = "";

        if (!requestWrapper.getRequestURI().contains("health") &&
            !requestWrapper.getRequestURI().contains("actuator") &&
            !requestWrapper.getRequestURI().contains("swagger") &&
            !requestWrapper.getRequestURI().contains("api-docs")) {

            if (httpServletRequest.getHeader("x-ticket-id") == null) {
                ticket = String.valueOf((int) (Math.random() * 999999999 + 1));
                requestWrapper.addHeader("x-ticket-id", ticket);
                message = "[ *** WARNING *** ]" + "\n\t" +
                        "The request does not have the 'x-ticket-id' header provided and one was generated." + "\n\t" +
                        "The x-ticket-id: '" + ticket + "' was added to the request header";
            } else {
                message = "[ *** INFO *** ]" + "\n\t" +
                        "The request: have the 'x-ticket-id' header provided and was not generated." + "\n\t" +
                        "The x-ticket-id request is: '" + httpServletRequest.getHeader("x-ticket-id") + "'";
            }
            httpServletResponse.addHeader("x-ticket-id", requestWrapper.getHeader("x-ticket-id"));

            if (httpServletRequest.getHeader("x-fapi-interaction-id") == null) {
                xFapiId = UUID.randomUUID().toString();
                requestWrapper.addHeader("x-fapi-interaction-id", UUID.randomUUID().toString());
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
                        "The x-fapi-interaction-id request is: '" + requestWrapper.getHeader("x-fapi-interaction-id") + "'";
            }
            httpServletResponse.addHeader("x-fapi-interaction-id", requestWrapper.getHeader("x-fapi-interaction-id"));


        }

        if (!message.isBlank()) {
            log.info("\n\t" +
                    "[" + System.getProperty("App.Module.Name") + "] " + "\n\t" +
                    "CALL is: " + ((HttpServletRequest) request).getRequestURI() + "\n\t" +
                    "TRACKING ID is: " + requestWrapper.getHeader("x-ticket-id") + " (x-ticket-id)" + "\n\t" +
                    "Step is: doFilter (javax.servlet.Filter)" + "\n\t" + "\n\t" +
                    message
                    + "\n\t"
            );
        }

        chain.doFilter(requestWrapper, httpServletResponse);
    }

    public class HeaderMapRequestWrapper extends HttpServletRequestWrapper {
        public HeaderMapRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        private Map<String, String> headerMap = new HashMap<String, String>();

        public void addHeader(String name, String value) {
            headerMap.put(name, value);
        }

        @Override
        public String getHeader(String name) {
            String headerValue = super.getHeader(name);
            if (headerMap.containsKey(name)) {
                headerValue = headerMap.get(name);
            }
            return headerValue;
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            List<String> names = Collections.list(super.getHeaderNames());
            for (String name : headerMap.keySet()) {
                names.add(name);
            }
            return Collections.enumeration(names);
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            List<String> values = Collections.list(super.getHeaders(name));
            if (headerMap.containsKey(name)) {
                values.add(headerMap.get(name));
            }
            return Collections.enumeration(values);
        }
    }

}