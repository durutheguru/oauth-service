package com.julianduru.oauthservice.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

/**
 * created by julian on 31/05/2022
 */
@Data
@Validated
@Component
@ConfigurationProperties(prefix = "oauth-service.config.bootstrap")
public class BootstrapProperties {


    @NotEmpty(message = "Bootstrap Client ID is required")
    private String clientId;


    @NotEmpty(message = "Bootstrap Client Secret is required")
    private String clientSecret;


    @NotEmpty(message = "Bootstrap Admin Username is required")
    private String adminUsername;


    @NotEmpty(message = "Bootstrap Admin Password is required")
    private String adminPassword;


    @NotEmpty(message = "Bootstrap Admin First Name is required")
    private String adminFirstName;


    @NotEmpty(message = "Bootstrap Admin Last Name is required")
    private String adminLastName;


    @NotEmpty(message = "Bootstrap Admin Email is required")
    private String adminEmail;


}

