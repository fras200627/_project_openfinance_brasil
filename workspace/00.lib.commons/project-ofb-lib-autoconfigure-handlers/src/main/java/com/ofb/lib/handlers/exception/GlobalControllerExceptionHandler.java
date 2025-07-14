package com.ofb.lib.handlers.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

@ControllerAdvice @Slf4j
public class GlobalControllerExceptionHandler {

    @Autowired private
    HttpServletRequest request;

    private void writeError(String className, String message, String[] messageDetails) {

        try {
            log.error("[" + System.getProperty("App.Module.Name") + "]\n\t" +
                    "[tracking error: {" +
                    "x-fapi-interaction-id: " + request.getAttribute("x-fapi-interaction-id") + "," +
                    "x-ticket-id: " + request.getAttribute("x-ticket-id") + "," +
                    "start: " + request.getAttribute("start") + "," +
                    "end: " + LocalDateTime.now().atOffset(ZoneOffset.UTC).toString() + "," +
                    "executeTime: " + (System.currentTimeMillis() - (long) request.getAttribute("begin")) + "ms" +
                    "}]" + "]\n\t" +
                    "[ERROR: " + className + (message == null ? "" : message) + "] " +
                    "Error details [" + (messageDetails == null ? "" : messageDetails[0]) + "]"
            );
        } catch (Exception e) {
            log.error("[" + System.getProperty("App.Module.Name") + "]\n\t" +
                    e.getMessage() + "]");
        }

        request.setAttribute("ERROR", true);
    }

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleException(IllegalArgumentException ex) {
        String sourceClass = String.valueOf(Arrays.stream(ex.getStackTrace()).findFirst());
        sourceClass =  sourceClass.substring(sourceClass.indexOf("[") +1, sourceClass.indexOf(".fromValue"));
        sourceClass = sourceClass.substring(sourceClass.lastIndexOf(".") +1).replace("Param", "");

        String[] message = ex.getMessage().split(";");
        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request,
                HttpStatus.BAD_REQUEST,
                        HttpStatus.BAD_REQUEST.name(),
                        "[Parameter Error: " + sourceClass +"] Value of " +
                                ex.getMessage().replace("Unexpected value", "") +
                                " is not valid. Please check and try again."),
                        HttpStatus.BAD_REQUEST);

    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleException(ConstraintViolationException ex) {
        String msgException =  ex.getMessage().replaceAll(":", "").substring(ex.getMessage().indexOf(".") + 1);

        String[] message = ex.getMessage().split(";");
        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request,
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                "[Parameter Error] Value of " + msgException +
                ". Please check and try again."),
                HttpStatus.BAD_REQUEST);

    }
    /* ------------------------------------------------------------------------ */

}