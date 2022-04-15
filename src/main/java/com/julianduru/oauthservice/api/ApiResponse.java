package com.julianduru.oauthservice.api;


/**
 * created by julian
 */

public class ApiResponse<T> {


    public final Status status;


    public final String message;


    public final T data;


    public ApiResponse(Status status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }


    public ApiResponse(Status status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }


}



