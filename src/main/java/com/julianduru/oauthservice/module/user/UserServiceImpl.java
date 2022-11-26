package com.julianduru.oauthservice.module.user;

import com.julianduru.data.messaging.dto.UserDataUpdate;
import com.julianduru.oauthservice.dto.UserDataDto;
import com.julianduru.oauthservice.entity.UserData;
import com.julianduru.oauthservice.exception.RuntimeServiceException;
import com.julianduru.oauthservice.module.user.component.UserMutator;
import com.julianduru.oauthservice.module.user.component.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * created by julian on 05/06/2022
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserMutator userMutator;


    private final UserReader userReader;



    @Override
    public UserData saveUser(UserDataDto userDto) throws RuntimeServiceException {
        return userMutator.saveUser(userDto);
    }


    @Override
    public UserData updateUser(UserDataUpdate userDataUpdate) {
        return userMutator.updateUser(userDataUpdate);
    }


    @Override
    public UserData fetchUser(String username) {
        return userReader.fetchUser(username);
    }


}


