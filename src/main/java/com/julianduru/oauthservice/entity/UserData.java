package com.julianduru.oauthservice.entity;

import com.julianduru.oauthservice.AuthServerConstants;
import com.julianduru.oauthservice.util.ListConverter;
import com.julianduru.oauthservice.util.MapConverter;
import com.julianduru.security.entity.BaseEntity;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * created by julian on 28/05/2022
 */
@Data
@Entity
public class UserData extends BaseEntity {


    @Column(nullable = false, unique = true, length = 200)
    private String username;


    @Column(nullable = false, length = 250)
    private String password;


    @Column(nullable = false, length = 200)
    private String name;


    @Column(nullable = false, unique = true, length = 200)
    @Pattern(regexp = AuthServerConstants.Patterns.EMAIL, message = "Email is invalid")
    private String email;


    @Column(columnDefinition = "TEXT")
    @Convert(converter = ListConverter.class)
    private List<String> authorities;


    @Convert(converter = MapConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String, String> additionalInfo;


    private boolean locked;


    private boolean credentialsExpired;


    public UsernamePasswordAuthenticationToken toToken() {
        var token = new UsernamePasswordAuthenticationToken(
            username, null,
            getAuthorities()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList())
        );
        token.setDetails(this);

        return token;
    }


}

