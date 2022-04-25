package com.julianduru.oauthservice.graphql.client;

import com.julianduru.oauthservice.dto.PagedClientResponse;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Component;

/**
 * created by julian on 22/04/2022
 */
@Component
@RequiredArgsConstructor
public class ClientQueryResolver implements GraphQLQueryResolver {


    private final RegisteredClientRepository clientRepository;


    public PagedClientResponse fetchClients(int page, int size) {
        //TODO: possible implementation for fetching clients...
        return PagedClientResponse.empty();
    }


}

