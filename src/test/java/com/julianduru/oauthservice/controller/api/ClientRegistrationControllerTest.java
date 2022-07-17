package com.julianduru.oauthservice.controller.api;

import com.julianduru.oauthservice.BaseControllerTest;
import com.julianduru.oauthservice.config.properties.BootstrapProperties;
import com.julianduru.oauthservice.data.NewRegisteringClientDtoProvider;
import com.julianduru.oauthservice.data.RegisteredClientProvider;
import com.julianduru.oauthservice.dto.ClientDto;
import com.julianduru.util.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * created by julian on 15/04/2022
 */
public class ClientRegistrationControllerTest extends BaseControllerTest {


    @Autowired
    private NewRegisteringClientDtoProvider registeringClientDtoProvider;


    @Autowired
    private RegisteredClientProvider registeredClientProvider;


    @Autowired
    private RegisteredClientRepository clientRepository;


    @Autowired
    private BootstrapProperties bootstrapProperties;



    @Test
    public void testClientRegistration() throws Exception {
        var clientDto = registeringClientDtoProvider.provide();

        mockMvc.perform(
            post(ClientRegistrationController.PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONUtil.asJsonString(clientDto))
                .with(
                    httpBasic(bootstrapProperties.getClientId(), bootstrapProperties.getClientSecret())
                )
        )
        .andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andDo(result -> {
            var resultString = result.getResponse().getContentAsString();
            var resultClientDto = JSONUtil.fromJsonString(resultString, ClientDto.class);

            var clientId = resultClientDto.getClientId();
            var persistedClient = clientRepository.findByClientId(clientId);

            assertThat(persistedClient).isNotNull();
            assertThat(persistedClient.getClientName()).isEqualTo(clientDto.getClientName());
        });
    }


}
