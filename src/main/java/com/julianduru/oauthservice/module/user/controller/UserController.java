package com.julianduru.oauthservice.module.user.controller;

import com.julianduru.oauthservice.AuthServerConstants;
import com.julianduru.oauthservice.dto.UserDataDto;
import com.julianduru.oauthservice.entity.UserData;
import com.julianduru.oauthservice.module.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by julian on 25/11/2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(UserController.PATH)
public class UserController {


    public static final String PATH = AuthServerConstants.API_BASE + "/user";


    private final UserService userService;


    @GetMapping
    public UserDataDto fetchUserDetails(@RequestParam("username") String username) {
        return userService.fetchUser(username).dto();
    }


    @GetMapping("/search")
    public Page<UserDataDto> searchUsers(@RequestParam("query") String query) {
        return userService.searchUsers(query).map(UserData::dto);
    }


}

