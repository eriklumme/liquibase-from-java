# Liquibase from Java

This is an example project showing how to run Liquibase 3.9.0 from Java.

The project uses a Postgres database with a JDBC connection. It allows specifying which schema to keep the change-log 
table in, and which one to run the updates on.

See `src/test/java/dev/lumme/liquibase/LiquibaseIT.java` for an example of how to use the project.