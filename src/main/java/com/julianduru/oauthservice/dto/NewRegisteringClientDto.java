package com.julianduru.oauthservice.dto;

import lombok.Data;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;

import javax.validation.constraints.NotEmpty;
import java.util.Map;
import java.util.Set;

/**
 * created by julian on 15/04/2022
 */
@Data
public class NewRegisteringClientDto {


    @NotEmpty(message = "Client Name is required. Cannot be empty.")
    private String clientName;


    @NotEmpty(message = "Client Redirect URI must be provided")
    private Set<String> redirectUris;


    private Map<String, Object> clientSettingsMap;


    private Map<String, Object> tokenSettingsMap;


    public RegisteredClientDto toRegisteredClientDto() {
        var registeredClientDto = new RegisteredClientDto();

        registeredClientDto.setClientName(getClientName());
        registeredClientDto.setRedirectUris(getRedirectUris());
        registeredClientDto.setClientSettings(buildClientSettings());
        registeredClientDto.setTokenSettings(buildTokenSettings());

        return registeredClientDto;
    }


    private ClientSettings buildClientSettings() {
        if (clientSettingsMap == null) {
            return null;
        }

        return ClientSettings.builder().settings(
            settings -> settings.putAll(clientSettingsMap)
        ).build();
    }


    private TokenSettings buildTokenSettings() {
        if (tokenSettingsMap == null) {
            return null;
        }

        return TokenSettings.builder().settings(
            settings -> settings.putAll(tokenSettingsMap)
        ).build();
    }


}



