package com.julianduru.oauthservicelib.modules.user.dto;


public record UserDataUpdate(
    String username,
    String firstName,
    String lastName,
    String email
) {
}
