package dev.lumme.liquibase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;

class PropertiesReaderTest {

    @Test
    void getProperties_returnsProperties() throws IOException {
        Properties properties = new PropertiesReader().getProperties();
        Assertions.assertEquals("postgres", properties.getProperty("username"));
    }
}
