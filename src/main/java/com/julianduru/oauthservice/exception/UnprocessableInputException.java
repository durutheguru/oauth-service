package com.julianduru.oauthservice.exception;

/**
 * created by julian on 24/04/2022
 */
public class UnprocessableInputException extends RuntimeServiceException {

    public UnprocessableInputException() {
    }

    public UnprocessableInputException(String message) {
        super(message);
    }

    public UnprocessableInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnprocessableInputException(Throwable cause) {
        super(cause);
    }

}
