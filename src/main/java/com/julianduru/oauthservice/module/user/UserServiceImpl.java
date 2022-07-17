package com.julianduru.oauthservice.module.user;

import com.julianduru.oauthservice.dto.UserDataDto;
import com.julianduru.oauthservice.entity.UserData;
import com.julianduru.oauthservice.exception.RuntimeServiceException;
import com.julianduru.oauthservice.module.user.component.UserSaver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * created by julian on 05/06/2022
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserSaver userSaver;


    @Override
    public UserData saveUser(UserDataDto userDto) throws RuntimeServiceException {
        return userSaver.saveUser(userDto);
    }


}


