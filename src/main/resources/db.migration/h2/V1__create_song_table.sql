CREATE SEQUENCE song_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE song
(
    id           BIGINT PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    release_date TIMESTAMP WITHOUT TIME ZONE,
    duration     BIGINT,
    language     VARCHAR(255)
);
