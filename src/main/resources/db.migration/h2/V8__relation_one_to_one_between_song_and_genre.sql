-- ALTER TABLE song
--     ADD genre_id BIGINT,
--     ADD CONSTRAINT FK_SONG_ON_GENRE FOREIGN KEY (genre_id) REFERENCES genre (id);

ALTER TABLE song ADD genre_id BIGINT;

ALTER TABLE song
    ADD CONSTRAINT FK_SONG_ON_GENRE
        FOREIGN KEY (genre_id)
            REFERENCES genre(id);
