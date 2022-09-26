package com.julianduru.oauthservice.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created by julian on 18/09/2022
 */
@Controller
@RequestMapping(WebController.PATH)
public class WebController {

    public static final String PATH = "/";


    @GetMapping
    public String index() {
        return "index";
    }


}


