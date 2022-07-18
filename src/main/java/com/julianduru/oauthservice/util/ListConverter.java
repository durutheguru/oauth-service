package com.julianduru.oauthservice.util;

import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * created by julian on 28/05/2022
 */
public class ListConverter implements AttributeConverter<List<String>, String> {

    private static final String DELIMITER = ",";


    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        if (strings == null || strings.isEmpty()) {
            return null;
        }

        return String.join(DELIMITER, strings);
    }


    @Override
    public List<String> convertToEntityAttribute(String s) {
        if (!StringUtils.hasText(s)) {
            return new ArrayList<>();
        }

        return Arrays.asList(s.split(DELIMITER));
    }


}


