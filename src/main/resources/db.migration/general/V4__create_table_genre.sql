CREATE TABLE genre
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

INSERT INTO genre (name) VALUES ('default');
