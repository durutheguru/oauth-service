package com.julianduru.oauthservice;

/**
 * created by julian on 13/04/2022
 */
public interface AuthServerConstants {


    String API_BASE = "/api/v1";


    String DEFAULT_AUTHORIZATION_ENDPOINT_URI = "/oauth2/authorize";


    String DEFAULT_TOKEN_ENDPOINT_URI = "/oauth2/token";


    interface ClientTokenSettings {

        String ALLOWED_RESOURCES = "settings.client.allowed.resources";

    }


    interface Patterns {

        String PHONE = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";


        String EMAIL = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

    }


    interface AppModes {

        String BUILD = "build";

        String DEV = "development";

    }


    interface UserDataUpdateAdditionalInfoKey {

        String PROFILE_PHOTO = "profile_photo";

    }


}


