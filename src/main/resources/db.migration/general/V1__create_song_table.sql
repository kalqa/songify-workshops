CREATE TABLE song
(
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    release_date TIMESTAMP WITHOUT TIME ZONE,
    duration     BIGINT,
    language     VARCHAR(255)
);
