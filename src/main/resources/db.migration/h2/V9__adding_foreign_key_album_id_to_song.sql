ALTER TABLE song
    ADD album_id BIGINT REFERENCES album(id);
