package com.julianduru.oauthservice.module.client.component;

import com.julianduru.oauthservice.dto.RegisteredClientDto;
import com.julianduru.oauthservice.jooq.tables.Oauth2RegisteredClient;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * created by julian on 25/05/2022
 */
@Component
@RequiredArgsConstructor
public class RegisteredClientFetcher {


    private final DSLContext dsl;


    public List<RegisteredClientDto> fetchClients() {
        var client = Oauth2RegisteredClient.OAUTH2_REGISTERED_CLIENT;

        return dsl
            .select(
                client.ID,
                client.CLIENT_ID,
                client.CLIENT_NAME,
                client.CLIENT_AUTHENTICATION_METHODS,
                client.AUTHORIZATION_GRANT_TYPES,
                client.REDIRECT_URIS
            )
            .from(client)
            .orderBy(client.CLIENT_ID_ISSUED_AT.desc())
            .fetch()
            .map(
                r -> {
                    var clientDto = new RegisteredClientDto();

                    clientDto.setId(r.value1());
                    clientDto.setClientId(r.value2());
                    clientDto.setClientName(r.value3());
                    clientDto.setClientAuthenticationMethods(
                        readValue(r.value4(), ClientAuthenticationMethod::new)
                    );
                    clientDto.setAuthorizationGrantTypes(
                        readValue(r.value5(), AuthorizationGrantType::new)
                    );
                    clientDto.setRedirectUris(
                        readValue(r.value6(), Function.identity())
                    );

                    return clientDto;
                }
            );
    }


    private <T> Set<T> readValue(String value, Function<String, T> mapper) {
        return !StringUtils.hasText(value) ? null
            : Arrays.stream(value.split(","))
                .map(mapper).collect(Collectors.toSet());
    }


}


