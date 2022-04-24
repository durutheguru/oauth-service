package com.julianduru.oauthservice;


import com.julianduru.oauthservice.config.TestConfig;
import com.julianduru.oauthservice.config.TestDataSourceConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
@SpringBootTest(
    classes = {
        TestConfig.class,
        TestDataSourceConfig.class,
    },
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public abstract class BaseServiceIntegrationTest {





}
