package com.julianduru.oauthservice.controller.api;

import com.julianduru.oauthservice.AuthServerConstants;
import com.julianduru.oauthservice.dto.ClientDto;
import com.julianduru.oauthservice.dto.NewRegisteringClient;
import com.julianduru.oauthservice.dto.NewRegisteringClientDto;
import com.julianduru.oauthservice.module.client.ClientService;
import com.julianduru.util.ValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * created by julian on 31/05/2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ClientRegistrationController.PATH)
public class ClientRegistrationController {

    public static final String PATH = AuthServerConstants.API_BASE + "/register_client";

    private final ClientService clientService;


    @PostMapping
    public ClientDto registerClient(@Valid @RequestBody NewRegisteringClientDto clientDto) {
        return clientService.registerClient(clientDto);
    }


}


