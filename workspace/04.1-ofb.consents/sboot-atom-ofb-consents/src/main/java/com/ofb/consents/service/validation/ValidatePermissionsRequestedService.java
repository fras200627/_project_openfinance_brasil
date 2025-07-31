package com.ofb.consents.service.validation;

import com.google.gson.Gson;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import com.ofb.lib.handlers.exception.ofb.UnprocessedEntityException;
import com.ofb.consents.model.ResponseValidateConsentModel;
import com.ofb.consents.repository.views.*;
import com.ofb.consents.server.consents.model.*;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class ValidatePermissionsRequestedService {

    @Autowired
    private ResourcePermissionsViewRepository resourcesPermissionsView;

    /**
     * Validates the Permissions required when creating a new consent.
     * https://openfinancebrasil.atlassian.net/wiki/spaces/OF/pages/219480491/Orienta+es+-+DC+Consentimento
     * <br>
     * @param objectData "Required (values described below)"
     * <p>The objectData parameter used in the validate function must contain valid information from a 'PermissionsEnum' List for the Permissions that require creating a consent.
     * <p> The objectData can be:
     * <p> - CreateConsent object -->> where CreateConsent.getData().getPermissions() is valid.
     * <p> - CreateConsentData object -->> where CreateConsentData().getPermissions() is valid.
     * <p> - List of PermissionsEnum object -->> where List of CreateConsentData.PermissionsEnum is valid.
     * </br>
     * @param referenceId "Optional (may be null)"
     * <p>The referenceId parameter is used to set a 'Key' value, if needed. Ex.: consentId
     * <p><b>NOTE: THIS METHOD DOES NOT USE THIS PARAMETER</b>
     * </br>
     * @param executeThrowImmediately "Required (values: true or false)"
     * <p>The executeThrowImmediately parameter is used to interrupt method execution
     * throwing a 'ConsentResponseErrorException' exception
     * </br>
     * @return ResponseValidateConsentModel
     * <p>Returns ObjectResponse with results for validation
     * <p><b>Note</b></p>
     * <p>in the 'objectData' field, returns a Validate List object of ResponseConsentData.PermissionsEnum
     * </br>
     * @throws UnprocessedEntityException
     * @throws InternalErrorException
     * <p>if an error occurs while trying to invoke the method
     */
    public ResponseValidateConsentModel validateRequestedPermissionsExists(Object objectData, Object referenceId, Boolean executeThrowImmediately) {

        List<CreateConsentData.PermissionsEnum> permissionsData = new ArrayList<>();
        List<ResponseErrorsInnerTemplate> listResponseErrors = new ArrayList<>();
//        List<ResponseConsentData.PermissionsEnum> permissionsRequested = new ArrayList<>();
        List<ResponseConsentData.PermissionsEnum> permissionsResponse = new ArrayList<>();

        ///  ObjectData parameter validate
        try {
            if (objectData == null) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Consent Permissions Requested validate")
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

            } else if (objectData instanceof CreateConsent) {
                permissionsData = ((CreateConsent) objectData).getData().getPermissions();
            } else if (objectData instanceof CreateConsentData) {
                permissionsData = ((CreateConsentData) objectData).getPermissions();
            } else if (objectData instanceof List) {
                permissionsData = (List<CreateConsentData.PermissionsEnum>) objectData;
            }
            if (permissionsData == null || permissionsData.isEmpty()) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Consent Permissions Requested validate")
                        .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("payload without the permissions tag or tag without informed permissions.")
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
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Consent Permissions Requested validate")
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

        try {
            List<String> listResourcesPermissionsAvailable = resourcesPermissionsView.findAllPermissionNamesByQualifiedPF();

            for (CreateConsentData.PermissionsEnum permission : permissionsData) {
                if (listResourcesPermissionsAvailable.contains(permission.getValue())) {
                    permissionsResponse.add(ResponseConsentData.PermissionsEnum.fromValue(permission.getValue()));
                }
            }

            if (permissionsResponse.isEmpty() || (permissionsResponse.size() == 1 && permissionsResponse.get(0).toString().equals("RESOURCES_READ"))) {
                listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                        .title("Consent Permissions Requested validate")
                        .code(ResponseOFBCodesEnum.CodeEnum.COMBINACAO_PERMISSOES_INCORRETA.getValue())
                        .detail("No permissions were found as available from the requested permissions list.")
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
                    .title("Consent Permissions Requested validate")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("Consent Permissions Requested validate: " + e.getMessage())
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
                .errorsListed(false)
                .responseErrorsList(listResponseErrors)
                .objectData(permissionsResponse)
                .build();
    }

}
