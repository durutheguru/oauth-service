package com.julianduru.oauthservice.module.signup.dto;

import com.julianduru.oauthservice.dto.UserDataDto;
import com.julianduru.util.UtilConstants;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * created by julian on 24/09/2022
 */
@Data
public class SignUpUserDto {


    @NotEmpty(message = "Client ID is required")
    @Size(max = 100, message = "Client ID should not exceed {max} characters")
    private String clientId;


    @NotEmpty(message = "Resource Server ID is required")
    @Size(max = 200, message = "Resource Server ID should not exceed {max} characters")
    private String resourceServerId;


    @NotEmpty(message = "Username is required")
    @Size(max = 200, message = "Username should not exceed {max} characters")
    private String username;


    @NotEmpty(message = "Password is required")
    @Size(min = 7, message = "Password must exceed {min} characters")
    private String password;


    private String confirm;


    @NotEmpty(message = "User First Name is required")
    @Size(max = 100, message = "First Name should not exceed {max} characters")
    private String firstName;


    @NotEmpty(message = "User Last Name is required")
    @Size(max = 100, message = "Last Name should not exceed {max} characters")
    private String lastName;


    @NotEmpty(message = "User Email is required")
    @Pattern(regexp = UtilConstants.Patterns.EMAIL, message = "Email is invalid")
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


