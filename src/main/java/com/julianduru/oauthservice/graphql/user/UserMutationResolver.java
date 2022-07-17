package com.julianduru.oauthservice.graphql.user;

import com.julianduru.oauthservice.dto.UserDataDto;
import com.julianduru.oauthservice.module.user.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * created by julian on 05/06/2022
 */
@Component
@RequiredArgsConstructor
public class UserMutationResolver implements GraphQLMutationResolver {


    private final UserService userService;


    public UserDataDto saveUser(UserDataDto userDto) throws Exception {
        return userService.saveUser(userDto).dto();
    }


}
