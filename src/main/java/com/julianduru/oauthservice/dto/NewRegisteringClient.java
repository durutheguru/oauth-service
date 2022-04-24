package com.julianduru.oauthservice.dto;

import com.julianduru.util.validation.URLCollection;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * created by julian on 22/04/2022
 */
@Data
public class NewRegisteringClient {


    @NotEmpty(message = "Client Name is required. Cannot be empty.")
    private String clientName;


    @URLCollection(message = "Redirect URIs must be valid URL pattern")
    @NotEmpty(message = "Client Redirect URI must be provided")
    private Set<String> redirectUris;


    private String clientSettingsMap;


    private String tokenSettingsMap;



}

