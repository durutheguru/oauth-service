package com.julianduru.oauthservice.module.user.kafka.consumer;

import com.julianduru.oauthservice.util.KafkaUtil;
import com.julianduru.oauthservicelib.modules.user.dto.UserDataUpdate;
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
public class OAuthServiceUserDataUpdateConsumer {


    @KafkaListener(
        topics = "oauth-service-logs",
        clientIdPrefix = "oauth-service",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void readOAuthServiceLogs(
        ConsumerRecord<String, UserDataUpdate> consumerRecord, @Payload UserDataUpdate payload
    ) {
        log.info(
            "Logger 1 [JSON] received key {}: Type [{}] | Payload: {} | Record: {}",
            consumerRecord.key(), KafkaUtil.typeIdHeader(consumerRecord.headers()), payload, consumerRecord
        );
    }


}

