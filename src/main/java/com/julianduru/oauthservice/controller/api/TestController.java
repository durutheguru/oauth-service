package com.julianduru.oauthservice.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by julian on 02/06/2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/testing")
public class TestController {


    @GetMapping
    public String getData() {
        return "hello";
    }


}
