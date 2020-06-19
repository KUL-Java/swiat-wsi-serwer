DROP SCHEMA IF EXISTS swiatwsi;
CREATE SCHEMA swiatwsi;

CREATE TABLE if not exists villages
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    x    INT         NOT NULL,
    y    INT         NOT NULL
);

