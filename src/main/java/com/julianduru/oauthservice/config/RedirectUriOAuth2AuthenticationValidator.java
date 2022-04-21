package com.julianduru.oauthservice.config;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.authentication.OAuth2AuthenticationContext;
import org.springframework.security.oauth2.core.authentication.OAuth2AuthenticationValidator;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationException;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * created by julian on 09/04/2022
 */
public class RedirectUriOAuth2AuthenticationValidator implements OAuth2AuthenticationValidator {


    @Override
    public void validate(OAuth2AuthenticationContext authenticationContext) throws OAuth2AuthenticationException {
        var authentication = (OAuth2AuthorizationCodeRequestAuthenticationToken) authenticationContext.getAuthentication();
        var registeredClient = Objects.requireNonNull(
            authenticationContext.get(RegisteredClient.class)
        );

        var redirectUri = authentication.getRedirectUri();
        if (StringUtils.hasText(redirectUri)) {
            var noneMatch = registeredClient.getRedirectUris().stream()
                .noneMatch(redirectUri::matches);

            if (noneMatch) {
                var error = new OAuth2Error(
                    OAuth2ErrorCodes.INVALID_REQUEST, "redirect_uri",
                    "http://datatracker.ietf.org"
                );
                throw new OAuth2AuthorizationCodeRequestAuthenticationException(error, authentication);
            }
        }
    }


}


