package org.tommy.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    /**
     * Singleton class responsible for loading and providing access to application configuration properties.
     *
     * It reads the {@code configuration.properties} file from the classpath and exposes key-value pairs via {@link #getProperty(String)}.
     */

    /** The name of the configuration file expected to be in the classpath. */
    private static final String CONFIG_FILE  = "configuration.properties";

    /** Eagerly initialized singleton instance of {@code AppConfig}. */
    private static final AppConfig appConfig = new AppConfig();
    private final Properties properties;

    /**
     * Private constructor that loads properties from the configuration file using the class loader.
     *
     * Throws a {@link RuntimeException} if the file is not found or if loading fails.
     */
    private AppConfig() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new RuntimeException("Configuration file not found");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the singleton instance of the {@code AppConfig}.
     *
     * @return The global {@code AppConfig} instance.
     */
    public static AppConfig getInstance() {
        return appConfig;
    }

    /**
     * Retrieves a property value by its key from the loaded configuration.
     *
     * @param key The property key.
     * @return The value associated with the given key, or {@code null} if not found.
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
