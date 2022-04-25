package com.julianduru.oauthservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.HashSet;
import java.util.Set;

/**
 * created by julian on 25/04/2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagedResourceServerList {


    private Integer page;


    private Long totalElements;


    private Integer totalPages;


    private Set<ResourceServer> resourceServers;


    public static PagedResourceServerList from(Page<com.julianduru.oauthservice.entity.ResourceServer> servers) {
        return PagedResourceServerList.builder()
            .page(servers.getNumber())
            .totalPages(servers.getTotalPages())
            .totalElements(servers.getTotalElements())
            .resourceServers(
                new HashSet<>(
                    servers.getContent().stream().map(
                        ResourceServer::from
                    ).toList()
                )
            ).build();
    }


}
