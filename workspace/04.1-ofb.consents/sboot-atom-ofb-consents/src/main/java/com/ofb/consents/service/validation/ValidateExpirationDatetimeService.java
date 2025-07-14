package com.ofb.consents.service.validation;

import com.google.gson.Gson;
import com.ofb.consents.enums.ConsentResponseEnum;
import com.ofb.consents.exception.ConsentResponseErrorException;
import com.ofb.consents.model.ResponseValidateConsentModel;
import com.ofb.consents.model.RespponseExpirationDatetimeModel;
import com.ofb.consents.server.consents.resources.model.CreateConsent;
import com.ofb.consents.server.consents.resources.model.CreateConsentData;
import com.ofb.consents.server.consents.resources.model.ResponseErrorErrorsInner;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import com.ofb.consents.server.consents.resources.model.ResponseErrorUnprocessableEntityErrorsInner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service @Slf4j
public class ValidateExpirationDatetimeService {

    private final Pattern pattern = Pattern.compile("^(\\d{4})-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])T(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)Z$");
    private final DateTimeFormatter PARSER1 = DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.ROOT);
    private final DateTimeFormatter PARSER2 = DateTimeFormatter.ofPattern("dd", Locale.ROOT);
    private enum ExpirationOptions {INDETERMINADO, TRIMESTRAL, SEMESTRAL, ANUAL};

    /**
     * Validates an ExpirationDatetime to obtain consent information
     * <br>
     * @param objectData "Required (values described below)"
     * </p>The objectData parameter used in the validate function must contain valid 'ExpirationDatetime' information
     * for creating the requesting consent.
     * <p> The objectData can be:
     * <p> - String -->> passing the 'ExpirationDatetime' directly
     * <p> - CreateConsent object -->> where CreateConsent.getData().getExpirationDateTime() is valid.
     * <p> - CreateConsentData object -->> where CreateConsentData().getExpirationDateTime is valid.
     * <br>
     * @param referenceId "Optional (may be null)"
     * <p>The referenceId parameter is used to set a 'Key' value, if necessary. Ex.: consentId
     * <p><b>NOTE: THIS METHOD DOES NOT USE THIS PARAMETER</b></p>
     * <br>
     * @param executeThrowImmediately "Required (values: true or false)"
     * <p>The executeThrowImmediately parameter is used to interrupt method execution
     * generating a 'ConsentResponseErrorException' Throw
     * <br>
     * @return ResponseValidateConsentModel
     * <p>Returns ObjectResponse with results for validation
     * <p><b>Note</b></p>
     * <p>in the 'objectData' field, returns a ResponseExpirationDatetimeModel object
     * <br>
     * @throws ConsentResponseErrorException
     * <p>if an error occurs while trying to invoke the method<p></p>
     */
    public ResponseValidateConsentModel validateExpirationDateInfo(Object objectData, Object referenceId, Boolean executeThrowImmediately) {

        List<ResponseErrorErrorsInner> listResponseErrors = new ArrayList<>();
        Timestamp  expirationDateTimeStamp      = null;
        Timestamp  expirationDateTimeAdjusted   = null;
        Timestamp  expirationDateTimeRequested  = null;
        Long       expirationInMonths           = null;
        String     expirationDateInfo           = null;
        String     expirationDateTimeParam      = null;

        try {
            if (objectData instanceof String) {
                expirationDateTimeParam = (String) objectData;
            } else if (objectData instanceof CreateConsent) {
                expirationDateTimeParam = ((CreateConsent) objectData).getData().getExpirationDateTime();
            }else if (objectData instanceof CreateConsentData) {
                expirationDateTimeParam = ((CreateConsentData) objectData).getExpirationDateTime();
            }
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent validate: Error in validate ExpirationDatetime.")
                    .code(ConsentResponseEnum.CodeEnum.ERRO_NAO_MAPEADO.getValue())
                    .detail("ExpirationDatetime verification error. ExpirationDatetime is mandatory and must inform the ExpirationDatetime value.")
                    .build());
            if (executeThrowImmediately) {
                throw new ConsentResponseErrorException(new Gson().toJson(listResponseErrors));
            }
            return ResponseValidateConsentModel.builder()
                    .errorsListed(true)
                    .objectException(e)
                    .responseErrorsList(listResponseErrors)
                    .build();
        }

        try {
            if (expirationDateTimeParam == null) {
                listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                        .title("Consent validate: ExpirationDatetime invalid.")
                        .code(ConsentResponseEnum.CodeEnum.DATA_EXPIRACAO_INVALIDA.getValue())
                        .detail("The Payload without the 'expirationDatetime' tag. " +
                                "The tag is mandatory and an empty date can be entered " +
                                "indicating that the consent will have an indefinite expiration date.")
                        .build());
                if (executeThrowImmediately) {
                    throw new ConsentResponseErrorException(new Gson().toJson(listResponseErrors));
                }
                return ResponseValidateConsentModel.builder()
                        .errorsListed(true)
                        .responseErrorsList(listResponseErrors)
                        .build();
            }



            RespponseExpirationDatetimeModel respponseExpirationDatetimeModel = new RespponseExpirationDatetimeModel();
            String zuluDateTime = expirationDateTimeParam;

            if (expirationDateTimeParam == null || expirationDateTimeParam.isEmpty()
                    || expirationDateTimeParam.isBlank()
                    || expirationDateTimeParam.trim().length() == 0) {

                expirationDateInfo = ExpirationOptions.INDETERMINADO.name();
                respponseExpirationDatetimeModel.setExpirationDateInfo(expirationDateInfo);

                return ResponseValidateConsentModel.builder()
                        .errorsListed(false)
                        .responseErrorsList(listResponseErrors)
                        .objectData(respponseExpirationDatetimeModel)
                        .build();
            }

            if (pattern.matcher(zuluDateTime).matches() == false) {
                listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                        .title("Consent validate: Error in validate ExpirationDateTime.")
                        .code(ResponseErrorUnprocessableEntityErrorsInner.CodeEnum.DATA_EXPIRACAO_INVALIDA.getValue())
                        .detail("Expiration Date Time '" + zuluDateTime + "' is invalid! Expiration date must follow the pattern 'yyyy-MM-ddTHH:mm:ssZ'.")
                        .build());
                if (executeThrowImmediately) {
                    throw new ConsentResponseErrorException(new Gson().toJson(listResponseErrors));
                }
                return ResponseValidateConsentModel.builder()
                        .errorsListed(true)
                        .responseErrorsList(listResponseErrors)
                        .build();
            }

            expirationDateTimeRequested = Timestamp.valueOf(expirationDateTimeParam.replace("T", " ").replace("Z", ""));
            OffsetDateTime expirationDateTime = OffsetDateTime.parse(zuluDateTime);
            OffsetDateTime actualDateTime = OffsetDateTime.now(ZoneId.of("UTC"));
            OffsetDateTime expirationDateTimeAdj = expirationDateTime.truncatedTo(ChronoUnit.DAYS).plusHours(23L).plusMinutes(59L).plusSeconds(59);
            expirationDateTimeAdjusted = Timestamp.valueOf(expirationDateTimeAdj.toString().replace("T", " ").replace("Z", ""));
            ;
            expirationDateTimeStamp = Timestamp.valueOf(expirationDateTimeAdj.toString().replace("T", " ").replace("Z", ""));

            if (expirationDateTime.isBefore(actualDateTime) || expirationDateTime.isEqual(actualDateTime)) {
                listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                        .title("Consent validate: Error in validate ExpirationDateTime.")
                        .code(ResponseErrorUnprocessableEntityErrorsInner.CodeEnum.DATA_EXPIRACAO_INVALIDA.getValue())
                        .detail("Expiration Date Time '" + zuluDateTime + "' is invalid! The expiration date cannot be less than or equal to the current date.")
                        .build());
                if (executeThrowImmediately) {
                    throw new ConsentResponseErrorException(new Gson().toJson(listResponseErrors));
                }
                return ResponseValidateConsentModel.builder()
                        .errorsListed(true)
                        .responseErrorsList(listResponseErrors)
                        .build();
            } else if (actualDateTime.plusMonths(3).format(PARSER1).equals(expirationDateTime.format(PARSER1))) {
                expirationInMonths = 3L;
                expirationDateInfo = ExpirationOptions.TRIMESTRAL.name();
            } else if (actualDateTime.plusMonths(6).format(PARSER1).equals(expirationDateTime.format(PARSER1))) {
                expirationInMonths = 6L;
                expirationDateInfo = ExpirationOptions.SEMESTRAL.name();
            } else if (actualDateTime.plusMonths(12).format(PARSER1).equals(expirationDateTime.format(PARSER1))) {
                expirationInMonths = 12L;
                expirationDateInfo = ExpirationOptions.ANUAL.name();
            } else {
                listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                        .title("Consent validate: Error in validate ExpirationDateTime.")
                        .code(ResponseErrorUnprocessableEntityErrorsInner.CodeEnum.DATA_EXPIRACAO_INVALIDA.getValue())
                        .detail("Expiration Date Time '" + zuluDateTime + "' is invalid! " +
                                "The expiration date must follow the rule of the term: TRIMESTRAL, SEMESTRAL ou ANUAL. " +
                                "(Day-month-year terms must be exact from the current date. Note: HH:mm:ss are not considered)")
                        .build());
                if (executeThrowImmediately) {
                    throw new ConsentResponseErrorException(new Gson().toJson(listResponseErrors));
                }
                return ResponseValidateConsentModel.builder()
                        .errorsListed(true)
                        .responseErrorsList(listResponseErrors)
                        .build();
            }

            respponseExpirationDatetimeModel.setExpirationDateInfo(expirationDateInfo);
            respponseExpirationDatetimeModel.setExpirationDateTimeAdjusted(expirationDateTimeAdjusted);
            respponseExpirationDatetimeModel.setExpirationDateTimeRequested(expirationDateTimeRequested);
            respponseExpirationDatetimeModel.setExpirationDateTimeStamp(expirationDateTimeStamp);
            respponseExpirationDatetimeModel.setExpirationInMonths(expirationInMonths);

            return ResponseValidateConsentModel.builder()
                    .errorsListed(false)
                    .responseErrorsList(listResponseErrors)
                    .objectData(respponseExpirationDatetimeModel)
                    .build();

        } catch (Exception e) {
            log.error(e.getMessage());
            listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent validate: Error in validate ExpirationDateTime.")
                    .code(ResponseErrorUnprocessableEntityErrorsInner.CodeEnum.ERRO_NAO_MAPEADO.getValue())
                    .detail("Consent validate: " + e.getMessage())
                    .build());
            return ResponseValidateConsentModel.builder()
                    .errorsListed(true)
                    .responseErrorsList(listResponseErrors)
                    .build();
        }
    }

}
