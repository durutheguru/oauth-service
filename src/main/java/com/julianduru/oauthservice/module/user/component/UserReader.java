package com.julianduru.oauthservice.module.user.component;

import com.julianduru.oauthservice.entity.UserData;
import com.julianduru.oauthservice.exception.UnprocessableInputException;
import com.julianduru.oauthservice.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * created by julian on 30/10/2022
 */
@Component
@RequiredArgsConstructor
public class UserReader {


    private final UserDataRepository userDataRepository;


    @Value("${code.config.search.page-size:1000}")
    private Integer userSearchPageSize;


    public UserData fetchUser(String username) {
        return userDataRepository.findByUsername(username)
            .orElseThrow(
                () -> new UnprocessableInputException(String.format("Username %s does not exist", username))
            );
    }


    public Page<UserData> searchUsers(String query) {
        return userDataRepository.findByQuery(query, PageRequest.of(0, userSearchPageSize));
    }


}
