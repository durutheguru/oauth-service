package com.julianduru.oauthservice.graphql.resourceserver;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.julianduru.oauthservice.BaseServiceIntegrationTest;
import com.julianduru.oauthservice.data.CreateResourceServerDataProvider;
import com.julianduru.oauthservice.data.ResourceServerDataProvider;
import com.julianduru.oauthservice.entity.ServerStatus;
import com.julianduru.oauthservice.graphql.GraphQLTemplateJwtTokenBuilder;
import com.julianduru.oauthservice.repository.ResourceServerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.OidcScopes;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * created by julian on 24/04/2022
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ResourceServerMutationResolverTest extends BaseServiceIntegrationTest {


    @Autowired
    private GraphQLTestTemplate testTemplate;


    @Autowired
    private GraphQLTemplateJwtTokenBuilder jwtTokenBuilder;


    @Autowired
    private CreateResourceServerDataProvider createResourceServerDataProvider;


    @Autowired
    private ResourceServerDataProvider resourceServerDataProvider;



    @BeforeAll
    public void beforeClass() {
        jwtTokenBuilder.setupTemplate(testTemplate);
    }


    @Test
    public void testRegisteringNewResourceServer() throws Exception {
        var serverRequest = createResourceServerDataProvider.provide();

        var response = testTemplate.postMultipart(
            String.format(
                """
                mutation {
                    registerResourceServer(server: {
                        serverId: "%s",
                        allowedScopes: %s
                    }) {
                        id
                        resourceServerId
                        allowedScopes
                        status
                    }
                }
                """,
                serverRequest.getServerId(),
                serverRequest.getAllowedScopes().toString()
                    .replace("[", "[\"")
                    .replace("]", "\"]")
                    .replace(",","\",\"")
                    .replace(" ", "")
            ), "{}"
        );

        assertThat(response.isOk()).isTrue();

        var resourceServer = (
            (ResourceServerRepository) resourceServerDataProvider.getRepository()
        ).findByResourceServerId(serverRequest.getServerId());

        assertThat(resourceServer).isNotEmpty();
        assertThat(
            resourceServer.get().getAllowedScopes()
            .stream().map(String::trim)
        )
        .containsExactlyInAnyOrder(
            OidcScopes.OPENID, OidcScopes.EMAIL
        );
        assertThat(resourceServer.get().getStatus()).isEqualTo(ServerStatus.ACTIVE);
    }


}
