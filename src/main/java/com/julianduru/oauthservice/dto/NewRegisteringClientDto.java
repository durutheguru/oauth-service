package com.julianduru.oauthservice.dto;

import com.julianduru.util.validation.URLCollection;
import lombok.Data;
import org.json.JSONObject;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.ConfigurationSettingNames;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotEmpty;
import java.time.Duration;
import java.util.Map;
import java.util.Set;

/**
 * created by julian on 15/04/2022
 */
@Data
public class NewRegisteringClientDto {


    @NotEmpty(message = "Client Name is required. Cannot be empty.")
    private String clientName;


    @URLCollection(message = "Redirect URIs must be valid URL pattern")
    @NotEmpty(message = "Client Redirect URI must be provided")
    private Set<String> redirectUris;


    private Map<String, Object> clientSettingsMap;


    private Map<String, Object> tokenSettingsMap;


    public static NewRegisteringClientDto from(NewRegisteringClient client) {
        var dto = new NewRegisteringClientDto();

        dto.setClientName(client.getClientName());
        dto.setRedirectUris(client.getRedirectUris());
        if (StringUtils.hasText(client.getClientSettingsMap())) {
            dto.setClientSettingsMap(
                new JSONObject(client.getClientSettingsMap()).toMap()
            );
        }
        if (StringUtils.hasText(client.getTokenSettingsMap())) {
            dto.setTokenSettingsMap(
                new JSONObject(client.getTokenSettingsMap()).toMap()
            );
        }

        return dto;
    }


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
            settings -> {
                settings.putAll(tokenSettingsMap);

                // in case access or refresh tokens are deserialized as Numbers,
                // they need to be converted to Duration of seconds

                if (settings.containsKey(ConfigurationSettingNames.Token.ACCESS_TOKEN_TIME_TO_LIVE)) {
                    var value = settings.get(ConfigurationSettingNames.Token.ACCESS_TOKEN_TIME_TO_LIVE);
                    if (value instanceof Number) {
                        settings.put(
                            ConfigurationSettingNames.Token.ACCESS_TOKEN_TIME_TO_LIVE,
                            Duration.ofSeconds(((Number)value).longValue())
                        );
                    }
                }

                if (settings.containsKey(ConfigurationSettingNames.Token.REFRESH_TOKEN_TIME_TO_LIVE)) {
                    var value = settings.get(ConfigurationSettingNames.Token.REFRESH_TOKEN_TIME_TO_LIVE);
                    if (value instanceof Number) {
                        settings.put(
                            ConfigurationSettingNames.Token.REFRESH_TOKEN_TIME_TO_LIVE,
                            Duration.ofSeconds(((Number)value).longValue())
                        );
                    }
                }
            }
        ).build();
    }


}



