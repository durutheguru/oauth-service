package com.julianduru.oauthservice.graphql.client;

import com.julianduru.oauthservice.dto.ClientDto;
import com.julianduru.oauthservice.dto.PagedClientResponse;
import com.julianduru.oauthservice.module.client.ClientService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * created by julian on 22/04/2022
 */
@Component
@RequiredArgsConstructor
public class ClientQueryResolver implements GraphQLQueryResolver {


    private final ClientService clientService;


    public List<ClientDto> fetchClients(int page, int size) {
        return clientService.fetchClients(page, size).stream().map(ClientDto::fromRegisteredClient).toList();
    }


}


