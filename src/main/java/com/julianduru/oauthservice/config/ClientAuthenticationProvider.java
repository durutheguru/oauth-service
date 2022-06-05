package com.julianduru.oauthservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * created by julian on 31/05/2022
 */
@Component
@RequiredArgsConstructor
public class ClientAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;

    private final RegisteredClientRepository clientRepository;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var clientId = authentication.getPrincipal().toString();
        var clientSecret = authentication.getCredentials().toString();

        var client = clientRepository.findByClientId(clientId);
        if (client == null) {
            return null;
        }

        if (!passwordEncoder.matches(clientSecret, client.getClientSecret())) {
            return null;
        }

        return new OAuth2ClientAuthenticationToken(
            client, ClientAuthenticationMethod.CLIENT_SECRET_BASIC, clientSecret
        );
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication == UsernamePasswordAuthenticationToken.class ||
            authentication == BearerTokenAuthenticationToken.class;
    }


}



