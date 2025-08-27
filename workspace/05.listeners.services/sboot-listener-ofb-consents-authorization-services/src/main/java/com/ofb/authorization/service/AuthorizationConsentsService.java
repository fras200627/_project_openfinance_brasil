package com.ofb.authorization.service;

import com.ofb.authorization.entity.ConsentPermissionsAuthorised;
import com.ofb.authorization.entity.ConsentPersonalData;
import com.ofb.authorization.entity.ConsentResourcesAuthorised;
import com.ofb.authorization.entity.ConsentResourcesPermissionsAuthorised;
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

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

@Service @Slf4j
public class AuthorizationConsentsService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${app.consents.authorization.expires-in-minutes}")
    private Long CONSENTS_EXPIRES_IN_MINUTES;

    @Value("${amqp.ofb.exchange-direct}")
    private String OFB_EXCHANGE_DIRECT;

    @Value("${amqp.ofb.audit.consents-authorization.routing-key}")
    private String AUDIT_CONSENTS_AUTHORIZATION_ROUTING_KEY;

    @Autowired private ConsentPersonalRepository                    consentPersonalRepository;
    @Autowired private ConsentPermissionsRequestedRepository permissionsRequestedView;
    @Autowired private ConsentPermissionsAuthorisedRepository       permissionsAuthorisedRepository;
    @Autowired private ConsentPermissionsAuthorisedlRepository permissionsAuthorisedView;
    @Autowired private PersonalAccountsRepository personalAccounts;
    @Autowired private ConsentResourcesConfirmedRepository          resourcesAuthorisedRepository;
    @Autowired private ConsentResourcesPermissionsConfirmedRepository resourcesPermissionsAuthorisedRepository;

    public void save(MessageAuthorisedConsentTemplate authorizationConsent) {

        ConsentPersonalData consentCreated = consentPersonalRepository.findById(authorizationConsent.getConsentId()).get();
        Timestamp timestampThisOperation = null;
        ConsentResourcesAuthorised resourcesCustomerConfirmed = null;

        /// Step 00: Validation time expiration of consent
        Timestamp timestampNow = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC")).minusMinutes(CONSENTS_EXPIRES_IN_MINUTES));
        if (consentCreated.getAwaitingAuthStart().before(timestampNow)) {
            consentCreated.setStatus("REJECTED");
            consentCreated.setStatusUpdateDatetime(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));
            consentCreated.setConsentStatusId(82L);
            ///
            consentCreated.setAwaitingAuthEnd(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));
            consentCreated.setAwaitingAuthAdditionalInfo("consents rejected - approval time expired");
            //
            consentCreated.setRejectedStartDatetime(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));
            consentCreated.setRejectedEndDatetime(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));
            ///
            consentCreated.setRejectedCode("CONSENT_EXPIRED");
            consentCreated.setRejectedBy("ControlBatchProcessor");
            consentCreated.setRejectedReason("CONSENT_EXPIRED");
            consentCreated.setRejectedAdditionalInfo("consents rejected - approval time expired");
            ///
            consentCreated.setModifyAt(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));
            consentCreated.setUserCode("ConsentsServiceAPI");
            /// SEND A MESSAGE AUDIT
            rabbitTemplate.convertAndSend(OFB_EXCHANGE_DIRECT, AUDIT_CONSENTS_AUTHORIZATION_ROUTING_KEY,
                    consentCreated,
                    new CorrelationData(authorizationConsent.getConsentId()));
            return;
        }

        /// Step 01: Insert consent permissions authorised
        List<ConsentPermissionRequestedModel> listConsentsPermissionsRequested = permissionsRequestedView.findAllConsentsPermissionsRequestedByConsentId(
                                                                                    consentCreated.getConsentId());

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
        List<String> list1 = permissionsAuthorisedView.findPersonalIdCustomerPermissionsAuthorisedByConsentId(consentCreated.getConsentId());
        for (String reg : list1) {
            timestampThisOperation = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
            resourcesCustomerConfirmed = resourcesAuthorisedRepository.saveAndFlush(ConsentResourcesAuthorised.builder()
                .consentId(consentCreated.getConsentId())
                .resourceId(reg)
                .resourceIdSummary("customerId")
                .resourceStatus(4L) //Pending Authorization
                .resourceTypeId(3L) //CUSTOMER
                .createAt(timestampThisOperation)
                .modifyAt(timestampThisOperation)
                .userCode("ConsentsServiceAPI")
                .build());

            List<ConsentPermissionAuthorisedModel> list11 = permissionsAuthorisedView.findAllConsentsCustomersPermissionsAuthorisedByConsentId(consentCreated.getConsentId());
            for (ConsentPermissionAuthorisedModel reg1 : list11) {
                timestampThisOperation = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
                resourcesPermissionsAuthorisedRepository.saveAndFlush(ConsentResourcesPermissionsAuthorised.builder()
                        .consentResourceId(resourcesCustomerConfirmed.getId())
                        .permissionId(Long.valueOf(reg1.getPermissionid()))
                        .createAt(timestampThisOperation)
                        .modifyAt(timestampThisOperation)
                        .userCode("ConsentsServiceAPI")
                        .build());
            }
        }

        /// Step 03 : Insert a Consent Resources Accounts and Permissions
        List<String> list2 = permissionsAuthorisedView.findPersonalIdByAccountPermissionsAuthorisedByConsentId(consentCreated.getConsentId());
        for (String reg : list2) {
            // pesquisa contas
            List<PersonalAccountsModel> listOfAccounts = personalAccounts.findAllAccountsByPersonalId(reg);
            // insere recurso
            for (PersonalAccountsModel regAccount : listOfAccounts) {
                timestampThisOperation = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
                resourcesCustomerConfirmed = resourcesAuthorisedRepository.saveAndFlush(ConsentResourcesAuthorised.builder()
                        .consentId(consentCreated.getConsentId())
                        .resourceId(regAccount.getAccountid())
                        .resourceIdSummary("accountId")
                        .resourceStatus(
                                regAccount.getAccountstatus().equals("ATIVA") ? 4L :
                                regAccount.getAccountstatus().equals("BLOQUEADA") ? 3L : 2L ) //Pending Authorization
                        .resourceTypeId(4L) //ACCOUNT
                        .createAt(timestampThisOperation)
                        .modifyAt(timestampThisOperation)
                        .userCode("ConsentsServiceAPI")
                        .build());

                // pesquisa permissões
                List<ConsentPermissionAuthorisedModel> listAccountPermissions = permissionsAuthorisedView.findAllConsentsAccountsPermissionsAuthorisedByConsentId(consentCreated.getConsentId());
                for (ConsentPermissionAuthorisedModel regAccountPermission : listAccountPermissions) {
                    timestampThisOperation = Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", ""));
                    resourcesPermissionsAuthorisedRepository.saveAndFlush(ConsentResourcesPermissionsAuthorised.builder()
                            .consentResourceId(resourcesCustomerConfirmed.getId())
                            .permissionId(Long.valueOf(regAccountPermission.getPermissionid()))
                            .createAt(timestampThisOperation)
                            .modifyAt(timestampThisOperation)
                            .userCode("ConsentsServiceAPI")
                            .build());
                }
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
        List<ConsentResourcesAuthorised> listResourcesConfirmed = resourcesAuthorisedRepository.findAllResourcesConfirmedByConsentId(consentCreated.getConsentId());
        for (ConsentResourcesAuthorised reg : listResourcesConfirmed) {
            if (reg.getResourceStatus() == 4L) {
                reg.setResourceStatus(1L); //Available
                reg.setModifyAt(Timestamp.valueOf(OffsetDateTime.now(ZoneId.of("UTC")).toString().replace("T", " ").replace("Z", "")));
                reg.setUserCode("ConsentsServicesAPI");
                resourcesAuthorisedRepository.saveAndFlush(reg);
            }
        }

        /// SEND A MESSAGE AUDIT
        rabbitTemplate.convertAndSend(OFB_EXCHANGE_DIRECT, AUDIT_CONSENTS_AUTHORIZATION_ROUTING_KEY,
                authorizationConsent,
                new CorrelationData(authorizationConsent.getConsentId()));
    }

}
