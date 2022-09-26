package com.julianduru.oauthservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * created by julian on 09/04/2022
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthorizationServerConfig {


    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain clientSecurityFilterChain(
        HttpSecurity http
    ) throws Exception {
        http
            .requestMatchers(
                customizer -> {
                    customizer.mvcMatchers(
                        "/api/v1/register_client"
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
            .formLogin()
            .and()
            .apply(configurer);

        return http.build();
    }


    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE + 2)
    public SecurityFilterChain defaultSecurityFilterChain(
        HttpSecurity http
    ) throws Exception {
        http
            .authorizeRequests()
            .antMatchers(
                "/signup",
                "/vendor/**/*.js",
                "/vendor/**/*.css",
                "/theme/**/*.js",
                "/theme/**/*.css",
                "/vendor/**/*.map",
                "/vendor/**/*.woff*",
                "/compiled/*"
            )
            .permitAll()
            .and()
            .requestMatchers(
                customizer -> customizer
                    .mvcMatchers("/**")
            )
            .cors().and().csrf().disable()
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .and()
            .oauth2ResourceServer().jwt();

        return http.build();
    }


}

