package com.julianduru.oauthservice.modules.client;

import com.julianduru.oauthservice.dto.RegisteredClientDto;

/**
 * created by julian on 13/04/2022
 */
public interface ClientService {


    RegisteredClientDto registerClient(RegisteredClientDto clientDto);


}
