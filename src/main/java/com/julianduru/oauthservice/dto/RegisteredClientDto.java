package com.julianduru.oauthservice.dto;

import lombok.Data;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;

import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.Set;

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

    @NotEmpty(message = "Client Name is required. Cannot be empty.")
    private String clientName;

    private Set<ClientAuthenticationMethod> clientAuthenticationMethods;

    private Set<AuthorizationGrantType> authorizationGrantTypes;

    @NotEmpty(message = "Client Redirect URI must be provided")
    private Set<String> redirectUris;

    private Set<String> scopes;

    private ClientSettings clientSettings;

    private TokenSettings tokenSettings;


}
