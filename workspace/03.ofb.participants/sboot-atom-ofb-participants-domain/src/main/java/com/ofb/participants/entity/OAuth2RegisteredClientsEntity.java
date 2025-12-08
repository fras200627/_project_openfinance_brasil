package com.ofb.participants.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

//Schema+table Spring-Security defaults
@Table(schema = "user", name = "oauth2_registered_client")
@Entity(name = "Oauth2RegisteredClientEntity")
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(of= "id")
@Builder
public class OAuth2RegisteredClientsEntity implements Serializable {

    /**
     *   `id` varchar(100) NOT NULL,
     *   `client_id` varchar(100) NOT NULL,
     *   `client_id_issued_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     *   `client_secret` varchar(200) DEFAULT NULL,
     *   `client_secret_expires_at` timestamp NULL DEFAULT NULL,
     *   `client_name` varchar(200) NOT NULL,
     *   `client_authentication_methods` varchar(1000) NOT NULL,
     *   `authorization_grant_types` varchar(1000) NOT NULL,
     *   `redirect_uris` varchar(1000) DEFAULT NULL,
     *   `scopes` varchar(1000) NOT NULL,
     *   `client_settings` varchar(2000) NOT NULL,
     *   `token_settings` varchar(2000) NOT NULL,
     *   PRIMARY KEY (`id`)
     */
    
    private static final long serialVersionUID = 0;
    
    @Id @Column(name = "id")
    private String id;

    @Column(name = "clientId")
    private String clientId;

    @Column(name = "clientIdIssuedAt")
    private Date clientIdIssuedAt;

    @Column(name = "clientSecret")
    private String clientSecret;

    @Column(name = "clientSecretExpiresAt")
    private Date clientSecretExpiresAt;

    @Column(name = "clientName")
    private String clientName;

    @Column(name = "clientAuthenticationMethods")
    private String clientAuthenticationMethods;

    @Column(name = "authorizationGrantTypes")
    private String authorizationGrantTypes;

    @Column(name = "redirectUris")
    private String redirectUris;

    @Column(name = "scopes")
    private String scopes;

    @Column(name = "clientSettings")
    private String clientSettings;

    @Column(name = "tokenSettings")
    private String tokenSettings;

    @Column(name = "status")
    private String status;

    @Column(name = "document")
    private String document;

    @Column(name = "document_type")
    private String documentType;

    public boolean isAccountExpired() {
        if (this.status.equals("EXPIRED")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isAccountLocked() {
        if (this.status.equals("LOCKED")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCredentialsExpired() {
        if (this.status.equals("EXPIRED")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEnabled() {
        if (this.status.equals("ENABLED")) {
            return true;
        } else {
            return false;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Date getClientIdIssuedAt() {
        return clientIdIssuedAt;
    }

    public void setClientIdIssuedAt(Date clientIdIssuedAt) {
        this.clientIdIssuedAt = clientIdIssuedAt;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public Date getClientSecretExpiresAt() {
        return clientSecretExpiresAt;
    }

    public void setClientSecretExpiresAt(Date clientSecretExpiresAt) {
        this.clientSecretExpiresAt = clientSecretExpiresAt;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientAuthenticationMethods() {
        return clientAuthenticationMethods;
    }

    public void setClientAuthenticationMethods(String clientAuthenticationMethods) {
        this.clientAuthenticationMethods = clientAuthenticationMethods;
    }

    public String getAuthorizationGrantTypes() {
        return authorizationGrantTypes;
    }

    public void setAuthorizationGrantTypes(String authorizationGrantTypes) {
        this.authorizationGrantTypes = authorizationGrantTypes;
    }

    public String getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(String redirectUris) {
        this.redirectUris = redirectUris;
    }

    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public String getClientSettings() {
        return clientSettings;
    }

    public void setClientSettings(String clientSettings) {
        this.clientSettings = clientSettings;
    }

    public String getTokenSettings() {
        return tokenSettings;
    }

    public void setTokenSettings(String tokenSettings) {
        this.tokenSettings = tokenSettings;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
}
