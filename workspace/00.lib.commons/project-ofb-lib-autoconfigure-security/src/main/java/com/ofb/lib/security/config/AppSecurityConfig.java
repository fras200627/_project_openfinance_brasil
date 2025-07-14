package com.ofb.lib.security.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Slf4j
public class AppSecurityConfig {

    @Value("${jwt.public.key}")
    private RSAPublicKey publicKey;

    @Value("${jwt.private.key}")
    private RSAPrivateKey privateKey;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(
                SessionCreationPolicy.STATELESS);
        http.formLogin().disable();
        http.authorizeHttpRequests()
                .antMatchers("/error", "/health", "/actuator/info",  "/swagger*/**", "/v3/api-docs/**", "/token/client").permitAll()
                .anyRequest().authenticated();
        http.httpBasic(Customizer.withDefaults());
        http.oauth2ResourceServer().jwt().decoder(jwtDecoder());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(this.publicKey).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(this.publicKey).privateKey(this.privateKey).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }
















//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.authorizeHttpRequests()
////                .antMatchers("/health", "/actuator/info",  "/swagger*/**", "/v3/api-docs/**").permitAll()
//                .antMatchers("/**").permitAll();
////                .anyRequest().authenticated();
////        http.httpBasic(Customizer.withDefaults());
////        http.oauth2ResourceServer().jwt().decoder(jwtDecoder());
//
//        return http.build();
//    }

    
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http.csrf().disable()
//            .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .authorizeHttpRequests()
//                .antMatchers("/health*/**", "/actuator*/**", "/swagger*/**", "/v3/api-docs/**")
//                    .permitAll()
//                .anyRequest()
//                    .authenticated()
//                    .and().oauth2ResourceServer()
//                    .jwt()
//                    .jwtAuthenticationConverter(jwtAuthenticationConverter());
//
//        log.info("OFB Build SecurityFilterChain executed successfully");
//
//        return http.build();
//
//    }

//    private JwtAuthenticationConverter jwtAuthenticationConverter() {
//        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//
//        converter.setJwtGrantedAuthoritiesConverter(
//                jwt -> {
//                    List<String> userRoleAuthorities = jwt.getClaimAsStringList("authorities");
//
//                    if(userRoleAuthorities == null) {
//                        userRoleAuthorities = Collections.emptyList();
//                    }
//
//                    JwtGrantedAuthoritiesConverter scopesConverter = new JwtGrantedAuthoritiesConverter();
//
//                    Collection<GrantedAuthority> scopeAuthorities = scopesConverter.convert(jwt);
//
//                    scopeAuthorities
//                            .addAll(userRoleAuthorities.stream()
//                            .map(SimpleGrantedAuthority::new)
//                            .toList());
//
//                    return scopeAuthorities;
//                }
//        );
//
//        return converter;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}
