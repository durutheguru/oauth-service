package com.julianduru.oauthservice.module.client;

import com.julianduru.oauthservice.dto.ClientDto;
import com.julianduru.oauthservice.dto.NewRegisteringClient;
import com.julianduru.oauthservice.dto.NewRegisteringClientDto;
import com.julianduru.oauthservice.dto.RegisteredClientDto;
import com.julianduru.oauthservice.exception.UnprocessableInputException;
import com.julianduru.oauthservice.module.client.component.NewRegisteringClientSettingsValidator;
import com.julianduru.oauthservice.module.client.component.RegisteredClientFetcher;
import com.julianduru.oauthservice.module.config.RegisteringClientConfigurer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by julian on 13/04/2022
 */
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {


    private final RegisteredClientRepository clientRepository;


    private final RegisteringClientConfigurer clientConfigurer;


    private final PasswordEncoder passwordEncoder;


    private final NewRegisteringClientSettingsValidator clientSettingsValidator;


    private final RegisteredClientFetcher registeredClientFetcher;



    @Override
    public ClientDto registerClient(NewRegisteringClient client) {
        return registerClient(NewRegisteringClientDto.from(client));
    }


    @Override
    public ClientDto registerClient(NewRegisteringClientDto clientDto) {
        validateClientIDNotExists(clientDto.getClientId());

        var registeredClientDto = clientConfigurer.init(
            clientDto.toRegisteredClientDto()
        );

        var registeredClient = registeredClientDto.mapToNewEntity(passwordEncoder);
        clientRepository.save(clientSettingsValidator.valid(registeredClient));

        // TODO: email dispatch of credentials to admin email ...

        return ClientDto.fromRegisteredClient(registeredClientDto.withId(registeredClient.getId()));
    }


    @Override
    public List<RegisteredClientDto> fetchClients() {
        return registeredClientFetcher.fetchClients();
    }


    private void validateClientIDNotExists(String clientId) {
        var existingClient = clientRepository.findByClientId(clientId);
        if (existingClient != null) {
            throw new UnprocessableInputException(
                String.format("Client ID %s already exists", clientId)
            );
        }
    }


}


