package com.julianduru.oauthservice;

import com.julianduru.data.messaging.dto.UserDataUpdate;
import com.julianduru.oauthservice.data.UserDataProvider;
import com.julianduru.oauthservice.data.UserDataUpdateProvider;
import com.julianduru.oauthservice.module.user.kafka.consumer.OAuthServiceUserDataUpdateConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * created by julian on 30/10/2022
 */
@ActiveProfiles({"h2"})
public class OAuthServiceUserDataUpdateConsumerTest extends BaseServiceIntegrationTest {


    @Autowired
    private UserDataProvider userDataProvider;


    @Autowired
    private UserDataUpdateProvider userDataUpdateProvider;


    @Autowired
    private OAuthServiceUserDataUpdateConsumer userDataUpdateConsumer;


    @Test
    public void testProcessingUserDataUpdate() throws Exception {
        var user = userDataProvider.save();
        var userDataUpdate = new UserDataUpdate();
        userDataUpdate.setUsername(user.getUsername());
        userDataUpdate.setFirstName(user.getFirstName() + " Update");
        userDataUpdate.setLastName(user.getLastName() + " Update");
        userDataUpdate = userDataUpdateProvider.provide(userDataUpdate);

        userDataUpdateConsumer.readOAuthServiceUserDataUpdateLog(
            new ConsumerRecord<>(
                OAuthServiceUserDataUpdateConsumer.USER_DATA_UPDATE_TOPIC,
                0, 0, UUID.randomUUID().toString(),
                userDataUpdate
            ),

            userDataUpdate
        );

        user = userDataProvider.getRepository().findById(user.getId()).get();

        assertThat(user.getFirstName()).isEqualTo(userDataUpdate.getFirstName());
        assertThat(user.getLastName()).isEqualTo(userDataUpdate.getLastName());
        assertThat(user.getAdditionalInfo().get(AuthServerConstants.UserDataUpdateAdditionalInfoKey.PROFILE_PHOTO))
            .isEqualTo(userDataUpdate.getProfilePhotoRef());

    }


}

