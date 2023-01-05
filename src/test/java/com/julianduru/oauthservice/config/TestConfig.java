package com.julianduru.oauthservice.config;

import com.julianduru.oauthservice.data.NewRegisteringClientProvider;
import com.julianduru.oauthservice.graphql.GraphQLTemplateJwtTokenBuilder;
import com.julianduru.oauthservice.module.client.ClientService;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;

/**
 * created by julian on 22/04/2022
 */
@Slf4j
@TestConfiguration
public class TestConfig {


    @Autowired
    private ServerProperties serverProperties;


    @Bean
    public JwtGenerator tokenGenerator(
        JWKSource<SecurityContext> jwkSource,
        OAuth2TokenCustomizer<JwtEncodingContext> accessTokenCustomizer
    ) {
        return new JwtGenerator(jwkSource, accessTokenCustomizer);
    }


    @Bean
    public GraphQLTemplateJwtTokenBuilder jwtTokenBuilder(
        NewRegisteringClientProvider clientProvider,
        ClientService clientService,
        RegisteredClientRepository clientRepository,
        ProviderSettings providerSettings,
        OAuth2TokenGenerator<?> jwtGenerator
    ) {
        return new GraphQLTemplateJwtTokenBuilder(
            clientProvider,
            clientService,
            clientRepository,
            providerSettings,
            jwtGenerator
        );
    }


}


