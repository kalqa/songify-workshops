package com.songify.domain.crud;

import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

class InMemorySongRepository implements SongRepository{

    Map<Long, Song> db = new HashMap<>();
    AtomicInteger index = new AtomicInteger(0);

    @Override
    public int deleteByIdIn(final Collection<Long> ids) {
        ids.forEach(
                id -> db.remove(id)
        );
        return 0;
    }

    @Override
    public List<Song> findAll(final Pageable pageable) {
        return db.values().stream().toList();
    }

    @Override
    public Optional<Song> findById(final Long id) {
        Song value = db.get(id);
        return Optional.ofNullable(value);
    }

    @Override
    public void deleteById(final Long id) {

    }

    @Override
    public void updateById(final Long id, final Song newSong) {

    }

    @Override
    public Song save(final Song song) {
        long index = this.index.getAndIncrement();
        db.put(index, song);
        song.setId(index);
        return song;
    }

    @Override
    public boolean existsById(final Long id) {
        return false;
    }
}
