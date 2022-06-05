package com.julianduru.oauthservice.config;

import com.julianduru.oauthservice.config.properties.BootstrapProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

/**
 * created by julian on 31/05/2022
 */
@Slf4j
public class OAuthJdbcRegisteredClientRepository extends JdbcRegisteredClientRepository {


    private final BootstrapProperties bootstrapProperties;


    private final PasswordEncoder passwordEncoder;


    public OAuthJdbcRegisteredClientRepository(
        JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder, BootstrapProperties bootstrapProperties
    ) {
        super(jdbcTemplate);
        this.passwordEncoder = passwordEncoder;
        this.bootstrapProperties = bootstrapProperties;
    }


    protected void init() {
        var existingClient = this.findByClientId(bootstrapProperties.getClientId());
        if (existingClient != null) {
            log.info("OAuth Service Boot Client already exists. Skipping initialization...");
            return;
        }

        var client = RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId(bootstrapProperties.getClientId())
            .clientSecret(passwordEncoder.encode(bootstrapProperties.getClientSecret()))
            .clientName("OAuth Service Boot")
            .clientIdIssuedAt(Instant.now())
            .authorizationGrantTypes(
                grantTypes -> grantTypes.addAll(
                    Arrays.asList(
                        AuthorizationGrantType.REFRESH_TOKEN,
                        AuthorizationGrantType.CLIENT_CREDENTIALS
                    )
                )
            )
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .build();

        save(client);
        log.info("OAuth Service Boot Client persisted..");
    }


}

