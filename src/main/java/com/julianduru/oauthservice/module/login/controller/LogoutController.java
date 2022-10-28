package com.julianduru.oauthservice.module.login.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created by julian on 12/10/2022
 */
@Slf4j
@Controller
@RequestMapping("/_logout")
public class LogoutController {


    @GetMapping
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
            log.info("Logout successful");
        }

        return "redirect:/login";
    }


}
