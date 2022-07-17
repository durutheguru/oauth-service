package com.julianduru.oauthservice.data;

import com.julianduru.oauthservice.dto.UserDataDto;
import com.julianduru.util.test.DataProvider;
import org.springframework.stereotype.Component;

/**
 * created by julian on 05/06/2022
 */
@Component
public class UserDataDtoProvider implements DataProvider<UserDataDto> {


    @Override
    public UserDataDto provide() {
        var user = new UserDataDto();

        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setUsername(faker.code().isbn10(false));
        user.setPassword(faker.code().isbn10(false));
        user.setEmail(faker.internet().emailAddress());

        return user;
    }


}
