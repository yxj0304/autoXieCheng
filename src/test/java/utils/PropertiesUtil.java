package utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    private static Logger log = LoggerFactory.getLogger(PropertiesUtil.class);

    public static String getValue(String fileName, String key) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
            properties.load(inputStream);
            return properties.getProperty(key);
        } catch (Exception e) {
            log.info("properties file not found*{}", fileName);
        }
        return "";
    }

    public static String getValue(String fileName, String key, String defaultValue) {
        String value = getValue(fileName, key);
        return StringUtils.isBlank(value) ? defaultValue : value;
    }
}
