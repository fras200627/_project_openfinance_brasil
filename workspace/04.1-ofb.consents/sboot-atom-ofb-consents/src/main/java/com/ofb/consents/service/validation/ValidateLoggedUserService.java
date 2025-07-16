package com.ofb.consents.service.validation;

import com.google.gson.Gson;
import com.ofb.consents.enums.ConsentResponseEnum;
import com.ofb.consents.exception.ConsentInternalErrorException;
import com.ofb.consents.exception.ConsentUnprocessedEntityException;
import com.ofb.consents.model.*;
import com.ofb.consents.repository.views.*;
import com.ofb.consents.server.consents.resources.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service @Slf4j
public class ValidateLoggedUserService {

    @Autowired
    private PersonalDataViewRepository personalsRepositoryView;

    /**
     * Validates a LoggedUser for consent information
     * <br>
     * @param objectData "Required (values described below)"
     * </p>The objectData parameter used in the validate function must contain valid 'Document' information for the LoggerUser requesting consent creation.
     * <p> The objectData can be:
     * <p> - String -->> passing the 'Document' directly
     * <p> - CreateConsent Object -->> where CreateConsent.getData().getLoggedUser().getDocument().getIdentification() is valid.
     * <p> - CreateConsentData Object -->> where CreateConsentData().getLoggedUser().getDocument().getIdentification() is valid.
     * <p> - LoggedUser Object -->> where LoggedUser.getDocument().getIdentification() is valid. * <p> - LoggedUserDocument object -->> where LoggedUserDocument().getIdentification() is valid.
     * <br>
     * @param referenceId "Optional (may be null)"
     * <p>The referenceId parameter is used to set a 'Key' value, if needed. Ex.: consentId
     * <p><b>NOTE: THIS METHOD DOES NOT USE THIS PARAMETER</b></p>
     * <br>
     * @param executeThrowImmediately "Required (values: true or false)"
     * <p>The executeThrowImmediately parameter is used to interrupt method execution
     * generating a 'ConsentResponseErrorException' Throw
     * <br>
     * @return ResponseValidateConsentModel
     * <p>Returns ObjectResponse with validation results
     * <p><b>Note</b></p>
     * <p>in the 'objectData' field, returns a PersonalDataModel object
     * <br>
     * @throws ConsentUnprocessedEntityException
     * @throws ConsentInternalErrorException
     * <p>if an error occurs while trying to invoke the method<p></p>
     */
    public ResponseValidateConsentModel validateLoggedUserInformation(Object objectData, Object referenceId, Boolean executeThrowImmediately) {

        List<ResponseErrorErrorsInner> listResponseErrors = new ArrayList<>();
        String loggedUserDocument = "";
        PersonalDataModel personalDataView;

        ///  ObjectData parameter validate
        try {
            if (objectData == null) {
                listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                        .title("Consent LoggerUser validate")
                        .code(ConsentResponseEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("ObjectData is null. ObjectData is mandatory and must inform the LoggedUser document.")
                        .build());
                if (executeThrowImmediately) {
                    throw new ConsentUnprocessedEntityException(new Gson().toJson(listResponseErrors));
                }
                return ResponseValidateConsentModel.builder()
                        .errorsListed(true)
                        .responseErrorsList(listResponseErrors)
                        .build();
            } else if (objectData instanceof String) {
                loggedUserDocument = (String) objectData;
            } else if (objectData instanceof CreateConsent) {
                loggedUserDocument = ((CreateConsent) objectData).getData().getLoggedUser().getDocument().getIdentification();
            } else if (objectData instanceof CreateConsentData) {
                loggedUserDocument = ((CreateConsentData) objectData).getLoggedUser().getDocument().getIdentification();
            } else if (objectData instanceof LoggedUser) {
                loggedUserDocument = ((LoggedUser) objectData).getDocument().getIdentification();
            }else if (objectData instanceof LoggedUserDocument) {
                loggedUserDocument = ((LoggedUserDocument) objectData).getIdentification();
            }
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent LoggerUser validate")
                    .code(ConsentResponseEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("LoggedUser verification error. LoggerUser is mandatory and must inform the LoggedUser document.")
                    .build());
            if (executeThrowImmediately) {
                throw new ConsentInternalErrorException(new Gson().toJson(listResponseErrors));
            }
            return ResponseValidateConsentModel.builder()
                    .errorsListed(true)
                    .objectException(e)
                    .responseErrorsList(listResponseErrors)
                    .build();
        }

        /// Search LoggedUser
        try {
            personalDataView = personalsRepositoryView.findById(loggedUserDocument.trim()).get();
        } catch (NoSuchElementException e) {
            listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent LoggerUser validate")
                    .code(ConsentResponseEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("The requested LoggedUser does not exist as a client of the Transmitting Unit.")
                    .build());
            if (executeThrowImmediately) {
                throw new ConsentUnprocessedEntityException(new Gson().toJson(listResponseErrors));
            }
            return ResponseValidateConsentModel.builder()
                    .errorsListed(true)
                    .responseErrorsList(listResponseErrors)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent LoggerUser validate")
                    .code(ConsentResponseEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An error occurred while checking the requested LoggerUser. Error: "  + e.getMessage())
                    .build());
            if (executeThrowImmediately) {
                throw new ConsentInternalErrorException(new Gson().toJson(listResponseErrors));
            }
            return ResponseValidateConsentModel.builder()
                    .errorsListed(true)
                    .responseErrorsList(listResponseErrors)
                    .objectException(e)
                    .build();
        }

        ///
        if (personalDataView == null) {
            listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent LoggerUser validate")
                    .code(ConsentResponseEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("The requested LoggedUser does not exist as a client of the Transmitting Unit.")
                    .build());
            if (executeThrowImmediately) {
                throw new ConsentUnprocessedEntityException(new Gson().toJson(listResponseErrors));
            }
            return ResponseValidateConsentModel.builder()
                    .errorsListed(true)
                    .responseErrorsList(listResponseErrors)
                    .build();
        }

        return ResponseValidateConsentModel.builder()
                .errorsListed(false)
                .objectData(personalDataView)
                .responseErrorsList(listResponseErrors)
                .build();
    }

}
