package com.julianduru.oauthservice.config.jooq;

import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

/**
 * created by julian on 25/05/2022
 */
@Configuration
public class JooqConfig {

    @Value("${jooq.generator.db.dialect}")
    private String jooqDialect;



    @Bean
    public DataSourceConnectionProvider connectionProvider(DataSource dataSource) {
        return new DataSourceConnectionProvider(dataSource);
    }


    @Bean
    public ExceptionTranslator exceptionTransformer() {
        return new ExceptionTranslator();
    }


    @Bean
    public DefaultConfiguration configuration(
        DataSourceConnectionProvider connectionProvider, ExceptionTranslator exceptionTransformer
    ) {
        var jooqConfiguration = new DefaultConfiguration();

        jooqConfiguration.set(connectionProvider);
        jooqConfiguration.set(new DefaultExecuteListenerProvider(exceptionTransformer));
        jooqConfiguration.set(SQLDialect.valueOf(jooqDialect));

        return jooqConfiguration;
    }


    @Bean
    public DefaultDSLContext dsl(DefaultConfiguration configuration) {
        return new DefaultDSLContext(configuration);
    }


}


