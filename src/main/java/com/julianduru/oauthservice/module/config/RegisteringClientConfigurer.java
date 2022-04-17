package com.julianduru.oauthservice.module.config;

import com.julianduru.oauthservice.dto.RegisteredClientDto;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.util.CollectionUtils;

import java.util.Set;

/**
 * created by julian on 13/04/2022
 */
public interface RegisteringClientConfigurer {

    ClientAuthenticationMethod clientAuthenticationMethod();

    Set<AuthorizationGrantType> authorizationGrantTypes();

    ClientSettings clientSettings();

    TokenSettings tokenSettings();


    default RegisteredClientDto init(RegisteredClientDto clientDto) {
        if (clientDto.getClientSettings() == null) {
            clientDto.setClientSettings(this.clientSettings());
        }

        if (clientDto.getTokenSettings() == null) {
            clientDto.setTokenSettings(this.tokenSettings());
        }

        if (CollectionUtils.isEmpty(clientDto.getClientAuthenticationMethods())) {
            clientDto.setClientAuthenticationMethods(Set.of(this.clientAuthenticationMethod()));
        }

        if (CollectionUtils.isEmpty(clientDto.getAuthorizationGrantTypes())) {
            clientDto.setAuthorizationGrantTypes(this.authorizationGrantTypes());
        }

        return clientDto;
    }

}
