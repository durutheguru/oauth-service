package com.julianduru.oauthservice.data;

import com.julianduru.oauthservice.dto.ExtendedConfigurationSettingNames;
import com.julianduru.oauthservice.dto.NewRegisteringClientDto;
import com.julianduru.util.test.DataProvider;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.ConfigurationSettingNames;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;
import java.util.Set;

/**
 * created by julian on 15/04/2022
 */
@Component
public class NewRegisteringClientDtoProvider implements DataProvider<NewRegisteringClientDto> {


    @Override
    public NewRegisteringClientDto provide() {
        var dto = new NewRegisteringClientDto();

        dto.setClientName(faker.name().firstName() + " Client");
        dto.setRedirectUris(Set.of(faker.internet().url()));
        dto.setTokenSettingsMap(
            Map.of(
                ConfigurationSettingNames.Token.ACCESS_TOKEN_TIME_TO_LIVE,
                Duration.ofSeconds(1000),
                ConfigurationSettingNames.Token.REUSE_REFRESH_TOKENS,
                true
            )
        );
        dto.setClientSettingsMap(
            Map.of(
                ConfigurationSettingNames.Client.JWK_SET_URL,
                faker.internet().url(),
                ExtendedConfigurationSettingNames.Client.ADMIN_EMAIL,
                faker.internet().emailAddress()
            )
        );

        return dto;
    }


}
