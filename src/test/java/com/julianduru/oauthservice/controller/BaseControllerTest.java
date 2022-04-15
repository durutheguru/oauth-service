package com.julianduru.oauthservice.controller;

import com.github.javafaker.Faker;
import com.julianduru.oauthservice.config.TestDataSourceConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * created by julian on 15/04/2022
 */
@Slf4j
@Testcontainers
@ExtendWith({SpringExtension.class})
@SpringBootTest(
    classes = {
        TestDataSourceConfig.class,
    }
)
@AutoConfigureMockMvc
public abstract class BaseControllerTest {


    @Autowired
    protected MockMvc mockMvc;


    protected Faker faker = new Faker();


}
