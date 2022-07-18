package com.julianduru.oauthservice.dto;

import lombok.Data;
import lombok.Getter;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * created by julian on 15/04/2022
 */
@Data
public class ClientDto {


    private String id;


    private String clientId;


    private String clientSecret;


    private String clientName;


    private Set<String> redirectUris;


    private Set<String> clientAuthenticationMethods;


    private Set<String> authorizationGrantTypes;



    public static ClientDto fromRegisteredClient(RegisteredClient client) {
        var dto = new ClientDto();

        dto.setId(client.getId());
        dto.setClientId(client.getClientId());
        dto.setClientSecret(client.getClientSecret());
        dto.setClientName(client.getClientName());
        dto.setRedirectUris(client.getRedirectUris());

        return dto;
    }


    public static ClientDto fromRegisteredClient(RegisteredClientDto clientDto) {
        var dto = new ClientDto();

        dto.setId(clientDto.getId());
        dto.setClientId(clientDto.getClientId());
        dto.setClientSecret(clientDto.getClientSecret());
        dto.setClientName(clientDto.getClientName());
        dto.setRedirectUris(clientDto.getRedirectUris());
        dto.setAuthorizationGrantTypes(
            clientDto.getAuthorizationGrantTypes()
                .stream()
                .map(AuthorizationGrantType::getValue)
                .collect(Collectors.toSet())
        );
        dto.setClientAuthenticationMethods(
            clientDto.getClientAuthenticationMethods()
                .stream()
                .map(ClientAuthenticationMethod::getValue)
                .collect(Collectors.toSet())
        );

        return dto;
    }

}
