package com.julianduru.oauthservice.data;

import com.julianduru.oauthservice.entity.UserData;
import com.julianduru.oauthservice.repository.UserDataRepository;
import com.julianduru.util.test.JpaDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * created by julian on 05/06/2022
 */
@Component
@RequiredArgsConstructor
public class UserDataProvider implements JpaDataProvider<UserData> {

    private final UserDataRepository userDataRepository;


    @Override
    public JpaRepository<UserData, Long> getRepository() {
        return userDataRepository;
    }


    @Override
    public UserData provide() {
        var user = new UserData();

        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setUsername(faker.code().isbn10(false));
        user.setPassword(faker.code().isbn10(false));
        user.setEmail(faker.internet().emailAddress());

        return user;
    }


}
