package com.julianduru.oauthservice.module.resourceserver;

import com.julianduru.oauthservice.dto.CreateResourceServerRequest;
import com.julianduru.oauthservice.entity.ResourceServer;
import com.julianduru.oauthservice.module.resourceserver.component.CreateResourceServerComponent;
import com.julianduru.oauthservice.repository.ResourceServerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * created by julian on 24/04/2022
 */
@Service
@RequiredArgsConstructor
public class ResourceServerServiceImpl implements ResourceServerService {


    private final ResourceServerRepository resourceServerRepository;


    private final CreateResourceServerComponent createResourceServerComponent;


    @Override
    @Transactional
    public ResourceServer createResourceServer(CreateResourceServerRequest request) {
        return createResourceServerComponent.newResourceServer(request);
    }


    @Override
    public Page<ResourceServer> fetchResourceServers(int page, int size) {
        return resourceServerRepository.findAll(
            PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "resourceServerId"))
        );
    }


}


