package com.julianduru.oauthservice.graphql.resourceserver;

import com.julianduru.oauthservice.dto.PagedResourceServerList;
import com.julianduru.oauthservice.module.resourceserver.ResourceServerService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * created by julian on 25/04/2022
 */
@Component
@RequiredArgsConstructor
public class ResourceServerQueryResolver implements GraphQLQueryResolver {


    private final ResourceServerService resourceServerService;


    public PagedResourceServerList fetchResourceServers(
        int page, int size
    ) {
        var servers = resourceServerService.fetchResourceServers(page, size);
        return PagedResourceServerList.from(servers);
    }


}
