package com.songify.domain.crud.dto;

import java.util.Set;

public record AlbumDtoWithArtistsAndSongs(
        AlbumDto album,
        Set<ArtistDto> artists,
        Set<SongDto> songs
) {
}
