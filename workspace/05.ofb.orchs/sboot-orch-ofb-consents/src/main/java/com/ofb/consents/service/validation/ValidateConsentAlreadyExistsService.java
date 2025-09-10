package com.ofb.consents.service.validation;

import com.google.gson.Gson;
import com.ofb.consents.model.*;
import com.ofb.consents.repository.views.*;
import com.ofb.consents.server.consents.model.*;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import com.ofb.lib.handlers.exception.ofb.UnprocessedEntityException;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class ValidateConsentAlreadyExistsService {

    @Autowired
    private ConsentPersonalViewRepository consentsRepositoryView;

    /**
     * Validates whether there is already a consent in progress
     * https://openfinancebrasil.atlassian.net/wiki/spaces/OF/pages/219480491/Orienta+es+-+DC+Consentimento
     * <br>
     * @param objectData "Required (values described below)"
     * </p>The objectData parameter used in the Validates whether there is already a consent in progress.
     * <p>  The objectData can be:
     * <p>  - String                -->> passing the 'Document' directly
     * <p>  - Object CreateConsent  -->> where CreateConsent.getData().getLoggedUser().getDocument().getIdentification() is valid.
     * <p>  - Object CreateConsentData  -->> where CreateConsentData().getLoggedUser().getDocument().getIdentification() is valid.
     * <p>  - Object LoggedUser         -->> where LoggedUser.getDocument().getIdentification() is valid.
     * <p>  - Object LoggedUserDocument -->> where LoggedUserDocument().getIdentification() is valid.
     * <br>
     * @param referenceId "Optional (can be null)"
     * <p>The referenceId parameter is used to set a 'Key' value if needed. Ex.: consentId
     * <p><b>NOTE: THIS METHOD DOES NOT USE THIS PARAMETER</b></p>
     * <br>
     * @param executeThrowImmediately "Required (values: true or false)"
     * <p>The executeThrowImmediately parameter is used to interrupt the execution of the method by
     * generating a Throw 'ConsentResponseErrorException'
     * <br>
     * @return ResponseValidateConsentModel
     * <p>Return ObjectResponse with results for validation
     * <br>
     * @throws UnprocessedEntityException
     * @throws InternalErrorException
     * <p>if an error occurs while attempting to invoke the method<p></p>
     */
    public ResponseValidateConsentModel validateConsentAlreadyExists(Object objectData, Object referenceId, Boolean executeThrowImmediately) {

        List<ResponseErrorsInnerTemplate> listResponseErrors = new ArrayList<>();
        String personalIdentification = "";

        ///  ObjectData parameter validate
        try {
            if (objectData == null) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Consent Already Exists validate")
                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("ObjectData is null. ObjectData is mandatory and must inform the LoggedUser document.")
                        .build());
                if (executeThrowImmediately) {
                    throw new UnprocessedEntityException(new Gson().toJson(listResponseErrors));
                }
                return ResponseValidateConsentModel.builder()
                        .errorsListed(true)
                        .responseErrorsList(listResponseErrors)
                        .build();
            } else if (objectData instanceof String) {
                personalIdentification = (String) objectData;
            } else if (objectData instanceof CreateConsent) {
                personalIdentification = ((CreateConsent) objectData).getData().getLoggedUser().getDocument().getIdentification();
            } else if (objectData instanceof CreateConsentData) {
                personalIdentification = ((CreateConsentData) objectData).getLoggedUser().getDocument().getIdentification();
            } else if (objectData instanceof BusinessEntity) {
                personalIdentification = ((LoggedUser) objectData).getDocument().getIdentification();
            }else if (objectData instanceof BusinessEntityDocument) {
                personalIdentification = ((LoggedUserDocument) objectData).getIdentification();
            }
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Consent Already Exists validate")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("LoggedUser verification error. Logged User is mandatory and must inform the LoggedUser document.")
                    .build());
            if (executeThrowImmediately) {
                throw new InternalErrorException(new Gson().toJson(listResponseErrors));
            }
            return ResponseValidateConsentModel.builder()
                    .errorsListed(true)
                    .objectException(e)
                    .responseErrorsList(listResponseErrors)
                    .build();
        }

        try {
            List<ConsentPersonalModel> listOfConsentsPersonalEnabled = consentsRepositoryView
                    .findAllConsentsEnabledByDocumentIdentification(personalIdentification);
            if (!listOfConsentsPersonalEnabled.isEmpty()) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Consent Already Exists validate")
                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("The requested LoggedUser identified in 'document' already has consent registered " +
                                "- in AWAITING_AUTHORISATION or AUTHORISED status - " +
                                "and this request will not be accepted." +
                                " Check whether a '/consents/{consentId}/extensions' should be requested " +
                                "to extend the expiration date or modify permissions.")
                        .build());
                if (executeThrowImmediately) {
                    throw new UnprocessedEntityException(new Gson().toJson(listResponseErrors));
                }
                return ResponseValidateConsentModel.builder()
                        .errorsListed(true)
                        .responseErrorsList(listResponseErrors)
                        .build();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Consent Already Exists validate")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An error occurred while checking the already consent exists.")
                    .build());
            if (executeThrowImmediately) {
                throw new InternalErrorException(new Gson().toJson(listResponseErrors));
            }
            return ResponseValidateConsentModel.builder()
                    .errorsListed(true)
                    .responseErrorsList(listResponseErrors)
                    .objectException(e)
                    .build();
        }

        return ResponseValidateConsentModel.builder()
                .errorsListed(!listResponseErrors.isEmpty() ? true : false)
                .responseErrorsList(listResponseErrors)
                .build();
    }
}

