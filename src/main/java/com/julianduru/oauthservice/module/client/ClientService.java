package com.julianduru.oauthservice.module.client;

import com.julianduru.oauthservice.dto.ClientDto;
import com.julianduru.oauthservice.dto.NewRegisteringClientDto;

/**
 * created by julian on 13/04/2022
 */
public interface ClientService {


    ClientDto registerClient(NewRegisteringClientDto clientDto);


}
