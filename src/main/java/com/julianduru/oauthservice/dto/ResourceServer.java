package com.julianduru.oauthservice.dto;

import com.julianduru.oauthservice.entity.ServerStatus;
import lombok.Data;

import java.util.Set;

/**
 * created by julian on 24/04/2022
 */
@Data
public class ResourceServer {


    private Long id;


    private String resourceServerId;


    private Set<String> allowedScopes;


    private ServerStatus status;


    public static ResourceServer from(com.julianduru.oauthservice.entity.ResourceServer server) {
        var resourceServer = new ResourceServer();

        resourceServer.setId(server.getId());
        resourceServer.setResourceServerId(server.getResourceServerId());
        resourceServer.setAllowedScopes(server.getAllowedScopes());
        resourceServer.setStatus(server.getStatus());

        return resourceServer;
    }


}


