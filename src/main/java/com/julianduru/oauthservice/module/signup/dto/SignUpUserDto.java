package com.julianduru.oauthservice.module.signup.dto;

import com.julianduru.oauthservice.dto.UserDataDto;
import lombok.Data;

import java.util.List;

/**
 * created by julian on 24/09/2022
 */
@Data
public class SignUpUserDto {


    private String clientId;


    private String resourceServerId;


    private String username;


    private String password;


    private String confirm;


    private String firstName;


    private String lastName;


    private String email;



    public UserDataDto toUserDataDto() {
        var dto = new UserDataDto();

        dto.setUsername(getUsername());
        dto.setPassword(getPassword());
        dto.setFirstName(getFirstName());
        dto.setLastName(getLastName());
        dto.setEmail(getEmail());

        return dto;
    }



}


