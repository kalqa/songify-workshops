ALTER TABLE song
    ADD uuid UUID DEFAULT UUID() NOT NULL UNIQUE;
