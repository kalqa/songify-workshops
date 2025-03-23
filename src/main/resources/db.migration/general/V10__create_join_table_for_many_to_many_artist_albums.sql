CREATE TABLE artist_albums
(
    albums_id  BIGINT NOT NULL,
    artists_id BIGINT NOT NULL,
    CONSTRAINT pk_artist_albums PRIMARY KEY (albums_id, artists_id)
);

ALTER TABLE artist_albums
    ADD CONSTRAINT fk_artalb_on_album FOREIGN KEY (albums_id) REFERENCES album (id);

ALTER TABLE artist_albums
    ADD CONSTRAINT fk_artalb_on_artist FOREIGN KEY (artists_id) REFERENCES artist (id);
