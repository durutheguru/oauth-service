package com.julianduru.oauthservice.controller.api;

import com.julianduru.oauthservice.dto.ClientDto;
import com.julianduru.oauthservice.dto.NewRegisteringClient;
import com.julianduru.oauthservice.module.client.ClientService;
import com.julianduru.util.ValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by julian on 31/05/2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ClientRegistrationController.PATH)
public class ClientRegistrationController {

    public static final String PATH = "/api/register_client";

    private final ClientService clientService;


    @PostMapping
    public ClientDto registerClient(NewRegisteringClient client) {
        ValidatorUtil.validate(client);
        return clientService.registerClient(client);
    }


}

