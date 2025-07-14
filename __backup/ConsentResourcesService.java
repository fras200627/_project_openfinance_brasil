package com.ofb.consents.service;

import com.ofb.consents.client.authentication.resources.handler.AppAuthenticationResourcesApi;
import com.ofb.consents.client.authentication.resources.model.*;
import com.ofb.consents.client.authentication.resources.model.BusinessEntity;
import com.ofb.consents.client.authentication.resources.model.BusinessEntityDocument;
import com.ofb.consents.client.authentication.resources.model.LoggedUser;
import com.ofb.consents.client.authentication.resources.model.LoggedUserDocument;
import com.ofb.consents.client.registered.clients.resources.handler.RegisteredClientsResourcesApi;
import com.ofb.consents.entity.*;
import com.ofb.consents.model.*;
import com.ofb.consents.repository.data.*;
import com.ofb.consents.repository.views.*;
import com.ofb.consents.server.consents.resources.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ConsentResourcesService {

    @Autowired
    private HttpServletRequest request;

    @Value("${app.consents.organization}")
    private String myOrganization;

    @Value("${app.consents.validate.alreadyExists}")
    private boolean consentsValidateAlreadyExists;

    private String consentId;

    /// Data Flow Control Objects
    private Timestamp              timestampThisOperation;
    private PersonalDataModel      personalDataView;

    @Autowired private ValidateExpirationDatetimeService validateExpirationDatetimeService;
    private List<ResponseConsentData.PermissionsEnum> permissionsRequested;
    private List<ResponseConsentData.PermissionsEnum> permissionsResponse;

    ///  Access to Client Services
    @Autowired private AppAuthenticationResourcesApi                        authenticationResourcesApi;
    @Autowired private RegisteredClientsResourcesApi                        registeredClientsResourcesApi;

    /// Data Persistence Repositories
    @Autowired private ConsentPersonalRepository                            consentsRepository;
    @Autowired private ConsentPermissionsRequestedRepository                permissionsRequestedRepository;
    @Autowired private ConsentPermissionsAuthorisedRepository               permissionsAuthorisedRepository;
    @Autowired private ConsentResourcesConfirmedRepository                  resourcesConfirmedRepository;
    @Autowired private ConsentResourcesPermissionsConfirmedRepository       resourcesPermissionsConfirmedRepository;

    /// Data Query Repositories
    @Autowired private PersonalDataViewRepository                           personalsRepositoryView;
    @Autowired private ConsentPersonalViewRepository                        consentsRepositoryView;
    @Autowired private ResourcePermissionsViewRepository                    resourcesPermissionsView;
    @Autowired private ConsentPermissionsRequestedlViewRepository           permissionsRequestedView;
    @Autowired private ConsentPermissionsAuthorisedlViewRepository          permissionsAuthorisedView;
    @Autowired private ConsentResourcesAuthorisedlCustomersViewRepository   resourcesAuthorisedCustomersView;
    @Autowired private ConsentResourcesAuthorisedlAccountsViewRepository    resourcesAuthorisedAccountsView;


    public void buildConsentPermissionsAndResources(String consentId) {

        ConsentPersonalModel consentsPersonalAccepted = consentsRepositoryView.findById(consentId).get();
        
        //======================================================================================================================================
        // *** REGISTER CONSENT PERMISSION AUTHORIZATIONS ***
        //======================================================================================================================================
        /// Step 01: Execute a register of consent permissions authorised 
        List<ConsentPermissionRequestedModel> listConsentsPermissionsRequested = permissionsRequestedView.findAllConsentsPermissionsRequestedByConsentId(consentId);
        for (ConsentPermissionRequestedModel permission : listConsentsPermissionsRequested) {
            timestampThisOperation = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
            permissionsAuthorisedRepository.saveAndFlush(ConsentPermissionsAuthorised.builder()
                    .consentId(permission.getConsentid())
                    .permissionId(Long.valueOf(permission.getPermissionid()))
                    .createAt(timestampThisOperation)
                    .modifyAt(timestampThisOperation)
                    .userCode("ConsentsServiceAPI")
                    .build());
        }

        //======================================================================================================================================
        // *** REGISTER RESOURCES CUSTOMERS REQUESTED ***
        //======================================================================================================================================
        /// Step 02 : Insert a Consent Resources Customer requested 
        String customerPersonalId = resourcesAuthorisedCustomersView.findPersonalIdOfConsentResourceAuthorisedCustomerByConsentId(consentsPersonalAccepted.getConsentId());
        timestampThisOperation = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
        ConsentResourcesConfirmed resourcesCustomerConfirmed = resourcesConfirmedRepository.saveAndFlush(ConsentResourcesConfirmed.builder()
                .consentId(consentsPersonalAccepted.getConsentId())
                .resourceId(customerPersonalId)
                .resourceIdSummary("customerId")
                .resourceStatus(4L) //Pending Authorization
                .resourceTypeId(3L) //CUSTOMER
                .createAt(timestampThisOperation)
                .modifyAt(timestampThisOperation)
                .userCode("ConsentsServiceAPI")
                .build());

        List<ConsentsResourcesAuthorisedCustomersModel> listConsentsResourcesAuthorisedCustomers = resourcesAuthorisedCustomersView.findAllConsentsResourcesAuthorisedCustomersByConsentId(consentsPersonalAccepted.getConsentId());
        for (ConsentsResourcesAuthorisedCustomersModel reg : listConsentsResourcesAuthorisedCustomers) {
            timestampThisOperation = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
            resourcesPermissionsConfirmedRepository.saveAndFlush(ConsentResourcesPermissionsConfirmed.builder()
                    .consentResourceId(resourcesCustomerConfirmed.getId())
                    .permissionId(Long.valueOf(reg.getPermissionid()))
                    .createAt(timestampThisOperation)
                    .modifyAt(timestampThisOperation)
                    .userCode("ConsentsServiceAPI")
                    .build());
        }

        //======================================================================================================================================
        // *** REGISTER RESOURCES ACCOUNTS REQUESTED ***
        //======================================================================================================================================
        /// Step 01 : Insert a Consent Resources Accounts and Permissions 
        List<String> listCustomerAccountsId = resourcesAuthorisedAccountsView.findAccountIdOfConsentResourceAuthorisedAccountsByConsentId(consentsPersonalAccepted.getConsentId());
        for (String accountId : listCustomerAccountsId) {
            //Resource Account
            timestampThisOperation = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
            ConsentResourcesConfirmed resourcesConfirmed = resourcesConfirmedRepository.saveAndFlush(ConsentResourcesConfirmed.builder()
                    .consentId(consentsPersonalAccepted.getConsentId())
                    .resourceId(accountId)
                    .resourceIdSummary("accountId")
                    .resourceStatus(4L) //Pending Authorization
                    .resourceTypeId(4L) //ACCOUNT
                    .createAt(timestampThisOperation)
                    .modifyAt(timestampThisOperation)
                    .userCode("ConsentsServiceAPI")
                    .build());

            // Resources Accounts Permissions
            List<ConsentsResourcesAuthorisedAccountsModel> listCustomerAccountsPermissions = resourcesAuthorisedAccountsView.findAllConsentsResourcesAuthorisedAccountsByConsentIdAndAccountId(resourcesConfirmed.getConsentId(), resourcesConfirmed.getResourceId());
            for (ConsentsResourcesAuthorisedAccountsModel permissionAuthorisedModel : listCustomerAccountsPermissions) {
                timestampThisOperation = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
                resourcesPermissionsConfirmedRepository.saveAndFlush(ConsentResourcesPermissionsConfirmed.builder()
                        .consentResourceId(resourcesConfirmed.getId())
                        .permissionId(permissionAuthorisedModel.getPermissionid())
                        .createAt(timestampThisOperation)
                        .modifyAt(timestampThisOperation)
                        .userCode("ConsentsServiceAPI")
                        .build());
            }
        }
        //======================================================================================================================================

        //======================================================================================================================================
        // *** SETTING CONSENT AUTHORISED ***
        //======================================================================================================================================
        /// Step 01: Execute consent authorization 
        timestampThisOperation = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
        ConsentPersonalData consentCreated = consentsRepository.findById(consentsPersonalAccepted.getConsentId()).get();
        consentCreated.setConsentStatusId(81L);
        consentCreated.setStatus("AUTHORISED");
        consentCreated.setAwaitingAuthEnd(timestampThisOperation);
        consentCreated.setAwaitingAuthAdditionalInfo("awaiting finished");
        consentCreated.setAuthorisedBy(consentsPersonalAccepted.getAuthorisedBy());
        consentCreated.setAuthorisedAdditionalInfo("authorised finished");
        consentCreated.setAuthorisedStart(timestampThisOperation);
        consentCreated.setAuthorisedEnd(timestampThisOperation);
        consentCreated.setModifyAt(timestampThisOperation);
        consentCreated.setUserCode("ConsentsServiceAPI");
        consentsRepository.saveAndFlush(consentCreated);

        //======================================================================================================================================
        // *** SETTING CONSENT RESOURCES AVAILABLE ***
        //======================================================================================================================================
        /// Step 01: Execute consent resources Customers authorization 
        List<ConsentResourcesConfirmed> listResourcesConfirmed = resourcesConfirmedRepository.findAllResourcesConfirmedByConsentId(consentsPersonalAccepted.getConsentId());
        for (ConsentResourcesConfirmed reg : listResourcesConfirmed) {
            reg.setResourceStatus(1L); //Available
            reg.setModifyAt(Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", "")));
            reg.setUserCode("ConsentsServicesAPI");
            resourcesConfirmedRepository.saveAndFlush(reg);
        }

        //======================================================================================================================================
        // *** CREATE AND REGISTER A ACCESS TOKEN FOR CONSENT ***
        //======================================================================================================================================
        /// Step 01: Create a AccessToken 
        authenticationResourcesApi.getApiClient().setBasePath("http://localhost:3001/ofb-auth-server");
        authenticationResourcesApi.getApiClient().setBearerToken(request.getHeader("Authorization").replace("Bearer ", ""));

        //
        AccessTokenRequest accessTokenRequest = new AccessTokenRequest();
        accessTokenRequest.setConsentId(consentsPersonalAccepted.getConsentId());
        accessTokenRequest.setCreationDateTime(consentsPersonalAccepted.getCreationDatetime().substring(0, 19) + "Z");
        accessTokenRequest.setExpirationDateTime(consentsPersonalAccepted.getExpirationDatetime().substring(0, 19) + "Z");
        //

        //
        LoggedUserDocument loggedUserDocument = new LoggedUserDocument();
        loggedUserDocument.setLoggedUserName(consentsPersonalAccepted.getCivilName());
        loggedUserDocument.setIdentification(consentsPersonalAccepted.getLoggedUserIdentification());
        loggedUserDocument.setRel(consentsPersonalAccepted.getLoggedUserDocumentRel());

        LoggedUser loggedUser = new LoggedUser();
        loggedUser.setDocument(loggedUserDocument);
        accessTokenRequest.setLoggedUser(loggedUser);
        //

        //
        BusinessEntityDocument businessEntityDocument = new BusinessEntityDocument();
        businessEntityDocument.setEntityBusinessName(request.getUserPrincipal().getName());
        businessEntityDocument.setIdentification(consentsPersonalAccepted.getBusinessEntityIdentification());
        businessEntityDocument.setRel(consentsPersonalAccepted.getBusinessEntityDocumentRel());

        BusinessEntity businessEntity = new BusinessEntity();
        businessEntity.setDocument(businessEntityDocument);
        accessTokenRequest.setBusinessEntity(businessEntity);
        //

        //
        List<ConsentPermissionAuthorisedModel> listPermissionAuthorised = permissionsAuthorisedView.findAllConsentsPermissionsAuthorisedByConsentId(consentsPersonalAccepted.getConsentId());
        List<AccessTokenRequest.ScopesEnum> scopes = new ArrayList<>();
        for (ConsentPermissionAuthorisedModel reg : listPermissionAuthorised) {
            scopes.add(AccessTokenRequest.ScopesEnum.fromValue(reg.getPermission()));
        }
        accessTokenRequest.setScopes(scopes);
        //

        TokenResponseModelTemplate tokenResponseModelTemplate = authenticationResourcesApi.postAccessTokenConsents(accessTokenRequest, UUID.randomUUID());

        System.out.println(tokenResponseModelTemplate.getAccessToken());

    }
    
}
