package com.julianduru.oauthservice.api;


/**
 * created by julian
 */

public class ApiResponse<T> {


    public final ApiStatus status;


    public final String message;


    public final T data;


    public ApiResponse(ApiStatus status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }


    public ApiResponse(ApiStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }


}



