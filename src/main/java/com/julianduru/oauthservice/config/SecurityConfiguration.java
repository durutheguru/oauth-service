package com.julianduru.oauthservice.config;

import com.julianduru.oauthservice.controller.ClientRegistrationController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * created by julian on 17/04/2022
 */
@Slf4j
@EnableWebSecurity
public class SecurityConfiguration {


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorizeRequests -> {
                try {
                    authorizeRequests
                        .and()
                        .cors().and().csrf().disable()
                        .authorizeRequests()
                        .antMatchers(ClientRegistrationController.PATH).permitAll()
                        .and()
                        .authorizeRequests()
                        .anyRequest().authenticated();
                } catch (Throwable t) {
                    log.error("Error during Security Filter Chain configuration", t);
                }
            })
            .formLogin(Customizer.withDefaults());

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
