package com.songify.domain.crud;

import com.songify.domain.crud.dto.AlbumInfo;

class ArtistInfoTestImpl implements AlbumInfo.ArtistInfo {

    private final Artist artist;

    ArtistInfoTestImpl(final Artist artist) {
        this.artist = artist;
    }

    @Override
    public Long getId() {
        return artist.getId();
    }

    @Override
    public String getName() {
        return null;
    }
}
