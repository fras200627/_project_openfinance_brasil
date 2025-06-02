package com.ofb.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@EnableWebSecurity
@Configuration
public class RegisteredClientConfig {
    
    @Bean
    public RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder,
                                                                 JdbcTemplate jdbcTemplate) {

        JdbcRegisteredClientRepository clientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);

        RegisteredClient ticanAdmin = RegisteredClient
                .withId("99999")
                .clientId("tican-admin-master")
                .clientName("Client Tican Admin Master")
                .clientIdIssuedAt(Instant.now())
                .clientSecretExpiresAt(Instant.now().plus(3650, ChronoUnit.DAYS))
                .clientSecret(passwordEncoder.encode("123456"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .scope("client.read")
                .scope("client.write")
                .scope("client.root")
                .redirectUri("")
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofMinutes(30))
                        .build())
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(false)  //Importante
                        .build())
                .build();
        
        clientRepository.save(ticanAdmin);
        
        /*
        RegisteredClient ticanClient1 = RegisteredClient
                .withId("1")
                .clientId("ticanclient.read")
                .clientName("Client type API consumer scopes client.read")
                .clientSecretExpiresAt(Instant.now())
                .clientSecret(passwordEncoder.encode("123456"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .scope("client.read")
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofMinutes(30))
                        .build())
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(false)
                        .build())
                .build();
        clientRepository.save(ticanClient1);
        
        RegisteredClient ticanClient2 = RegisteredClient
                .withId("2")
                .clientId("ticanclient.write")
                .clientName("Client type API consumer scopes client.write")
                .clientSecretExpiresAt(Instant.now())
                .clientSecret(passwordEncoder.encode("123456"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .scope("client.write")
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofMinutes(30))
                        .build())
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(false)
                        .build())
                .build();
        clientRepository.save(ticanClient2);

        RegisteredClient ticanClient3 = RegisteredClient
                .withId("3")
                .clientId("ticanclient")
                .clientName("Client type API consumer scopes read and write")
                .clientSecretExpiresAt(Instant.now())
                .clientSecret(passwordEncoder.encode("123456"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .scope("client.read")
                .scope("client.write")
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofMinutes(30))
                        .build())
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(false)
                        .build())
                .build();
        clientRepository.save(ticanClient3);
        
        RegisteredClient ticanFront1 = RegisteredClient
                .withId("4")
                .clientId("ticanfront")
                .clientName("Client type front-end consumer")
                .clientSecret(passwordEncoder.encode("123456"))
                .clientSecretExpiresAt(Instant.now())
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("http://localhost:3000/authorized")
                //.redirectUri("https://oidcdebugger.com/debug")
                //.redirectUri("https://oauth.pstmn.io/v1/callback")
                .scope("client.read")
                .scope("client.write")
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofMinutes(15))
                        .refreshTokenTimeToLive(Duration.ofDays(1))
                        .reuseRefreshTokens(false)
                        .build())
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(true)
                        .build())
                .build();
        clientRepository.save(ticanFront1);
        */
        
        return clientRepository;
    }
    
}
