package com.ofb.consents.service.persistence;

import com.google.gson.Gson;
import com.ofb.consents.entity.ConsentPersonalData;
import com.ofb.consents.enums.ConsentResponseEnum;
import com.ofb.consents.exception.ConsentInternalErrorException;
import com.ofb.consents.exception.ConsentUnprocessedEntityException;
import com.ofb.consents.model.ResponseValidateConsentModel;
import com.ofb.consents.repository.data.ConsentPersonalRepository;
import com.ofb.consents.server.consents.resources.model.ResponseErrorErrorsInner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class ConsentAwaitingAuthorizationService {

    @Autowired
    private ConsentPersonalRepository consentRepositoryData;

    /**
     * Update a new Consent to the Waiting for Authorization Status
     * <br>
     * @param objectData "Required (values described below)"
     * </p>The objectData parameter used in the function must contain valid information for a 'ConsentPersonalData' object to update the requested consent.
     * <p> The objectData can be:
     * <p> - ConsentPersonalData object-->> where ConsentPersonalData is a valid object.
     * <br>
     * @param referenceId "Optional (can be null)"
     * <p>The referenceId parameter is used to set a 'Key' value, if needed. Ex.: consentId
     * <p><b>NOTE: THIS METHOD DOES NOT USE THIS PARAMETER</b></p>
     * <br>
     * @param executeThrowImmediately "Required (values: true or false)"
     * <p>The executeThrowImmediately parameter is used to interrupt method execution
     * by throwing a 'ConsentResponseErrorException' exception
     * <br>
     * @return ResponseValidateConsentModel
     * <p>Returns ObjectResponse with results for validation
     * <p><b>Note</b></p>
     * <p>In the 'objectData' field, returns the updated ConsentPersonalData object
     * <br>
     * @throws ConsentUnprocessedEntityException
     * @throws ConsentInternalErrorException
     * <p>If an error occurs while trying to invoke the method<p></p>
     */
    public ResponseValidateConsentModel updateConsentToAwatingAuthorization(Object objectData, Object referenceId, Boolean executeThrowImmediately) {

        Timestamp timestampThisOperation = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
        List<ResponseErrorErrorsInner> listResponseErrors = new ArrayList<>();
        ConsentPersonalData consentCreated = null;
        ConsentPersonalData consentUpdated;

        ///  ObjectData parameter validate
        try {
            if (objectData == null) {
                listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                        .title("Consent create: Error in validate objectData.")
                        .code(ConsentResponseEnum.CodeEnum.INTERNAL_ERROR.getValue())
                        .detail("ObjectData is null. ObjectData is mandatory and must inform the ConsentPersonalData.")
                        .build());
                if (executeThrowImmediately) {
                    throw new ConsentInternalErrorException(new Gson().toJson(listResponseErrors));
                }
                return ResponseValidateConsentModel.builder()
                        .errorsListed(true)
                        .responseErrorsList(listResponseErrors)
                        .build();
            } else if (objectData instanceof ConsentPersonalData) {
                consentCreated = (ConsentPersonalData) objectData;
            }
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent update: Error in validation of objectData.")
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

        try {
            consentCreated.setConsentId(consentCreated.getConsentId());
            consentCreated.setConsentStatusId(80L);
            consentCreated.setStatus("AWAITING_AUTHORISATION");
            consentCreated.setStatusUpdateDatetime(timestampThisOperation);
            consentCreated.setAwaitingAuthStart(timestampThisOperation);
            consentCreated.setUserCode("ConsentsServiceAPI");
            consentUpdated = consentRepositoryData.saveAndFlush(consentCreated);
        } catch (Exception ex) {
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

        return ResponseValidateConsentModel.builder()
                .errorsListed(false)
                .responseErrorsList(listResponseErrors)
                .objectData(consentUpdated)
                .build();
    }

}
