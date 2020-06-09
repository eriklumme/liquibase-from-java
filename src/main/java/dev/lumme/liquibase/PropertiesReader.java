package dev.lumme.liquibase;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    public Properties getProperties() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getResourceAsStream("/db.properties");

        properties.load(inputStream);
        return properties;
    }
}
