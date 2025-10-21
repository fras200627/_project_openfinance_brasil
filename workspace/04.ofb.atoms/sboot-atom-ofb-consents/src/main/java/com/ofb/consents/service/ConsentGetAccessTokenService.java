package com.ofb.consents.service;

import com.google.gson.Gson;
import com.ofb.consents.model.ConsentPersonalModel;
import com.ofb.consents.repository.views.ConsentPersonalViewRepository;
import com.ofb.consents.server.model.*;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service @Slf4j
public class ConsentGetAccessTokenService {

    @Autowired
    private ConsentPersonalViewRepository consentsRepositoryView;


    public ResponseAccessTokenRead consentsGetAccessTokenConsentId(String consentId) {

        List<ResponseErrorsInnerTemplate> listError = new ArrayList<>();
        ConsentPersonalModel consentRequested;

        try {
            consentRequested = consentsRepositoryView.findById(consentId).get();
        } catch (NoSuchElementException e) {
            listError.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Consent GET request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.CONSENT_NOT_FOUND.getValue())
                    .detail("The informed consentId does not exist")
                    .build());
            throw new BadRequestException(new Gson().toJson(listError));
        } catch (Exception e) {
            listError.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Consent GET request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
            throw new InternalErrorException(new Gson().toJson(listError));
        }

        if (consentRequested.getStatus().equals("AUTHORISED")) {
            if (consentRequested.getAccessTokenAuthorised() == null) {
                listError.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Consent GET request error")
                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("The informed consentId does not AccessToken generated")
                        .build());
                throw new BadRequestException(new Gson().toJson(listError));
            }
        } else {
            listError.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Consent GET request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("The informed consentId does not status = 'AUTHORISED'")
                    .build());
            throw new BadRequestException(new Gson().toJson(listError));
        }

        ResponseAccessTokenReadData responseAccessTokenReadData = ResponseAccessTokenReadData.builder()
                .consentId(consentRequested.getConsentId())
                .accessToken(consentRequested.getAccessTokenAuthorised())
                .status(ResponseAccessTokenReadData.StatusEnum.fromValue(consentRequested.getStatus()))
                .build();

        Meta meta  = Meta.builder().requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString()).build();

        ResponseAccessTokenRead responseConsentRead = ResponseAccessTokenRead.builder()
                .data(responseAccessTokenReadData)
                .meta(meta)
                .build();

        return responseConsentRead;
    }

}
