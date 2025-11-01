package org.example.api.core.config;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@UtilityClass
public class ConfigurationManager {
    private final String CONFIG_FILE = "config.properties";
    private final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigurationManager.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) throw new RuntimeException("Cannot find " + CONFIG_FILE);
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    public String getBaseUrl() {
        return properties.getProperty("base.url");
    }
}
