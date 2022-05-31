package com.julianduru.oauthservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * created by julian on 31/05/2022
 */
@Component
@RequiredArgsConstructor
public class ClientAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        return null;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }


}


