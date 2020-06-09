--liquibase formatted sql

--changeset lumme:1
CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    username text
);

--change lumme:2
INSERT INTO "user" (username) VALUES ('admin');