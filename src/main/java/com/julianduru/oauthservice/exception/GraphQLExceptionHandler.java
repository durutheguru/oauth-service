package com.julianduru.oauthservice.exception;

import graphql.GraphQLException;
import graphql.kickstart.spring.error.ThrowableGraphQLError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;


/**
 * created by julian on 24/04/2022
 */
@Slf4j
@Component
public class GraphQLExceptionHandler {


//    @ExceptionHandler({GraphQLException.class})
//    public ThrowableGraphQLError handleGQLError(GraphQLException e) {
//        return new ThrowableGraphQLError(e);
//    }
//
//
//    @ExceptionHandler({ValidationException.class})
//    public RuntimeServiceException handleValidationException(Exception e) {
//        log.error(e.getMessage(), e);
//        return new RuntimeServiceException(e);
//    }


}
