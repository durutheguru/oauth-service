package com.julianduru.oauthservice.api;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.julianduru.oauthservice.exception.CodedException;
import com.julianduru.util.json.LocalDateTimeDeserializer;
import com.julianduru.util.json.LocalDateTimeSerializer;

import java.time.LocalDateTime;

/**
 * created by julian
 */
public class ApiErrorResponse extends ApiResponse<String> {

    public Integer code;


    @JsonIgnore
    private Exception exception;


    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm")
    public LocalDateTime timeStamp;


    public ApiErrorResponse() {
        super(ApiStatus.ERROR, null);
        initialize();
    }


    public ApiErrorResponse(String message) {
        super(ApiStatus.ERROR, message);
        initialize();
    }


    public ApiErrorResponse(String message, String data) {
        super(ApiStatus.ERROR, message, data);
        initialize();
    }


    public ApiErrorResponse(Exception exception) {
        super(ApiStatus.ERROR, ApiBodySanitizer.sanitizeMessage(exception));
        this.exception = exception;
        initialize();
    }


    private void initialize() {
        this.code = exception != null && exception instanceof CodedException ?
                ((CodedException)exception).getCode() : -1;
        timeStamp = LocalDateTime.now();
    }


}

