package com.julianduru.oauthservice.graphql.client;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.julianduru.oauthservice.BaseServiceIntegrationTest;
import com.julianduru.oauthservice.data.NewRegisteringClientProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * created by julian on 22/04/2022
 */
public class ClientMutationResolverTest extends BaseServiceIntegrationTest {


    @Autowired
    private GraphQLTestTemplate testTemplate;


    @Autowired
    private NewRegisteringClientProvider clientProvider;


    @Test
    public void testRegisteringNewClient() throws Exception {
        var clientDto = clientProvider.provide();

        var response = testTemplate
            .postMultipart(
                String.format(
                    """
                    mutation {
                        registerClient(client: {
                            clientName: "%s",
                            redirectUris: [
                                "%s"
                            ]
                        }) {
                            id
                            clientId
                            clientSecret
                            clientName
                            redirectUris
                        }
                    }
                    """,
                    clientDto.getClientName(),
                    clientDto.getRedirectUris().stream().findAny().get()
                ), "{}"
            );

        assertThat(response.isOk()).isTrue();

        assertThat(response.get("$.data.registerClient.id")).isNotBlank();
        assertThat(response.get("$.data.registerClient.clientId")).isNotBlank();
        assertThat(response.get("$.data.registerClient.clientSecret")).isNotBlank();
    }


}


