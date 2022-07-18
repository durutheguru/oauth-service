package com.julianduru.oauthservice.config;

import com.julianduru.oauthservice.config.authproviders.ClientAuthenticationProvider;
import com.julianduru.oauthservice.config.properties.BootstrapProperties;
import com.julianduru.oauthservice.repository.UserDataRepository;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.ProviderManagerBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;
import java.util.UUID;

/**
 * created by julian on 17/04/2022
 */
@Slf4j
@Configuration
public class SecurityBeansConfiguration {


    @Value("${oauth.service.config.issuer.url:http://localhost:8080}")
    private String issuerUrl;

    @Value("${oauth.service.config.password-encoder.id:bcrypt}")
    private String passwordEncoderId;




    @Bean
    public PasswordEncoder passwordEncoder() {
        return new DelegatingPasswordEncoder(
            passwordEncoderId,
            Map.of(
                "bcrypt", new BCryptPasswordEncoder(),
                "noop", NoOpPasswordEncoder.getInstance(),
                "pbkdf2", new Pbkdf2PasswordEncoder(),
                "scrypt", new SCryptPasswordEncoder(),
                "sha256", new StandardPasswordEncoder()
            )
        );
    }


    @Bean
    public RegisteredClientRepository registeredClientRepository(
        JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder, BootstrapProperties bootstrapProperties
    ) {
        var repository = new OAuthJdbcRegisteredClientRepository(jdbcTemplate, passwordEncoder, bootstrapProperties);
        repository.init();

        return repository;
    }


    @Bean
    public OAuth2AuthorizationService oAuth2AuthorizationService(
        JdbcTemplate jdbcTemplate, RegisteredClientRepository clientRepository
    ) {
        return new JdbcOAuth2AuthorizationService(jdbcTemplate, clientRepository);
    }


    @Bean
    public OAuth2AuthorizationConsentService oAuth2AuthorizationConsentService(
        JdbcTemplate jdbcTemplate, RegisteredClientRepository clientRepository
    ) {
        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, clientRepository);
    }


    @Bean
    public UserDetailsService userDetailsService(
        UserDataRepository userDataRepository, PasswordEncoder passwordEncoder, BootstrapProperties bootstrapProperties
    ) {
        var userDetailsService = new OAuthUserDetailsService(passwordEncoder, userDataRepository, bootstrapProperties);
        userDetailsService.init();

        return userDetailsService;
    }


    @Bean
    public JWKSource<SecurityContext> jwkSource() throws NoSuchAlgorithmException {
        RSAKey rsaKey = generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);

        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }


    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }


    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
        return new OAuthServiceTokenCustomizer();
    }


    @Bean
    public ProviderSettings providerSettings() {
        return ProviderSettings.builder()
            .issuer(issuerUrl)
            .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationManagerBuilder authenticationManagerBuilder,
        ClientAuthenticationProvider clientAuthenticationProvider
    ) {
        return authenticationManagerBuilder
            .authenticationProvider(clientAuthenticationProvider).getOrBuild();
    }


    private static RSAKey generateRsa() throws NoSuchAlgorithmException {
        var keyPair = generateRsaKey();
        var publicKey = (RSAPublicKey) keyPair.getPublic();
        var privateKey = (RSAPrivateKey) keyPair.getPrivate();

        return new RSAKey.Builder(publicKey)
            .privateKey(privateKey)
            .keyID(UUID.randomUUID().toString())
            .build();
    }


    private static KeyPair generateRsaKey() throws NoSuchAlgorithmException {
        var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        return keyPairGenerator.generateKeyPair();
    }


}

