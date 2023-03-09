package com.julianduru.oauthservice.entity;

import com.julianduru.oauthservice.AuthServerConstants;
import com.julianduru.oauthservice.dto.UserDataDto;
import com.julianduru.oauthservice.util.ListConverter;
import com.julianduru.oauthservice.util.MapConverter;
import com.julianduru.security.entity.BaseEntity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

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
@Slf4j
@Data
@Entity
public class UserData extends BaseEntity {


    @Column(nullable = false, unique = true, length = 200)
    private String username;


    @Column(nullable = false, length = 250)
    private String password;


    @Column(nullable = false, length = 100)
    private String firstName;


    @Column(nullable = false, length = 100)
    private String lastName;


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


    public UserDataDto dto() {
        return dto(true);
    }


    public UserDataDto dto(boolean failSilently) {
        var dto = new UserDataDto();

        dto.setUsername(getUsername());
        dto.setPassword(getPassword());
        dto.setFirstName(getFirstName());
        dto.setLastName(getLastName());
        dto.setEmail(getEmail());
        dto.setLocked(isLocked());
        dto.setCredentialsExpired(isCredentialsExpired());
        dto.setAuthorities(getAuthorities());

        if (getAdditionalInfo() != null) {
            dto.setAdditionalInfo(getAdditionalInfo());
        }

        return dto;
    }


    public UserData encodePassword(PasswordEncoder encoder) {
        this.setPassword(encoder.encode(getPassword()));
        return this;
    }


}

