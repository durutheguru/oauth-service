package com.julianduru.oauthservice.util;

import com.julianduru.oauthservice.AuthServerConstants;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

/**
 * created by julian on 26/09/2022
 */
public class ClientUtil {


    public static void verifyClientCanAccessResource(RegisteredClient client, String resourceId) {
        var resources = client.getClientSettings()
            .getSetting(AuthServerConstants.ClientTokenSettings.ALLOWED_RESOURCES)
            .toString();

        if (!resources.contains(resourceId)) {
            throw new SecurityException(
                String.format("Client ID %s not allowed to Resource %s", client.getClientId(), resourceId)
            );
        }
    }


}
