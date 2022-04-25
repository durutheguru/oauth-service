package com.julianduru.oauthservice.module.resourceserver;

import com.julianduru.oauthservice.dto.CreateResourceServerRequest;
import com.julianduru.oauthservice.entity.ResourceServer;
import org.springframework.data.domain.Page;

/**
 * created by julian on 24/04/2022
 */
public interface ResourceServerService {


    ResourceServer createResourceServer(CreateResourceServerRequest request);


    Page<ResourceServer> fetchResourceServers(int page, int size);


}
