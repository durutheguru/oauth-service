package com.julianduru.oauthservice.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * created by julian on 24/04/2022
 */
@Data
public class CreateResourceServerRequest {


    @NotEmpty(message = "Resource Server ID must be provided")
    @Size(max = 240, message = "Server ID should not exceed {max}")
    private String serverId;


    @NotEmpty(message = "Allowed Scopes cannot be empty")
    private Set<String> allowedScopes;


    private Set<String> userAuthoritiesOnSignUp;


}

