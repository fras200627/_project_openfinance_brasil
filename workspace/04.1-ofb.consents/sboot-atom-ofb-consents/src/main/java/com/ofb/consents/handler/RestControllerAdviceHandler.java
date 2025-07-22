package com.ofb.consents.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.ofb.consents.enums.ConsentResponseEnum;
import com.ofb.consents.exception.ConsentBadRequestException;
import com.ofb.consents.exception.ConsentInternalErrorException;
import com.ofb.consents.exception.ConsentUnprocessedEntityException;
import com.ofb.consents.server.consents.resources.model.MetaError;
import com.ofb.consents.server.consents.resources.model.ResponseError;
import com.ofb.consents.server.consents.resources.model.ResponseErrorErrorsInner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice @Slf4j
public class RestControllerAdviceHandler {

    @Autowired private
    HttpServletRequest request;

    @Autowired private
    HttpServletResponse response;

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

    private record buildBobyResponse(String field, String message) {
        public buildBobyResponse(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(ConsentUnprocessedEntityException.class)
    public ResponseEntity handleException(ConsentUnprocessedEntityException ex) {
        String[] messageDetails = ex.getLocalizedMessage().split(":");
        this.writeError(ex.getClass().toString(), ex.getMessage(), messageDetails);

        ObjectMapper objectMapper = new ObjectMapper();
        List<ResponseErrorErrorsInner> errors = new ArrayList<>();
        try {
            errors = objectMapper.readValue(ex.getMessage(), new TypeReference<List<ResponseErrorErrorsInner>>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        MetaError meta = new MetaError().toBuilder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString().substring(0, 19) + "Z").build();

        response.addHeader("x-v", "x-v: " + System.getProperty("App.Build.Version"));
        response.addHeader("x-webhook-interaction-id", response.getHeader("x-fapi-interaction-id"));

        return ResponseEntity.unprocessableEntity().body(new ResponseError().toBuilder()
                .errors(errors)
                .meta(meta)
                .build());
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(ConsentBadRequestException.class)
    public ResponseEntity handleException(ConsentBadRequestException ex) {
        String[] messageDetails = ex.getLocalizedMessage().split(":");
        this.writeError(ex.getClass().toString(), ex.getMessage(), messageDetails);

        ObjectMapper objectMapper = new ObjectMapper();
        List<ResponseErrorErrorsInner> errors = new ArrayList<>();
        try {
            errors = objectMapper.readValue(ex.getMessage(), new TypeReference<List<ResponseErrorErrorsInner>>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        MetaError meta = new MetaError().toBuilder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString().substring(0, 19) + "Z").build();
        response.addHeader("x-v", "x-v: " + System.getProperty("App.Build.Version"));
        response.addHeader("x-webhook-interaction-id", response.getHeader("x-fapi-interaction-id"));

        return ResponseEntity.badRequest().body(new ResponseError().toBuilder()
                .errors(errors)
                .meta(meta)
                .build());
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(ConsentInternalErrorException.class)
    public ResponseEntity handleException(ConsentInternalErrorException ex) {
        String[] messageDetails = ex.getLocalizedMessage().split(":");
        this.writeError(ex.getClass().toString(), ex.getMessage(), messageDetails);

        ObjectMapper objectMapper = new ObjectMapper();
        List<ResponseErrorErrorsInner> errors = new ArrayList<>();
        try {
            errors = objectMapper.readValue(ex.getMessage(), new TypeReference<List<ResponseErrorErrorsInner>>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        MetaError meta = new MetaError().toBuilder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString().substring(0, 19) + "Z").build();
        response.addHeader("x-v", "x-v: " + System.getProperty("App.Build.Version"));
        response.addHeader("x-webhook-interaction-id", response.getHeader("x-fapi-interaction-id"));

        return ResponseEntity.internalServerError().body(new ResponseError().toBuilder()
                .errors(errors)
                .meta(meta)
                .build());
    }
    /* ------------------------------------------------------------------------ */
    
    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity handleException(MethodArgumentTypeMismatchException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        List<ResponseErrorErrorsInner> errors = new ArrayList<ResponseErrorErrorsInner>();
        errors.add(new ResponseErrorErrorsInner().toBuilder()
                .code(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.getValue())
                .title(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.toString())
                .detail(ex.getMessage())
                .build());

        MetaError meta = new MetaError().toBuilder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString().substring(0, 19) + "Z").build();

//        response.addHeader("Content-Encoding", "gzip");
        response.addHeader("x-v", "x-v: " + System.getProperty("App.Build.Version"));
        response.addHeader("x-webhook-interaction-id", response.getHeader("x-fapi-interaction-id"));

        return ResponseEntity.internalServerError().body(new ResponseError().toBuilder()
                .errors(errors)
                .meta(meta)
                .build());
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(MethodArgumentConversionNotSupportedException.class)
    public ResponseEntity handleException(MethodArgumentConversionNotSupportedException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        List<ResponseErrorErrorsInner> errors = new ArrayList<ResponseErrorErrorsInner>();
        errors.add(new ResponseErrorErrorsInner().toBuilder()
                .code(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.getValue())
                .title(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.toString())
                .detail(ex.getMessage())
                .build());

        MetaError meta = new MetaError().toBuilder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString().substring(0, 19) + "Z").build();

//        response.addHeader("Content-Encoding", "gzip");
        response.addHeader("x-v", "x-v: " + System.getProperty("App.Build.Version"));
        response.addHeader("x-webhook-interaction-id", response.getHeader("x-fapi-interaction-id"));

        return ResponseEntity.internalServerError().body(new ResponseError().toBuilder()
                .errors(errors)
                .meta(meta)
                .build());
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleException(HttpMessageNotReadableException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        List<ResponseErrorErrorsInner> errors = new ArrayList<ResponseErrorErrorsInner>();
        errors.add(new ResponseErrorErrorsInner().toBuilder()
                .code(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.getValue())
                .title(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.toString())
                .detail(ex.getMessage())
                .build());

        MetaError meta = new MetaError().toBuilder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString().substring(0, 19) + "Z").build();

//        response.addHeader("Content-Encoding", "gzip");
        response.addHeader("x-v", "x-v: " + System.getProperty("App.Build.Version"));
        response.addHeader("x-webhook-interaction-id", response.getHeader("x-fapi-interaction-id"));

        return ResponseEntity.internalServerError().body(new ResponseError().toBuilder()
                .errors(errors)
                .meta(meta)
                .build());
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity handleException(HttpMessageConversionException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        List<ResponseErrorErrorsInner> errors = new ArrayList<ResponseErrorErrorsInner>();
        errors.add(new ResponseErrorErrorsInner().toBuilder()
                .code(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.getValue())
                .title(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.toString())
                .detail(ex.getMessage())
                .build());

        MetaError meta = new MetaError().toBuilder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString().substring(0, 19) + "Z").build();

//        response.addHeader("Content-Encoding", "gzip");
        response.addHeader("x-v", "x-v: " + System.getProperty("App.Build.Version"));
        response.addHeader("x-webhook-interaction-id", response.getHeader("x-fapi-interaction-id"));

        return ResponseEntity.internalServerError().body(new ResponseError().toBuilder()
                .errors(errors)
                .meta(meta)
                .build());
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(HttpMessageNotWritableException.class)
    public ResponseEntity handleException(HttpMessageNotWritableException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        List<ResponseErrorErrorsInner> errors = new ArrayList<ResponseErrorErrorsInner>();
        errors.add(new ResponseErrorErrorsInner().toBuilder()
                .code(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.getValue())
                .title(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.toString())
                .detail(ex.getMessage())
                .build());

        MetaError meta = new MetaError().toBuilder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString().substring(0, 19) + "Z").build();

//        response.addHeader("Content-Encoding", "gzip");
        response.addHeader("x-v", "x-v: " + System.getProperty("App.Build.Version"));
        response.addHeader("x-webhook-interaction-id", response.getHeader("x-fapi-interaction-id"));

        return ResponseEntity.internalServerError().body(new ResponseError().toBuilder()
                .errors(errors)
                .meta(meta)
                .build());
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity handleException(MissingPathVariableException ex){
        String[] message = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        List<ResponseErrorErrorsInner> errors = new ArrayList<ResponseErrorErrorsInner>();
        errors.add(new ResponseErrorErrorsInner().toBuilder()
                .code(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.getValue())
                .title(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.toString())
                .detail(ex.getMessage())
                .build());

        MetaError meta = new MetaError().toBuilder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString().substring(0, 19) + "Z").build();

//        response.addHeader("Content-Encoding", "gzip");
        response.addHeader("x-v", "x-v: " + System.getProperty("App.Build.Version"));
        response.addHeader("x-webhook-interaction-id", response.getHeader("x-fapi-interaction-id"));

        return ResponseEntity.internalServerError().body(new ResponseError().toBuilder()
                .errors(errors)
                .meta(meta)
                .build());
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(MissingRequestValueException.class)
    public ResponseEntity handleException(MissingRequestValueException ex){
        String message = ((MissingServletRequestParameterException) ex).getParameterName();
        String[] messageDetails = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), ex.getMessage(), messageDetails );

        List<ResponseErrorErrorsInner> errors = new ArrayList<ResponseErrorErrorsInner>();
        errors.add(new ResponseErrorErrorsInner().toBuilder()
                .code(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.getValue())
                .title(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.toString())
                .detail(ex.getMessage())
                .build());

        MetaError meta = new MetaError().toBuilder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString().substring(0, 19) + "Z").build();

//        response.addHeader("Content-Encoding", "gzip");
        response.addHeader("x-v", "x-v: " + System.getProperty("App.Build.Version"));
        response.addHeader("x-webhook-interaction-id", response.getHeader("x-fapi-interaction-id"));

        return ResponseEntity.internalServerError().body(new ResponseError().toBuilder()
                .errors(errors)
                .meta(meta)
                .build());
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleException(MethodArgumentNotValidException ex) {
        var errorList = ex.getFieldErrors();
        String[] messageDetails = ex.getLocalizedMessage().split(":");

        this.writeError(ex.getClass().toString(), errorList.stream().map(buildBobyResponse::new).toList().toString() , messageDetails);

        List<ResponseErrorErrorsInner> errors = new ArrayList<ResponseErrorErrorsInner>();
        errors.add(new ResponseErrorErrorsInner().toBuilder()
                .code(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.getValue())
                .title(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.toString())
                .detail(ex.getMessage())
                .build());

        MetaError meta = new MetaError().toBuilder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString().substring(0, 19) + "Z").build();

//        response.addHeader("Content-Encoding", "gzip");
        response.addHeader("x-v", "x-v: " + System.getProperty("App.Build.Version"));
        response.addHeader("x-webhook-interaction-id", response.getHeader("x-fapi-interaction-id"));

        return ResponseEntity.internalServerError().body(new ResponseError().toBuilder()
                .errors(errors)
                .meta(meta)
                .build());
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity handleException(SQLException ex){
        String[] message = ex.getMessage().split(";");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        List<ResponseErrorErrorsInner> errors = new ArrayList<ResponseErrorErrorsInner>();
        errors.add(new ResponseErrorErrorsInner().toBuilder()
                .code(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.getValue())
                .title(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.toString())
                .detail(ex.getMessage())
                .build());

        MetaError meta = new MetaError().toBuilder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString().substring(0, 19) + "Z").build();

//        response.addHeader("Content-Encoding", "gzip");
        response.addHeader("x-v", "x-v: " + System.getProperty("App.Build.Version"));
        response.addHeader("x-webhook-interaction-id", response.getHeader("x-fapi-interaction-id"));

        return ResponseEntity.internalServerError().body(new ResponseError().toBuilder()
                .errors(errors)
                .meta(meta)
                .build());
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(JpaSystemException.class)
    public ResponseEntity handleException(JpaSystemException ex){
        String[] message = ex.getMessage().split(";");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        List<ResponseErrorErrorsInner> errors = new ArrayList<ResponseErrorErrorsInner>();
        errors.add(new ResponseErrorErrorsInner().toBuilder()
                .code(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.getValue())
                .title(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.toString())
                .detail(ex.getMessage())
                .build());

        MetaError meta = new MetaError().toBuilder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString().substring(0, 19) + "Z").build();

//        response.addHeader("Content-Encoding", "gzip");
        response.addHeader("x-v", "x-v: " + System.getProperty("App.Build.Version"));
        response.addHeader("x-webhook-interaction-id", response.getHeader("x-fapi-interaction-id"));

        return ResponseEntity.internalServerError().body(new ResponseError().toBuilder()
                .errors(errors)
                .meta(meta)
                .build());
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity handleException(IllegalStateException ex){
        String[] message = ex.getMessage().split(";");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        List<ResponseErrorErrorsInner> errors = new ArrayList<ResponseErrorErrorsInner>();
        errors.add(new ResponseErrorErrorsInner().toBuilder()
                .code(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.getValue())
                .title(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.toString())
                .detail(ex.getMessage())
                .build());

        MetaError meta = new MetaError().toBuilder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString().substring(0, 19) + "Z").build();

//        response.addHeader("Content-Encoding", "gzip");
        response.addHeader("x-v", "x-v: " + System.getProperty("App.Build.Version"));
        response.addHeader("x-webhook-interaction-id", response.getHeader("x-fapi-interaction-id"));

        return ResponseEntity.internalServerError().body(new ResponseError().toBuilder()
                .errors(errors)
                .meta(meta)
                .build());
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(ServletException.class)
    public ResponseEntity handleException(ServletException ex){
        String[] message = ex.getMessage().split(";");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        List<ResponseErrorErrorsInner> errors = new ArrayList<ResponseErrorErrorsInner>();
        errors.add(new ResponseErrorErrorsInner().toBuilder()
                .code(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.getValue())
                .title(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.toString())
                .detail(ex.getMessage())
                .build());

        MetaError meta = new MetaError().toBuilder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString().substring(0, 19) + "Z").build();

//        response.addHeader("Content-Encoding", "gzip");
        response.addHeader("x-v", "x-v: " + System.getProperty("App.Build.Version"));
        response.addHeader("x-webhook-interaction-id", response.getHeader("x-fapi-interaction-id"));

        return ResponseEntity.internalServerError().body(new ResponseError().toBuilder()
                .errors(errors)
                .meta(meta)
                .build());
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(IOException.class)
    public ResponseEntity handleException(IOException ex) {
        String[] message = ex.getMessage().split(";");

        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        List<ResponseErrorErrorsInner> errors = new ArrayList<ResponseErrorErrorsInner>();
        errors.add(new ResponseErrorErrorsInner().toBuilder()
                .code(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.getValue())
                .title(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.toString())
                .detail(ex.getMessage())
                .build());

        MetaError meta = new MetaError().toBuilder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString().substring(0, 19) + "Z").build();

//        response.addHeader("Content-Encoding", "gzip");
        response.addHeader("x-v", "x-v: " + System.getProperty("App.Build.Version"));
        response.addHeader("x-webhook-interaction-id", response.getHeader("x-fapi-interaction-id"));

        return ResponseEntity.internalServerError().body(new ResponseError().toBuilder()
                .errors(errors)
                .meta(meta)
                .build());
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleException(RuntimeException ex) {
        String[] message = ex.getMessage().split(";");
        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        List<ResponseErrorErrorsInner> errors = new ArrayList<ResponseErrorErrorsInner>();
        errors.add(new ResponseErrorErrorsInner().toBuilder()
                .code(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.getValue())
                .title(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.toString())
                .detail(ex.getMessage())
                .build());

        MetaError meta = new MetaError().toBuilder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString().substring(0, 19) + "Z").build();

//        response.addHeader("Content-Encoding", "gzip");
        response.addHeader("x-v", "x-v: " + System.getProperty("App.Build.Version"));
        response.addHeader("x-webhook-interaction-id", response.getHeader("x-fapi-interaction-id"));

        return ResponseEntity.internalServerError().body(new ResponseError().toBuilder()
                .errors(errors)
                .meta(meta)
                .build());
    }
    /* ------------------------------------------------------------------------ */

    /* ------------------------------------------------------------------------ */
    @ExceptionHandler(RuntimeErrorException.class)
    public ResponseEntity handleException(RuntimeErrorException ex) {
        String[] message = ex.getMessage().split(";");
        this.writeError(ex.getClass().toString(), ex.getMessage(), message );

        List<ResponseErrorErrorsInner> errors = new ArrayList<ResponseErrorErrorsInner>();
        errors.add(new ResponseErrorErrorsInner().toBuilder()
                .code(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.getValue())
                .title(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.toString())
                .detail(ex.getMessage())
                .build());

        MetaError meta = new MetaError().toBuilder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString().substring(0, 19) + "Z").build();

//        response.addHeader("Content-Encoding", "gzip");
        response.addHeader("x-v", "x-v: " + System.getProperty("App.Build.Version"));
        response.addHeader("x-webhook-interaction-id", response.getHeader("x-fapi-interaction-id"));

        return ResponseEntity.internalServerError().body(new ResponseError().toBuilder()
                .errors(errors)
                .meta(meta)
                .build());
    }
    /* ------------------------------------------------------------------------ */

}
