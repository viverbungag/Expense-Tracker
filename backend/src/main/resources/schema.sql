DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE TABLE expense (
    id SERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    date Date NOT NULL,
    amount DECIMAL(10, 2) NOT NULL
);