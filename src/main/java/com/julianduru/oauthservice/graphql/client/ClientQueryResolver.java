package com.julianduru.oauthservice.graphql.client;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * created by julian on 22/04/2022
 */
@Component
public class ClientQueryResolver implements GraphQLQueryResolver {


    public List<String> fetchClients() {
        return List.of("client-one");
    }


}

