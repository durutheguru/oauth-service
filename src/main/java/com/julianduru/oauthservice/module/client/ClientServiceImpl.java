package com.julianduru.oauthservice.module.client;

import com.julianduru.oauthservice.dto.ClientDto;
import com.julianduru.oauthservice.dto.NewRegisteringClientDto;
import com.julianduru.oauthservice.module.config.RegisteringClientConfigurer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

/**
 * created by julian on 13/04/2022
 */
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {


    private final RegisteredClientRepository clientRepository;


    private final RegisteringClientConfigurer clientConfigurer;


    private final PasswordEncoder passwordEncoder;



    @Override
    public ClientDto registerClient(NewRegisteringClientDto newClientDto) {
        var clientDto = clientConfigurer.init(newClientDto.toRegisteredClientDto());
        var registeredClient = clientDto.mapToNewEntity(passwordEncoder);

        clientRepository.save(registeredClient);
        clientDto.setId(registeredClient.getId());

        // TODO: email dispatch of credentials to admin email ...

        return ClientDto.fromRegisteredClient(clientDto);
    }


}



