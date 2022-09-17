package com.julianduru.oauthservice.module.config;

import com.julianduru.oauthservice.Scopes;
import com.julianduru.oauthservice.dto.RegisteredClientDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

/**
 * created by julian on 13/04/2022
 */
@Component
public class DefaultRegisteringClientConfigurer implements RegisteringClientConfigurer {


    @Value("${oauth-service.config.access-token-time-to-live}")
    private Long accessTokenTimeToLive;


    @Value("${oauth-service.config.refresh-token-time-to-live}")
    private Long refreshTokenTimeToLive;


    @Value("${oauth-service.config.client-secret-time-to-live}")
    private Long clientSecretTimeToLive;



    @Override
    public ClientAuthenticationMethod clientAuthenticationMethod() {
        return ClientAuthenticationMethod.CLIENT_SECRET_BASIC;
    }

    @Override
    public Set<AuthorizationGrantType> authorizationGrantTypes() {
        return Set.of(
            AuthorizationGrantType.AUTHORIZATION_CODE,
            AuthorizationGrantType.REFRESH_TOKEN,
            AuthorizationGrantType.CLIENT_CREDENTIALS,
            AuthorizationGrantType.JWT_BEARER
        );
    }


    @Override
    public Set<String> scopes() {
        return Set.of(Scopes.OPENID, Scopes.READ);
    }


    @Override
    public ClientSettings clientSettings() {
        return ClientSettings.builder()
            .requireProofKey(true)
            .requireAuthorizationConsent(true)
            .build();
    }

    @Override
    public TokenSettings tokenSettings() {
        return TokenSettings.builder()
            .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
            .accessTokenTimeToLive(Duration.ofSeconds(accessTokenTimeToLive))
            .refreshTokenTimeToLive(Duration.ofSeconds(refreshTokenTimeToLive))
            .reuseRefreshTokens(true)
            .build();
    }

    @Override
    public RegisteredClientDto init(RegisteredClientDto clientDto) {
        RegisteringClientConfigurer.super.init(clientDto);

        initClientId(clientDto);
        initClientSecret(clientDto);

        return clientDto;
    }


    private void initClientId(RegisteredClientDto clientDto) {
        if (!StringUtils.hasText(clientDto.getClientId())) {
            clientDto.setClientId(UUID.randomUUID().toString());
        }
        clientDto.setClientIdIssuedAt(Instant.now());
    }


    private void initClientSecret(RegisteredClientDto clientDto) {
        if (!StringUtils.hasText(clientDto.getClientSecret())) {
            clientDto.setClientSecret(UUID.randomUUID() + UUID.randomUUID().toString());
        }
        clientDto.setClientSecretExpiresAt(Instant.now().plusSeconds(clientSecretTimeToLive));
    }


}



