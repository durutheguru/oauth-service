package com.julianduru.oauthservice.api;


import java.lang.reflect.UndeclaredThrowableException;

/**
 * created by julian
 */
public class ApiBodySanitizer {


    private static final String DEFAULT_ERROR_MESSAGE = "An error has occurred";


    public static String sanitizeMessage(Exception e) {
        String message = "";

        if (e instanceof UndeclaredThrowableException) {
            message = ((UndeclaredThrowableException) e).getUndeclaredThrowable().getMessage();
        }
        else {
            message = e.getMessage();
        }

        if (message == null || containsJavaLanguage(message)) {
            return DEFAULT_ERROR_MESSAGE;
        }

        return message;
    }


    private static boolean containsJavaLanguage(String message) {
        for (String string : ResponseMessageBlackList.blackList) {
            if (message.toLowerCase().contains(string.toLowerCase())) {
                return true;
            }
        }

        return false;
    }


}
