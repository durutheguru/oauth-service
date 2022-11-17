package com.julianduru.oauthservice.module.user.kafka.consumer;

import com.julianduru.kafkaintegrationlib.util.KafkaUtil;
import com.julianduru.oauthservice.module.user.UserService;
import com.julianduru.data.messaging.dto.UserDataUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
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



    @KafkaListener(
        topics = {USER_DATA_UPDATE_TOPIC},
        clientIdPrefix = "oauth-service",
        groupId = "oauth-service-logs",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void readOAuthServiceUserDataUpdateLog(
        ConsumerRecord<String, UserDataUpdate> consumerRecord, @Payload UserDataUpdate payload
    ) {
        KafkaUtil.logConsumerRecord(consumerRecord);
        userService.updateUser(payload);
    }


}

