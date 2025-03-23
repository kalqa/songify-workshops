package com.songify.domain.crud;

import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

interface SongRepository {

    int deleteByIdIn(Collection<Long> ids);

    List<Song> findAll(Pageable pageable);

    Optional<Song> findById(Long id);

    void deleteById(Long id);

    void updateById(Long id, Song newSong);

    Song save(Song song);

    boolean existsById(Long id);

}