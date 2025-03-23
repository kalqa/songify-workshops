package com.songify.domain.crud;

import com.songify.domain.crud.dto.ArtistDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class ArtistRetriever {

    private final ArtistRepository artistRepository;

    Set<ArtistDto> findAllArtists(Pageable pageable) {
        return artistRepository.findAll(pageable)
                .stream()
                .map(artist -> new ArtistDto(
                        artist.getId(),
                        artist.getName()))
                .collect(Collectors.toSet());
    }

    Artist findById(final Long artistId) {
        return artistRepository.findById(artistId)
                .orElseThrow(() -> new ArtistNotFoundException(artistId.toString()));
    }
}
