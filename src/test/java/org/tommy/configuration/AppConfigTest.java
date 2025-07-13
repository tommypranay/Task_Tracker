package org.tommy.configuration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppConfigTest {

    @Test
    void testGetProperty() {
        AppConfig config = AppConfig.getInstance();
        assertNotNull(config.getProperty("json.file.path"));
    }
}