package com.julianduru.oauthservice.api;

import com.julianduru.oauthservice.config.ClientAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * created by julian on 09/04/2022
 */
public class CustomAuthenticationConfigurer extends AbstractHttpConfigurer<CustomAuthenticationConfigurer, HttpSecurity> {

    private final ClientAuthenticationProvider authenticationProvider;


    public CustomAuthenticationConfigurer(ClientAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        ProviderManager authenticationManager = (ProviderManager)http.getSharedObject(AuthenticationManager.class);
        authenticationManager.getProviders().add(authenticationProvider);

        http.addFilter(new BasicAuthenticationFilter(authenticationManager));
    }


    public static CustomAuthenticationConfigurer configure(ClientAuthenticationProvider authenticationProvider) {
        return new CustomAuthenticationConfigurer(authenticationProvider);
    }


}