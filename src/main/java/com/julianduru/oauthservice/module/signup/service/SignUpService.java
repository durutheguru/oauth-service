package com.julianduru.oauthservice.module.signup.service;

import com.julianduru.oauthservice.api.OperationStatus;
import com.julianduru.oauthservice.dto.UserDataDto;
import com.julianduru.oauthservice.entity.UserData;
import com.julianduru.oauthservice.module.signup.dto.SignUpUserDto;

/**
 * created by julian on 24/09/2022
 */
public interface SignUpService {


    OperationStatus<UserData> signupUser(SignUpUserDto dto);


}
