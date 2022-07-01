package com.example.openbankpaydemo.Util;

import com.example.openbankpaydemo.Controller.AccountCheckController;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
