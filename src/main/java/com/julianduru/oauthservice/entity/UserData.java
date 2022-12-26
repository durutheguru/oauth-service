package com.julianduru.oauthservice.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.julianduru.oauthservice.AuthServerConstants;
import com.julianduru.oauthservice.dto.UserDataDto;
import com.julianduru.oauthservice.util.ListConverter;
import com.julianduru.oauthservice.util.MapConverter;
import com.julianduru.security.entity.BaseEntity;
import com.julianduru.util.JSONUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

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
        var data = new UserDataDto();

        try {

            data.setUsername(getUsername());
            data.setPassword(getPassword());
            data.setFirstName(getFirstName());
            data.setLastName(getLastName());
            data.setEmail(getEmail());
            data.setLocked(isLocked());
            data.setCredentialsExpired(isCredentialsExpired());
            data.setAuthorities(getAuthorities());

            if (getAdditionalInfo() != null) {
                data.setAdditionalInfo(JSONUtil.asJsonString(getAdditionalInfo()));
            }

            return data;
        }
        catch (JsonProcessingException e) {
            if (failSilently) {
                log.error(e.getMessage(), e);
                return data;
            }
            else {
                throw new RuntimeException(e);
            }
        }
    }


    public UserData encodePassword(PasswordEncoder encoder) {
        this.setPassword(encoder.encode(getPassword()));
        return this;
    }


}

