package com.ofb.clients.business.entity;

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
@AllArgsConstructor @NoArgsConstructor @Builder @Data
@EqualsAndHashCode(of= "id")
public class ClientsBusinessEntity implements Serializable {

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
}
