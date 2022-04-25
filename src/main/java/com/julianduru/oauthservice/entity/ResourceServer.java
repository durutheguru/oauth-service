package com.julianduru.oauthservice.entity;


import com.julianduru.security.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * created by julian on 24/04/2022
 */
@Data
@Entity
public class ResourceServer extends BaseEntity {


    @Column(nullable = false, unique = true, length = 250)
    private String resourceServerId;


    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> allowedScopes;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ServerStatus status;


}


