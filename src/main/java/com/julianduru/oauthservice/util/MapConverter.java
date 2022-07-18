package com.julianduru.oauthservice.util;

import org.json.JSONObject;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.HashMap;
import java.util.Map;

/**
 * created by julian on 28/05/2022
 */
public class MapConverter implements AttributeConverter<Map<String, String>, String> {


    @Override
    public String convertToDatabaseColumn(Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            return new JSONObject(map).toString();
        }

        return null;
    }


    @Override
    public Map<String, String> convertToEntityAttribute(String str) {
        if (!StringUtils.hasText(str)) {
            return null;
        }

        var map = new JSONObject(str).toMap();
        var outputMap = new HashMap<String, String>();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            outputMap.put(entry.getKey(), entry.getValue().toString());
        }

        return outputMap;
    }


}

