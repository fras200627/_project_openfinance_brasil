package com.ofb.resources.service;

import com.nimbusds.jose.shaded.gson.Gson;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import com.ofb.resources.model.ResourcesPermissionsAuthorisedModel;
import com.ofb.resources.repository.ResourcesPermissionsAuthorisedRepository;
import com.ofb.resources.server.corporate.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class ResourcesCorporateService {

    @Autowired
    private ResourcesPermissionsAuthorisedRepository resourcesPermissionsRepository;

    @Autowired
    private JwtDecoder jwtDecoder;

    public ResourcesAccountPermissions resourcesGetAccountPermissions(String authorization, String consentId) {

        Gson gson = new Gson();
        ConsentIdentification consentIdentification = null;
        List<ResponseErrorsInnerTemplate> listResponseErrors = new ArrayList<>();
        List<ResourcesPermissionsAuthorisedModel> listResourcesPermissions = new ArrayList<>();
        List<ResourcesAccountAuthorisedInner> resourcesAccountAuthorisedInnerList = new ArrayList<>();
        List<PermissionsInner> listPermissions = new ArrayList<>();

        /// Validations
        try {
            listResourcesPermissions = resourcesPermissionsRepository.findAllResourcesAccountsPermissionsByConsentId(consentId);
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources Permissions request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
            throw new InternalErrorException(gson.toJson(listResponseErrors));
        }

        if (listResourcesPermissions.size() == 0) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources Permissions request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Unable to request RESOURCES PERMISSIONS information. The consentId (" + consentId + ") provided in the " +
                            "request has not found in authorized resources.")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        for (ResourcesPermissionsAuthorisedModel reg : listResourcesPermissions) {
            consentIdentification = ConsentIdentification.builder()
                    .consentId(reg.getConsentId())
                    .consentStatus(reg.getConsentStatus())
                    .consentDateCreation(reg.getConsentDateCreation())
                    .consentExpiration(reg.getConsentExpiration())
                    .personalCPF(reg.getPersonalCPF())
                    .personalId(reg.getPersonalId())
                    .personalName(reg.getPersonalName())
                    .build();
            break;
        }

        if (!consentIdentification.getConsentStatus().equals("AUTHORISED")) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources Permissions request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Unable to request RESOURCES PERMISSIONS information. The consentId (" + consentId + ") provided in the " +
                            "request has a current status of [" + consentIdentification.getConsentStatus() + "].")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        if (!OffsetDateTime.parse(consentIdentification.getConsentExpiration()).isAfter(OffsetDateTime.now(ZoneId.of("UTC")))) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources Permissions request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Unable to request RESOURCES PERMISSIONS information. The consentId (" + consentId + ") provided in " +
                            "the request has an ExpirationDateTime " +
                            "(" +
                            consentIdentification.getConsentExpiration()
                            + ") of 'expired'. ")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        String resourceId = listResourcesPermissions.get(0).getResourceId();
        ResourcesAccountAuthorisedInner resourcesAuthorisedInner = null;
        for (ResourcesPermissionsAuthorisedModel reg : listResourcesPermissions) {

            if (!reg.getResourceId().equals(resourceId)) {
                resourcesAccountAuthorisedInnerList.add(resourcesAuthorisedInner);
                resourceId = reg.getResourceId();
                listPermissions = new ArrayList<>();
                resourcesAuthorisedInner = new ResourcesAccountAuthorisedInner();
            }

            listPermissions.add(PermissionsInner.builder()
                    .permission(reg.getPermission())
                    .build());

            resourcesAuthorisedInner = ResourcesAccountAuthorisedInner.builder()
                    .resourceId(reg.getResourceId())
                    .resourceStatus(reg.getResourceStatus())
                    .resourceSummary(reg.getResourceIdSummary())
                    .resourceType(reg.getResourceType())
                    .accountBranchCode(reg.getAccountBranchCode())
                    .accountBrandName(reg.getAccountBrandName())
                    .accountCheckDigit(reg.getAccountCheckDigit())
                    .accountCompanyCNPJ(reg.getAccountCompanyCNPJ())
                    .accountCompeCode(reg.getAccountCompeCode())
                    .accountNumber(reg.getAccountNumber())
                    .accountStatus(reg.getAccountStatus())
                    .accountSubType(reg.getAccountSubType())
                    .accountType(reg.getAccountType())
                    .accountCurrency(reg.getAccountCurrency())
                    .permissions(listPermissions)
                    .build();

        }
        // add last resource after loop
        resourcesAccountAuthorisedInnerList.add(resourcesAuthorisedInner);

        ResourcesAccountPermissions resourcesAccountPermissions = ResourcesAccountPermissions.builder()
                .data(ResourcesAccountPermissionsData.builder()
                        .consentIdentification(consentIdentification)
                        .resourcesAuthorised(resourcesAccountAuthorisedInnerList)
                        .build())
                .meta(Meta.builder()
                        .requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")))
                        .build())
                .build();

        return resourcesAccountPermissions;
    }

    public ResourcesCustomerPermissions resourcesGetCustomerPermissions(String authorization, String consentId) {

        Gson gson = new Gson();
        ConsentCompleteIdentification consentCompleteIdentification = null;
        List<ResponseErrorsInnerTemplate> listResponseErrors = new ArrayList<>();
        List<ResourcesPermissionsAuthorisedModel> listResourcesPermissions = new ArrayList<>();
        List<ResourcesCustomerAuthorisedInner> listResourcesAuthorised = new ArrayList<>();
        List<PermissionsInner> listPermissions = new ArrayList<>();

        /// Validations
        try {
            listResourcesPermissions = resourcesPermissionsRepository.findAllResourcesCustomerPermissionsByConsentId(consentId);
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources Permissions request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
            throw new InternalErrorException(gson.toJson(listResponseErrors));
        }

        if (listResourcesPermissions.size() == 0) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources Permissions request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Unable to request RESOURCES PERMISSIONS information. The consentId (" + consentId + ") provided in the " +
                            "request has not found in authorized resources.")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        for (ResourcesPermissionsAuthorisedModel reg : listResourcesPermissions) {
            consentCompleteIdentification = ConsentCompleteIdentification.builder()
                    .consentId(reg.getConsentId())
                    .consentStatus(reg.getConsentStatus())
                    .consentDateCreation(reg.getConsentDateCreation())
                    .consentExpiration(reg.getConsentExpiration())
                    .personalId(reg.getPersonalId())
                    .personalName(reg.getPersonalName())
                    .personalAddress(reg.getPersonalAddress())
                    .personalBirthDate(reg.getPersonalBirthDate())
                    .personalCountry(reg.getPersonalCountry())
                    .personalCountrySubDivision(reg.getPersonalCountrySubDivision())
                    .personalCpfNumber(reg.getPersonalCpfNumber())
                    .personalDistrictName(reg.getPersonalDistrictName())
                    .personalEmail(reg.getPersonalEmail())
                    .personalPhoneAreaCode(reg.getPersonalPhoneAreaCode())
                    .personalPhoneNumber(reg.getPersonalPhoneNumber())
                    .personalPhoneType(reg.getPersonalPhoneType())
                    .personalPostCode(reg.getPersonalPostCode())
                    .personalSex(reg.getPersonalSex())
                    .personalTownName(reg.getPersonalTownName())
                    .build();
            break;
        }

        if (!consentCompleteIdentification.getConsentStatus().equals("AUTHORISED")) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources Permissions request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Unable to request RESOURCES PERMISSIONS information. The consentId (" + consentId + ") provided in the " +
                            "request has a current status of [" + consentCompleteIdentification.getConsentStatus() + "].")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        if (!OffsetDateTime.parse(consentCompleteIdentification.getConsentExpiration()).isAfter(OffsetDateTime.now(ZoneId.of("UTC")))) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Resources Permissions request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Unable to request RESOURCES PERMISSIONS information. The consentId (" + consentId + ") provided in " +
                            "the request has an ExpirationDateTime " +
                            "(" +
                            consentCompleteIdentification.getConsentExpiration()
                            + ") of 'expired'. ")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        String resourceId = listResourcesPermissions.get(0).getResourceId();
        ResourcesCustomerAuthorisedInner resourcesAuthorisedInner = null;
        for (ResourcesPermissionsAuthorisedModel reg : listResourcesPermissions) {

            if (!reg.getResourceId().equals(resourceId)) {
                listResourcesAuthorised.add(resourcesAuthorisedInner);
                resourceId = reg.getResourceId();
                listPermissions = new ArrayList<>();
                resourcesAuthorisedInner = new ResourcesCustomerAuthorisedInner();
            }

            listPermissions.add(PermissionsInner.builder()
                    .permission(reg.getPermission())
                    .build());

            resourcesAuthorisedInner = ResourcesCustomerAuthorisedInner.builder()
                    .resourceId(reg.getResourceId())
                    .resourceStatus(reg.getResourceStatus())
                    .resourceSummary(reg.getResourceIdSummary())
                    .resourceType(reg.getResourceType())
                    .permissions(listPermissions)
                    .build();

        }
        // add last resource after loop
        listResourcesAuthorised.add(resourcesAuthorisedInner);

        ResourcesCustomerPermissions resourcesCustomerPermissions = ResourcesCustomerPermissions.builder()
                .data(ResourcesCustomerPermissionsData.builder()
                        .consentCompleteIdentification(consentCompleteIdentification)
                        .resourcesAuthorised(listResourcesAuthorised)
                        .build())
                .meta(Meta.builder()
                        .requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")))
                        .build())
                .build();

        return resourcesCustomerPermissions;
    }

}
