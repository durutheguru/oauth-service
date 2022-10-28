package com.julianduru.oauthservice.util;

import org.apache.kafka.common.header.Headers;

import java.util.stream.StreamSupport;

/**
 * created by julian on 28/10/2022
 */
public class KafkaUtil {


    public static String typeIdHeader(Headers headers) {
        return StreamSupport.stream(headers.spliterator(), false)
            .filter(header -> header.key().equals("__TypeId__"))
            .findFirst()
            .map(header -> new String(header.value()))
            .orElse("N/A");
    }


}

