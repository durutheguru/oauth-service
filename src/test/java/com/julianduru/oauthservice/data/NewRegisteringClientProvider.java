package com.julianduru.oauthservice.data;

import com.julianduru.oauthservice.AuthServerConstants;
import com.julianduru.oauthservice.dto.ExtendedConfigurationSettingNames;
import com.julianduru.oauthservice.dto.NewRegisteringClient;
import com.julianduru.oauthservice.entity.ResourceServer;
import com.julianduru.util.test.DataProvider;
import org.json.JSONObject;
import org.springframework.security.oauth2.core.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.config.ConfigurationSettingNames;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * created by julian on 22/04/2022
 */
@Component
public class NewRegisteringClientProvider implements DataProvider<NewRegisteringClient> {


    @Override
    public NewRegisteringClient provide() {
        var client = new NewRegisteringClient();

        client.setClientId(faker.code().isbn10(false));
        client.setClientSecret(faker.code().isbn10(false));
        client.setClientName(faker.name().fullName() + " Client");
        client.setRedirectUris(
            new HashSet<>(
                Set.of(
                    "https://oidcdebugger.com/debug"
                )
            )
        );
        client.setTokenSettingsMap(
            new JSONObject(
                Map.of(
                    ConfigurationSettingNames.Token.ACCESS_TOKEN_TIME_TO_LIVE,
                    1000,
                    ConfigurationSettingNames.Token.REUSE_REFRESH_TOKENS,
                    true
                )
            ).toString()
        );
        client.setClientSettingsMap(
            new JSONObject(
                Map.of(
                    ConfigurationSettingNames.Client.JWK_SET_URL,
                    faker.internet().url(),
                    ExtendedConfigurationSettingNames.Client.ADMIN_EMAIL,
                    faker.internet().emailAddress()
                )
            ).toString()
        );

        return client;
    }


}
