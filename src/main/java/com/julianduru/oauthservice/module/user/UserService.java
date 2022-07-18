package com.julianduru.oauthservice.module.user;

import com.julianduru.oauthservice.dto.UserDataDto;
import com.julianduru.oauthservice.entity.UserData;
import com.julianduru.oauthservice.exception.RuntimeServiceException;

/**
 * created by julian on 05/06/2022
 */
public interface UserService {


    UserData saveUser(UserDataDto userDto) throws RuntimeServiceException;


}
