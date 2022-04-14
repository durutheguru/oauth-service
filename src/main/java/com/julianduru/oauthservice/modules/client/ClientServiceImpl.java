package com.julianduru.oauthservice.modules.client;

import com.julianduru.oauthservice.dto.RegisteredClientDto;
import com.julianduru.oauthservice.modules.config.RegisteringClientConfigurer;
import com.julianduru.util.MapperUtil;
import lombok.RequiredArgsConstructor;
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



    @Override
    public RegisteredClientDto registerClient(RegisteredClientDto clientDto) {
        clientDto = clientConfigurer.init(clientDto);

        var registeredClient = MapperUtil.map(clientDto, RegisteredClient.class);
        clientRepository.save(registeredClient);

        return MapperUtil.map(registeredClient, RegisteredClientDto.class);
    }


}


