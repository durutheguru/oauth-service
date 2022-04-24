package com.julianduru.oauthservice.data;

import com.julianduru.oauthservice.graphql.model.NewRegisteringClient;
import com.julianduru.util.test.DataProvider;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * created by julian on 22/04/2022
 */
@Component
public class NewRegisteringClientProvider implements DataProvider<NewRegisteringClient> {


    @Override
    public NewRegisteringClient provide() {
        var client = new NewRegisteringClient();

        client.setClientName(faker.name().fullName() + " Client");
        client.setRedirectUris(
            Set.of(
                "http://oidcdebugger.com"
            )
        );

        return client;
    }


}
