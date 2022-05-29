package com.julianduru.oauthservice.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * created by julian on 28/05/2022
 */
public interface IUserDetailsService extends UserDetailsService {


    UserDetails findByUsernameAndPassword(String username, String password);


}
