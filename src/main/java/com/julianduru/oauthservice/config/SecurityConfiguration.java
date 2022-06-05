package com.julianduru.oauthservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.filter.CompositeFilter;

import java.util.Arrays;

/**
 * created by julian on 17/04/2022
 */
@Slf4j
@EnableWebSecurity
public class SecurityConfiguration {


    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain clientSecurityFilterChain(
        HttpSecurity http
    ) throws Exception {
        http
            .requestMatchers(
                customizer -> {
                    customizer.mvcMatchers(
                        "/api/register_client",
                        "/api/register_resource_server"
                    );
                }
            )
            .authorizeRequests()
            .anyRequest().authenticated().and().httpBasic()
            .and().cors().and().csrf().disable();

        return http.build();
    }


    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE + 1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(
        HttpSecurity http, AuthenticationManagerBuilder managerBuilder
    ) throws Exception {
        var configurer = new OAuth2AuthorizationServerConfigurer<HttpSecurity>();
        var endpointMatcher = configurer.getEndpointsMatcher();

        http
            .requestMatchers(
                customizer -> customizer
                    .requestMatchers(endpointMatcher)
            )
            .authorizeRequests(
                requests -> requests.anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.ignoringRequestMatchers(endpointMatcher).disable())
            .apply(configurer);

        return http.build();
    }


    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE + 2)
    public SecurityFilterChain defaultSecurityFilterChain(
        HttpSecurity http
    ) throws Exception {
        http
            .requestMatchers(
                customizer -> customizer
                    .mvcMatchers("/**")
            )
            .cors().and().csrf().disable()
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .oauth2ResourceServer().jwt();

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationManagerBuilder authenticationManagerBuilder,
        ClientAuthenticationProvider clientAuthenticationProvider
    ) {
        return authenticationManagerBuilder
            .authenticationProvider(clientAuthenticationProvider).getOrBuild();
    }


}


