package com.julianduru.oauthservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.context.TestConfiguration;

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


}


