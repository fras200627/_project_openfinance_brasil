package com.tican.lib.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class GenericInterceptorHandler implements HandlerInterceptor  {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        if (!request.getRequestURI().contains("health") &&
            !request.getRequestURI().contains("actuator") &&
            !request.getRequestURI().contains("swagger") &&
            !request.getRequestURI().contains("api-docs") &&
            !request.getRequestURI().contains("/error")) {

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> requestPack       = new HashMap<>();
            Map<String, String> requestComponent  = new HashMap<>();
            Map<String, String> requestTracking   = new HashMap<>();
            Map<String, String> requestContent    = new HashMap<>();

            try {
                JWT jwt = JWTParser.parse(request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer", ""));

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
                calendar.setTimeInMillis((Long) ((SignedJWT) jwt).getPayload().toJSONObject().get("exp") * 1000);

                /* ****************************************************************************************************
                At this point, a final security check is made of the x-ticket-id and x-fapi-interaction-id information.
                If they are not in the REQUEST Headers, they are inserted in the RESPONSE and ensure that the call
                will have the response identified and the AUDIT records will be made correctly. ******************** */
                if (request.getHeader("x-ticket-id") == null) {
                    String ticket = String.valueOf((int) (Math.random() * 999999999 + 1));
                    response.addHeader("x-ticket-id", ticket);
                }

                if (request.getHeader("x-fapi-interaction-id") == null) {
                    String xFapiId = UUID.randomUUID().toString();
                    response.addHeader("x-fapi-interaction-id", xFapiId);
                }
                /* ************************************************************************************************* */

                requestTracking.put("x-ticket-id",              request.getHeader("x-ticket-id"));
                requestTracking.put("x-fapi-interaction-id",    request.getHeader("x-fapi-interaction-id"));

                requestComponent.put("App.Module.Name",         System.getProperty("App.Module.Name"));
                requestComponent.put("App.Build.Version",       System.getProperty("App.Build.Version"));
                requestComponent.put("App.Build.Date",          System.getProperty("App.Build.Date"));

                requestContent.put("x-ticket-id",               request.getHeader("x-ticket-id"));
                requestContent.put("x-fapi-interaction-id",     request.getHeader("x-fapi-interaction-id"));
                requestContent.put("requestDateTime",           LocalDateTime.now().atOffset(ZoneOffset.UTC).toString());
                requestContent.put("requestRemoteAddr",         request.getRemoteAddr());
                requestContent.put("requestRemoteHost",         request.getRemoteHost());
                requestContent.put("requestRemotePort",         String.valueOf(request.getRemotePort()));
                requestContent.put("requestServerName",         request.getServerName());
                requestContent.put("requestServerPort",         String.valueOf(request.getServerPort()));
                requestContent.put("requestURL",                request.getRequestURL().toString());
                requestContent.put("requestURI",                request.getRequestURI());
                requestContent.put("requestMethod",             request.getMethod());
                requestContent.put("requestUserName",           request.getRemoteUser());
                requestContent.put("requestUserScope",          "{" + ((JSONArray) ((SignedJWT) jwt).getPayload().toJSONObject().get("scope")).toJSONString() + "}");
                requestContent.put("requestExpiration",         calendar.getTime().toString());

                requestPack.put("trackingId",                   objectMapper.writeValueAsString(requestTracking));
                requestPack.put("component",                    objectMapper.writeValueAsString(requestComponent));
                requestPack.put("content",                      objectMapper.writeValueAsString(requestContent));

                request.setAttribute("start",                   LocalDateTime.now().atOffset(ZoneOffset.UTC).toString());
                request.setAttribute("begin",                   System.currentTimeMillis());
                request.setAttribute("x-ticket-id",             request.getHeader("x-ticket-id"));
                request.setAttribute("x-fapi-interaction-id",   request.getHeader("x-fapi-interaction-id"));
                request.setAttribute("requestDateTime",         LocalDateTime.now().atOffset(ZoneOffset.UTC).toString());
                request.setAttribute("requestURI",              request.getRequestURI());
                request.setAttribute("requestSource",           System.getProperty("App.Module.Name"));
                request.setAttribute("requestMethod",           request.getMethod());
                request.setAttribute("requestUserName",         request.getRemoteUser());
                request.setAttribute("requestPayload",          requestPack.toString());

                log.info("\n\t" +
                        "[" + System.getProperty("App.Module.Name") + "] " + "\n\t" +
                        "CALL is: " + ((HttpServletRequest) request).getRequestURI() + "\n\t" +
                        "TRACKING ID is: " + request.getHeader("x-ticket-id") + " (x-ticket-id)" + "\n\t" +
                        "Step is: preHandle (HandlerInterceptor)" + "\n\t" + "\n\t" +
                        "Payload is: " + "\n\t" +
                        request.getAttribute("requestPayload") + "\n\t");
            } catch (Exception e) {
                log.error("\n\t" +
                        "[" + System.getProperty("App.Module.Name") + "] " + "\n\t" +
                        "CALL is: " + ((HttpServletRequest) request).getRequestURI() + "\n\t" +
                        "TRACKING ID is: " + request.getHeader("x-ticket-id") + " (x-ticket-id)" + "\n\t" +
                        "Step is: preHandle (HandlerInterceptor)" + "\n\t" + "\n\t" +
                        "ERROR: " + "\n\t" +
                        e.getMessage() + "\n\t");
            }

        }

        return true;
    }

    // Not necessary
    // @Override
    // public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
    //                        ModelAndView modelAndView) throws Exception {
    //
    //     if (!request.getRequestURI().contains("health") &&
    //             !request.getRequestURI().contains("actuator") &&
    //             !request.getRequestURI().contains("swagger") &&
    //             !request.getRequestURI().contains("api-docs") &&
    //             request.getAttribute("ERROR") == null) {
    //
    //
    //         log.info("[" + System.getProperty("App.Module.Name") + "]\n\t" +
    //                 "[tracking: {" +
    //                 "x-fapi-interaction-id: "   + request.getAttribute("x-fapi-interaction-id") + "," +
    //                 "ticket: "                  + request.getAttribute("x-ticket-id") + "," +
    //                 "start: "                   + request.getAttribute("start") + "," +
    //                 "end: "                     + LocalDateTime.now().atOffset(ZoneOffset.UTC).toString() + "," +
    //                 "executeTime: "             + (System.currentTimeMillis() - (long) request.getAttribute("begin")) + "ms" +
    //                 "}]"
    //         );
    //     }
    // }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception exception) throws Exception {

        if (!request.getRequestURI().contains("health") &&
                !request.getRequestURI().contains("actuator") &&
                !request.getRequestURI().contains("swagger") &&
                !request.getRequestURI().contains("api-docs") &&
                request.getAttribute("ERROR") == null &&
                exception == null) {

            log.info("\n\t" +
                    "[" + System.getProperty("App.Module.Name") + "] " + "\n\t" +
                    "CALL is: " + ((HttpServletRequest) request).getRequestURI() + "\n\t" +
                    "TRACKING ID is: " + request.getHeader("x-ticket-id") + " (x-ticket-id)" + "\n\t" +
                    "Step is: afterCompletion (HandlerInterceptor)" + "\n\t" + "\n\t" +
                    "x-fapi-interaction-id: "   + response.getHeader("x-fapi-interaction-id") + "\n\t" +
                    "x-ticket-id: "             + response.getHeader("x-ticket-id") + "\n\t" +
                    "start: "                   + request.getAttribute("start") + "\n\t" +
                    "end: "                     + LocalDateTime.now().atOffset(ZoneOffset.UTC).toString() + "\n\t" +
                    "executeTime: "             + (System.currentTimeMillis() - (long) request.getAttribute("begin")) + "ms" + "\n\t"
            );
        }

    }

    private String getParameters(HttpServletRequest request) {
        StringBuffer posted = new StringBuffer();
        Enumeration<?> e = request.getParameterNames();
        if (e != null) {
            posted.append("?");
        }
        while (e.hasMoreElements()) {
            if (posted.length() > 1) {
                posted.append("&");
            }
            String curr = (String) e.nextElement();
            posted.append(curr + "=");
            if (curr.contains("password")
                    || curr.contains("pass")
                    || curr.contains("pwd")) {
                posted.append("*****");
            } else {
                posted.append(request.getParameter(curr));
            }
        }
        String ip = request.getHeader("X-FORWARDED-FOR");
        String ipAddr = (ip == null) ? getRemoteAddr(request) : ip;
        if (ipAddr!=null && !ipAddr.equals("")) {
            posted.append("&_psip=" + ipAddr);
        }
        return posted.toString();
    }

    private String getRemoteAddr(HttpServletRequest request) {
        String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
        if (ipFromHeader != null && ipFromHeader.length() > 0) {
            log.debug("ip from proxy - X-FORWARDED-FOR : " + ipFromHeader);
            return ipFromHeader;
        }
        return request.getRemoteAddr();
    }

}
