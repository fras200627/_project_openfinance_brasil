package com.ofb.consents.service.persistence;

import com.google.gson.Gson;
import com.ofb.consents.entity.ConsentPersonalData;
import com.ofb.consents.enums.ConsentResponseEnum;
import com.ofb.consents.exception.ConsentBadRequestException;
import com.ofb.consents.exception.ConsentInternalErrorException;
import com.ofb.consents.exception.ConsentUnprocessedEntityException;
import com.ofb.consents.model.PersonalDataModel;
import com.ofb.consents.model.ResponseValidateConsentModel;
import com.ofb.consents.model.RespponseExpirationDatetimeModel;
import com.ofb.consents.repository.data.*;
import com.ofb.consents.repository.views.*;
import com.ofb.consents.server.consents.resources.model.*;
import com.ofb.consents.service.validation.ValidateExpirationDatetimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class ConsentCreateService {

    @Autowired
    private PersonalDataViewRepository personalDataViewRepository;

    @Autowired
    private ValidateExpirationDatetimeService validateExpirationDatetimeService;

    @Autowired
    private ConsentPersonalRepository consentsRepository;

    /**
     * Creates a new Consent
     * <br>
     * @param objectData "Required (values described below)"
     * </p>The objectData parameter used in the validate function must contain valid information for a 'CreateConsent' object to create the requested consent.
     * <p> The objectData can be:
     * <p> - CreateConsent object -->> where CreateConsent is valid.
     * <br>
     * @param referenceId "Optional (can be null)"
     * <p>The referenceId parameter is used to set a 'Key' value, if necessary. Ex.: consentId
     * <p><b>NOTE: THIS METHOD DOES NOT USE THIS PARAMETER</b></p>
     * <br>
     * @param executeThrowImmediately "Required (values: true or false)"
     * <p>The executeThrowImmediately parameter is used to interrupt method execution
     * throwing a 'ConsentResponseErrorException' exception
     * <br>
     * @return ResponseValidateConsentModel
     * <p>Returns ObjectResponse with results for validation
     * <p><b>Note</b></p>
     * <p>In the 'objectData' field, returns the created CreateConsent object
     * <br>
     * @throws ConsentUnprocessedEntityException
     * <p>If an error occurs while trying to invoke the method<p></p>
     */
    public ResponseValidateConsentModel insertNewConsent(Object objectData, Object referenceId, Boolean executeThrowImmediately) {

        CreateConsent createConsent = null;
        String consentId            = null;
        List<ResponseErrorErrorsInner> listResponseErrors = new ArrayList<>();
        Timestamp timestampThisOperation                  = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));

        ///  ObjectData parameter validate
        try {
            if (objectData == null) {
                listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                        .title("Consent create: Error in validate objectData.")
                        .code(ConsentResponseEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("ObjectData is null. ObjectData is mandatory and must inform the CreateConsent.")
                        .build());
                if (executeThrowImmediately) {
                    throw new ConsentBadRequestException(new Gson().toJson(listResponseErrors));
                }
                return ResponseValidateConsentModel.builder()
                        .errorsListed(true)
                        .responseErrorsList(listResponseErrors)
                        .build();
            } else if (objectData instanceof CreateConsent) {
                createConsent = (CreateConsent) objectData;
            }
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent create: Error in validation of objectData.")
                    .code(ConsentResponseEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An error occurred while checking the data object.")
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

        ///  referenceId parameter validate
        try {
            if (referenceId == null) {
                listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                        .title("Consent validate: Error in validate referenceId.")
                        .code(ConsentResponseEnum.CodeEnum.INTERNAL_ERROR.getValue())
                        .detail("referenceId is null. Reference Id is mandatory and must inform the Consent Id.")
                        .build());
                if (executeThrowImmediately) {
                    throw new ConsentInternalErrorException(new Gson().toJson(listResponseErrors));
                }
                return ResponseValidateConsentModel.builder()
                        .errorsListed(true)
                        .responseErrorsList(listResponseErrors)
                        .build();
            } else if (referenceId instanceof String) {
                consentId = (String) referenceId;
            }
            if (consentId.isEmpty() || consentId.isBlank()) {
                listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                        .title("Consent validate: Error in validate referenceId.")
                        .code(ConsentResponseEnum.CodeEnum.INTERNAL_ERROR.getValue())
                        .detail("referenceId is null. Reference Id is mandatory and must inform the Consent Id.")
                        .build());
                if (executeThrowImmediately) {
                    throw new ConsentInternalErrorException(new Gson().toJson(listResponseErrors));
                }
                return ResponseValidateConsentModel.builder()
                        .errorsListed(true)
                        .responseErrorsList(listResponseErrors)
                        .build();
            }
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent validate: Error in validate referenceId.")
                    .code(ConsentResponseEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("referenceId is null. Reference Id is mandatory and must inform the Consent Id.")
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

        try {
            RespponseExpirationDatetimeModel respponseExpirationDatetimeModel = (RespponseExpirationDatetimeModel)
                    validateExpirationDatetimeService
                    .validateExpirationDateInfo(createConsent, consentId, true)
                    .getObjectData();

            PersonalDataModel personalDataView = personalDataViewRepository.findById(createConsent.getData().getLoggedUser().getDocument().getIdentification()).get();

            ConsentPersonalData consentAccept = consentsRepository.saveAndFlush(ConsentPersonalData.builder()
                    .consentId(consentId)
                    .consentStatusId(85L)
                    .status("REQUEST_IN_PROGRESS")
                    .creationDatetime(timestampThisOperation)
                    .statusUpdateDatetime(timestampThisOperation)
                    .expirationDatetime(respponseExpirationDatetimeModel.getExpirationDateTimeStamp())
                    .expirationDatetimeRequested(respponseExpirationDatetimeModel.getExpirationDateTimeRequested())
                    .expirationDatetimeAdjusted(respponseExpirationDatetimeModel.getExpirationDateTimeAdjusted())
                    .expirationInMonths(respponseExpirationDatetimeModel.getExpirationInMonths())
                    .expirationDateInfo(respponseExpirationDatetimeModel.getExpirationDateInfo())
                    .personalId(personalDataView.getPersonalid())
                    .loggedUserIdentification(createConsent.getData().getLoggedUser().getDocument().getIdentification())
                    .loggedUserDocumentRel(createConsent.getData().getLoggedUser().getDocument().getRel())
                    .businessEntityIdentification(createConsent.getData().getBusinessEntity().getDocument().getIdentification())
                    .businessEntityDocumentRel(createConsent.getData().getBusinessEntity().getDocument().getRel())
                    .awaitingAuthStart(timestampThisOperation)
                    .awaitingAuthBy(createConsent.getData().getLoggedUser().getDocument().getIdentification())
                    .awaitingAuthAdditionalInfo(null)
                    .createAt(timestampThisOperation)
                    .modifyAt(timestampThisOperation)
                    .userCode("ConsentsServiceAPI")
                    .build());
            return ResponseValidateConsentModel.builder()
                    .errorsListed(false)
                    .responseErrorsList(listResponseErrors)
                    .objectData(consentAccept)
                    .build();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent: Error consent creation.")
                    .code(ConsentResponseEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("Internal Error creation consent required.")
                    .build());
            if (executeThrowImmediately) {
                throw new ConsentInternalErrorException(new Gson().toJson(listResponseErrors));
            }
            return ResponseValidateConsentModel.builder()
                    .errorsListed(true)
                    .responseErrorsList(listResponseErrors)
                    .objectException(ex)
                    .build();
        }
    }

}
