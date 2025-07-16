package com.ofb.consents.service.validation;

import com.google.gson.Gson;
import com.ofb.consents.enums.ConsentResponseEnum;
import com.ofb.consents.exception.ConsentInternalErrorException;
import com.ofb.consents.exception.ConsentUnprocessedEntityException;
import com.ofb.consents.model.ResourcePermissionsModel;
import com.ofb.consents.model.ResponseValidateConsentModel;
import com.ofb.consents.repository.views.ResourcePermissionsViewRepository;
import com.ofb.consents.server.consents.resources.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class ValidateGroupsAndPermissionsService {

    @Autowired
    private ResourcePermissionsViewRepository resourcesPermissionsView;

    /**
     * Validate Groups and Permissions Required in a Consent
     * <br>
     * @param objectData "Required (values described below)"
     * </p>The objectData parameter used in the validate function must contain valid 'Permissions' information for creating a consent.
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
     * <br>
     * @throws ConsentUnprocessedEntityException
     * @throws ConsentInternalErrorException
     * <p>if an error occurs while trying to invoke the method<p></p>
     */
    public ResponseValidateConsentModel validateGroupsAndPermissionsRequested(Object objectData, Object referenceId, Boolean executeThrowImmediately) {

        List<CreateConsentData.PermissionsEnum> permissionsData = new ArrayList<>();
        List<ResponseErrorErrorsInner> listResponseErrors = new ArrayList<>();
        List<String> listPermissionsRequested = new ArrayList<>();

        ///  ObjectData parameter validate
        try {
            if (objectData == null) {
                listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                        .title("Consent Permissions validate")
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

            } else if (objectData instanceof CreateConsent) {
                permissionsData = ((CreateConsent) objectData).getData().getPermissions();
            } else if (objectData instanceof CreateConsentData) {
                permissionsData = ((CreateConsentData) objectData).getPermissions();
            } else if (objectData instanceof List) {
                permissionsData = (List<CreateConsentData.PermissionsEnum>) objectData;
            }
            if (permissionsData == null || permissionsData.isEmpty()) {
                listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                        .title("Consent Permissions validate")
                        .code(ConsentResponseEnum.CodeEnum.BAD_REQUEST.getValue())
                        .detail("payload without the permissions tag or tag without informed permissions.")
                        .build());
                if (executeThrowImmediately) {
                    throw new ConsentUnprocessedEntityException(new Gson().toJson(listResponseErrors));
                }
                return ResponseValidateConsentModel.builder()
                        .errorsListed(true)
                        .responseErrorsList(listResponseErrors)
                        .build();
            }
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent Permissions validate")
                    .code(ConsentResponseEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("payload without the permissions tag or tag without informed permissions.")
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
            /// Verificação completa se as permissions existem
            List<String> listResourcesPermissions = resourcesPermissionsView.findAllPermissionNames();
            for (CreateConsentData.PermissionsEnum permission : permissionsData) {
                listPermissionsRequested.add(permission.getValue());
                if (!listResourcesPermissions.contains(permission.getValue())) {
                    listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                            .title("Consent Permissions validate")
                            .code(ConsentResponseEnum.CodeEnum.BAD_REQUEST.getValue())
                            .detail("Permission = '" + permission.getValue().toString() + "' invalid.")
                            .build());
                }
            }

            /// Verificação se existem permissions duplicadas
            if (listPermissionsRequested.stream().distinct().count() != listPermissionsRequested.size()) {
                for (String permission : listPermissionsRequested.stream().distinct().toList()) {
                    int itemFounds = 0;
                    for (String permissionRequested : listPermissionsRequested) {
                        if (permission.equals(permissionRequested)) {
                            itemFounds++;
                        }
                    }
                    if (itemFounds > 1) {
                        listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                                .title("Consent Permissions")
                                .code(ConsentResponseEnum.CodeEnum.BAD_REQUEST.getValue())
                                .detail("Permission Item = '" + permission + "' it is declared '" + itemFounds + "' times.")
                                .build());
                    }
                }
            }

            /// Verificação se as permissions Mandatórias existem
            List<String> listPermissionsMandatory = resourcesPermissionsView.findAllPermissionsNameByMandatoryQualifiedPF();
            for (String permissionMandatory : listPermissionsMandatory) {
                if (!listPermissionsRequested.contains(permissionMandatory)) {
                    listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                            .title("Consent Permissions validate")
                            .code(ConsentResponseEnum.CodeEnum.BAD_REQUEST.getValue())
                            .detail("Permission = '" + permissionMandatory + "' is mandatory and was not informed.")
                            .build());
                }
            }

            /// Verificação das permissões GroupItems
            List<String> listPermissionsGroupControl = resourcesPermissionsView.findAllPermissionsNameByGroupControl();
            for (String groupControl : listPermissionsGroupControl) {
                if (listPermissionsRequested.contains(groupControl)) {
                    String[] permissionFlag = groupControl.split("_");
                    List<String> listPermissionsGroupItems = resourcesPermissionsView.findAllPermissionsNameByPermissionFlagGroupItem(permissionFlag[0].toString());
                    if (listPermissionsGroupItems.size() == 0) {
                        listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                                .title("Consent Permissions validate")
                                .code(ConsentResponseEnum.CodeEnum.BAD_REQUEST.getValue())
                                .detail("Permission Group = '" + groupControl + "' not found.")
                                .build());
                    } else {
                        int foundItems = 0;
                        for (String groupItem : listPermissionsGroupItems) {
                            if (listPermissionsRequested.contains(groupItem)) {
                                foundItems++;
                            }
                        }
                        if (foundItems == 0) {
                            listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                                    .title("Consent Permissions validate")
                                    .code(ConsentResponseEnum.CodeEnum.BAD_REQUEST.getValue())
                                    .detail("Permission Group = '" + groupControl + "' has no items reported.")
                                    .build());
                        }
                    }
                }
            }

            /// Verificação das permissões GroupControl
            for (String permission : listPermissionsRequested) {
                ResourcePermissionsModel resourcePermission = resourcesPermissionsView.findPermissionByPermissionName(permission);
                if (resourcePermission.getControl().equals("GROUP-ITEM")) {
                    String[] permissionFlag = permission.split("_");
                    List<String> listGroupControl = resourcesPermissionsView.findAllPermissionsNameByPermissionFlagGroupControl(permissionFlag[0].toString());
                    if (listGroupControl.size() != 1) {
                        listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                                .title("Consent Permissions validate")
                                .code(ConsentResponseEnum.CodeEnum.BAD_REQUEST.getValue())
                                .detail("Permission Item = '" + permission + "' has no Group reported.")
                                .build());
                    } else {
                        int foundItems = 0;
                        for (String groupItem : listPermissionsRequested) {
                            if (groupItem.equals(listGroupControl.get(0))) {
                                foundItems++;
                            }
                        }
                        if (foundItems == 0) {
                            listResponseErrors.add(new ResponseErrorErrorsInner().toBuilder()
                                    .title("Consent Permissions validate")
                                    .code(ConsentResponseEnum.CodeEnum.BAD_REQUEST.getValue())
                                    .detail("Permission Item = '" + permission + "' has no Group '" + listGroupControl.get(0) + "' reported.")
                                    .build());
                        }
                    }
                }
            }
            if (listResponseErrors.size() != 0) {
                return ResponseValidateConsentModel.builder()
                        .errorsListed(true)
                        .responseErrorsList(listResponseErrors)
                        .build();
            } else {
                return ResponseValidateConsentModel.builder()
                        .errorsListed(false)
                        .responseErrorsList(listResponseErrors)
                        .build();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            if (executeThrowImmediately) {
                throw new ConsentInternalErrorException(new Gson().toJson(listResponseErrors));
            }
            return ResponseValidateConsentModel.builder()
                    .errorsListed(true)
                    .objectData(e)
                    .responseErrorsList(listResponseErrors)
                    .build();
        }
    }

}
