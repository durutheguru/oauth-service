package com.julianduru.oauthservice.graphql.user;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.julianduru.oauthservice.BaseServiceIntegrationTest;
import com.julianduru.oauthservice.data.UserDataDtoProvider;
import com.julianduru.oauthservice.graphql.GraphQLTemplateJwtTokenBuilder;
import com.julianduru.oauthservice.repository.UserDataRepository;
import com.julianduru.util.StringUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * created by julian on 05/06/2022
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserMutationResolverTest extends BaseServiceIntegrationTest {


    @Autowired
    private GraphQLTestTemplate testTemplate;


    @Autowired
    private GraphQLTemplateJwtTokenBuilder jwtTokenBuilder;


    @Autowired
    private UserDataDtoProvider userDataDtoProvider;


    @Autowired
    private UserDataRepository userDataRepository;



    @BeforeAll
    public void beforeClass() {
        jwtTokenBuilder.setupTemplate(testTemplate);
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


    /**
     * test registering user where username already exists
     * test registering user where email already exists
     */


}


