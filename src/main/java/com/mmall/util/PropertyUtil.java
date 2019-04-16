package com.mmall.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
//todo this class use to config the properties from mmall.properties?
public class PropertyUtil {
    private static Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
    private static Properties prop;
    static {
        String fileName = "mmall.properties";
        try {
            //todo through the classLoader to understand the problem of jvm
            prop.load(new InputStreamReader(PropertyUtil.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
        } catch (IOException e) {
            logger.error("loadding mmall.properties error", e);
        }
    }

    public static String getProperty(String key) {
        String value = prop.getProperty(key.trim());
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return value.trim();
    }

    public static String getProperty(String key, String defaultValue) {
        String value = prop.getProperty(key.trim());
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return value.trim();
    }
}
