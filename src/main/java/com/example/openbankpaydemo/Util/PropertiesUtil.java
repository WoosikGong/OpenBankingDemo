package com.example.openbankpaydemo.Util;

import com.example.openbankpaydemo.Service.AppContext;
import org.springframework.context.ApplicationContext;

public class PropertiesUtil {

    public static String getProperty(String propertyName) {
        return getProperty(propertyName, null);
    }

    public static String getProperty(String propertyName, String defaultValue) {
        String value = defaultValue;
        ApplicationContext applicationContext = AppContext.getApplicationContext();
        if(applicationContext.getEnvironment().getProperty(propertyName) == null) {
            System.out.println(propertyName + " properties was not loaded.");
        } else {
            value = applicationContext.getEnvironment().getProperty(propertyName).toString();
        }
        return value;
    }
}
