package com.julianduru.oauthservice.graphql.user;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.julianduru.oauthservice.BaseServiceIntegrationTest;
import com.julianduru.oauthservice.data.NewRegisteringClientProvider;
import com.julianduru.oauthservice.data.RegisteredClientProvider;
import com.julianduru.oauthservice.data.UserDataDtoProvider;
import com.julianduru.oauthservice.module.client.ClientService;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.JwtGenerator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * created by julian on 05/06/2022
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserMutationResolverTest extends BaseServiceIntegrationTest {


    @Autowired
    private GraphQLTestTemplate testTemplate;


    @Autowired
    private JwtGenerator jwtGenerator;


    @Autowired
    private RegisteredClientProvider registeredClientProvider;


    @Autowired
    private RegisteredClientRepository registeredClientRepository;


    @Autowired
    private ClientService clientService;


    @Autowired
    private NewRegisteringClientProvider clientProvider;


    @Autowired
    private UserDataDtoProvider userDataDtoProvider;


    private TokenService authTokenServices;



    @BeforeClass
    public void beforeClass() {
        var client = clientProvider.provide();
        var clientDto = clientService.registerClient(client);

        var registeredClient = registeredClientRepository.findByClientId(clientDto.getClientId());
        if (registeredClient == null) {
            throw new IllegalStateException("Registered Client was not found.");
        }

        var jwt = jwtGenerator.generate(
            DefaultOAuth2TokenContext.builder()
                .principal(
                    new OAuth2ClientAuthenticationToken(
                        registeredClient, ClientAuthenticationMethod.CLIENT_SECRET_BASIC,
                        clientDto.getClientSecret()
                    )
                )
                .registeredClient(registeredClient)
                .tokenType(OAuth2TokenType.ACCESS_TOKEN)
                .build()
        );
//        JWT

        if (jwt == null) {
            throw new IllegalStateException("JWT was empty");
        }
        testTemplate.withBearerAuth(jwt.getTokenValue());
    }


    @Test
    public void testRegisteringNewUser() throws Exception {
        var userDto = userDataDtoProvider.provide();

        var response = testTemplate
            .postMultipart(
                String.format(
                    """
                        mutation {
                            saveUser(
                                userDto: {
                                    username: "%s",
                                    password: "%s",
                                    firstName: "%s",
                                    lastName: "%s",
                                    email: "%s"
                                }
                            ) {
                                username
                                password
                                firstName
                                lastName
                                email
                            }
                        }
                        """,
                    userDto.getUsername(),
                    userDto.getPassword(),
                    userDto.getFirstName(),
                    userDto.getLastName(),
                    userDto.getEmail()
                ), "{}"
            );

        assertThat(response.isOk()).isTrue();
    }


}
