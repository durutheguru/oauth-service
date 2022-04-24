package com.julianduru.oauthservice.data;

import com.julianduru.oauthservice.dto.CreateResourceServerRequest;
import com.julianduru.util.test.DataProvider;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * created by julian on 24/04/2022
 */
@Component
public class CreateResourceServerDataProvider implements DataProvider<CreateResourceServerRequest> {


    @Override
    public CreateResourceServerRequest provide() {
        var request = new CreateResourceServerRequest();

        request.setServerId(faker.code().isbn10(false));
        request.setAllowedScopes(
            Set.of(
                OidcScopes.OPENID,
                OidcScopes.EMAIL
            )
        );

        return request;
    }


}

