package com.julianduru.oauthservice.controller.web;

import com.julianduru.oauthservice.AuthServerConstants;
import com.julianduru.oauthservice.BaseControllerTest;
import com.julianduru.oauthservice.data.NewRegisteringClientProvider;
import com.julianduru.oauthservice.data.RegisteredClientProvider;
import com.julianduru.oauthservice.data.ResourceServerDataProvider;
import com.julianduru.oauthservice.dto.ClientDto;
import com.julianduru.oauthservice.dto.NewRegisteringClient;
import com.julianduru.oauthservice.entity.ResourceServer;
import com.julianduru.oauthservice.module.client.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.util.UriComponentsBuilder;
import org.testcontainers.shaded.okhttp3.HttpUrl;

import java.util.Base64;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * created by julian on 21/04/2022
 */
@Slf4j
public class AuthorizationCodeFlowTest extends BaseControllerTest {


    @Autowired
    private RegisteredClientProvider clientProvider;


    @Autowired
    private RegisteredClientRepository clientRepository;


    @Autowired
    private NewRegisteringClientProvider clientDtoProvider;


    @Autowired
    private ResourceServerDataProvider resourceServerDataProvider;


    @Autowired
    private ClientService clientService;


    @Test
    public void testClientFetchingAuthorizationCode() throws Exception {
        var clientDto = clientDtoProvider.provide();
        var registeredClient = clientService.registerClient(clientDto);

        mockMvc.perform(
            post(AuthServerConstants.DEFAULT_AUTHORIZATION_ENDPOINT_URI)
                .param("client_id", registeredClient.getClientId())
                .param("redirect_uri", clientDto.getRedirectUris().stream().findAny().get())
                .param("scope", "openid")
                .param("response_type", "code")
                .param("response_mode", "query")
                .param("nonce", faker.code().isbn10(false))
                .with(
                    httpBasic(registeredClient.getClientId(), registeredClient.getClientSecret())
                )
        ).andDo(print())
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("http://localhost/login"));
    }


    @Test
    public void testFullAuthorizationCodeFlow() throws Exception {
        var resourceId1 = "resource-server-" + faker.code().isbn10(false);
        var resourceId2 = "resource-server-" + faker.code().isbn10(false);

        var resourceServerSample = new ResourceServer();
        resourceServerSample.setResourceServerId(resourceId1);
        resourceServerDataProvider.save(resourceServerSample);

        resourceServerSample = new ResourceServer();
        resourceServerSample.setResourceServerId(resourceId2);
        resourceServerDataProvider.save(resourceServerSample);

        var registeringClientSample = new NewRegisteringClient();
        registeringClientSample.setClientSettingsMap(
            new JSONObject(
                Map.of(
                    AuthServerConstants.ClientTokenSettings.ALLOWED_RESOURCES,
                    String.format("%s, %s", resourceId1, resourceId2)
                )
            ).toString()
        );

        var clientDto = clientDtoProvider.provide(registeringClientSample);
        var client = clientService.registerClient(clientDto);

        testFetchingAccessToken(
            client,

            result -> {
                var responseString = result.getResponse().getContentAsString();
                var refreshToken = new JSONObject(responseString).getString("refresh_token");

                mockMvc.perform(
                    post(AuthServerConstants.DEFAULT_TOKEN_ENDPOINT_URI)
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString(
                            String.format("%s:%s", client.getClientId(), client.getClientSecret()).getBytes()
                        ))
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("grant_type", "refresh_token")
                        .param("refresh_token", refreshToken)
                ).andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.access_token", not(empty())))
                .andExpect(jsonPath("$.refresh_token", not(empty())))
                .andExpect(jsonPath("$.expires_in", notNullValue()))
                .andExpect(jsonPath("$.scope", not(empty())));
            }
        );
    }


    private void testFetchingAccessToken(ClientDto client, ResultMatcher resultMatcher) throws Exception {
        testClientRetrievingAuthorizationCode(
            client,

            result -> {
                var redirectedUrlString = result.getResponse().getRedirectedUrl();
                assertThat(redirectedUrlString).isNotBlank();

                var redirectedUrl = HttpUrl.parse(redirectedUrlString);
                assertThat(redirectedUrl).isNotNull();

                var code = redirectedUrl.queryParameter("code");
                assertThat(code).isNotBlank();
                log.info("Authorization Code:>>> " + code);

                mockMvc.perform(
                        post(AuthServerConstants.DEFAULT_TOKEN_ENDPOINT_URI)
                            .header("Authorization", "Basic " + Base64.getEncoder().encodeToString(
                                String.format("%s:%s", client.getClientId(), client.getClientSecret()).getBytes()
                            ))
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("grant_type", "authorization_code")
                            .param("code", code)
                            .param("client_id", client.getClientId())
                            .param("redirect_uri", stripQueriesFromRedirectUri(redirectedUrl))
                    )
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(jsonPath("$.access_token", not(empty())))
                    .andExpect(jsonPath("$.refresh_token", not(empty())))
                    .andExpect(jsonPath("$.expires_in", notNullValue()))
                    .andExpect(jsonPath("$.scope", not(empty())))
                    .andExpect(resultMatcher);
            }
        );
    }


    private void testClientRetrievingAuthorizationCode(ClientDto client, ResultMatcher matcher) throws Exception {
        mockMvc.perform(
                post(AuthServerConstants.DEFAULT_AUTHORIZATION_ENDPOINT_URI)
                    .with(SecurityMockMvcRequestPostProcessors.user("admin"))
                    .param("client_id", client.getClientId())
                    .param("redirect_uri", client.getRedirectUris().stream().findAny().get())
                    .param("scope", "openid")
                    .param("response_type", "code")
                    .param("response_mode", "query")
                    .param("nonce", faker.code().isbn10(false))
            ).andDo(print())
            .andExpect(status().is3xxRedirection())
            .andExpect(matcher);
    }


    private String stripQueriesFromRedirectUri(HttpUrl redirectUri) {
        var uri = redirectUri.uri();
        var strippedUri = UriComponentsBuilder.fromUri(uri).replaceQuery(null).build();
        var strippedUriString = strippedUri.toString();
        strippedUriString = strippedUriString.endsWith("/") ?
            strippedUriString.substring(0, strippedUriString.length() - 1) : strippedUriString;

        return strippedUriString;
    }


}




