package com.julianduru.oauthservice.graphql.client;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.julianduru.oauthservice.AuthServerConstants;
import com.julianduru.oauthservice.BaseServiceIntegrationTest;
import com.julianduru.oauthservice.data.NewRegisteringClientProvider;
import com.julianduru.oauthservice.data.ResourceServerDataProvider;
import com.julianduru.oauthservice.dto.NewRegisteringClient;
import com.julianduru.oauthservice.entity.ResourceServer;
import com.julianduru.oauthservice.graphql.GraphQLTemplateJwtTokenBuilder;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * created by julian on 22/04/2022
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClientMutationResolverTest extends BaseServiceIntegrationTest {


    @Autowired
    private GraphQLTestTemplate testTemplate;


    @Autowired
    private GraphQLTemplateJwtTokenBuilder jwtTokenBuilder;


    @Autowired
    private NewRegisteringClientProvider clientProvider;


    @Autowired
    private ResourceServerDataProvider resourceServerDataProvider;



    @BeforeAll
    public void beforeClass() {
        jwtTokenBuilder.setupTemplate(testTemplate);
    }


    @Test
    public void testRegisteringNewClient() throws Exception {
        var clientDto = clientProvider.provide();

        var response = testTemplate
            .postMultipart(
                String.format(
                    """
                    mutation {
                        registerClient(client: {
                            clientId: "%s",
                            clientSecret: "%s",
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
                    clientDto.getClientId(),
                    clientDto.getClientSecret(),
                    clientDto.getClientName(),
                    clientDto.getRedirectUris().stream().findAny().get()
                ), "{}"
            );

        assertThat(response.isOk()).isTrue();

        assertThat(response.get("$.data.registerClient.id")).isNotBlank();
        assertThat(response.get("$.data.registerClient.clientId")).isNotBlank();
        assertThat(response.get("$.data.registerClient.clientSecret")).isNotBlank();
    }


    @Test
    public void testRegisteringNewClientWithoutPassingSecret() throws Exception {
        var clientDto = clientProvider.provide();

        var response = testTemplate
            .postMultipart(
                String.format(
                    """
                    mutation {
                        registerClient(client: {
                            clientId: "%s",
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
                    clientDto.getClientId(),
                    clientDto.getClientName(),
                    clientDto.getRedirectUris().stream().findAny().get()
                ), "{}"
            );

        assertThat(response.isOk()).isTrue();

        assertThat(response.get("$.data.registerClient.id")).isNotBlank();
        assertThat(response.get("$.data.registerClient.clientId")).isNotBlank();
        assertThat(response.get("$.data.registerClient.clientSecret")).isNotBlank();
    }


    @Test
    public void testRegisteringNewClientWithNonExistingResourceServerId() throws Exception {
        var registeringClientSample = new NewRegisteringClient();
        registeringClientSample.setClientSettingsMap(
            new JSONObject(
                Map.of(
                    AuthServerConstants.ClientTokenSettings.ALLOWED_RESOURCES,
                    "resource-server-one"
                )
            ).toString()
        );

        var clientDto = clientProvider.provide(registeringClientSample);

        var response = testTemplate
            .postMultipart(
                String.format(
                    """
                    mutation {
                        registerClient(client: {
                            clientId: "%s",
                            clientName: "%s",
                            redirectUris: [
                                "%s"
                            ],
                            clientSettingsMap: "%s"
                        }) {
                            id
                            clientId
                            clientSecret
                            clientName
                            redirectUris
                        }
                    }
                    """,
                    clientDto.getClientId(),
                    clientDto.getClientName(),
                    clientDto.getRedirectUris().stream().findAny().get(),
                    clientDto.getClientSettingsMap().replaceAll("\\\"", "\\\\\"")
                ), "{}"
            );

        assertThat(response.isOk()).isTrue();
        assertThat(response.get("$.errors[0].extensions.code", Integer.class)).isEqualTo(294171366);
    }


    @Test
    public void testRegisteringNewClientWithExistingResourceServerId() throws Exception {
        var resourceServerSample = new ResourceServer();
        resourceServerSample.setResourceServerId("resource-server-one");
        resourceServerDataProvider.save(resourceServerSample);

        var registeringClientSample = new NewRegisteringClient();
        registeringClientSample.setClientSettingsMap(
            new JSONObject(
                Map.of(
                    AuthServerConstants.ClientTokenSettings.ALLOWED_RESOURCES,
                    "resource-server-one"
                )
            ).toString()
        );

        var clientDto = clientProvider.provide(registeringClientSample);

        var response = testTemplate
            .postMultipart(
                String.format(
                    """
                    mutation {
                        registerClient(client: {
                            clientId: "%s",
                            clientName: "%s",
                            redirectUris: [
                                "%s"
                            ],
                            clientSettingsMap: "%s"
                        }) {
                            id
                            clientId
                            clientSecret
                            clientName
                            redirectUris
                        }
                    }
                    """,
                    clientDto.getClientId(),
                    clientDto.getClientName(),
                    clientDto.getRedirectUris().stream().findAny().get(),
                    clientDto.getClientSettingsMap().replaceAll("\\\"", "\\\\\"")
                ), "{}"
            );

        assertThat(response.isOk()).isTrue();

        assertThat(response.get("$.data.registerClient.id")).isNotBlank();
        assertThat(response.get("$.data.registerClient.clientId")).isNotBlank();
        assertThat(response.get("$.data.registerClient.clientSecret")).isNotBlank();
    }


}


