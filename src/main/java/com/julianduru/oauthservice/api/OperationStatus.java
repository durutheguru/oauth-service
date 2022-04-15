package com.julianduru.oauthservice.api;


import lombok.Data;
import lombok.Getter;

/**
 * created by julian on 03/03/2021
 */
@Data
public class OperationStatus<T> {


    private Value status;


    private String statusMessage;


    private T data;


    public OperationStatus() { }


    public OperationStatus(Value status) {
        this(status, status.getDefaultMessage());
    }


    public OperationStatus(Value status, String statusMessage) {
        this(status, statusMessage, null);
    }


    public OperationStatus(Value status, String statusMessage, T data) {
        this.status = status;
        this.statusMessage = statusMessage;
        this.data = data;
    }


    public static OperationStatus<String> success() {
        return new OperationStatus<>(Value.SUCCESS);
    }


    public static OperationStatus<String> success(String message) {
        return new OperationStatus<>(Value.SUCCESS, message, null);
    }


    public static OperationStatus<String> success(String message, String data) {
        return new OperationStatus<>(Value.SUCCESS, message, data);
    }


    public static OperationStatus<String> failure() {
        return new OperationStatus<>(Value.FAILURE);
    }


    public static OperationStatus<String> failure(String message) {
        return new OperationStatus<>(Value.FAILURE, message, null);
    }


    public boolean is(Value value) {
        return this.status == value;
    }


    @Getter
    public enum Value {

        PENDING("Pending"),

        IN_PROGRESS("In Progress"),

        SUCCESS("Success"),

        FAILURE("Failure"),

        INDETERMINATE("Unknown");

        private final String defaultMessage;


        Value(String defaultMessage) {
            this.defaultMessage = defaultMessage;
        }


    }


}
