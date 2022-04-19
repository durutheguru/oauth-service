package com.julianduru.oauthservice.data;

import com.julianduru.util.test.DataProvider;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * created by julian on 15/04/2022
 */
@Component
public class RegisteredClientProvider implements DataProvider<RegisteredClient> {


    @Override
    public RegisteredClient provide() {
        return RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId(faker.code().isbn10(false))
            .clientSecret(UUID.randomUUID() + UUID.randomUUID().toString())
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .redirectUri(faker.internet().url())
            .scope(OidcScopes.OPENID)
            .build();
    }


}
