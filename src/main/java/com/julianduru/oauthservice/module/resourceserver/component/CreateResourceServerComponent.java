package com.julianduru.oauthservice.module.resourceserver.component;

import com.julianduru.oauthservice.dto.CreateResourceServerRequest;
import com.julianduru.oauthservice.entity.ResourceServer;
import com.julianduru.oauthservice.entity.ServerStatus;
import com.julianduru.oauthservice.exception.UnprocessableInputException;
import com.julianduru.oauthservice.repository.ResourceServerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * created by julian on 24/04/2022
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CreateResourceServerComponent {


    private final ResourceServerRepository serverRepository;


    public ResourceServer newResourceServer(CreateResourceServerRequest request) {
        validateRequest(request);
        return process(request);
    }


    private void validateRequest(CreateResourceServerRequest request) {
        if (serverRepository.existsByResourceServerId(request.getServerId())) {
            throw new UnprocessableInputException(
                String.format("Server ID %s already exists", request.getServerId())
            );
        }
    }


    private ResourceServer process(CreateResourceServerRequest request) {
        var resourceServer = new ResourceServer();

        resourceServer.setResourceServerId(request.getServerId());
        resourceServer.setAllowedScopes(request.getAllowedScopes());
        if (request.getUserAuthoritiesOnSignUp() != null && !request.getUserAuthoritiesOnSignUp().isEmpty()) {
            resourceServer.setUserAuthoritiesOnSignUp(request.getUserAuthoritiesOnSignUp());
        }
        resourceServer.setStatus(ServerStatus.ACTIVE);

        return serverRepository.save(resourceServer);
    }


}


