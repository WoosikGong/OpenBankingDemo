package com.example.openbankpaydemo.Util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setPropertyNamingStrategy(new PropertiesNamingStrategy());
    }

    public static String serialization(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public static <T> T deserialization(String jsonData, Class<T> valueType) throws JsonProcessingException {
        return (T) mapper.readValue(jsonData, valueType);
    }
}
