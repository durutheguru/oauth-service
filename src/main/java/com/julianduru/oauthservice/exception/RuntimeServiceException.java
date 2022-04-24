package com.julianduru.oauthservice.exception;

import com.julianduru.util.CryptoUtil;
import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Slf4j
public class RuntimeServiceException extends RuntimeException implements GraphQLError {


    protected int code = 1000000;


    public RuntimeServiceException() {
        super();
        setCode();
    }


    public RuntimeServiceException(String message) {
        super(message);
        setCode();
    }


    public RuntimeServiceException(String message, Throwable cause) {
        super(message, cause);
        setCode();
    }


    public RuntimeServiceException(Throwable cause) {
        super(cause);
        setCode();
    }


    public static RuntimeServiceException of(Throwable cause) {
        log.error(cause.getMessage(), cause);
        return new RuntimeServiceException(cause);
    }


    @Override
    public String getMessage() {
        String message = super.getMessage();

        try {
            if (StringUtils.hasText(message) && message.contains(":")) {
                message = message.substring(message.indexOf(":") + 2);
            }
        }
        catch (IndexOutOfBoundsException e) {
            log.error(e.getMessage(), e);
        }

        return message;
    }


    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }


    @Override
    public ErrorClassification getErrorType() {
        return null;
    }


    @Override
    public Map<String, Object> getExtensions() {
        return Map.of("code", getCode());
    }


    public Integer generateCode() {
        byte[] classNameBytes = getClass().getName().getBytes();
        return Math.abs(CryptoUtil.hashBytes(classNameBytes));
    }


    private void setCode() {
        this.code = generateCode();
    }


    public int getCode() {
        return code;
    }



}

