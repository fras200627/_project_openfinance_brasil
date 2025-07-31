package com.ofb.authorization.service;

import com.ofb.authorization.entity.ConsentPermissionsAuthorised;
import com.ofb.authorization.entity.ConsentPersonalData;
import com.ofb.authorization.entity.ConsentResourcesConfirmed;
import com.ofb.authorization.entity.ConsentResourcesPermissionsConfirmed;
import com.ofb.authorization.model.*;
import com.ofb.authorization.repository.data.ConsentPermissionsAuthorisedRepository;
import com.ofb.authorization.repository.data.ConsentPersonalRepository;
import com.ofb.authorization.repository.data.ConsentResourcesConfirmedRepository;
import com.ofb.authorization.repository.data.ConsentResourcesPermissionsConfirmedRepository;
import com.ofb.authorization.repository.views.*;
import com.ofb.lib.amqp.model.MessageAuthorisedConsentTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

@Service @Slf4j
public class AuthorizationConsentsService {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${amqp.ofb.exchange-direct}")
    private String OFB_EXCHANGE_DIRECT;

    @Value("${amqp.ofb.audit.consents-authorization.routing-key}")
    private String AUDIT_CONSENTS_AUTHORIZATION_ROUTING_KEY;

    @Value("${app.paths.clients.authentication-server}")
    private String PATH_AUTHENTICATION_SERVER;

    @Autowired private ConsentPersonalRepository                    consentPersonalRepository;
    @Autowired private ConsentPersonalViewRepository                consentsRepositoryView;
    @Autowired private ConsentPermissionsRequestedlViewRepository   permissionsRequestedView;
    @Autowired private ConsentPermissionsAuthorisedRepository       permissionsAuthorisedRepository;
    @Autowired private ConsentResourcesAuthorisedlCustomersViewRepository resourcesAuthorisedCustomersView;
    @Autowired private ConsentResourcesConfirmedRepository                resourcesConfirmedRepository;
    @Autowired private ConsentResourcesPermissionsConfirmedRepository     resourcesPermissionsConfirmedRepository;
    @Autowired private ConsentResourcesAuthorisedlAccountsViewRepository  resourcesAuthorisedAccountsView;
    @Autowired private ConsentPermissionsAuthorisedlViewRepository        permissionsAuthorisedView;

    public void save(MessageAuthorisedConsentTemplate authorizationConsent) {

        Timestamp timestampThisOperation = null;
        ConsentPersonalData consentCreated = consentPersonalRepository.findById(authorizationConsent.getConsentId()).get();
        ConsentPersonalModel consentsPersonalAccepted = consentsRepositoryView.findById(authorizationConsent.getConsentId()).get();

        /// Step 01: Insert consent permissions authorised
        List<ConsentPermissionRequestedModel> listConsentsPermissionsRequested = permissionsRequestedView
                                                                                    .findAllConsentsPermissionsRequestedByConsentId(
                                                                                            consentCreated.getConsentId()
                                                                                    );

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

        /// Step 02 : Insert a Consent Resources Customer requested
        String customerPersonalId = resourcesAuthorisedCustomersView.findPersonalIdOfConsentResourceAuthorisedCustomerByConsentId(consentCreated.getConsentId());
        timestampThisOperation = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
        ConsentResourcesConfirmed resourcesCustomerConfirmed = resourcesConfirmedRepository.saveAndFlush(ConsentResourcesConfirmed.builder()
                .consentId(consentCreated.getConsentId())
                .resourceId(customerPersonalId)
                .resourceIdSummary("customerId")
                .resourceStatus(4L) //Pending Authorization
                .resourceTypeId(3L) //CUSTOMER
                .createAt(timestampThisOperation)
                .modifyAt(timestampThisOperation)
                .userCode("ConsentsServiceAPI")
                .build());

        List<ConsentsResourcesAuthorisedCustomersModel> listConsentsResourcesAuthorisedCustomers = resourcesAuthorisedCustomersView
                                                                                                    .findAllConsentsResourcesAuthorisedCustomersByConsentId(
                                                                                                    consentCreated.getConsentId()
                                                                                                    );

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

        /// Step 03 : Insert a Consent Resources Accounts and Permissions
        List<String> listCustomerAccountsId = resourcesAuthorisedAccountsView.findAccountIdOfConsentResourceAuthorisedAccountsByConsentId(consentCreated.getConsentId());
        for (String accountId : listCustomerAccountsId) {
            //Resource Account
            timestampThisOperation = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
            ConsentResourcesConfirmed resourcesConfirmed = resourcesConfirmedRepository.saveAndFlush(ConsentResourcesConfirmed.builder()
                    .consentId(consentCreated.getConsentId())
                    .resourceId(accountId)
                    .resourceIdSummary("accountId")
                    .resourceStatus(4L) //Pending Authorization
                    .resourceTypeId(4L) //ACCOUNT
                    .createAt(timestampThisOperation)
                    .modifyAt(timestampThisOperation)
                    .userCode("ConsentsServiceAPI")
                    .build());

            /// Resources Accounts Permissions
            List<ConsentsResourcesAuthorisedAccountsModel> listCustomerAccountsPermissions = resourcesAuthorisedAccountsView.findAllConsentsResourcesAuthorisedAccountsByConsentIdAndAccountId(
                    resourcesConfirmed.getConsentId(), resourcesConfirmed.getResourceId());
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

        /// Step 04: Execute consent authorization
        timestampThisOperation = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
        consentCreated.setConsentStatusId(81L);
        consentCreated.setStatus("AUTHORISED");
        consentCreated.setAwaitingAuthEnd(timestampThisOperation);
        consentCreated.setAwaitingAuthAdditionalInfo("awaiting finished");
        consentCreated.setAuthorisedBy(consentCreated.getAuthorisedBy());
        consentCreated.setAuthorisedAdditionalInfo("authorised finished");
        consentCreated.setAuthorisedStart(timestampThisOperation);
        consentCreated.setAuthorisedEnd(timestampThisOperation);
        consentCreated.setModifyAt(timestampThisOperation);
        consentCreated.setUserCode("ConsentsServiceAPI");
        consentPersonalRepository.saveAndFlush(consentCreated);

        /// Step 05: Execute consent resources Customers authorization
        List<ConsentResourcesConfirmed> listResourcesConfirmed = resourcesConfirmedRepository.findAllResourcesConfirmedByConsentId(consentCreated.getConsentId());
        for (ConsentResourcesConfirmed reg : listResourcesConfirmed) {
            reg.setResourceStatus(1L); //Available
            reg.setModifyAt(Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", "")));
            reg.setUserCode("ConsentsServicesAPI");
            resourcesConfirmedRepository.saveAndFlush(reg);
        }

        /// SEND A MESSAGE AUDIT
        rabbitTemplate.convertAndSend(OFB_EXCHANGE_DIRECT, AUDIT_CONSENTS_AUTHORIZATION_ROUTING_KEY,
                authorizationConsent,
                new CorrelationData(authorizationConsent.getConsentId()));
    }

}
