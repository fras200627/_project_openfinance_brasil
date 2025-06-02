package com.tican.lib.handlers.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestControllerAdvice @Slf4j
public class GlobalRestControllerExceptionHandler {

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
                    "}]\n\t" +
                    "[ERROR: " + className + (message == null ? "" : message) + "]\n\t" +
                    "[Error details: " + (messageDetails == null ? "" : messageDetails[0]) + "]\n\t"
            );
        } catch (Exception e) {
            log.error("[" + System.getProperty("App.Module.Name") + "]\n\t" +
                    e.getMessage() + "]");
        }

        request.setAttribute("ERROR", true);
    }

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(BadJwtException.class)
    public ResponseEntity handleException(BadJwtException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request,
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                message[0]),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(JwtException.class)
    public ResponseEntity handleException(JwtException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request,
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                message[0]),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(JwtValidationException.class)
    public ResponseEntity handleException(JwtValidationException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request,
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                message[0]),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(OAuth2AuthenticationException.class)
    public ResponseEntity handleException(OAuth2AuthenticationException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request,
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                message[0]),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(InvalidBearerTokenException.class)
    public ResponseEntity handleException(InvalidBearerTokenException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        return new ResponseEntity(new GenericErrorResponseTemplate(request,
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                message[0]),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleException(AccessDeniedException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request,
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                message[0]),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(AuthorizationServiceException.class)
    public ResponseEntity handleException(AuthorizationServiceException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request,
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                message[0]),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity handleException(UsernameNotFoundException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request,
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                message[0]),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(LockedException.class)
    public ResponseEntity handleException(LockedException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request,
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                message[0]),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(AccountExpiredException.class)
    public ResponseEntity handleException(AccountExpiredException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request,
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                message[0]),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity handleException(InternalAuthenticationServiceException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request,
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                message[0]),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity handleException(AuthenticationCredentialsNotFoundException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request,
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                message[0]),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(AccountStatusException.class)
    public ResponseEntity handleException(AccountStatusException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request,
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                message[0]),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handleException(BadCredentialsException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request,
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                message[0]),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(CredentialsExpiredException.class)
    public ResponseEntity handleException(CredentialsExpiredException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request,
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                message[0]),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(DisabledException.class)
    public ResponseEntity handleException(DisabledException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request,
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                message[0]),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity handleException(InsufficientAuthenticationException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request,
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                message[0]),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(AuthenticationServiceException.class)
    public ResponseEntity handleException(AuthenticationServiceException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request,
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                message[0]),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity handleException(MethodArgumentTypeMismatchException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request, 
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                message[0]),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(MethodArgumentConversionNotSupportedException.class)
    public ResponseEntity handleException(MethodArgumentConversionNotSupportedException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request, 
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                message[0]),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleException(HttpMessageNotReadableException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request, 
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                message[0]),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity handleException(HttpMessageConversionException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request, 
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                message[0]),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(HttpMessageNotWritableException.class)
    public ResponseEntity handleException(HttpMessageNotWritableException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request, 
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                message[0]),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity handleException(MissingPathVariableException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request, 
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                message[0]),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(MissingRequestValueException.class)
    public ResponseEntity handleException(MissingRequestValueException ex){
        String message = ((MissingServletRequestParameterException) ex).getParameterName();
        String[] messageDetails = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), messageDetails );

        return new ResponseEntity(new GenericErrorResponseTemplate(request, 
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.name(),
                "[Parameter Error] Value of " +
                        message + " is not valid. " +
                "Please check and try again."),
                HttpStatus.BAD_REQUEST);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleException(MethodArgumentNotValidException ex) {
        var errorList = ex.getFieldErrors();
        String[] messageDetails = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), errorList.stream().map(buildBobyResponse::new).toList().toString() , messageDetails);

        return ResponseEntity.badRequest().body(
                new BadResponseTemplate(request,
                        HttpStatus.BAD_REQUEST,
                        "The payload is invalid. Check the error(s) displayed in the 'message' field.",
                        errorList.stream().map(buildBobyResponse::new).toList()));
    }
    private record buildBobyResponse(String field, String message) {
        public buildBobyResponse(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(BadRequestExceptionHandler.class)
    public ResponseEntity handleException(BadRequestExceptionHandler ex) {
        String[] messageDetails = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), messageDetails);

        return ResponseEntity.badRequest().body(new BadResponseTemplate(request,
                HttpStatus.BAD_REQUEST,
                "The payload or request is invalid. Check the error(s) displayed in the 'message' field.",
                ex.getMessage()));
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity handleException(SQLException ex){
        String[] message = ex.getMessage().split(";");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request, 
                HttpStatus.UNPROCESSABLE_ENTITY,
                HttpStatus.UNPROCESSABLE_ENTITY.name(),
                "SQL Error. Database Code return [" + ex.getErrorCode() + "] " +
                        "Error details [" + message[0] + "]"),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(JpaSystemException.class)
    public ResponseEntity handleException(JpaSystemException ex){
        String[] message = ex.getMessage().split(";");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request,
                HttpStatus.UNPROCESSABLE_ENTITY,
                HttpStatus.UNPROCESSABLE_ENTITY.name(),
                "JPA System Exception: [" + ex.getMessage() + "] " +
                        "Error details [" + message[0] + "]"),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity handleException(IllegalStateException ex){
        String[] message = ex.getMessage().split(";");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request, 
                HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                "JPA System Exception: [" + ex.getMessage() + "] " +
                        "Error details [" + message[0] + "]"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(ServletException.class)
    public ResponseEntity handleException(ServletException ex){
        String[] message = ex.getMessage().split(";");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request, 
                HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                ex.getLocalizedMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(IOException.class)
    public ResponseEntity handleException(IOException ex) {
        String[] message = ex.getMessage().split(";");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request, 
                HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                ex.getLocalizedMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(RuntimeErrorException.class)
    public ResponseEntity handleException(RuntimeException ex) {
        String[] message = ex.getMessage().split(";");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        return new ResponseEntity(new GenericErrorResponseTemplate(request, 
                HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                ex.getLocalizedMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
    /* ------------------------------------------------------------------------ */

}
