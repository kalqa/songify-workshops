package com.songify.domain.crud;

import com.songify.domain.crud.dto.AlbumDto;
import com.songify.domain.crud.dto.AlbumInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class AlbumRetriever {

    private final AlbumRepository albumRepository;

    AlbumInfo findAlbumByIdWithArtistsAndSongs(final Long id) {
        return albumRepository.findAlbumByIdWithSongsAndArtists(id)
                .orElseThrow(() -> new AlbumNotFoundException("Album with id: " + id + " not found"));
    }

    long countArtistsByAlbumId(final Long id) {
        return findById(id)
                .getArtists()
                .size();
    }

    Set<Album> findAlbumsByArtistId(final Long artistId) {
        return albumRepository.findAllAlbumsByArtistId(artistId);
    }

    Set<AlbumDto> findAlbumsDtoByArtistId(final Long artistId) {
        return findAlbumsByArtistId(artistId).stream()
                .map(album -> new AlbumDto(album.getId(), album.getTitle(), album.getSongsIds()))
                .collect(Collectors.toSet());
    }

    Album findById(final Long albumId) {
        return albumRepository.findById(albumId)
                .orElseThrow(
                        () -> new AlbumNotFoundException("Album with id: " + albumId + " not found")
                );
    }

    AlbumDto findDtoById(final Long albumId) {
        Album album = findById(albumId);
        return new AlbumDto(
                album.getId(),
                album.getTitle(),
                album.getSongsIds()
        );
    }

    Set<AlbumDto> findAll() {
        return albumRepository.findAll()
                .stream()
                .map(album -> new AlbumDto(album.getId(), album.getTitle(), album.getSongsIds()))
                .collect(Collectors.toSet());
    }
}
