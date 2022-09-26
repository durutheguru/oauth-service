package com.julianduru.oauthservice.module.client;

import com.github.javafaker.Faker;
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
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
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
    public List<RegisteredClientDto> fetchClients(int page, int size) {
        return registeredClientFetcher.fetchClients();
    }


    @Override
    public String buildLoginUrl(String clientId) {
        var client = fetchClient(clientId);
        return buildLoginUrl(client);
    }


    @Override
    public String buildLoginUrl(RegisteredClient client) {
        return String.format(
            """
            /oauth2/authorize?client_id=%s&redirect_uri=%s&scope=%s&response_type=code&response_mode=form_post&state=%s&nonce=%s
            """,
            client.getClientId(),
            client.getRedirectUris().stream().findFirst().orElseThrow(
                () -> new IllegalStateException("No Redirect URI configured for client")
            ),
            client.getScopes().stream().findFirst().orElseThrow(
                () -> new IllegalStateException("No Scopes configured for client")
            ),
            Faker.instance().code().isbn10(false),
            Faker.instance().code().isbn10(false)
        ).trim();
    }


    @Override
    public RegisteredClient fetchClient(String clientId) {
        var client = clientRepository.findByClientId(clientId);
        if (client == null) {
            throw new IllegalArgumentException(String.format("Client ID %s not found", clientId));
        }

        return client;
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


