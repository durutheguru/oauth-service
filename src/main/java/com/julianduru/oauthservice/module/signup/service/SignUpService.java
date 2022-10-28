package com.julianduru.oauthservice.module.signup.service;

import com.julianduru.oauthservice.module.signup.dto.SignUpUserDto;
import org.springframework.ui.Model;

/**
 * created by julian on 24/09/2022
 */
public interface SignUpService {


    String signupUser(SignUpUserDto dto);


    String showUserSignUp(Model model, String clientId, String resourceServerId);


    String showUserSignUp(Model model, String clientId, String resourceServerId, String error);


}
