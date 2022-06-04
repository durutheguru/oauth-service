package com.julianduru.oauthservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * created by julian on 17/04/2022
 */
@Slf4j
@EnableWebSecurity
//@Order(1)
public class SecurityConfiguration {


    @Bean
    @Primary
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(
        HttpSecurity http
    ) throws Exception {

        http
            .authorizeHttpRequests(
                (registry) -> registry
                    .mvcMatchers("/api/register_client")
                    .authenticated()
            )
            .httpBasic()
            .and().csrf().disable();

        return http.build();
    }


    @Autowired
    public void setProviderManager(
        AuthenticationManagerBuilder managerBuilder, ClientAuthenticationProvider authenticationProvider
    ) {
        managerBuilder.authenticationProvider(authenticationProvider);
    }



}


