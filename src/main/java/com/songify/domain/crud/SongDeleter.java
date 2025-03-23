package com.songify.domain.crud;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Log4j2
@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class SongDeleter {

    private final SongRepository songRepository;
    private final SongRetriever songRetriever;
    private final GenreDeleter genreDeleter;

    void deleteById(Long id) {
        log.info("deleting song by id: " + id);
        songRepository.deleteById(id);
    }

    void deleteAllSongsById(final Set<Long> songsIds) {
        songRepository.deleteByIdIn(songsIds);
    }

//    void deleteSongAndGenreById(final Long songId) {
//        Song song = songRetriever.findSongById(songId);
//        Long genreId = song.getGenre().getId();
//
//        deleteById(songId);
//        genreDeleter.deleteById(genreId);
//    }
}
