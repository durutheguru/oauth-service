package com.julianduru.oauthservice.graphql.resourceserver;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.julianduru.oauthservice.BaseServiceIntegrationTest;
import com.julianduru.oauthservice.data.ResourceServerDataProvider;
import com.julianduru.oauthservice.graphql.GraphQLTemplateJwtTokenBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * created by julian on 25/04/2022
 */
public class ResourceServerQueryResolverTest extends BaseServiceIntegrationTest {


    @Autowired
    private GraphQLTestTemplate testTemplate;


    @Autowired
    private GraphQLTemplateJwtTokenBuilder jwtTokenBuilder;


    @Autowired
    private ResourceServerDataProvider resourceServerDataProvider;



    @BeforeAll
    public void beforeClass() {
        jwtTokenBuilder.setupTemplate(testTemplate);
    }


    @Test
    public void testFetchingResourceServers() throws Exception {
        resourceServerDataProvider.save(50);

        var response = testTemplate.postMultipart(
    """
            {
                fetchResourceServers(page: 0) {
                    page
                    totalElements
                    totalPages
                    resourceServers {
                        resourceServerId
                        status
                    }
                }
            }
            """, "{}"
        );

        assertThat(response.isOk()).isTrue();

    }


}
