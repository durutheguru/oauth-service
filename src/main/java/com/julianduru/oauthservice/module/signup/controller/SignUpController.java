package com.julianduru.oauthservice.module.signup.controller;

import com.julianduru.oauthservice.AuthServerConstants;
import com.julianduru.oauthservice.controller.web.AnonymousUserWebController;
import com.julianduru.oauthservice.module.signup.dto.SignUpUserDto;
import com.julianduru.oauthservice.module.signup.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * created by julian on 24/09/2022
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(SignUpController.PATH)
public class SignUpController extends AnonymousUserWebController {

    public static final String PATH = "/signup";


    private final SignUpService signUpService;


    @GetMapping
    public String signup(Model model, HttpServletRequest request) {
        augmentModelWithAssets(model);

        var clientId = request.getParameter("client_id");
        model.addAttribute("client_id", clientId);
        model.addAttribute("user", new SignUpUserDto());

        return "signup";
    }


    @PostMapping
    public String signup(@ModelAttribute SignUpUserDto userDto, Model model) {
        var signup = signUpService.signupUser(userDto);
        return "index"; // TODO: fix this
    }


}
