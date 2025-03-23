package com.songify.domain.crud;

import com.songify.domain.crud.dto.ArtistDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
class ArtistUpdater {

    private final ArtistRetriever artistRetriever;
    private final ArtistRepository repository;

    ArtistDto updateArtistNameById(final Long artistId, final String name) {
        Artist artist = artistRetriever.findById(artistId);
        artist.setName(name);
//        repository.updateNameById(name, artistId);
        return new ArtistDto(artist.getId(), artist.getName());
    }
}
