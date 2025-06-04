package com.ofb.authentication.model;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Table(schema = "user", name = "oauth2_registered_client")
@Entity(name = "RegisteredClient")
@Data @Builder @AllArgsConstructor
@RequiredArgsConstructor @ToString
public class RegisteredClientEntity {

    @Id
    private String      id;
    private String      clientId;
    private Timestamp   clientIdIssuedAt;
    private String      clientSecret;
    private Timestamp   clientSecretExpiresAt;
    private String      clientName;
    private String      clientAuthenticationMethods;
    private String      authorizationGrantTypes;
    private String      redirectUris;
    private String      scopes;
    private String      clientSettings;
    private String      tokenSettings;
    private String      status;
}
