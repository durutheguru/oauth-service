package com.julianduru.oauthservice;

import org.springframework.security.oauth2.server.authorization.config.ConfigurationSettingNames;

/**
 * created by julian on 13/04/2022
 */
public interface AuthServerConstants {


    String API_VERSION = "/api/v1";


    String DEFAULT_AUTHORIZATION_ENDPOINT_URI = "/oauth2/authorize";


    String DEFAULT_TOKEN_ENDPOINT_URI = "/oauth2/token";


    interface ClientTokenSettings {


        String ALLOWED_RESOURCES = "settings.client.allowed.resources";


    }


}
