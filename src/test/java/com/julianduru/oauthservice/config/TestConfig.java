package com.julianduru.oauthservice.config;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

/**
 * created by julian on 22/04/2022
 */
@Slf4j
@TestConfiguration
public class TestConfig {


    @Autowired
    private ServerProperties serverProperties;


//    @Lazy @Bean
//    public TestRestTemplate testRestTemplate() {
////        await().until(() -> TestServletContextListener.getPort() != 0);
//
//        var builder = new RestTemplateBuilder();
//        builder.rootUri("http://localhost:" + serverProperties.getPort());
//
//        return new TestRestTemplate(builder);
//    }


    @Bean
    public JwtGenerator tokenGenerator(
        JWKSource<SecurityContext> jwkSource,
        OAuth2TokenCustomizer<JwtEncodingContext> accessTokenCustomizer
    ) {
        return new JwtGenerator(jwkSource, accessTokenCustomizer);
    }


}


