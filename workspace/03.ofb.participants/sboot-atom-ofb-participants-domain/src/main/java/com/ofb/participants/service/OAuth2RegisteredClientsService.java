package com.ofb.participants.service;

import com.ofb.lib.amqp.service.MessageService;
import com.ofb.participants.entity.OAuth2RegisteredClientsEntity;
import com.ofb.participants.mapper.OAuth2RegisteredClientMapper;
import com.ofb.participants.repository.OAuth2RegisteredClientsRepository;
import com.ofb.participants.server.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.stereotype.Service;

import com.ofb.lib.handlers.exception.ofb.BadRequestException;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class OAuth2RegisteredClientsService {

    @Autowired private
    HttpServletRequest request;

    @Autowired private
    OAuth2RegisteredClientsRepository repository;

    @Autowired private
    PasswordEncoder passwordEncoder;

    @Autowired private
    MessageService messageService;

    public OAuth2RegisteredClientsEntity findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new BadRequestException("id= '" + id + "' not found!"));
    }

    public OAuth2ClientResponse save(ClientCreateRecord clientRequest) {

        if (repository.findByClientId(clientRequest.getClientId()) != null) {
            throw new BadRequestException("Client id='" + clientRequest.getClientId() + "' already exists in other Registered Client. check and adjust!");
        }
        if (repository.findByClientName(clientRequest.getClientName()) != null) {
            throw new BadRequestException("Client Name='" + clientRequest.getClientName() + "' already exists in other Registered Client. check and adjust!");
        }

        String id = UUID.randomUUID().toString();

        OAuth2RegisteredClientsEntity newRegister = OAuth2RegisteredClientsEntity.builder()
                .id(id)
                .clientId(clientRequest.getClientId())
                .clientName(clientRequest.getClientName())
                .clientIdIssuedAt(Date.from(Instant.now()))
                .clientSecretExpiresAt(Date.from(Instant.now().plus(Long.valueOf(clientRequest.getClientSecretExpiresAtOfDays()), ChronoUnit.DAYS)))
                .clientSecret(passwordEncoder.encode(clientRequest.getClientSecret()))
                .clientAuthenticationMethods(this.convertListOfClientAuthenticationMethods(clientRequest.getListOfClientAuthenticationMethods()))
                .authorizationGrantTypes(this.convertListOfAuthorizationGrantTypes(clientRequest.getListOfAuthorizationGrantTypes()))
                .scopes(this.convertListOfScopes(clientRequest.getListOfScopes()))
                .redirectUris(this.convertListOfRedirectUris(clientRequest.getListOfRedirectUris()))
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofMinutes(Long.valueOf(clientRequest.getTokenDurationOfMinutes())))
                        .build().toString())
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(Boolean.valueOf(clientRequest.getRequireAuthorizationConsent()))
                        .build().toString())
                .status("LOCKED")
                .build();

        repository.saveAndFlush(newRegister);

        OAuth2RegisteredClientsEntity clientEntity = this.findById(newRegister.getId());
        OAuth2ClientResponse result = OAuth2RegisteredClientMapper.INSTANCE.entityToResponse(clientEntity);

        messageService.sendMessageAuditTemplate(request);

        return result;
    }

    public OAuth2ClientResponse replace(ClientUpdateRecord clientRequest) {

        OAuth2RegisteredClientsEntity clientEntity = this.findById(clientRequest.getRegisteredId());

        //Validate unique Client Name and unique Client Name
        if (clientRequest.getClientId() != null) {
            if (repository.findByClientId(clientRequest.getRegisteredId(), clientRequest.getClientId()) != null) {
                throw new BadRequestException("Client Id='" + clientRequest.getClientId() + "' already exists in other Registered Client. check and adjust!");
            }
        }
        if (clientRequest.getClientName() != null) {
            if (repository.findByClientName(clientRequest.getRegisteredId(), clientRequest.getClientName()) != null) {
                throw new BadRequestException("Client Name='" + clientRequest.getClientId() + "' already exists in other Registered Client. check and adjust!");
            }
        }

        clientEntity.setClientId(clientRequest.getClientId() != null ? clientRequest.getClientId() : clientEntity.getClientId());
        clientEntity.setClientSecretExpiresAt(clientRequest.getClientSecretExpiresAtOfDays() != null
                                              ? Date.from(Instant.now()
                                                 .plus(Long.valueOf(clientRequest.getClientSecretExpiresAtOfDays().toString())
                                                 , ChronoUnit.DAYS))
                                              : clientEntity.getClientSecretExpiresAt());
        clientEntity.setClientName(clientRequest.getClientName() != null ? clientRequest.getClientName() : clientEntity.getClientName());
        clientEntity.setAuthorizationGrantTypes(clientRequest.getListOfAuthorizationGrantTypes().size() != 0
                                                ? this.convertListOfAuthorizationGrantTypes(clientRequest.getListOfAuthorizationGrantTypes())
                                                : clientEntity.getAuthorizationGrantTypes());
        clientEntity.setClientAuthenticationMethods(clientRequest.getListOfClientAuthenticationMethods().size() != 0
                                                    ? this.convertListOfClientAuthenticationMethods(clientRequest.getListOfClientAuthenticationMethods())
                                                    : clientEntity.getClientAuthenticationMethods());
        clientEntity.setRedirectUris(clientRequest.getListOfRedirectUris().size() != 0
                                     ? this.convertListOfRedirectUris(clientRequest.getListOfRedirectUris())
                                     : clientEntity.getRedirectUris());
        clientEntity.setScopes(clientRequest.getListOfScopes().size() != 0
                               ? this.convertListOfScopes(clientRequest.getListOfScopes())
                               : clientEntity.getScopes());
        clientEntity.setClientSettings(clientRequest.getRequireAuthorizationConsent() != null
                                        ? (clientRequest.getRequireAuthorizationConsent().equals("false")
                                            ? clientEntity.getClientSettings().replace("-consent\":true", "-consent\":false")
                                            : clientEntity.getClientSettings().replace("-consent\":false", "-consent\":true")
                                          )
                                        : clientEntity.getClientSettings());

        if (clientRequest.getTokenDurationOfMinutes() != null) {
            clientEntity.setTokenSettings(TokenSettings.builder()
                    .accessTokenTimeToLive(Duration.ofMinutes(Long.valueOf(clientRequest.getTokenDurationOfMinutes())))
                    .build().toString());
        }

        clientEntity.setStatus(clientRequest.getStatus() != null ? clientRequest.getStatus().toString() : clientEntity.getStatus());
        repository.saveAndFlush(clientEntity);

        clientEntity = this.findById(clientRequest.getRegisteredId());
        OAuth2ClientResponse result = OAuth2RegisteredClientMapper.INSTANCE.entityToResponse(clientEntity);

        messageService.sendMessageAuditTemplate(request);

        return result;
    }

    public String changeClientSecret(String registeredId, String clientSecretOld, String clientSecretNew) {

        OAuth2RegisteredClientsEntity clientEntity = this.findById(registeredId);

        if (!passwordEncoder.matches(clientSecretOld, clientEntity.getClientSecret())) {
            throw new BadRequestException("The Old Secret does not match. check and adjust!");
        }

        clientEntity.setClientSecret(passwordEncoder.encode(clientSecretNew));
        OAuth2ClientResponse result = OAuth2RegisteredClientMapper.INSTANCE.entityToResponse(repository.saveAndFlush(clientEntity));

        messageService.sendMessageAuditTemplate(request);

        return "Change Client Secret executed successfully!";
    }

    public String changeStatus(String registeredId, String status) {

        OAuth2RegisteredClientsEntity clientEntity = this.findById(registeredId);
        clientEntity.setStatus(status);

        OAuth2ClientResponse result = OAuth2RegisteredClientMapper.INSTANCE.entityToResponse(repository.saveAndFlush(clientEntity));

        messageService.sendMessageAuditTemplate(request);

        return "Change Client Status executed successfully!";
    }

    public String convertListOfScopes(List<Scopes> request) {
        String result = "";
        for (Scopes reg:request) {
            if (result.length() != 0) {
                result+=  ",";
            }
            result+= reg.getScope();
        }
        return result;
    }

    public String convertListOfRedirectUris(List<Uris> request) {
        String result = "";
        for (Uris reg:request) {
            if (result.length() != 0) {
                result+=  ",";
            }
            result+= reg.getRedirectUri();
        }
        return result;
    }

    public String convertListOfAuthorizationGrantTypes(List<GrandTypes> request) {
        String result = "";
        for (GrandTypes reg:request) {
            if (result.length() != 0) {
                result+=  ",";
            }
            result+= reg.getAuthorizationGrantType();
        }
        return result;
    }

    public String convertListOfClientAuthenticationMethods(List<Methods> request) {
        String result = "";
        for (Methods reg:request) {
            if (result.length() != 0) {
                result+=  ",";
            }
            result+= reg.getClientAuthenticationMethod();
        }
        return result;
    }
    
}
