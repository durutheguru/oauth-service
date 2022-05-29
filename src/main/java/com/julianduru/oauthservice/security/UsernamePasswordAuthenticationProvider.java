package com.julianduru.oauthservice.security;

import com.julianduru.oauthservice.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * created by julian on 28/05/2022
 */
//@Component
@RequiredArgsConstructor
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {


    private final PasswordEncoder passwordEncoder;


    private final UserDataRepository userDataRepository;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var username = authentication.getPrincipal().toString();
        var credentials = authentication.getCredentials().toString();
        var userOptional = userDataRepository.findByUsernameAndPassword(
            username, passwordEncoder.encode(credentials)
        );

        if (userOptional.isEmpty()) {
//            throw new BadCredentialsException("User Authentication Failed");
            return null;
        }

        return userOptional.get().toToken();
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


}

