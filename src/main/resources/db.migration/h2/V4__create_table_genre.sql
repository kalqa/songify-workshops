CREATE SEQUENCE genre_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE genre
(
    id   BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

INSERT INTO genre (id, name) VALUES (nextval('genre_id_seq'), 'default');
