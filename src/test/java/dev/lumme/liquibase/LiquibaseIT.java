package dev.lumme.liquibase;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class LiquibaseIT {

    @Test
    void runLiquibase_noErrors() throws IOException {
        PropertiesReader propertiesReader = new PropertiesReader();
        LiquibaseRunner liquibaseRunner = new LiquibaseRunner(propertiesReader.getProperties(),
                "changelog", "test");

        liquibaseRunner.run();
    }
}
