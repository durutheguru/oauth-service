package com.julianduru.oauthservice.dto;

import com.julianduru.oauthservice.entity.UserData;
import com.julianduru.util.JSONUtil;
import com.julianduru.util.UtilConstants;
import lombok.Data;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * created by julian on 05/06/2022
 */
@Data
public class UserDataDto {


    @NotEmpty(message = "Username is required")
    @Size(max = 200, message = "Username should not exceed {max} characters")
    private String username;


    @NotEmpty(message = "Password is required")
    @Size(min = 7, message = "Password must exceed {min} characters")
    private String password;


    @NotEmpty(message = "User First Name is required")
    @Size(max = 100, message = "First Name should not exceed {max} characters")
    private String firstName;


    @NotEmpty(message = "User Last Name is required")
    @Size(max = 100, message = "Last Name should not exceed {max} characters")
    private String lastName;


    @NotEmpty(message = "User Email is required")
    @Pattern(regexp = UtilConstants.Patterns.EMAIL, message = "Email is invalid")
    private String email;


    private List<String> authorities;


    private String additionalInfo;


    private boolean locked;


    private boolean credentialsExpired;


    public String name() {
        return String.format("%s %s", getFirstName(), getLastName());
    }


    public UserData data() {
        var data = new UserData();

        data.setUsername(getUsername());
        data.setPassword(getPassword());
        data.setFirstName(getFirstName());
        data.setLastName(getLastName());
        data.setEmail(getEmail());
        data.setLocked(isLocked());
        data.setAuthorities(getAuthorities());
        data.setCredentialsExpired(isCredentialsExpired());

        if (StringUtils.hasText(getAdditionalInfo())) {
            data.setAdditionalInfo(JSONUtil.readJSONMap(getAdditionalInfo()));
        }

        return data;
    }


}
