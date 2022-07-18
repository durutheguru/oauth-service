package com.julianduru.oauthservice.data;

import com.julianduru.oauthservice.entity.ResourceServer;
import com.julianduru.oauthservice.entity.ServerStatus;
import com.julianduru.oauthservice.repository.ResourceServerRepository;
import com.julianduru.util.test.JpaDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * created by julian on 24/04/2022
 */
@Component
@RequiredArgsConstructor
public class ResourceServerDataProvider implements JpaDataProvider<ResourceServer> {


    private final ResourceServerRepository resourceServerRepository;


    @Override
    public JpaRepository<ResourceServer, Long> getRepository() {
        return resourceServerRepository;
    }

    @Override
    public ResourceServer provide() {
        var server = new ResourceServer();

        server.setResourceServerId(faker.code().isbn10(false));
        server.setAllowedScopes(
            new HashSet<>(
                Set.of(
                    OidcScopes.OPENID,
                    OidcScopes.EMAIL
                )
            )
        );
        server.setStatus(ServerStatus.ACTIVE);

        return server;
    }
}
