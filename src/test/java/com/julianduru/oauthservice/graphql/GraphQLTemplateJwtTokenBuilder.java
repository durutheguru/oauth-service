package com.julianduru.oauthservice.graphql;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.julianduru.oauthservice.data.NewRegisteringClientProvider;
import com.julianduru.oauthservice.module.client.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.context.ProviderContext;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

/**
 * created by julian on 18/07/2022
 */
@RequiredArgsConstructor
public class GraphQLTemplateJwtTokenBuilder {


    private final NewRegisteringClientProvider clientProvider;


    private final ClientService clientService;


    private final RegisteredClientRepository registeredClientRepository;


    private final ProviderSettings providerSettings;


    private final OAuth2TokenGenerator<?> jwtGenerator;



    public void setupTemplate(GraphQLTestTemplate testTemplate) {
        var client = clientProvider.provide();
        var clientDto = clientService.registerClient(client);

        var registeredClient = registeredClientRepository.findByClientId(clientDto.getClientId());
        if (registeredClient == null) {
            throw new IllegalStateException("Registered Client was not found.");
        }

        var jwt = jwtGenerator.generate(
            DefaultOAuth2TokenContext.builder()
                .principal(
                    new OAuth2ClientAuthenticationToken(
                        registeredClient, ClientAuthenticationMethod.CLIENT_SECRET_JWT,
                        clientDto.getClientSecret()
                    )
                )
                .registeredClient(registeredClient)
                .tokenType(OAuth2TokenType.ACCESS_TOKEN)
                .providerContext(
                    new ProviderContext(
                        providerSettings,
                        providerSettings::getIssuer
                    )
                )
                .authorizedScopes(
                    new HashSet<>(List.of("OPENID"))
                )
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .build()
        );

        if (jwt == null) {
            throw new IllegalStateException("JWT was empty");
        }

        testTemplate.withBearerAuth(jwt.getTokenValue());
    }



}
