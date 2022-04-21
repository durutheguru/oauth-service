package com.julianduru.oauthservice.exception;


import org.springframework.http.HttpStatus;

/**
 * created by julian
 */
public interface HttpStatusAwareException {


    HttpStatus getHttpStatus();


}
