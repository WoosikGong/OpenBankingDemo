package com.example.openbankpaydemo.Util;

import com.example.openbankpaydemo.Controller.HeaderController;
import com.example.openbankpaydemo.Service.AppContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

public class PropertiesUtil {

    private static final Logger logger = LogManager.getLogger(HeaderController.class);

    public static String getProperty(String propertyName) {
        return getProperty(propertyName, null);
    }

    public static String getProperty(String propertyName, String defaultValue) {
        String value = defaultValue;
        ApplicationContext applicationContext = AppContext.getApplicationContext();
        if(applicationContext.getEnvironment().getProperty(propertyName) == null) {
            logger.info(propertyName + " properties was not loaded.");
        } else {
            value = applicationContext.getEnvironment().getProperty(propertyName);
        }
        return value;
    }
}
