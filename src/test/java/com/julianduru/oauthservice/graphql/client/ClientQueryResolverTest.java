package com.julianduru.oauthservice.graphql.client;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.julianduru.oauthservice.BaseServiceIntegrationTest;
import com.julianduru.oauthservice.data.RegisteredClientProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * created by julian on 25/05/2022
 */
public class ClientQueryResolverTest extends BaseServiceIntegrationTest {


    @Autowired
    private GraphQLTestTemplate testTemplate;


    @Autowired
    private RegisteredClientProvider registeredClientProvider;


    @Autowired
    private RegisteredClientRepository registeredClientRepository;



    @Test
    public void testFetchingClients() throws Exception {
        var clients = registeredClientProvider.provide(5);
        clients.forEach(registeredClientRepository::save);

        var response = testTemplate
            .postMultipart(
        """
                {
                    fetchClients {
                        id
                        clientId
                        clientSecret
                        clientName
                        redirectUris
                        clientAuthenticationMethods
                        authorizationGrantTypes
                    }
                }
                """, "{}"
            );

        assertThat(response.isOk()).isTrue();
    }


}
