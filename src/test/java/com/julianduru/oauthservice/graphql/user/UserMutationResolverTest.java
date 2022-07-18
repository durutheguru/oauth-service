package com.julianduru.oauthservice.graphql.user;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.julianduru.oauthservice.BaseServiceIntegrationTest;
import com.julianduru.oauthservice.data.NewRegisteringClientProvider;
import com.julianduru.oauthservice.data.RegisteredClientProvider;
import com.julianduru.oauthservice.data.UserDataDtoProvider;
import com.julianduru.oauthservice.module.client.ClientService;
import com.julianduru.oauthservice.repository.UserDataRepository;
import com.julianduru.util.StringUtil;
import org.codehaus.plexus.interpolation.util.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.context.ProviderContext;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * created by julian on 05/06/2022
 */
//@ActiveProfiles({"h2"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserMutationResolverTest extends BaseServiceIntegrationTest {


    @Autowired
    private GraphQLTestTemplate testTemplate;


    @Autowired
    private OAuth2TokenGenerator<?> jwtGenerator;


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


    @Autowired
    private ProviderSettings providerSettings;


    @Autowired
    private UserDataRepository userDataRepository;




    @BeforeAll
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
                        registeredClient, ClientAuthenticationMethod.CLIENT_SECRET_JWT,
                        clientDto.getClientSecret()
                    )
                )
                .registeredClient(registeredClient)
                .tokenType(OAuth2TokenType.ACCESS_TOKEN)
                .providerContext(
                    new ProviderContext(
                        providerSettings,
                        () -> providerSettings.getIssuer()
                    )
                )
                .authorizedScopes(
                    new HashSet<>(List.of("OPENID"))
                )
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .build()
        );

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
                                    email: "%s",
                                    authorities: %s
                                }
                            ) {
                                username
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
                    userDto.getEmail(),
                    StringUtil.stringifyList(userDto.getAuthorities(), "\\\"")
                ), "{}"
            );

        assertThat(response.isOk()).isTrue();

        var userDetails = userDataRepository.findByUsername(userDto.getUsername());

        assertThat(userDetails).isPresent();
        assertThat(userDetails.get().getEmail()).isEqualTo(userDto.getEmail());
        assertThat(userDetails.get().getFirstName()).isEqualTo(userDto.getFirstName());
        assertThat(userDetails.get().getLastName()).isEqualTo(userDto.getLastName());
        assertThat(userDetails.get().getAuthorities()).containsAll(userDto.getAuthorities());
    }


}
