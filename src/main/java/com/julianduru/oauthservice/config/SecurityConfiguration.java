package com.julianduru.oauthservice.config;

import com.julianduru.oauthservice.api.CustomAuthenticationConfigurer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * created by julian on 17/04/2022
 */
@Slf4j
@EnableWebSecurity
//@Order(1)
public class SecurityConfiguration {


    @Autowired
    private ClientAuthenticationProvider authenticationProvider;


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
            .apply(CustomAuthenticationConfigurer.configure(authenticationProvider))
            .and().csrf().disable()
//            .apply(new HttpBasicConfigurer<>());
//            .authenticationProvider(authenticationProvider)
        ;

        return http.build();
    }



}


