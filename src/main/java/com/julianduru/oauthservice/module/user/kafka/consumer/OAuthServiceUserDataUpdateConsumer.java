package com.julianduru.oauthservice.module.user.kafka.consumer;

import com.julianduru.data.messaging.dto.UserDataUpdate;
import com.julianduru.oauthservice.module.user.UserService;
import com.julianduru.queueintegrationlib.model.OperationStatus;
import com.julianduru.queueintegrationlib.module.subscribe.annotation.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * created by julian on 28/10/2022
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OAuthServiceUserDataUpdateConsumer {


    private final UserService userService;


    public static final String USER_DATA_UPDATE_TOPIC = "oauth-user-data-update-log";



    @Consumer(topic = USER_DATA_UPDATE_TOPIC)
    public OperationStatus readOAuthServiceUserDataUpdateLog(UserDataUpdate payload) {
        try {
            userService.updateUser(payload);
            return OperationStatus.success();
        }
        catch (Throwable e) {
            log.error("Error processing user data update log", e);
            return OperationStatus.failure(e.getMessage());
        }
    }


}

