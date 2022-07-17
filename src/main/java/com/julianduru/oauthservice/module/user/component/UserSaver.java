package com.julianduru.oauthservice.module.user.component;

import com.julianduru.oauthservice.dto.UserDataDto;
import com.julianduru.oauthservice.entity.UserData;
import com.julianduru.oauthservice.exception.UnprocessableInputException;
import com.julianduru.oauthservice.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * created by julian on 05/06/2022
 */
@Component
@RequiredArgsConstructor
public class UserSaver {


    private final PasswordEncoder passwordEncoder;


    private final UserDataRepository userDataRepository;


    public UserData saveUser(UserDataDto userDataDto) throws UnprocessableInputException {
        var data = verifyUserDataInput(userDataDto.data()).encodePassword(passwordEncoder);
        return userDataRepository.save(data);
    }


    private UserData verifyUserDataInput(UserData data) {
        if (userDataRepository.existsByUsername(data.getUsername())) {
            throw new UnprocessableInputException(
                String.format("Username %s already exists", data.getUsername())
            );
        }

        if (userDataRepository.existsByEmail(data.getEmail())) {
            throw new UnprocessableInputException(
                String.format("Email %s already exists", data.getEmail())
            );
        }

        return data;
    }


}

