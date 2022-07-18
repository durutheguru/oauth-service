package com.julianduru.oauthservice.graphql.client;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.julianduru.oauthservice.BaseServiceIntegrationTest;
import com.julianduru.oauthservice.data.RegisteredClientProvider;
import com.julianduru.oauthservice.graphql.GraphQLTemplateJwtTokenBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * created by julian on 25/05/2022
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClientQueryResolverTest extends BaseServiceIntegrationTest {


    @Autowired
    private GraphQLTestTemplate testTemplate;


    @Autowired
    private GraphQLTemplateJwtTokenBuilder jwtTokenBuilder;


    @Autowired
    private RegisteredClientProvider registeredClientProvider;


    @Autowired
    private RegisteredClientRepository registeredClientRepository;



    @BeforeAll
    public void beforeClass() {
        jwtTokenBuilder.setupTemplate(testTemplate);
    }


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

