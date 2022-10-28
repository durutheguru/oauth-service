package com.julianduru.oauthservice.module.login.service;

import org.springframework.ui.Model;

/**
 * created by julian on 07/10/2022
 */
public interface LoginService {


    String showUserLogin(Model model, String clientId, String resourceServerId);


}
