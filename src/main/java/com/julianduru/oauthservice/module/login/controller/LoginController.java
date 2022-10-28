package com.julianduru.oauthservice.module.login.controller;

import com.julianduru.oauthservice.controller.web.AnonymousUserWebController;
import com.julianduru.oauthservice.module.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * created by julian on 26/09/2022
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(LoginController.PATH)
public class LoginController extends AnonymousUserWebController {


    public static final String PATH = "/login";


    private final LoginService loginService;


    @GetMapping
    public String login(Model model, HttpServletRequest request) {
        augmentModelWithAssets(model);
        return loginService.showUserLogin(
            model, request.getParameter("cid"), request.getParameter("rid")
        );
    }


}


