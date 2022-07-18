package com.julianduru.oauthservice.config.jooq;


import org.jooq.ExecuteContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultExecuteListener;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;

import java.util.Objects;

/**
 * @author Julian Duru
 */
public class ExceptionTranslator extends DefaultExecuteListener {


    public void exception(ExecuteContext context) {
        var dialect = context.configuration().dialect();
        var translator = new SQLErrorCodeSQLExceptionTranslator(dialect.name());
        context.exception(
            translator.translate(
                "Access database using Jooq",
                context.sql(), context.sqlException()
            )
        );
    }


}


