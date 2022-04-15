package com.julianduru.oauthservice.controller;

import com.julianduru.oauthservice.AuthServerConstants;
import com.julianduru.oauthservice.dto.ClientDto;
import com.julianduru.oauthservice.dto.NewRegisteringClientDto;
import com.julianduru.oauthservice.dto.RegisteredClientDto;
import com.julianduru.oauthservice.exception.RuntimeServiceException;
import com.julianduru.oauthservice.modules.client.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * created by julian on 13/04/2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ClientRegistrationController.PATH)
public class ClientRegistrationController {

    public static final String PATH = AuthServerConstants.API_VERSION + "/client";


    private final ClientService clientService;


    @PostMapping
    public ClientDto registerClient(
        @Valid @RequestBody NewRegisteringClientDto clientDto
    ) {
        try {
            return clientService.registerClient(clientDto);
        }
        catch (Throwable t) {
            throw new RuntimeServiceException(t);
        }
    }


}

