package com.julianduru.oauthservice.module.resourceserver;

import com.julianduru.oauthservice.dto.CreateResourceServerRequest;
import com.julianduru.oauthservice.entity.ResourceServer;
import com.julianduru.oauthservice.module.resourceserver.component.CreateResourceServerComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * created by julian on 24/04/2022
 */
@Service
@RequiredArgsConstructor
public class ResourceServerServiceImpl implements ResourceServerService {


    private final CreateResourceServerComponent createResourceServerComponent;


    @Override
    public ResourceServer createResourceServer(CreateResourceServerRequest request) {
        return createResourceServerComponent.newResourceServer(request);
    }


}

