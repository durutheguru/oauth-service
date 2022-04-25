package com.julianduru.oauthservice.module.client.component;

import com.julianduru.oauthservice.AuthServerConstants;
import com.julianduru.oauthservice.entity.ResourceServer;
import com.julianduru.oauthservice.exception.UnprocessableInputException;
import com.julianduru.oauthservice.repository.ResourceServerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * created by julian on 25/04/2022
 */
@Component
@RequiredArgsConstructor
public class NewRegisteringClientSettingsValidator {


    private final ResourceServerRepository resourceServerRepository;


    public RegisteredClient valid(RegisteredClient registeredClient) {
        var clientSettings = registeredClient.getClientSettings();
        if (clientSettings.getSetting(AuthServerConstants.ClientTokenSettings.ALLOWED_RESOURCES) != null) {
            var resourcesString = (String) clientSettings.getSetting(
                AuthServerConstants.ClientTokenSettings.ALLOWED_RESOURCES
            );
            var resourceIds = resourcesString.split("\\s*,\\s*");
            var existingResourceIds = resourceServerRepository.findByResourceServerIdIn(
                Set.of(resourceIds)
            ).stream().map(ResourceServer::getResourceServerId).toList();

            for (String id : resourceIds) {
                if (!existingResourceIds.contains(id)) {
                    throw new UnprocessableInputException(
                        String.format("Resource ID %s not found", id)
                    );
                }
            }
        }

        return registeredClient;
    }


}
