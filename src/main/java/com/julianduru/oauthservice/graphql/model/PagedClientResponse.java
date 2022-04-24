package com.julianduru.oauthservice.graphql.model;

import com.julianduru.oauthservice.dto.ClientDto;
import lombok.Data;

import java.util.List;

/**
 * created by julian on 24/04/2022
 */
@Data
public class PagedClientResponse {

    private int page;

    private int totalElements;

    private int totalPages;

    private List<ClientDto> clients;


    public static PagedClientResponse empty() {
        return new PagedClientResponse();
    }

}
