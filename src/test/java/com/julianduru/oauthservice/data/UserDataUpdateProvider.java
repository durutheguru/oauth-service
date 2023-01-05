package com.julianduru.oauthservice.data;

import com.julianduru.data.messaging.dto.UserDataUpdate;
import com.julianduru.util.test.DataProvider;
import org.springframework.stereotype.Component;

/**
 * created by julian on 30/10/2022
 */
@Component
public class UserDataUpdateProvider implements DataProvider<UserDataUpdate> {


    @Override
    public UserDataUpdate provide() {
        return new UserDataUpdate(
            faker.internet().uuid(),
            faker.name().firstName(),
            faker.name().lastName(),
            faker.internet().emailAddress(),
            faker.code().ean13()
        );
    }


}

