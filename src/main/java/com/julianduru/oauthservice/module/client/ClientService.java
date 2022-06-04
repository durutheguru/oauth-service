package com.julianduru.oauthservice.module.client;

import com.julianduru.oauthservice.dto.ClientDto;
import com.julianduru.oauthservice.dto.NewRegisteringClient;
import com.julianduru.oauthservice.dto.NewRegisteringClientDto;
import com.julianduru.oauthservice.dto.RegisteredClientDto;

import java.util.List;

/**
 * created by julian on 13/04/2022
 */
public interface ClientService {


    ClientDto registerClient(NewRegisteringClient client);

    ClientDto registerClient(NewRegisteringClientDto client);

    List<RegisteredClientDto> fetchClients();

}

