package com.julianduru.oauthservice.config;



import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;

import javax.sql.DataSource;

@TestConfiguration
public class TestDataSourceConfig {


    @Bean
    @ConditionalOnProperty(name = "testcontainers.enabled", havingValue = "true")
    public MySQLContainer mySQLContainer() {
        MySQLContainer container = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("oauth_service")
            .withUsername("username")
            .withPassword("password")
            .withUrlParam("createDatabaseIfNotExist", "true")
            .withUrlParam("serverTimezone", "UTC")
            .withReuse(true)
            .withLogConsumer(
                new Slf4jLogConsumer(
                    LoggerFactory.getLogger(getClass())
                )
            );

        container.start();

        return container;
    }


    @Bean
    @Primary
    @ConditionalOnProperty(name = "testcontainers.enabled", havingValue = "true")
    public DataSource dataSource(MySQLContainer mySQLContainer) {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setJdbcUrl(mySQLContainer.getJdbcUrl());
        dataSource.setUsername(mySQLContainer.getUsername());
        dataSource.setPassword(mySQLContainer.getPassword());
        dataSource.setDriverClassName(mySQLContainer.getDriverClassName());

        return dataSource;
    }


}
