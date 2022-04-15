package com.julianduru.oauthservice.dto;

import lombok.Data;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

/**
 * created by julian on 15/04/2022
 */
@Data
public class ClientDto {

    private String id;


    private String clientId;


    private String clientSecret;


    private String clientName;


    public static ClientDto fromRegisteredClient(RegisteredClient client) {
        var dto = new ClientDto();

        dto.setId(client.getId());
        dto.setClientId(client.getClientId());
        dto.setClientSecret(client.getClientSecret());
        dto.setClientName(client.getClientName());

        return dto;
    }

}
