package dev.lumme.liquibase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

class LiquibaseRunnerTest {

    @Test
    void getConnection_returnsConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("url", "jdbc:postgresql://localhost:32768/postgres");
        properties.setProperty("username", "postgres");
        properties.setProperty("password", "postgres");

        LiquibaseRunner runner = new LiquibaseRunner(properties, "public", "public");
        Connection connection = runner.getConnection();

        Assertions.assertNotNull(connection);

        connection.close();
    }
}
