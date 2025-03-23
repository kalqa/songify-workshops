package com.songify.domain.crud;

import com.songify.domain.crud.Song;
import com.songify.domain.crud.dto.AlbumInfo;

import java.time.Instant;

class SongInfoTestImpl implements AlbumInfo.SongInfo {

    private final Song song;

    SongInfoTestImpl(final Song song) {
        this.song = song;
    }

    @Override
    public Long getId() {
        return song.getId();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Instant getReleaseDate() {
        return null;
    }

    @Override
    public Long getDuration() {
        return null;
    }

    @Override
    public GenreInfo getGenre() {
        return null;
    }
}
