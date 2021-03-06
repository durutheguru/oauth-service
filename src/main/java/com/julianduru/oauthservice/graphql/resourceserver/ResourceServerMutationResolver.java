package com.julianduru.oauthservice.graphql.resourceserver;

import com.julianduru.oauthservice.dto.CreateResourceServerRequest;
import com.julianduru.oauthservice.dto.ResourceServer;
import com.julianduru.oauthservice.module.resourceserver.ResourceServerService;
import com.julianduru.util.ValidatorUtil;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * created by julian on 24/04/2022
 */
@Component
@RequiredArgsConstructor
public class ResourceServerMutationResolver implements GraphQLMutationResolver {


    private final ResourceServerService resourceServerService;


    public ResourceServer registerResourceServer(CreateResourceServerRequest request) {
        ValidatorUtil.validate(request);

        var resourceServer = resourceServerService.createResourceServer(request);
        return ResourceServer.from(resourceServer);
    }


}

