package com.julianduru.oauthservice.graphql.client;


import com.julianduru.oauthservice.dto.ClientDto;
import com.julianduru.oauthservice.dto.NewRegisteringClientDto;
import com.julianduru.oauthservice.dto.NewRegisteringClient;
import com.julianduru.oauthservice.module.client.ClientService;
import com.julianduru.util.ValidatorUtil;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * created by julian on 22/04/2022
 */
@Component
@RequiredArgsConstructor
public class ClientMutationResolver implements GraphQLMutationResolver {


    private final ClientService clientService;


    public ClientDto registerClient(NewRegisteringClient client) {
        ValidatorUtil.validate(client);
        return clientService.registerClient(client);
    }


}



