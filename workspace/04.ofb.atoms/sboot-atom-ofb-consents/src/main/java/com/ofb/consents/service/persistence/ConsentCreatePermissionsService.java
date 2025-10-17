package com.ofb.consents.service.persistence;

import com.google.gson.Gson;
import com.ofb.consents.entity.ConsentPermissionsRequested;
import com.ofb.consents.model.ResponseValidateConsentModel;
import com.ofb.consents.repository.data.ConsentPermissionsRequestedRepository;
import com.ofb.consents.repository.views.ResourcePermissionsViewRepository;
import com.ofb.consents.server.model.CreateConsent;
import com.ofb.consents.server.model.CreateConsentData;
import com.ofb.consents.server.model.ResponseConsentData;
import com.ofb.consents.service.validation.ValidatePermissionsRequestedService;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class ConsentCreatePermissionsService {

    @Autowired
    private ValidatePermissionsRequestedService validatePermissionsRequestedService;

    @Autowired
    private ConsentPermissionsRequestedRepository permissionsRequestedRepository;

    @Autowired
    private ConsentCancelService consentCancelService;

    @Autowired
    private ResourcePermissionsViewRepository resourcesPermissionsView;

    /**
     * Create permissions for a new Consent
     * <br>
     * @param objectData "Required (values described below)"
     * </p>The objectData parameter used in the validate function must contain valid information for a 'CreateConsent' object to create the requested consent.
     * <p> The objectData can be:
     * <p> - CreateConsent object -->> where CreateConsent.getData().getPermissions() is valid.
     * <p> - CreateConsentData object -->> where CreateConsentData().getPermissions() is valid.
     * <p> - List of PermissionsEnum object -->> where List of CreateConsentData.PermissionsEnum is valid.
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
     * <p>In the 'objectData' field, returns a Validate List object of ResponseConsentData.PermissionsEnum
     * <br>
     */
    public ResponseValidateConsentModel insertConsentPermissions(Object objectData,
                                                                 Object referenceId,
                                                                 Boolean executeThrowImmediately) {

        String consentId = "";
        List<ResponseErrorsInnerTemplate> listResponseErrors = new ArrayList<>();
        Timestamp timestampThisOperation;

        List<CreateConsentData.PermissionsEnum> permissionsData = new ArrayList<>();
        List<ResponseConsentData.PermissionsEnum> permissionsResponse = new ArrayList<>();

        ///  ObjectData parameter validate
        try {
            if (objectData == null) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Consent validate: Error in validate LoggedUser.")
                        .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                        .detail("ObjectData is null. ObjectData is mandatory and must inform the LoggedUser document.")
                        .build());
                if (executeThrowImmediately) {
                    throw new InternalErrorException(new Gson().toJson(listResponseErrors));
                }
                return ResponseValidateConsentModel.builder()
                        .errorsListed(true)
                        .responseErrorsList(listResponseErrors)
                        .build();
            } else if (objectData instanceof CreateConsent) {
                permissionsData = ((CreateConsent) objectData).getData().getPermissions();
            } else if (objectData instanceof CreateConsentData) {
                permissionsData = ((CreateConsentData) objectData).getPermissions();
            } else if (objectData instanceof List) {
                permissionsData = (List<CreateConsentData.PermissionsEnum>) objectData;
            }
            if (permissionsData == null || permissionsData.isEmpty()) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Consent validate: Permissions invalid.")
                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("payload without the permissions tag or tag without informed permissions.")
                        .build());
                if (executeThrowImmediately) {
                    throw new BadRequestException(new Gson().toJson(listResponseErrors));
                }
                return ResponseValidateConsentModel.builder()
                        .errorsListed(true)
                        .responseErrorsList(listResponseErrors)
                        .build();
            }
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Consent validate: Permissions invalid.")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("payload without the permissions tag or tag without informed permissions.")
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

        ///  referenceId parameter validate
        try {
            if (referenceId == null) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Consent validate: Error in validate referenceId.")
                        .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                        .detail("referenceId is null. Reference Id is mandatory and must inform the Consent Id.")
                        .build());
                if (executeThrowImmediately) {
                    throw new InternalErrorException(new Gson().toJson(listResponseErrors));
                }
                return ResponseValidateConsentModel.builder()
                        .errorsListed(true)
                        .responseErrorsList(listResponseErrors)
                        .build();
            } else if (referenceId instanceof String) {
                consentId = (String) referenceId;
            }
            if (consentId.isEmpty() || consentId.isBlank()) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Consent validate: Error in validate referenceId.")
                        .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                        .detail("referenceId is null. Reference Id is mandatory and must inform the Consent Id.")
                        .build());
                if (executeThrowImmediately) {
                    throw new InternalErrorException(new Gson().toJson(listResponseErrors));
                }
                return ResponseValidateConsentModel.builder()
                        .errorsListed(true)
                        .responseErrorsList(listResponseErrors)
                        .build();
            }
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Consent validate: Error in validate referenceId.")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("referenceId is null. Reference Id is mandatory and must inform the Consent Id.")
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
            permissionsResponse = (List<ResponseConsentData.PermissionsEnum>) validatePermissionsRequestedService
                                    .validateRequestedPermissionsExists(objectData, referenceId, true)
                                    .getObjectData();

            for (ResponseConsentData.PermissionsEnum permission : permissionsResponse) {
                List<String> listOfPermissionsId = resourcesPermissionsView.findPermissionsIdByPermissionName(permission.getValue());
                for (String reg : listOfPermissionsId) {
                    timestampThisOperation = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
                    permissionsRequestedRepository.saveAndFlush(ConsentPermissionsRequested
                            .builder()
                            .consentId(consentId)
                            .permissionId(Long.valueOf(reg))
                            .createAt(timestampThisOperation)
                            .modifyAt(timestampThisOperation)
                            .userCode("ConsentsServiceAPI")
                            .build());
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Consent: Error consent permissions creation.")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("Internal Error creation consent permissions required. Consent cancelled")
                    .build());
            if (executeThrowImmediately) {
                throw new InternalErrorException(new Gson().toJson(listResponseErrors));
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
                .objectData(permissionsResponse)
                .build();
    }

}
