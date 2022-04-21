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


    public static ClientDto fromRegisteredClient(RegisteredClientDto clientDto) {
        var dto = new ClientDto();

        dto.setId(clientDto.getId());
        dto.setClientId(clientDto.getClientId());
        dto.setClientSecret(clientDto.getClientSecret());
        dto.setClientName(clientDto.getClientName());

        return dto;
    }

}
