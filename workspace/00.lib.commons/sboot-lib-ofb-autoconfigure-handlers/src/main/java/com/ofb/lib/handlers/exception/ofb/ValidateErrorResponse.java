package com.ofb.lib.handlers.exception.ofb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.template.ResponseErrorTemplate;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class ValidateErrorResponse {

    private Gson gson = new Gson();

    public List<ResponseErrorsInnerTemplate> buildErrorResponse(Exception ex, Boolean executeThrowImmediatally) {

        List<ResponseErrorsInnerTemplate> listValidateErrorResponse = new ArrayList<>();

        if (ex.getClass().toString().contains("HttpClientErrorException")) {
            String message = ex.getMessage().replace("}}\"", "}}").toString();
            int messagePos1 = message.indexOf("{");
            message = message.substring(messagePos1);

            ObjectMapper mapper = new ObjectMapper();
            ResponseErrorTemplate responseHttpErrors = new ResponseErrorTemplate();
            try {
                responseHttpErrors = mapper.readValue(message, ResponseErrorTemplate.class);
            } catch (JsonProcessingException e) {
                listValidateErrorResponse.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Authorization validate error")
                        .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                        .detail("An error occurred while checking the Authorization validate: [" + e.getMessage() + "].")
                        .build());
                if (executeThrowImmediatally) {
                    throw new InternalErrorException(gson.toJson(listValidateErrorResponse));
                }
            }
            listValidateErrorResponse.addAll(responseHttpErrors.getErrors());

            if (executeThrowImmediatally) {
                throw new BadRequestException(gson.toJson(listValidateErrorResponse));
            }
        } else if (ex.getClass().toString().contains("NoSuchElementException")) {
            listValidateErrorResponse.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization validate error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("An error occurred while checking the Authorization validate: [" + ex.getMessage() + "].")
                    .build());
            if (executeThrowImmediatally) {
                throw new BadRequestException(gson.toJson(listValidateErrorResponse));
            }
        } else {
            listValidateErrorResponse.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Authorization validate error")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An error occurred while checking the Authorization validate: [" + ex.getMessage() + "].")
                    .build());
            if (executeThrowImmediatally) {
                throw new InternalErrorException(gson.toJson(listValidateErrorResponse));
            }
        }

        return listValidateErrorResponse;
    }

}
