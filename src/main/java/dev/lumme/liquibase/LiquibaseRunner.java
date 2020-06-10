package dev.lumme.liquibase;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class LiquibaseRunner {

    private static final String CHANGELOG_PATH = "changelog.sql";

    private final Properties properties;
    private final String changeLogSchema;
    private final String updateSchema;

    public LiquibaseRunner(Properties properties, String changeLogSchema, String updateSchema) {
        this.properties = properties;
        this.changeLogSchema = changeLogSchema;
        this.updateSchema= updateSchema;
    }

    public void run() {
        try {
            doRun();
        } catch (SQLException | LiquibaseException e) {
            throw new RuntimeException("Error running Liquibase changelog", e);
        }
    }

    private void doRun() throws SQLException, LiquibaseException {
        Liquibase liquibase = getLiquibase();
        liquibase.update(new Contexts(), new LabelExpression());
    }

    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("username"),
                properties.getProperty("password"));
    }

    private Database getDatabase() throws SQLException, DatabaseException {
        Connection connection = getConnection();
        connection.prepareStatement(String.format("SET search_path TO '%s'", updateSchema)).execute();

        Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(connection));
        database.setDefaultSchemaName(changeLogSchema);

        return database;
    }

    private Liquibase getLiquibase() throws DatabaseException, SQLException {
        // If the changelog is not on the classpath, use a liquibase.resource.FileSystemResourceAccessor or other appropriate accessor
        return new Liquibase(CHANGELOG_PATH, new ClassLoaderResourceAccessor(), getDatabase());
    }
}
