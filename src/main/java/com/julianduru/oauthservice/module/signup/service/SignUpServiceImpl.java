package com.julianduru.oauthservice.module.signup.service;

import com.julianduru.oauthservice.api.OperationStatus;
import com.julianduru.oauthservice.dto.UserDataDto;
import com.julianduru.oauthservice.entity.UserData;
import com.julianduru.oauthservice.exception.RuntimeServiceException;
import com.julianduru.oauthservice.module.signup.dto.SignUpUserDto;
import com.julianduru.oauthservice.module.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * created by julian on 24/09/2022
 */
@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {


    private final UserService userService;


    @Override
    public OperationStatus<UserData> signupUser(SignUpUserDto dto) {
        return OperationStatus.of(userService.saveUser(dto.toUserDataDto()));
    }


}

