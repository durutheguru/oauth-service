package com.julianduru.oauthservice.modules.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Set;

/**
 * created by julian on 13/04/2022
 */
@Component
public class DefaultRegisteringClientConfigurer implements RegisteringClientConfigurer {


    @Value("${oauth-service.config.access-token-time-to-live}")
    private Long accessTokenTimeToLive;


    @Value("${oauth-service.config.refresh-token-time-to-live}")
    private Long refreshTokenTimeToLive;



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
    public ClientSettings clientSettings() {
        return ClientSettings.builder()
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


}


