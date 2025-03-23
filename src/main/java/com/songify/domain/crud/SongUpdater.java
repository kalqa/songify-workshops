package com.songify.domain.crud;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class SongUpdater {

    private final SongRepository songRepository;

    void updateById(Long id, Song newSong) {
        songRepository.updateById(id, newSong);
    }
}
