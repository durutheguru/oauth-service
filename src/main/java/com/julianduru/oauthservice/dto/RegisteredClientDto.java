package com.julianduru.oauthservice.dto;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;

import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

/**
 * created by julian on 13/04/2022
 */
@Data
public class RegisteredClientDto {

    private String id;

    private String clientId;

    private Instant clientIdIssuedAt;

    private String clientSecret;

    private Instant clientSecretExpiresAt;

    private String clientName;

    private Set<ClientAuthenticationMethod> clientAuthenticationMethods;

    private Set<AuthorizationGrantType> authorizationGrantTypes;

    @NotEmpty(message = "Client Redirect URI must be provided")
    private Set<String> redirectUris;

    private Set<String> scopes;

    private ClientSettings clientSettings;

    private TokenSettings tokenSettings;


    public RegisteredClientDto withId(String id) {
        this.id = id;
        return this;
    }


    public static RegisteredClientDto with(String id) {
        return new RegisteredClientDto().withId(id);
    }


    public RegisteredClient mapToNewEntity(PasswordEncoder encoder) {
        return RegisteredClient.withId(UUID.randomUUID().toString())
            .clientName(clientName)
            .clientId(clientId)
            .clientIdIssuedAt(clientIdIssuedAt)
            .clientSecret(encoder.encode(clientSecret))
            .clientSecretExpiresAt(clientSecretExpiresAt)
            .redirectUris(
                uris -> {
                    if (redirectUris != null) {
                        uris.addAll(redirectUris);
                    }
                }
            )
            .clientAuthenticationMethods(
                methods -> {
                    if (clientAuthenticationMethods != null) {
                        methods.addAll(clientAuthenticationMethods);
                    }
                }
            )
            .authorizationGrantTypes(
                grants -> {
                    if (authorizationGrantTypes != null) {
                        grants.addAll(authorizationGrantTypes);
                    }
                }
            )
            .scopes(
                s -> {
                    if (scopes != null) {
                        s.addAll(scopes);
                    }
                }
            )
            .clientSettings(clientSettings)
            .tokenSettings(tokenSettings)
            .build();
    }


}


