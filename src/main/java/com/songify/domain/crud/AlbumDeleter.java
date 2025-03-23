package com.songify.domain.crud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@AllArgsConstructor
@Service
class AlbumDeleter {

    private final AlbumRepository albumRepository;

    void deleteAllAlbumsByIds(final Set<Long> albumIdsToDelete) {
        albumRepository.deleteByIdIn(albumIdsToDelete);
    }
}
