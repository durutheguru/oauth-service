package com.julianduru.oauthservice;

import org.springframework.security.oauth2.core.oidc.OidcScopes;

/**
 * created by julian on 18/04/2022
 */
public interface Scopes extends OidcScopes {


    String READ = "read";


    String WRITE = "write";


}


